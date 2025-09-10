import java.io.*;
import java.util.*;

public class Main_bj_14500_테트로미노 {
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] map;
    static int N, M; 
    static int max_sum = 0; 
    static boolean[][] v; 
    static void dfs(int i, int j, int count, int sum){
        if(count == 3){
            // 3인 줄 알았는데 왜 4냐 
            max_sum = Math.max(max_sum, sum);
            return; 
        }
        // 현재 위치 i,j에 대해서 상하좌우로 이동해서
        // 범위 내에 있으면 sum에 더한다. 
        // 현재 위치를 방문 표시한다. 
        v[i][j] = true; 

        for(int d=0; d<4; d++){
            int ni = i+di[d]; 
            int nj = j+dj[d]; 
            if(ni<0 || ni>=N || nj<0 || nj>=M) continue; 
            if(v[ni][nj]) continue; 
            // 새로운 경로로 들어감 
            dfs(ni, nj, count+1, sum+map[ni][nj]);
        }
        //!!! 왜 여기에 있어야 하는 걸까
        // 새로운 경로 갔다가 원래 위치로 돌아옴.
        // 이 위치를 방문하지 않았다고 표시해야, 탐색한 경로 간에 방문 간섭이 없다. 
        v[i][j] = false; 
    }
    static void dfs2(int i, int j, int count, int sum){
        // 현재 내 위치 i,j를 포함하면서 
        // 행 또는 열 방향으로 연속 3칸, 그리고 그 중간에 1칸
        // max_sum과 비교해 갱신한다. 
        // 열 방향으로 먼저 따진다. 
        for(int di=-2; di<=0; di++){
            int temp_sum = 0; 
            int ni = i+di; 
            if(ni<0 || ni>=N-2) continue;
            for(int k=0; k<3; k++){
                temp_sum += map[ni+k][j]; 
            }
            if(j==0) temp_sum += map[ni+1][j+1];
            else if(j==M-1) temp_sum += map[ni+1][j-1];
            else temp_sum += Math.max(map[ni+1][j+1], map[ni+1][j-1]);
            max_sum = Math.max(temp_sum, max_sum);
        }

        // 행 방향으로도 따져보자. 
        for(int dj=-2; dj<=0; dj++){
            int nj = j+dj; 
            if(nj<0 || nj>=M-2) continue; 
            int temp_sum = 0; 
            for(int k=0; k<3; k++){
                temp_sum += map[i][nj+k];
            }
            if(i==0) temp_sum += map[i+1][nj+1];
            else if(i==N-1) temp_sum += map[i-1][nj+1];
            else temp_sum += Math.max(map[i+1][nj+1], map[i-1][nj+1]);
            max_sum = Math.max(temp_sum, max_sum);
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; 

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[N][M];
        int answer = 0; 
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                for(int k=0; k<N; k++) Arrays.fill(v[k], false);
                // 각 지점 i, j에 대해서 상하좌우로 3번 갔다와야 됨 
                max_sum = 0; 
                dfs(i, j, 0, map[i][j]);
                dfs2(i, j, 0, map[i][j]);
                // System.out.println("i:" + i + " j:" + j + "에서 최대치: " + max_sum);
                // map[i][j] = max_sum; 
                answer = Math.max(answer, max_sum);
            }
        }
        StringBuilder sb = new StringBuilder(); 
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
