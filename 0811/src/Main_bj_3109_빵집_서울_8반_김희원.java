import java.util.*;
import java.io.*; 

public class Main_bj_3109_빵집_서울_8반_김희원 {
	/*
	 * 포인트: dfs에서 리턴 타입을 boolean으로 둔다. 이를 통해서 성공/ 실패의 경우를 나눌 수 있다.
	 * DFS/ 백트래킹 문제에서 매우 중요한 기법 -> 기억해두기 
	 */
	static int answer = 0; // 놓을 수 있는 파이프라인의 최대개수 
	static int R, C; 
	static char[][] mat; 
	static int[] di = {-1, 0, 1};
	public static void main(String[] args) throws Exception {
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		mat = new char[R][C]; 
		for(int i=0; i<R; i++) {
			mat[i] = br.readLine().toCharArray(); /*포인트*/
		}
 
		// 0열의 각 위치에서 갈 수 있는 곳 가고, 마지막까지 도달하면 전부 x 처리 필요 
		// dfs로 진행됨 
		for(int i=0; i<R; i++) {
			if(dfs(i, 0)) {
				answer++;
			}
		}
		sb.append(answer);
		System.out.println(sb.toString());
	}
	static boolean dfs(int i, int j) {
		mat[i][j] = 'x'; 
		if(j == C-1) {
			return true; 
		}
		for(int d=0; d<3; d++) {
			int ni = i + di[d];
			int nj = j + 1;
			if(0<=ni && ni<R) {
				if(mat[ni][nj] == '.') {
					if(dfs(ni, nj))	return true; /*포인트: 결국 dfs가 true가 될 때 함수가 완전히 종료됨.*/
				}
			}
		}
		return false; 
	}

}
