import java.io.*; 
import java.util.*; 

public class Solution_d5_3421_수제버거장인_서울_8반_김희원 {
    static int answer = 0; 
    static int N; 
    static boolean[] visited; 
    static boolean[][] conflict; 
    static void comb(int cnt, int start, int R){
        // step5. R개를 모두 골랐으면 
        if(cnt == R){
            // step6. 서로 충돌하는 게 있는지 살핀다 
            for(int i=1; i<N+1; i++){
                for(int j=i+1; j<N+1; j++){
                    if(visited[i] && visited[j] && conflict[i][j]) return;
                }
            }
            // step7. 없다면 답을 하나 증가 
            answer++;
            return ;
        }
        // step1. start부터 R개를 고른다. 
        for(int i=start; i<N+1; i++){
            boolean able = true; 
            // step2. 이미 고른 것(j)과 지금 추가하는 것(i)가 공존 불가면 continue
            for(int j=1; j<N+1; j++){
                if(visited[j] && conflict[i][j]){
                    able = false; 
                    break; 
                }
            }
            if(!able) continue; 
            // step3. 공존 가능하다면 추가 후 조합 더 꾸림
            visited[i] = true; 
            comb(cnt+1, i+1, R);
            // step4. 백트래킹 
            visited[i] = false; 
        }
    }
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 충돌 관계를 표시한다. 
            conflict = new boolean[N+1][N+1];
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                conflict[a][b] = conflict[b][a] = true; 
            }
            // 공집합과 원소가 1개인 부분집합은 미리 센다
            answer += (1 + N);

            // 원소가 2개 이상인 부분집합 중, 가능한 조합의 개수를 센다. 
            for(int R=2; R<N+1; R++){
                visited = new boolean[N+1];
                comb(0, 1, R);
            }
            sb.append('#').append(t).append(' ').append(answer).append("\n");
            answer = 0; 
        }
        System.out.println(sb.toString());
    }
}
/*
[이해]
서로 공존하면 안되는 재료 i, j M개의 쌍 
화섭이가 만들 수 있는 버거의 종류 몇 가지? 
재료 순서는 안 중요함 
재료 종류 N개 

[입력]
N 20 
M 400 
같은 쌍이 여러 번 주어질 수 있음 
제한 시간 2초  

[구현]
순서 없는 부분집합 공집합 포함 
중복 안되는 조합
근데 공존하면 안되는 쌍이 존재하는 
20C10 = 18만 연산 -> 2초면 충분 

방법1) 
조합을 다 만들고 20C0 + 20C1 + .. + 20C20 = 2^20 = 1024 * 1024 = 10^6
하나씩 보면서 되는지 안 되는지 체크 

하나의 조합을 만든다. comb 
그 조합 안에서
    서로 충돌할 여지가 있는 애들이 있는지 검토 

번호가 1번부터 시작이니까 배열이나 for문 모두 N+1 

서로 충돌할 여지가 있는 애들 
boolean[][] conflict = new boolean[N+1][N+1]; 
for i 0..M
    a, b
    conflict[a][b] = conflict[b][a] = true; 

공집합 1개 
1개인 집합 N개 

2개부터 N-1개까지 
for R in 2..N-1 
    visited = new boolean[N+1]
    comb(0, 1, R)

static answer = 0
comb(int cnt, int start, int R)
    if(cnt == R)
        for(int i=1; i<N+1; i++)
            for(int j=i+1; j<N+1; j++)
                if(visited[i] && visited[j] && conflict[i][j]) return;
        answer++
        return 
    for(int i=start; i<N+1; i++) 
        boolean ok = true; 
        for(int j=1; j<N+1; j++) 
            if(visited[j] && conflict[i][j]) 
                ok = false; 
                break; 
        if(!ok) continue; 
        visited[i] = true; 
        comb(cnt+1, i+1, R)
        visited[i] = false; 


방법2) 
하나의 조합을 만들 때 
하나를 뽑으면, 다른 수 중에서 뽑을 수 있는 것만 

공존하면 안되는 리스트를 인접리스트 List<int[]>[] 로 만들고 
하나를 뽑으면 u 
u의 인접 정점들은 모두 true 처리해버리기 
그리고 방문 안한 정점들 중 첫 번째 것만 넣기 

List<int[]>[] g = new List[N+1]; 

for i 1..N
    bfs(i);

bfs(u)
    boolean[] v = new boolean[N+1]
    Queue<Integer> q = new ArrayDeque<>(); 

    q.add(u) 
    v[u] = true; 
    while !q.isEmpty 
        int cur = q.poll
        for(int a: g[cur])
            v[a] = true
        for(int i=1; i<N+1; i++)
            if(v[i]) continue
            q.add(i)
            break 
comb(int cnt, int start)
    if(cnt == N)
        answer++; 
        break; 
    for(int i=start; i<N; i++)
        b[cnt] = 
 */