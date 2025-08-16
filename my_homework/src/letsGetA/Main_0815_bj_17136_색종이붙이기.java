package letsGetA;
import java.io.*; 
import java.util.*; 

public class Main_0815_bj_17136_색종이붙이기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		
		int[][] mat = new int[10][10]; 
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<10; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] visited = new boolean[10][10]; 
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(mat[i][j] == 1 && !visited[i][j]) {
					fill(i, j, mat, visited);
					if(paper_cnt == -1) {
						System.out.println(-1);return;  
					}
				}
			}
		}
		System.out.println(paper_cnt);
	}
	static final int[] dx = {0, 1, 1}; 
	static final int[] dy = {1, 1, 0}; 
	static int paper_cnt = 0; 
	static int[] quantity = new int[]{0, 5, 5, 5, 5, 5}; 
	static void fill(int i, int j, int[][] mat, boolean[][] visited) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		q.add(new int[] {i, j});
		boolean can_expand; 
		int width = 1, add_cnt; 
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int cx = cur[0], cy = cur[1]; 
			if(visited[cx][cy] && width == 1) continue; 
			visited[cx][cy] = true; 
			can_expand = true; 
			add_cnt = 0; 
			for(int d=0; d<3; d++) {
				int nx = cx+dx[d], ny = cy+dy[d]; 
				if(nx>=10 || ny>=10 || mat[nx][ny] == 0) {
					can_expand = false; 
				}
				if(!visited[nx][ny]) {
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true; 
					add_cnt++; 
					System.out.println("1/ 큐에 추가한 좌표: " + nx + ", " + ny);
				}
			}
			// 넓어진 너비가 5가 되면 더 이상 확장은 불가능하다. 
			if((width+1) == 5) can_expand = false; 
			if(can_expand) {
				// 확장이 가능하게 되면 너비를 +1하고, 위의 과정 반복 
				width++; 
			}else {
				System.out.println("확장불가: width = " + width + " 좌표: " + cx + " " + cy + " " + visited[cx][cy]);
				// 확장이 불가능하다면, 너비는 그대로 두고, nx, ny 넣었던 것들을 뽑고 방문도 지운다. 
				while(!q.isEmpty() && (add_cnt) > 0) {
					int[] del = q.pollLast();
					visited[del[0]][del[1]] = false; 
					add_cnt--;
					System.out.println("방문 취소: " + del[0] + ", " + del[1]);
				}
				System.out.println("현재 너비: " + width + " 남은 종이 개수 = " + quantity[width]);
				if(quantity[width] < 0) {
					// 더 이상 여분의 색종이가 없을 때 
					paper_cnt = -1; 
					break; 
				}else {
					// 덮을 수 있는 색종이가 있을 때 
					quantity[width]--; 
					paper_cnt++; 
					System.out.printf("=> (%d, %d) 기준 너비: %d, 정답 = %d\n", cx, cy, width, paper_cnt);
				}
				width = 1; 
			}
			
		}
	}
}
