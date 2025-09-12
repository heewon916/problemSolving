import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d4_5643_키순서2_dfs {

    static int N, count, adj[][], radj[][];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            adj = new int[N+1][N+1]; // 학생번호가 1번부터: 나보다 큰 관계 표현
            radj = new int[N+1][N+1]; // 학생번호가 1번부터: 나보다 키가 작은 관계 표현

            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                radj[b][a] = adj[a][b] = 1; // a가 b보다 키가 크다
            }

            int ans = 0;
            for(int i=1; i<=N; i++){
                count = 0;
                boolean[] v = new boolean[N+1];
                dfs(i, adj, v);
                dfs(i, radj, v);
                if(count == N-1) ++ans;
            }

            System.out.println("#"+tc+" " +ans);
        }
    }
    private static void dfs(int cur, int[][] arr, boolean[] v){
        v[cur] = true;
        for(int i=1; i<=N; i++){
            if(adj[cur][i] == 1 && !v[i]){
                ++count;
                dfs(i, arr, v);
            }
        }
    }
}
