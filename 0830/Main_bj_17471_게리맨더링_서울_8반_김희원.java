import java.util.*;
import java.io.*;

public class Main_bj_17471_게리맨더링_서울_8반_김희원 {
    static int N, answer = Integer.MAX_VALUE; 
    static int[] b;
    static int[] people; 
    static List<Integer>[] g; 
    static void dfs(int start, boolean[] visited){
        visited[start] = true; 
        // start에서 갈 수 있는 정점들을 찾아가야 하니까 
        // g[start]를 이용해야 한다. 
        for(int adj: g[start]){
            if(!visited[adj] && b[start] == b[adj]){
                dfs(adj, visited);
            }
        }
        // if(g[start].size() > 0){
        // }
        // for(int i=1; i<N+1; i++){
        //     if(b[start] == b[i] && !visited[i]) {
        //         dfs(i, visited);
        //     }
        // }
    }
    static void comb(int cnt, int start, int R){
        if(cnt == R){
            // b에 대해서, 같은 그룹인 애들이 서로 연결되어 있는지를 확인해야 한다. 
            // 현재 서로 모두 떨어져 있는 정점들은 처리가 안되고 있다. 
            boolean[] visited = new boolean[N+1];
            int idx0 = -1, idx1 = -1; 
            for(int i=1; i<N+1; i++){
                if(b[i] == 0) {
                    idx0 = i;
                    break;
                } 
            }
            for(int i=1; i<N+1; i++){
                if(b[i] == 1) {
                    idx1 = i;
                    break;
                } 
            }
            dfs(idx0, visited);
            boolean able = true; 
            for(int i=1; i<N+1; i++){
                if(b[idx0] == b[i] && !visited[i]){
                    // able = true; 
                    break;
                } 
            }
            // if(!able) return; 
            // visited = new boolean[N+1];
            Arrays.fill(visited, false); 
            dfs(idx1, visited);
            // able = true; 
            for(int i=1; i<N+1; i++){
                if(b[idx1] == b[i] && !visited[i]){
                    // able = false; 
                    break; 
                }
            }
            // if(!able) return; 
            // System.out.println("계산가능: b: "+Arrays.toString(b) + "visited: " + Arrays.toString(visited));
            int group_zero = 0, group_one = 0; 
            for(int i=1; i<N+1; i++){
                if(b[i] == 0) group_zero += people[i-1]; 
                else if(b[i] == 1) group_one += people[i-1]; 
            }
            answer = Math.min(answer, Math.abs(group_zero - group_one));
            return; 
        }
        for(int i=start; i<N+1; i++){
            b[i] = 1; 
            comb(cnt+1, i+1, R);
            b[i] = 0; 
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        people = new int[N]; 
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }
        g = new List[N+1]; 
        for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();

        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            for(int j=0; j<c; j++){
                g[i].add(Integer.parseInt(st.nextToken())); 
            }
        }
        
        b = new int[N+1];
        for(int depth = 1; depth < N; depth++){
            comb(0, 1, depth);
        }
        if(answer == Integer.MAX_VALUE) sb.append("-1");
        else sb.append(answer);
        System.out.println(sb.toString());
    }
}
/*
[이해]
1번부터 N개의 구역이 있음 
2개의 그룹으로 나눠야 함 
하나의 그룹은 1개 이상의 정점을 가지고 모두 연결되어 있음
두 그룹의 인구 수 차이를 최소로 해야 함

N 10 
인구수 100 
int[] people <- 각 구역별 인구 수 
각 구열별 - 인접구역 개수, 인접정점 번호들 

[설계] 
n개의 정점을 2개의 집합으로 나누는 경우의 수 10C5 =  252
2개의 집합 안에서 
- 각 집합의 크기가 1 이상인지 -> 아니면 종료 
- 그렇다면, 각 그룹의 인구 수를 계산한다 O(n) 10 

그래프는 인접리스트로 
List<[]>[] g 
g[u].add(v)

n개의 정점을 b[] 배열에서 0번 그룹, 1번 그룹으로 나눈다 
int[] b <- new int[N+1]; //i번 정점이 어디에 속하는가 
answer = Integer.Max_value 
comb(int cnt, int start, int R)
    if(cnt == R)
        // R은 1이상, N-1이하이다. 따라서 공집합이 구역이 되는 일은 없음. 
        두 그룹 안에서 모든 정점이 서로 연결되어 있는지 확인해야 한다 
        boolean[] visited
        dfs(1, visited)
        visited배열에서 같은 그룹으로 묶인 정점들이 모두 방문되었는지 
        boolean able = true; 
        for(int i=1; i<N+1; i++)
            if(!visited[i] && b[i] == b[1])
                able = false; 
                break;
        if(!able) 
            return; 
        // b 배열에서 0인 것들끼리의 인구 수 합 
        // b 배열에서 1인 것들끼리의 인구 수 합 
        int group_zero = 0, group_one = 0; 
        for(int i=1; i<N+1; i++)
            if(b[i] == 0) group_zero++; 
            else if(b[i] == 1) group_one++; 
        answer = Math.min(answer, Math.abs(group_zero - group_one))
    for(int i=start; i<N; i++)
        b[i] = 1
        comb(cnt+1, i+1, R)
        b[i] = 0 


void dfs(int start, boolean[] visited)
    visited[start] = true; 
    for(int adj: g[start])
        if(!visited[adj] && b[adj] == b[start]) 
            dfs(adj, visited)
 */