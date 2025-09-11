import java.io.*; 
import java.util.*; 

public class Solution_d4_5643_키순서_서울_8반_김희원 {

    static int N, adj[][];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            adj = new int[N+1][N+1]; // 학생번호가 1번부터

            //메모 안된 상태로 초기화
            for(int i=0; i<N+1; i++){
                // 모든 행의 0열
                adj[i][0] = -1;
            }

            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a][b] = 1; // a가 b보다 키가 크다
            }

            for(int i=1; i<=N; i++){
                // 모든 학생에 대해 자신보다 키가 큰 학생 탐색
                // 이 과정에서 간접관계를 직접관계로 경로 압축
                if(adj[i][0] == -1) getDFS(i);
            }

            // 자신보다 작은 학생 count
            for(int i=1; i<=N; i++){
                int cnt = 0;
                for(int j=1; j<=N; j++){
                    if(adj[j][i] == 1) cnt++;
                }
                adj[0][i] = cnt;
            }

            // 자신보다 키가 큰 학생 + 작은 학생 카운팅
            int ans = 0;
            for(int i=1; i<=N; i++){
                if(adj[0][i] + adj[i][0] == N-1) ans++;
            }

            System.out.println("#"+tc+" " +ans);
        }
    }
    private static void getDFS(int cur){
        for(int i=1; i<=N; i++){
            if(adj[cur][i] == 1){ // cur < i 인 i에 대해서
                if(adj[i][0] == -1) getDFS(i);  // i가 탐색을 하지 않았으면 탐색하러 가기

                // i가 탐색을 이미 해서 내려왔거나, i탐색을 마치고 내려왔거나
                if(adj[i][0] > 0){
                    // i보다 큰 학생이 있다면 -> cur < i < ??? 에서 ???가 존재한다는 뜻임
                    // cur < i < j => cur < j로 압축해보자.
                    for(int j=1; j<=N; j++){
                        if(adj[i][j] == 1) adj[cur][j] = 1;
                    }
                }
            }
        }
        // 자신보다 큰 학생들 수 카운팅 후 메모
        int cnt = 0;
        for(int i=1; i<=N; i++){
            cnt += adj[cur][i];
        }
        adj[cur][0] = cnt;
    }
}
