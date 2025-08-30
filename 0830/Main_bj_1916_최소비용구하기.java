import java.io.*; 
import java.util.*; 
public class Main_bj_1916_최소비용구하기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] g = new List[N+1]; 
        for(int i=1; i<N+1; i++) g[i] = new ArrayList<>(); 

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            g[a].add(new int[]{b, cost});
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0; 
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        boolean[] visited = new boolean[N+1];
        pq.add(new int[]{start, dist[start]});
        while(!pq.isEmpty()){
            int[] cur = pq.poll(); 
            int minVertex = cur[0]; 
            int min = cur[1]; 
            if(visited[minVertex]) continue; 
            visited[minVertex] = true; 
            if(minVertex == dest) break; 
            for(int[] jc: g[minVertex]){
                if(!visited[jc[0]] && dist[jc[0]] > min + jc[1]){
                    dist[jc[0]] = min + jc[1]; 
                    pq.add(new int[]{jc[0], dist[jc[0]]});
                }
            }
        }

        System.out.println(dist[dest]);

    }
}
/*
[이해]
N개의 정점 
정점 번호: 1번.. N번
M개의 간선 - 가중치 서로 다름 
A->B 정점으로 가는 버스 비용의 최소화 
N 10^3 
M 10^5 
a, b, w 
맨 마지막 입력: start, dest (시작점이 고정된)

[설계]
출발정점이 고정된 + 최소비용 + 그래프 -> 다익스트라 

List<int[]>[] g <- 정점 i의 인접정점 {j, cost}
int[] dist <- 시작정점 start로부터의 최소 비용 
구해야 하는 것: 출발점에서 도착점을 가는 최소 비용이니까, dist[도착점 인덱스]

다익스트라 + PQ
dist[start] = 0; 
PriorityQueue<int[]> pq = new PQ<>((o1, o2)->Integer.comparingInt(o1[1], o2[1]));
pq <- start, dist[start]
boolean[] visited <- 각 정점을 방문했는지 체크 
while pq not Empty 
    int[] cur = pq.poll
    int minVertex = cur[0]
    int min = cur[1]
    if(v[minVertex]) continue
    v[minVertex] = true; 
    if(cnt++ == N-1) break 

    for(int[] jc: g[minVertex])
        if(!v[jc[0]] && dist[jc[0]] > min + jc[1])
            dist[jc[0]] = min + jc[1] 
            pq.add(new int[]{jc[0], dist[jc[0]]})

===
반례 찾기 
N = 1 M = 1
1 
1 
1 1 2 
1 1 
 */
