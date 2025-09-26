import java.io.*; 
import java.util.*; 
public class Main_bj_16236_아기상어 {
	static int N; 
	static int[][] map ;
	static boolean[][] v; 
	static Fish me;
	static public class Fish {
		int r, c, size; 
		int dist;
		public Fish(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size; 
			if(me != null) this.dist = dist(r, c);
			else this.dist = 0; 
		}
		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", size=" + size + ", dist=" + dist + "]";
		} 
		
	}
	/**
	 * 내 위치 기준으로 갈 수 있는 최단 경로 구해서 거리 돌려줌 
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	static int bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		int[][] dist = new int[N][N]; 
		boolean[][] v = new boolean[N][N]; 
		v[me.r][me.c] = true; 
		q.add(new int[] {me.r, me.c, });
		int d = 0; 
		while(!q.isEmpty()) {
			Fish cur = q.poll(); 
			for(int i=0; i<4; i++) {
				int nr = r+di[i];
				int nc = c+dj[i]; 
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue; 
				if(!v[nr][nc] && map[nr][nc] <= me.size) {
					q.add(new  )
				}
			}
		}
		return d; 
	}
	static int[] di = {-1, 1, 0, 0}; 
	static int[] dj = {0, 0, -1, 1}; 
	static int dist(int r, int c) {
		return Math.abs(r-me.r) + Math.abs(c-me.c);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		v = new boolean[N][N]; 
		 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					me = new Fish(i, j, 2);
					System.out.println(me);
					map[i][j] = 0; 
				}
			}
		}
		PriorityQueue<Fish> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.dist == o2.dist) {
				if(o1.r == o2.r) return o1.c - o2.c; 
				else return o1.r - o2.r; 
			}else {
				return o1.dist - o2.dist; 
			}
		});
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 1 && map[i][j] < me.size) {
					pq.add(new Fish(i, j, map[i][j]));
					v[i][j] = true; 
				}
			}
		}
		int time = 0; 
		int eatCnt = 0; 
		System.out.println("현재 내위치: " + me.r + ", " + me.c);
		for(Fish o: pq) {
			System.out.println(o);
		}
		while(!pq.isEmpty()) {
			System.out.println("현재 내위치: " + me);
			Fish f = pq.poll(); 
			System.out.println("타겟 대상: " + f);
			time += dist(f.r, f.c); // 맨허튼 거리만큼 시간 증가함 
			me.r = f.r; me.c = f.c; // 이동한다. 
			map[me.r][me.c] = 0;
			System.out.println("===이동완료===");
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("======");
			eatCnt++; 
			if(me.size == eatCnt){
				me.size ++; 
				eatCnt = 0; 
				System.out.println("@크기 커짐: " + me.size + ", "  + eatCnt);
			}
			for(Fish o: pq) {
				o.dist = dist(o.r, o.c);
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!v[i][j] && me.size > map[i][j] && map[i][j] >= 1) {
						System.out.println("얘도 갈 수 있게 됨: " + i + ", " + j + " size = " + map[i][j] + " dist = " + dist(i, j));
						pq.add(new Fish(i, j, map[i][j]));
						v[i][j] = true; 
					}
				}
			}
		}
		sb.append(time);
		System.out.println(sb);
		
		

	}

}
/*
[문제 이해하기]
nxn 배열 
물고기 m마리 
아기상어 1마리 

아기상어 크기 2 
나의 크기 <= 물고기 크기 -> 못 먹고 지나감 
나의 크기 > 물고기 크기 -> 먹고 지나감 

나의 크기와 같은 개수의 물고기를 먹을 때마다 크기가 1 증가한다 

인접한 상하좌우 
- 물고기 여러 마리 -> 가까운 물고기 먹으러 감 - 행, 열 순서대로 작은 좌표로 이동 

이동 1초 

더 이상 먹을 수 있는 물고기가 없으면 끝남 = 나보다 작은 크기의 물고기가 없음 

나보다 작은 크기를 가진 물고기의 좌표, 거리(지금 내 위치 기준)을 거리-행-열 기준으로 정렬해서 가지고 있는다. 
우선순위 큐가 비면 끝나고 
우선순위 큐가 비지 않았으면 
	가장 가까운 거리에 있는 물고기 먹으러 이동한다. 
	이동해서 먹는다.  -> time+1
	 이동할 때 최단경로로 이동하는데 장애물이 없으니까 그냥 맨허튼 거리 아닌가 
	내 크기와 비교해서, 먹은 개수가 내 크기랑 같으면 크기+1 
	현재 위치에 대해서 우선순위 큐에 있는 거리들을 갱신한다. 
	전체 배열에 대해서 	
		만약 나보다 크기가 작은 물고기가 더 생긴다면 
			큐에 저장한다. 
*/