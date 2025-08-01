package my_homework.bj;
import java.util.*; 
import java.io.*; 

/**
 * 0, 0에서 시작 
N-1, N-1이 도착지 
최소로 잃기 

잃을 수밖에 없는 최소 금액 
= 잃는 금액 경우의 수 중에서 가장 적게 잃은 금액 -> minLost 
상하좌우 인접 위치 1칸씩 이동 가능 -> dx, dy 
1초 
- bfs
- 가장 적은 금액을 갖는 곳으로 이동 
- 큐에 모두 add하지 말고, 가장 적은 금액을 가진 위치만 add 
 */
public class Main_4485 {
	static int[][] mat; 
	static int N; 
	static int[] dx = {-1, 1, 0, 0}; 
	static int[] dy = {0, 0, -1, 1};
	static int minLost; // 

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		st = new StringTokenizer(br.readLine());
		int tc = 1; 
		int N = Integer.parseInt(st.nextToken());
		while(N != 0) {
			int[][] mat = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			tc++; 
			sb.append("Problem ").append(tc++).append(minLost);
		}
		
		
		
		
		System.out.println(sb.toString());
			
	}

}
