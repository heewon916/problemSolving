package my_homework.bj;
import java.io.*; 
import java.util.*; 
public class Main_15685_드래곤커브{

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 커브 개수 
		int mat[] = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			mat[i] = Integer.parseInt(st.nextToken());
		}
		
		// K(K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로
		// 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것이다. (끝점끼리 붙임) 
				
		
	}

}
