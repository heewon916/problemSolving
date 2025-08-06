import java.io.*; 
import java.util.*; 
public class Solution_d3_6808_규영이와인영이의카드게임_서울_8반_김희원 {
	static int[] p1 = new int[9];
	static int[] left = new int[9]; 
	static int[] p2 = new int[9];
	static int winCase = 0, loseCase = 0; 
	static boolean[] v = new boolean[9]; // 전체 카드에 대해 방문순서가 아닌, 9장의 카드에 대해서만 고려한다.  
	static void perm(int cnt) {
		// p2의 순열을 만들자. 
		if(cnt == 9) {
			// 누가 이겼는지 확인하자. 
			checkWinner(p2);
			return; 
		}
		for(int i=0; i<9; i++) {
			if(v[i]) continue; 
			v[i] = true; 
			p2[cnt] = left[i]; 
			perm(cnt+1);
			v[i] = false;
		}
	} 
	static void checkWinner(int[] p2) {
		int p1sum = 0, p2sum = 0; 
		for(int i=0; i<9; i++) {
			if(p1[i] > p2[i]) p1sum += p1[i] + p2[i]; 
			else if(p1[i] < p2[i]) p2sum += p1[i] + p2[i];
		}
		if(p1sum > p2sum) winCase++; 
		else if(p1sum < p2sum) loseCase++; 
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0806/Test6808.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 18장의 카드 9라운드 
		// 높은 수를 낸 사람 => 합만큼 점수 얻기 
		// 낮은 수를 낸 사람 => 0점 
		// 규영 9장 정해진 순서 고정 => 다른 한 사람의 9! 순서에 따라서 승패 정해짐. 
		// 규영 이기는 경우의 수
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			boolean[] used = new boolean[19]; 
			for(int i=0; i<9; i++) {
				p1[i] = Integer.parseInt(st.nextToken());
				used[p1[i]] = true; 
			}
			int idx = 0; 
			for(int i=1; i<=18; i++) {
				if(!used[i]) left[idx++] = i; 
			}
		
			// 9! ~= 3 * 10^5 -> 순열 ?
			// 3초 = 3 * 10^8
		
			perm(0);
			sb.append("#").append(tc).append(" ").append(winCase).append(" ").append(loseCase).append("\n");
			winCase = 0; loseCase = 0; 
		}
		System.out.println(sb.toString());
	}
}

/**
 * 중간에 헤맨 이유: 완전탐색으로 생각했다가 순열을 정해줘야 하니까 permMain이니까..?하면서 좀 헤맴
 * 그리고 perm 함수작성할 때 매개변수로 뭘 줘야 할지 고민이 많았음. 
 * 정적변수로 전부 다 선언할지, 매개변수로 전달할지 갈팡질팡 좀 함.. ;; 
 */
