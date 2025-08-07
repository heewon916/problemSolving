package my_homework.bj;

import java.io.*; 
import java.util.*; 

/*
 * 유형: 빡구현 
 * 나름 구현은 선방했는데, else문에서 1번으로 돌아간다를 어떻게 구현해야 할지 명확히 모르겠어서 헤맴. 
 * 그리고 else문에서 d값으로 반시계 방향으로 회전을 하는데 그걸 for문으로 4방향을 다 확인해야한다. 
 * 왜냐하면 주변의 4칸 중 청소되지 않은 빈칸을 향해 가야 하기 때문에, 그걸 찾아야됨. 그래서 내 위치에서 한번만 반시계 방향 턴이 아님. 
 */
public class Main_14503_로봇청소기 {
	static int N = 0, M = 0; 
	// 0 -> 청소 안됨 1 -> 벽 
	// 처음 모든 빈칸은 0 
	// d: 0 북 1 동 2 남 3 서 
	static int[] dr = {-1, 0, 1, 0}; 
	static int[] dc = {0, 1, 0, -1};
	static int[][] mat; 
	static int cleanCount = 0; 
	static void clean(int r, int c, int d) {
//		System.out.println("r " + r + " c " + c + " d" + d);
		// 1. 청소됐나? 
		if(mat[r][c] == 0) {
			mat[r][c] = -1;
			cleanCount++;
		}
		// 2. 4칸 주변도 확인 
		boolean allClean = true; 
		for(int k=0; k<4; k++) {
			int nr = r+dr[k], nc = c+dc[k]; 
			if(0<=nr && nr<N && 0<=nc && nc<M) {
				if(mat[nr][nc] == 0) { // 청소가 안 된 곳이 있을 때 
					allClean = false; 
				}
			}
		}
		if(allClean) {
			int backR = r-dr[d], backC = c-dc[d]; 
			if(0<=backR && backR<N && 0<=backC && backC<M && mat[backR][backC] != 1) {
//				System.out.println("d = " + d + "후진: "+ backR + ", " + backC);
				clean(backR, backC, d);
			} else { // 뒤로 못 갈 때 
				return; // stop Clean 
			}
		}
		else { // 청소할 곳이 있으면
//			int tempD = d; 
			for(int k=0; k<4; k++) { // !!!: 4방향을 모두 봐야해 
				d = (d-1+4)%4; 		
				int gotoR = r+dr[d], gotoC = c+dc[d]; 
				if(0<=gotoR && gotoR<N && 0<=gotoC && gotoC<M) {
					if(mat[gotoR][gotoC] == 0) {
						clean(gotoR,gotoC, d);		
						return; // 여기서 리턴한 이유: 청소안된 곳 발견하면 바로 전진하기 때문에 더 이상 다른 방향 보지 말 것 
					}
					// else문 필요 없음: 이미 청소할 곳은 있기 때문에, else문에 대해서는 실행할 내용이 없다. 
					// 그저 어디서 0이 되는지 알기 위해서 실행되는 것임.
				}
			}
			
		}
			/*
			d = (d-1+4)%4; 
			int gotoR = r+dr[d], gotoC = c+dc[d]; 
			if(0<=gotoR && gotoR<N && 0<=gotoC && gotoC<M) {
				System.out.println("d = " + d + "전진: "+ gotoR + ", " + gotoC);
				if(mat[gotoR][gotoC] == 0) {
					clean(gotoR,gotoC, d);					
				}
				else {
					clean(r, c, d);
				}
			}
			*/	
	}
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken()); 
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		mat = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean(r, c, d);
//		for(int[] a: mat) System.out.println(Arrays.toString(a));
		sb.append(cleanCount);
		System.out.println(sb.toString());
	}

}
