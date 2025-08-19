import java.io.*; 
import java.util.*; 

public class Solution_d3_1873_상호의배틀필드_서울_8반_김희원 {
	static char[][] mat; 
	static int[] pos; 
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0819/Test1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			mat = new char[H][W]; 
			pos = new int[2]; 		// 사용자의 처음 시작 위치 
			int d = -1; 					// 사용자의 바라보는 방향 
			
			for(int i=0; i<H; i++) {
				String line = br.readLine(); 
				for(int j=0; j<W; j++) {
					mat[i][j] = line.charAt(j);
					
					if(mat[i][j] == '<') {
						d = 2; 
						pos = new int[] {i, j}; 
					}else if(mat[i][j] == '>') {
						d = 3;
						pos = new int[] {i, j}; 
					}else if(mat[i][j] == '^') {
						d = 0; 
						pos = new int[] {i, j}; 
					}else if(mat[i][j] == 'v') {
						d = 1; 
						pos = new int[] {i, j}; 
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			int input_cnt = Integer.parseInt(st.nextToken());
			String input = br.readLine();
			
			int cx = pos[0], cy = pos[1]; 	// 사용자의 현재 위치 
			for(int i=0; i<input_cnt; i++) {
				mat[cx][cy] = '.'; 
				char cmd = input.charAt(i);
				if(cmd == 'S') { 		// 현재 바라보고 있는 방향으로 포탄을 발사
					int dirX = cx + dx[d], dirY = cy + dy[d]; 
					while(dirX >= 0 && dirX < H && dirY >=0 && dirY <W) {
						// 범위 안에 있는 동안 포탄은 계속 직진 
						if(mat[dirX][dirY] == '#') break;
						else if(mat[dirX][dirY] == '*') {
							mat[dirX][dirY] = '.';
							break;
						}
						dirX += dx[d]; dirY += dy[d]; 	// 그 방향으로 계속 직진 
					}
					
				}else {
//					System.out.println(cmd);
					if(cmd == 'U') d = 0; 
					else if(cmd == 'R') d = 3; 
					else if(cmd == 'L') d = 2; 
					else if(cmd == 'D') d = 1; 
//					System.out.println(d);
					int nx = cx + dx[d], ny = cy + dy[d]; 
					if(nx < 0 || nx >= H || ny <0 || ny >= W) continue;
					if(mat[nx][ny] == '.') {
						cx = nx; 
						cy = ny; 
					}
					
				}

			}
			if(d == 0) mat[cx][cy] = '^';
			else if(d == 1) mat[cx][cy] = 'v';
			else if(d == 2) mat[cx][cy] = '<';
			else if(d == 3) mat[cx][cy] = '>';
			
 			sb.append("#").append(tc).append(" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(mat[i][j]);
				}
				sb.append("\n");
			}
			
		}
		System.out.println(sb.toString());

	}
}
