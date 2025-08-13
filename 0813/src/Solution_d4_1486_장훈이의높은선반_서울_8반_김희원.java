import java.io.*;
import java.util.*; 
public class Solution_d4_1486_장훈이의높은선반_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0813/Test1486.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); 	// N명의 점원 
			B = Integer.parseInt(st.nextToken());	// 선반의 높이 
			
			st = new StringTokenizer(br.readLine(), " ");
			H = new int[N];
			for(int i=0; i<N; i++) H[i] = Integer.parseInt(st.nextToken());
			
			// 점원들을 쌓아서 만들 수 있는 높이 중 1) B이상이고, 2) 그 중에서 가장 낮은 탑 
			subset(0, 0);
			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
			minDiff = Integer.MAX_VALUE;
		}
		System.out.println(sb.toString());
	}
	// 몇 개를 선택해야 할지 모름 
	// 부분집합 subset느낌 
	// i번째 점원의 키를 선택했을 때 
	// i번째 점원의 키를 선택하지 않았을 때 
	// 그리고 i의 범위가 N이 되면 멈춰야 함. 
	// 선택해온 점원들의 키 합이 B를 넘으면 차이 계산 -> 가장 작은 차이를 구한다. 
	static int minDiff = Integer.MAX_VALUE; 
	static int N, B; 
	static int[] H; 
	static void subset(int i, int h) {
		if(i >= N && h<B) return; 
		// i가 N보다 작거나 같고  
		if(h < B) { // 높이가 B보다 작은 경우
			subset(i+1, h+H[i]);
			subset(i+1, h);
		}else { // 높이가 B 이상인 경우 
			minDiff = Math.min(h-B, minDiff);
		}
	}

}
