import java.io.*; 
import java.util.*; 
public class Solution_모의_2382_미생물격리_서울_8반_김희원 {
	static class Cell{
		int r, c, n, d; 
		Cell(int r, int c, int n, int d){
			this.r = r; this.c = c; 
			this.n = n; this.d = d;
		}
		public String toString() {
			return this.r + " " + this.c + " " + this.n + " " + this.d; 
		}
	}
	static int[] dr = {0, -1, 1, 0, 0}; 
	static int[] dc = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		 System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0827\\Input2382.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			ArrayDeque<Cell> q = new ArrayDeque<>(); 
			Cell[][] mat = new Cell[N][N]; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					mat[i][j] = new Cell(i, j, 0, 0);
				}
			}
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				q.add(new Cell(r, c, n, d));
				mat[r][c].n = n; 
				mat[r][c].d = d; 
			}
			
			/*
			 * 각 미생물마다 바라보는 방향으로 이동한다. 
			 * - 만약에 가장자리에 도착하면 방향은 반대로 돌리고, n값은 반절로 깎는다. 
			 * 
			 * 전체 미생물에 대해서 
			 * - n이 0인 미생물 군집이 생기면 제거한다. 
			 * 
			 * 그렇게 남은 군집 사이에서 동일 위치에 도착한 군집들을 처리해줘야 한다. 
			 * - 일단 미생물 수는 누적해주는데, 
			 * - 만약 기존에 있던 값하고 현재 내 값을 비교했을 때 
			 * - 내 군집의 미생물 수가 더 크면 바라보는 방향을 내 걸로 변경해줘야 한다. 
			 * - 내 군집의 미생물 수가 더 작으면 바라보는 방향을 유지한다. 
			 */
			while(M>0) {
				for(Cell cur: q) {
					int nr = cur.r + dr[cur.d]; 
					int nc = cur.c + dc[cur.d]; 
					if(nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
						cur.n = (int)Math.floor((double)(cur.n)/2);
						cur.d = cur.d%2==0? cur.d-1: cur.d+1; 	
					}
					cur.r = nr; 
					cur.c = nc;
				}	

				int cnt = q.size(); 
				while(cnt > 0) {
					Cell cur = q.poll(); 
					if(cur.n == 0) continue; 
					q.offer(cur);
					cnt--; 
				}
				
				// 같은 위치에 여러 군집이 모이게 되면 미생물 수 누적합 어떻게 해야 하지 
				// 누적합 배열 따로, 그 자리의 최댓값 따로, 방향도 따로 다 정해두자
				int[][] sum = new int[N][N]; 
				int[][] max = new int[N][N]; 
				int[][] nextD = new int[N][N]; 
				
				for(Cell cur: q) {
					sum[cur.r][cur.c] += cur.n;
					if(cur.n > max[cur.r][cur.c]) {
						nextD[cur.r][cur.c] = cur.d; 
						max[cur.r][cur.c] = cur.n; 
					}
					// else인 경우에는 max값과 nextD값 유지하기 
				}
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						mat[i][j] = new Cell(i, j, sum[i][j], nextD[i][j]);
					}
				}
				
				// ✅ q에서 0을 앞서 제거한 것과 별개로, 
				// 현재 mat에는 새로운 값(sum,nextd)를 갖는 애들로 채워졌기 때문에 이들이 q에 들어가야 한다. 
				// 재구성하지 않으면, 두 군집이 합쳐진 게 그대로 남아있게 된다. 
				q.clear(); 
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(mat[i][j].n > 0) q.offer(new Cell(i, j, mat[i][j].n, mat[i][j].d));
					}
				}
				M--; 
			}
			int left_cells_cnt = 0; 
			for(Cell a: q) {
				left_cells_cnt += a.n; 
			}
			sb.append("#").append(t).append(" ").append(left_cells_cnt).append("\n"); 
		}
		System.out.println(sb.toString());
	}

}
/*
1
7 2 9
1 1 7 1
2 1 7 1
5 1 5 4
3 2 8 4
4 3 14 1
3 4 3 3
1 5 8 2
3 5 100 1
5 5 1 1
===

[이해]
10^3 * 10^3 * 50 
n x n 배열 

군집 위치: i, j로 주어진다 
미생물 수: 
이동방향: 상하좌우 가능 

1시간마다 이동방향에 있는 셀로 이동 

1. 약품이 칠해진 셀에 도착 -> 미생물 수 = round (double)((미생물 수)/2) & 이동방향 반대 d = (d+
- 미생물 수 = 0 -> 소멸 
2. 그렇지 않을 경우 
- 2개 이상의 군집 모이면 -> 미생물 수 = 모인 미생물 수의 합 & 이동방향은 미생물수가 가장 많은 군집의 이동방향을 따름 


[입력] 
배열크기: n: 5~10^2 
그룹개수: k 5 ~ 10^3 
지난 시간: 1  ~ 10^3
가장자리 셀: 약품 (*,0) (*,0) (n-1, *) (*, n-1) 
미생물 수: 10^4 

상 => 1
하 => 2
좌 => 3
우 => 4

d = d%2==0? d-1: d+1; 
1 2 
2 1 
3 4 
4 3 

입력 순: i, j, 미생물수, 이동방향 

[설계] 
n <- 한 변 길이 
m <- 격리 시간 
k <- 군집 개수 

1. mat의 자료형을 (n,d)를 담는 클래스로 할까 
cell[][] mat = new cel[][]; 
class cell
	int i, j, n, d; 
	생성자 작성 
	
2. 리스트에서 관리하기 
큐에 {i, j, n, d}를 넣고, 
m--; 될 때 
1) 전체 한 바퀴 돌리기 q.size()만큼 
cur <- i, j, n, d; 
- 이동방향 d로 1만큼 이동하기 : ni = i+di[d], nj = j+dj[d]  
- 만약 ni == 0 || ni == n-1 || nj == 0 || nj == n-1이 되면 
	약품 묻은 거니까 
		미생물 개수 = round (double)((미생물 수)/2) 
		이동방향 반대로 d = d%2==0? d-1: d+1; 
		
2) 그 중에서 n이 0이 된 게 있다면 
- 큐에서 제거한다. 

3) 만약 같은 위치에 군집이 도착하면 
- int[][] sum, int[][] max, int[][] nextD 로 해서 가장 많은 미생물 수를 가진 군집의 정보를 갖도록 한다. 
- sum[i][j] += cur.c; 
- 만약에 현재 내 미생물 수 cur.n이 cur의 위치에 있는 max[cur.r][cur.c] 최댓값보다 크면 방향 d와 max값을 갱신해줘야 한다. 

4) mat배열 값 재초기화 
- 앞서 계산한 sum과 d값으로 값을 다시 넣어준다. 

5) q 클리어하고 다시 넣기 
- 두 개 이상의 군집이 뭉쳤다고 한다면 q에는 아직 그 두개의 군집과 새로운 군집까지 남게 되므로 
- 비워준 뒤에, 
- mat에서 n의 값이 0보다 큰 경우의 좌표와 n,d값들만 add해주자. 

[헤맨 점] 
1. 미생물 군집의 이동과 함께 기존위치의 n은 0으로, 새로운 위치의 n은 갱신하려고 했었는데, 그러면 이미 갱신된 값이 덮어씌워질 수 있다. 
2. 같은 위치에 미생물 군집이 들어간다면 그 중에서 최댓값을 알아내야 하는데, Map을 쓰고 싶어도 사용법이 어색해서 애 먹다가, 결국 int[][] 배열을 써도 충분히 해결 가능하다는 걸 깨달음 
3. 마지막에 q를 반드시 비워주고 다시 채워줘야 한다는 거 
*/