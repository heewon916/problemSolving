import java.io.*; 
import java.util.*; 

public class Solution_d2_2001_파리퇴치_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0807/Test2001.txt"));
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N][N]; 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = -1; 
			for(int i=0; i<N-M+1; i++) {
				for(int j=0; j<N-M+1; j++) {
					int x = i, y = j;
					int temp = 0; 
					for(int k=0; k<M; k++) {
						for(int l=0; l<M; l++) {
							temp += mat[x+k][y+l];
						}
					}
					max = Math.max(max, temp);
				}
			}
			sb.append("#").append(tc).append(" ").append(max).append("\n"); 
		}
		System.out.println(sb.toString());
	}	

}
