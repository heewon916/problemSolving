import java.util.*; 
import java.io.*;

public class Solution_d4_4796_의석이의우뚝선산_서울_8반_김희원 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:/SSAFY/homework/0813/Test4796.txt"));
		StringBuilder sb = new StringBuilder(); 
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1 ;tc<=T; tc++) {

			int N = sc.nextInt();
			int[] H = new int[N]; 

			for(int i=0; i<N; i++) H[i] = sc.nextInt();

			int answer = 0; 	// 정답 넣는 곳 
			int[] inc = new int[N]; 	// 오르막길 DP 
			int[] dec = new int[N]; 	// 내리막길 DP 
			
			for(int i=1; i<N; i++) {
				if(H[i-1]<H[i]) inc[i] = inc[i-1]+1;	// 오르막이면 +1
				else inc[i] = 0;		// 오르막 끊기면 0 
			}
			for(int j=N-2; j>=0; j--) {
				if(H[j]>H[j+1]) dec[j] = dec[j+1]+1;	// 내리막이면 +1
				else dec[j] = 0;		// 내리막 끊기면 0 
			}
			
			for(int i=0; i<N; i++) {
				if(inc[i]>0 && dec[i]>0)	// 동시에 만족될때만 
					answer += inc[i] * dec[i]; 
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}
   
/* 시간초과: 
			int i = 0; 
			while(i< N-1) {
				int upCount = 0; 
				while(i < N-1 && H[i]<H[i+1]) {
					upCount++; 
					i++; 
				}
				int downCount = 0; 
				while(i < N-1 && H[i]>H[i+1]) {
					downCount++; 
					i++; 
				}
				
				if(upCount>0 && downCount>0)
					answer += upCount * downCount; 
				else
					i++; 
			}
 */
/*시간초과: O(N^2) 
			int point = 1;		// 봉우리 위치: 탐색 시작은 인덱스1부터 
			List<Integer> points = new ArrayList<>();	// 봉우리 인덱스만 모아둠 
						
			while(point <= N-2) {	// 봉우리 기준 내리막길은 있는 인덱스까지  
				if(H[point-1] < H[point] && H[point] > H[point+1]) { // 봉우리 기준 양옆으로 오르막, 내리막이 1칸이라도 모두 있다면 봉우리List에 add
					points.add(point);
				}
				point++; 			// 봉우리든 아니든, 탐색 계속 이어함. 
			}
			for(int p: points) {	
				int left = 1, right = 1; 					// 왼쪽, 오른쪽으로 경사로의 길이가 얼만큼 되는지 각각 센다. 
				while(p-left>0 && p+right<N-1) {			// ArrayIndexOutOfBoundException 일어나지 않도록 
					if(H[p-left-1] < H[p-left]) left++; 	// 봉우리의 왼쪽에서 오르막길의 길이를 
					if(H[p+right] > H[p+right+1]) right++; 	// 봉우리의 오른쪽에서 내리막길의 길이를 
				}
				answer += left*right; 						// 오르막길의 길이와 내리막길의 길이의 곱은 결국 경우의 수가 된다. 
			}
 */
/*
 * 			// 조합의 개수를 만들..고? 그 안에서 오르막 내리막을 계산? 
			// 조합의 개수 = NC2 = 50000 x 4999 / 2 = 2초 넘어감 
			
			

			 * 우뚝 솟으려면 일단 오르막 다음에 내리막이어야 됨. 
			 * 그냥 dp같은 개념으로 볼까? 
			 * i, i+1 이렇게 두고 h[i] < h[i+1] 이면 보다가 h[i]>h[i+1]의 구간이 오는지만 보면 되지 않을까 
			 * 
			 * 오르막이면 뒷 포인터만 이동 
			 * 오르막인 상태에서 내리막길 발생 시, 계속 뒷 포인터 이동 -> 그러다 오르막길 만나면 멈춤 
			 * 그냥 내리막길 발생 시, 넘김 

			int s=0, e=1; 				
			boolean up = false, down = false; 
			while(s < e && e<N && s<N) {
				if(H[s] > H[e]) { // 내리막길 
					if(up) {
						// 내리막이다가 오르막 발견 
						down = true;
						e++; 
					}else {
						// 그냥 계속 내리막이면 넘김 
						s++; 
						e++;						
					}
				}
				else if(H[s] < H[e]) { // 오르막길 
					// 오르막길이면 뒷 포인터만 계속 이동 
					if(down) {
						up = false; down = false; 
						ans.add(new int[] {s,e});
						System.out.println("add: " + s + ", " + e);
					}else {
						up = true; 
						e++;											
					}
				}
			}
			for(int[] arr: ans) System.out.println(Arrays.toString(arr));
 */