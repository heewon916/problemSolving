import java.util.*; 
import java.io.*;
public class Solution_1227 {
	static int[] dx = {-1, 1, 0, 0}; // 상하좌우 
	static int[] dy = {0, 0, -1, 1};
	static int[] start, dest;  // 시작점, 도착점 위치
	static int[][] mat = new int[100][100];
	
	public static int bfs(int[] s, int[] d) {
		boolean[][] v = new boolean[100][100];
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		q.add(s);
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int x = cur[0], y = cur[1]; 
			if(x == d[0] && y == d[1]) return 1; 
			v[x][y] = true; 
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i], ny = y+dy[i]; 
				if(0<=nx && nx<100 && 0<=ny && ny<100 && !v[nx][ny] && mat[nx][ny] != 1) {
					v[nx][ny] = true; 
					q.add(new int[] {nx, ny});
				}
			}
		}
		return 0; 
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0805/Test1227.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=0; t<10; t++) {
			st = new StringTokenizer(br.readLine());
			int tc = Integer.parseInt(st.nextToken());
			
			int answer = 0; // 도달 가능 여부; 1은 가능함. 0은 불가능 의미
			
			// start = 2, dest = 3, 벽 = 1 
 			for(int i=0; i<100; i++) {
				String input = br.readLine();
				for(int j=0; j<100; j++) {
					int n = input.charAt(j) - '0';
					mat[i][j] = n; 
					if(mat[i][j] == 2) start = new int[] {i, j};
					else if(mat[i][j] == 3) dest = new int[] {i, j};
				}
			}
 		
			// 탐색하면서 start에서 dest로 갈 수 있는지 검색하면 됨. 
			answer = bfs(start, dest);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		
	}

}
