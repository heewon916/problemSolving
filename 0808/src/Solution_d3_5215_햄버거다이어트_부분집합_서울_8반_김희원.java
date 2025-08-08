import java.io.*; 
import java.util.*; 
public class Solution_d3_5215_햄버거다이어트_부분집합_서울_8반_김희원 {
	static int N, limit; 
	static List<int[]> list;
	static int maxScore; 
	static void subSet(int start, int score, int cal) {
		if(cal > limit) return ; 
		if(start == N) {
			maxScore = Math.max(maxScore, score);	
			return;
		}
		subSet(start+1, score, cal);
		subSet(start+1, score+list.get(start)[0], cal+list.get(start)[1]);			
	}
	/*
	static PriorityQueue<Integer> avail;
	static void comb(int start, int cnt, int cal) {
		if(cal > limit) return; 
		if(maxCal < cal) maxCal = cal; 
		if(start == N) return; 
		for(int i=start; i<N; i++) {
			comb(start+1, cnt+1, cal + list.get(start)[1]);
			comb(start+1, cnt+1, cal);
		}
	}
	static void comb(int start, int cnt, int cal, int score) {
		if(cal > limit) return; 
		if(cal > maxCal && cal < limit) maxCal = cal; 
		if(score > maxScore) maxScore = score; 
		for(int i=start; i<N; i++) {
			System.out.println((start+1) + " " + (cnt+1) + " " + (cal+list.get(i)[1]) + " " + (score+list.get(i)[1]));
			comb(start+1, cnt+1, cal+list.get(i)[1], score+list.get(i)[1]);
		}
	}
	*/
	public static void main(String[] args) throws Exception{
		// 재료점수, 재료칼로리
		// 칼로리 제한 이하 && 점수합최고치 
		// 같은 재료 중복 불가 => 중복 불가 조합 
		// 주어진 제한 칼로리 이하의 조합중에서 가장 맛에 대한 점수가 높은 햄버거의 점수를 출력
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			maxScore = -1;   
			list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken()); 
				int l = Integer.parseInt(st.nextToken()); 
				list.add(new int[] {s, l});
			}
			subSet(0, 0, 0);
			sb.append("#").append(t).append(" ").append(maxScore).append("\n");  
		}
		System.out.println(sb.toString());

	}
}
/*

1
5 1000
100 200
300 500
250 300
500 1000
400 400
*/