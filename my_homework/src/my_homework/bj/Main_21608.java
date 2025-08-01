package my_homework.bj;
import java.util.*; 
import java.io.*; 
public class Main_21608 {
	static int N; 
	static int[][] mat;
	static int[][] seats; 
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 모든 자리에 대해서 -> 상하좌우 탐색 -> <좋아하는 학생 수, 빈자리수, 행, 열> 기록 
	static ArrayList[] count(int stdId) {
		List<int[]> list = new ArrayList<int[]>(); 
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<4; k++) {
					int ni = i+dx[k], nj = j+dy[k];
					if(0<=ni && ni<N && 0<=nj && nj<N) {
						for(int w=0; w<4; w++) {
							
							
						}
					}
				}
				
	 
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		mat = new int[N*N][5];
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			mat[i][0] = Integer.parseInt(st.nextToken());
			for(int j=1; j<5; j++)
				mat[i][j] = Integer.parseInt(st.nextToken());
		}
		seats = new int[N][N];
		
		
		
	}	
}


