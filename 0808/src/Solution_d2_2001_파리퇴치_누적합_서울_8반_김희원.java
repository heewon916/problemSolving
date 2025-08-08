import java.util.*;
import java.io.*; 

public class Solution_d2_2001_파리퇴치_누적합_서울_8반_김희원 {
	/**
	 * 누적합: NxN배열에서 MxM 크기만큼의 합 중 최고치 계산하기 
	 * NxN배열: int[][] mat = new int[N][N]; 
	 * 누적합 미리 계산
	 * - int[][] sum = new int[N+1][N+1]; 
	 * - sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i-1][j-1]
	 * 
	 * MxM 크기의 부분집합 계산 
	 * - sum[i][j] - sum[i-M][j] - sum[i][j-M] + sum[i-M][j-M]
	 * for(int i=M-1; i<N+1; i++) {
			for(int j=M-1; j<N+1; j++) {
				int temp = sum[i][j]; 
				if(i-M>=0) temp -= sum[i-M][j]; 
				if(j-M>=0) temp -= sum[i][j-M];
				if(i-M>=0 && j-M>=0) temp += sum[i-M][j-M];
				answer = Math.max(answer, temp);
			}
		}
	 */
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0808/Test2001.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N+1][N+1]; 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] sum = new int[N+1][N+1]; 
			// sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i-1][j-1]
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					sum[i][j] = mat[i-1][j-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
				}
			}
			// M x M 크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.
			int answer = 0; 
			for(int i=M-1; i<N+1; i++) {
				for(int j=M-1; j<N+1; j++) {
//					int temp = sum[i][j] - sum[i-M][j] - sum[i][j-M] + sum[i-M][j-M];
					int temp = sum[i][j]; 
					if(i-M>=0) temp -= sum[i-M][j]; 
					if(j-M>=0) temp -= sum[i][j-M];
					if(i-M>=0 && j-M>=0) temp += sum[i-M][j-M];
					answer = Math.max(answer, temp);
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
