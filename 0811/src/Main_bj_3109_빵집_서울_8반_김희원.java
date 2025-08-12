import java.util.*;
import java.io.*; 

public class Main_bj_3109_빵집_서울_8반_김희원 {
	static int answer = 0; // 놓을 수 있는 파이프라인의 최대개수 
	static int R, C; 
	static String[][] mat; 
	static int[] di = {-1, 0, 1};
	public static void main(String[] args) throws Exception {
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		mat = new String[R][C]; 
		for(int i=0; i<R; i++) {
			String[] t = br.readLine().split("");  
			for(int j=0; j<C; j++) {
				mat[i][j] = t[j];  
			}
		}
 
		// 0열의 각 위치에서 갈 수 있는 곳 가고, 마지막까지 도달하면 전부 x 처리 필요 
		// dfs로 진행됨 
		for(int i=0; i<R; i++) {
			if(mat[i][0].equals(".")) {
//				System.out.println("dfs called: " + i + ", " + 0);
				dfs(i, 0);
			}
		}
		sb.append(answer);
		System.out.println(sb.toString());
	}
	static boolean able = true; 
	static void dfs(int i, int j) {
		if(j == C-1) {
//			System.out.println("proceed at " + i + " " + j);
			answer++; 
			return; 
		}
		for(int d=0; d<3; d++) {
			int ni = i + di[d];
			int nj = j + 1;
			if(0<=ni && ni<R && 0<=nj && nj<C) {
				if(mat[ni][nj].equals(".")) {
//					System.out.println("goto" + ni + " " + nj);
					mat[ni][nj] = "x"; 
					dfs(ni, nj); 
					return; 
				}
			}
//			able = false; 
		}
	}

}
