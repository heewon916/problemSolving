import java.io.*; 
import java.util.*; 

public class Solution_d4_5643_키순서_서울_8반_김희원 {
    static int N; 
    static int bfs(List<Integer>[] g, int start){
        ArrayDeque<Integer> q = new ArrayDeque<>(); 
        boolean[] visited = new boolean[N+1];
        int adj_cnt = 0; 
        q.add(start);
        visited[start] = true; 
        while(!q.isEmpty()){
            int cur = q.poll(); 
            for(int adj: g[cur]){
                if(!visited[adj]) {
                    q.offer(adj);
                    visited[adj] = true; 
                    adj_cnt++; 
                }
            }
        }
        return adj_cnt; 
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("C://SSAFY//homework//0829//Input5643.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            List<Integer>[] g = new List[N+1];
            for(int i=0; i<N+1; i++) g[i] = new ArrayList<>(); 
            List<Integer>[] reverse_g = new List[N+1];
            for(int i=0; i<N+1; i++) reverse_g[i] = new ArrayList<>(); 

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                g[a].add(b); reverse_g[b].add(a);
            }

            // int[] bigger = new int[N+1];
            // int[] smaller = new int[N+1];
            int answer = 0; 
            for(int i=1; i<N+1; i++){
                int bigCnt = bfs(g, i);
                int smallCnt = bfs(reverse_g, i);

                if(bigCnt + smallCnt == N-1) answer++; 
            }
            sb.append("#").append(t).append(' ').append(answer).append("\n"); 
        }
        System.out.println(sb.toString());
    }
}
/*
[이해] 
1<5,3<4<2,6 
키가 a<b이면 a->b로 간다 
4를 제외한 학생들은 자신의 키가 몇 번째인지 알 수 없음 
자신의 키가 몇 번째인지 알 수 있는 학생이 몇 명? 

[입력] 
T 15 
N 500 
M 125000

[설계]
그래프 순방향/역방향 탐색 
---
나보다 큰 사람 명수 a 
나보다 작은 사람 명수 b

a+b = N-1 인 사람이 키를 다 알 수 있는 사람이다. 

a는 내가 갈 수 있는 정점의 개수 
b는 내가 역행해서 갈 수 있는 정점의 개수 

입력 u, v가 들어오면 
List<int>[] g 정방향 
List<int>[] reverse_g 역방향 
g[u].add(v)
reverse_g[v].add(u)

int[] bigger <- 정점 u보다 큰 사람 명수를 저장 
int[] smaller <- 정점 v보다 큰 사람 명수를 저장 
a를 구할 때 
- g를 이용하고 visited[]는 매번 새롭게 선언해야 한다. 
- 나 u를 기준으로 bfs해서 갈 수 있는 정점 개수를 카운트해서 bigger[u]에 저장 

b를 구할 때 
- reverse_g 를 이용하고 
- 나 u를 기준으로 bfs해서 갈 수 있는 정점 개수를 카운트해서 smaller[u]에 저장 

for i 
    bigger[i] + smaller[i] == N-1 인 경우 answer++ 
 */