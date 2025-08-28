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
		
		// System.out.println(id); // 섬이 4개면 id의 마지막 값은 5 
		
		// 2. id가 다른 섬끼리의 거리 중 최소 비용 거리 구하기 (단, 2이상이어야 한다)
		// u에서 v로 가는 거리의 최솟값을 dist[][]에 넣자. dist는 서로 다른 섬 간의 거리니까 id 크기만큼 필요하다. 
		// pq를 쓸 수 없는 이유는 같은 u->v 간선에 대해서 최솟값 갱신이 어렵기 때문이다. 
		// len이 2 이상인 출발점과 다른 섬을 찾으면 넣자 
		// PriorityQueue<int[]> pq = new PriorityQueue<>(); 
		int[][] dist = new int[id][id]; 
		for(int i=0;i<id; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0; 
		}
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
						// System.out.println("while문 시작");
						if(mat[ni][nj] == nowId) break; // 나 자신이 됨 
						if(mat[ni][nj] > 0) {
							// 시작 섬의 id가 아닌 다른 섬에 도착했을 때 
							// len이 2이상이면 챙겨야 되고, 아니면 버려야 됨 
							if(len >= 2) {
								int newId = mat[ni][nj];
								if(len < dist[nowId][newId]){
									dist[nowId][newId] = dist[newId][nowId] = len;
								}
							}
							break; 
						}
						len++; 
						ni = ni+dx[d]; 
						nj = nj+dy[d];
					}
				}
			}
		}
		
		// for(int i=0; i<id; i++) {
		// 	for(int j=0; j<id; j++) {
		// 		System.out.print(dist[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }
		
		// 3. 1번 정점부터 시작해서, 최소비용 신장트리를 만들어야 함. 
		// 각 정점 간의 최소 거리는 dist에 저장되어있음 
		int[] prim = new int[id]; 		// 각 정점에 대해, 트리와의 최소 거리 구함 
		Arrays.fill(prim, Integer.MAX_VALUE);
		boolean[] v = new boolean[id]; 	// 각 정점이 트리에 포함되었는지 여부를 구함 
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[1], o2[1]));  			// 각 정점과 가중치 값을 넣고 뺌.
		
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
			// System.out.printf("minVertex=%d, mst=%d\n", minVertex, mst);
			if(++cnt == id-1) break; 
			
			for(int k=1; k<id; k++) {
				if(!v[k] && dist[minVertex][k] < prim[k]) {
					prim[k] = dist[minVertex][k]; 
					pq.offer(new int[]{k, prim[k]});
				}
			}
		}
		
		// 주의! mst가 0이라고 무조건 답이 -1은 아니다. 
		// 모든 정점을 방문했는지, 그러지 못했는지가 -1이 되는 조건이 된다. 
		System.out.println(cnt == (id-1)? mst: -1); 
		
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
mat에 그래프 입력을 받는다. 
id는 1부터 시작. 각 그룹의 번호를 뜻한다.  
i, j에 대해서 bfs 
	방문 안 했고, mat[i][j]가 1인 애들에 대해서만 
	bfs(i,j) 
	id++ 
2. 섬을 하나의 정점으로 보고 서로 다른 섬끼리의 최소 거리를 구해야 한다. PQ는 같은 간선에 대해 업데이트가 쉽지 않다.
한 좌표에 대해서 4방탐색을 하는데, 그 방향으로 직선 방향으로 쭉 뻗어야 한다. 
int[][] dist :: dist[u][v] = u에서 v로 가는 직선 거리의 최솟값 
인덱스 값은 각 그룹 번호니까, id값이다. 
최솟값으로 만들어가야 하니까, dist의 원소값을 모두 MAX값으로 초기화한다. 
dist[i][i] = 0으로 한다 

for i, j에 대해서 
	nowId <- 현재 id값을 넣는다. mat[i][j]
	4방 탐색 
		ni, nj 
		범위 벗어나면 continue 
		만약 바다가 아니면 continue 

		len <- 0 : 서로 다른 그룹과의 거리 (2 이상일 때만 유효하다)
		while ni,nj가 범위 안에 있을 때 
			만약 mat[ni][nj]가 nowId가 되면 break 
			만약 mat[ni][nj]가 다른 섬이면 >0 
				그때 len > 2이고 dist > len이면 
					nextId <- mat[ni][nj] 
					dist[nowId][nextId] = dist[nextId][nowId] = len

3. 프림처리해서 mst를 구하면 되지 않을까 mst += w 
boolean[] v <- 트리에 포함된 정점인지 
int[] prim <- 트리에서의 최소 거리 
int mst, cnt 
PQ<int[]> pq <- {i, prim[i]}


*/