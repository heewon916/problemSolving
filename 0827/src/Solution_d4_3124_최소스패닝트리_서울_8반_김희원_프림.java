import java.io.*; 
import java.util.*;

public class Solution_d4_3124_최소스패닝트리_서울_8반_김희원_프림 {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<long[]>[] g = new List[V+1]; for(int i=0; i<V+1; i++) g[i] = new ArrayList<>(); 
			for(int i=0;i<E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long w = Integer.parseInt(st.nextToken());
				g[a].add(new long[] {b, w});
				g[b].add(new long[] {a, w});
				
			}
			long[] prim = new long[V+1]; 
			boolean[] v = new boolean[V+1]; 
			PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1])); 
			Arrays.fill(prim, Long.MAX_VALUE);
			prim[1] = 0L; 
			pq.add(new long[] {1, prim[1]});
			long mst = 0L; 
			int cnt = 0; 
			while(!pq.isEmpty()) {
				long[] cur = pq.poll();
				int minVertex = (int)cur[0]; 
				long min = cur[1]; 
				if(v[minVertex]) continue; 
				v[minVertex] = true; 
				mst += min; 
				
				if(++cnt == V) break; 
				
				for(long[] adj: g[minVertex]) {
					int next = (int)adj[0]; long nextW = adj[1]; 
					if(!v[next] && prim[next] > nextW) {
						prim[next] = nextW; 
						pq.add(new long[] {next, prim[next]});
					}
				}
			}
			sb.append("#").append(t).append(' ').append(mst).append("\n"); 
		}
		System.out.println(sb.toString());

	}

}
/*
[이해]
최소 스패닝 트리 구하기 
주어진 그래프의 모든 정점 연결하는 부분 그래프 중 그 가중치의 합이 최소인 트리 

정점 중심은 프림 
간선 중심은 크루스칼 

정점 개수 v ~10^5
간선 개수 E ~2 * 10^5
가중치 값 w ~10^6 

[입력] 
간선 리스트 입력 
a, b, w 

[설계]
프림 + PQ O(E logV)
- 간선이 주어져서 크루스칼로도 해결 가능하지만 
- 간선 정보를 정점 정보로 바꿔서 해결해보자 

int[] prim <- 트리 전체와의 거리 중 최소 비용 
boolean[] v <- 트리에 포함된 정점 
List<int[]>[] g <- 간선 리스트 

PriorityQueue<int[]> pq + (o1, o2)->Integer.compare(o1[1], o2[1]) <- 가중치 기준으로 오름차순 정렬됨 
<- (정점번호, 트리와의 최소거리) 넣는다. 

prim[0] = 0; 
prim 나머지는 MAX값으로 초기화 

pq에 0과 prim[0] 값 전달 
pq에 빌때까지 
	cur <- 정점, prim[정점] 
	
	cur이 최소비용 정점이니까 초기화하고 
	int minVertex = cur[0]
	int min = cur[1]
	
	방문했었던 애면 패스하고 아니면 mst에 포함시켜야 됨 
	if(v[minVertex]) continue; 
	v[minVertex] = true; 
	mst += min 
	
	만약 정점 모두 트리에 포함했으면 끝내면 됨 
	if(++cnt == N-1) break; 
	
	minVertex에 인접한 정점에 대해서, minVertex를 통해서 갈 떄 최소 비용이 발생하면 prim[adj] > g[minVertex][adj]이면 prim[adj]갱신해줘야됨 
*/