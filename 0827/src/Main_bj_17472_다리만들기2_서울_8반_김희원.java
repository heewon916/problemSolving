import java.io.*; 
import java.util.*; 

public class Main_bj_17472_다리만들기2_서울_8반_김희원 {
	static int[][] mat;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
	static int id; 
	static int N, M; 
	static void bfs(int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		q.add(new int[] {i, j});
		visited[i][j] = true; 
		mat[i][j] = id; 
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1]; 
			for(int d=0; d<4; d++) {
				int ni = x+dx[d], nj = y+dy[d]; 
				if(0>ni || ni >= N || 0>nj || nj >= M) continue; 
				if(!visited[ni][nj] && mat[ni][nj] == 1) {
					mat[ni][nj] = id; 
					q.add(new int[] {ni, nj});
					visited[ni][nj] = true; 
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		mat = new int[N][M]; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 각 섬들에게 id 붙이기 
		id = 1; 
		visited = new boolean[N][M]; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && mat[i][j] == 1) {
					bfs(i, j); 
					id++; 
				}
			}
		}
		
		System.out.println(id); // 섬이 4개면 id의 마지막 값은 5 
		
		// 2. id가 다른 섬끼리의 거리 중 최소 비용 거리 구하기 (단, 2이상이어야 한다)
		// u에서 v로 가는 거리의 최솟값을 dist에 넣자. dist는 서로 다른 섬 간의 거리니까 id 크기만큼 필요하다. 
		// pq를 쓸 수 없는 이유는 같은 u->v 간선에 대해서 최솟값 갱신이 어렵기 때문이다. 
		// len이 2 이상인 출발점과 다른 섬을 찾으면 넣자 
		// PriorityQueue<int[]> pq = new PriorityQueue<>(); 
		int[][] dist = new int[id][id]; 
		for(int i=0;i<id; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int nowId = mat[i][j]; 
				
				// 내 위치에서 4방향으로 다 알아봐야 한다. 
				for(int d=0; d<4; d++) {
					int ni = i+dx[d], nj = j+dy[d]; 
					if(0>ni || ni>=N || 0>nj || nj>=M) continue;
					// 바다가 아니면 탐색할 이유가 없음 
					if(mat[ni][nj] != 0) continue;
					
					// 바다이고, 범위 안에 들어온다면 
					// 다른 섬에 도착할 때까지 계속 이동해야 됨 
					int len = 0; 
					while(0<=ni && ni<N && 0<=nj && nj<M) {
						if(mat[ni][nj] == nowId) break; // 나 자신이 됨 
						if(mat[ni][nj] > 0 && mat[ni][nj] != nowId) {
							// 시작 섬의 id가 아닌 다른 섬에 도착했을 때 
							// len이 2이상이면 챙겨야 되고, 아니면 버려야 됨 
							if(len >= 2) {
								int newId = mat[ni][nj];
								if(len < dist[nowId][newId]) dist[nowId][newId] = dist[newId][nowId] = len; 
							}
							break; 
						}
						len++; 
						ni += i+dx[d]; 
						nj += j+dy[d];
					}
				}
			}
		}
		
		for(int i=0; i<id; i++) {
			for(int j=0; j<id; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		
		// 3. 1번 정점부터 시작해서, 최소비용 신장트리를 만들어야 함. 
		// 각 정점 간의 최소 거리는 dist에 저장되어있음 
		int[] prim = new int[id]; 		// 각 정점에 대해, 트리와의 최소 거리 구함 
		boolean[] v = new boolean[id]; 	// 각 정점이 트리에 포함되었는지 여부를 구함 
		PriorityQueue<int[]> pq = new PriorityQueue<>();  			// 각 정점과 가중치 값을 넣고 뺌.
		
		prim[1] = 0; 
		pq.add(new int[] {1, prim[1]});
		int mst = 0, cnt = 0; 
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int i = cur[0], w = cur[1]; 
			int minVertex = i; 
			int min = w; 
			
			if(v[minVertex]) continue; 
			v[minVertex] = true; 
			mst += min; 
			
			if(++cnt == id) break; 
			
			for(int k=1; k<id; k++) {
				if(!v[k] && dist[minVertex][k] < prim[k]) {
					prim[k] = dist[minVertex][k]; 
				}
			}
		}
		System.out.println(mst);
	}

}

/*
[이해] 
nxm 크기의 격자 
섬 = 연결된 땅이 상하좌우로 연결된 덩어리 
색칠된 칸 = 땅 

다리는 바다에만 건설 가능 
다리의 길이 = 칸 수 
다리로 모든 섬 연결 
다리 방향 세로 또는 가로 
양 끝은 땅이어야 해 
다리의 길이는 2이상

다리가 교차되어도 길이에 뺴고 더함 없음 

나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최솟값 
-> 모든 정점을 연결하는 가중치 합의 최소 
-> 가중치는 모두 다르다

각 정점 간의 최소 거리를 미리 구해두고 어떤 간선을 선택하면 그 간선의 양 끝점을 방문처리? 

최소거리는 직선거리여야 한다. 세로 방향이면 한 방향, 가로 방향이면 한 방향으로만 

트리 기준으로 방문안된 정점들을 최소 비용으로 하나씩 끌어오는 거 => 프림인 것 같다. 


[입력] 
N M <- 세로 N 가로 M 
지도 정보 
NxM ~ 100 
섬의 개수 ~6 

[구현] 
1. 각 섬을 구분지어야 한다. 
bfs로 주변에서 같은 섬이면 같은 id값을 갖도록 한다. 
mat에 입력을 받은 다음에 id = 1로 둔 다음에 
bfs를 0,0부터 돌면서 visited[][] 에 대해서 같은 그룹에 속하는 좌표에는 같은 id값을 갖도록 한다. 

2. 섬을 하나의 정점으로 보고 서로 다른 섬끼리의 최소 거리를 구해서 PQ에 넣어둔다. 
PQ <- new int[]{id1, id2, w} 
boolean[] v <- 트리에 포함된 정점인지 
int[] prim <- 트리에서의 최소 거리 

int mst, cnt 
3. 프림처리해서 mst를 구하면 되지 않을까 mst += w 

*/