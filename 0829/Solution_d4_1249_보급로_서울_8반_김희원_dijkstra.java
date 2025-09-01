
import java.io.*;
import java.util.*;

public class Solution_d4_1249_보급로_서울_8반_김희원_dijkstra {

    static final int INF = 1_000_000_000;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0829\\Input1249.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            // step1. 입력 전처리
            int N = Integer.parseInt(br.readLine().trim());

            int[][] cost = new int[N][N];
            for (int r = 0; r < N; r++) {
                String s = br.readLine().trim();
                for (int c = 0; c < N; c++) {
                    cost[r][c] = s.charAt(c) - '0';
                }
            }

            // step2. 최소 거리 찾는 거니까 MAX로 초기화 
            int[][] dist = new int[N][N];
            for (int r = 0; r < N; r++) {
                Arrays.fill(dist[r], INF);
            }

            // step3. 4방 탐색해서 가장 적은 비용을 챙겨야 한다. 
            // 출발정점이 고정되어 있으니까 다익스트라다. 
            // int[][] dist배열
            // PQ 로 최소 비용 정점 저장 
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0])); 
            pq.add(new int[]{0, 0, 0}); // 누적비용, x, y
            dist[0][0] = 0; 

            while(!pq.isEmpty()){
              int[] cur = pq.poll();

              int w = cur[0], x = cur[1], y = cur[2]; 
              for(int d=0; d<4; d++){
                int nx = x+dx[d]; 
                int ny = y+dy[d]; 
                if(0>nx || nx>=N || 0>ny || ny>=N) continue; 
                int new_w = w + cost[x][y]; 
                if(new_w < dist[nx][ny]){
                  dist[nx][ny] = new_w; 
                  pq.add(new int[]{dist[nx][ny], nx, ny});
                }
              }

            }

            sb.append('#').append(tc).append(' ').append(dist[N - 1][N - 1]).append('\n');
        }
        System.out.print(sb.toString());
    }
}

/*
[이해] 
출발지에서 도착지로 가는 가중치의 합이 최소인 경로 
가중치가 모두 다름 

간선중심 크루스칼 
정점중심 프림 트리 전체와 비교하는 거 
시작정점에서 다른 모든 정점에 최단경로로 가야 하는 건 다익스트라 
이동은 상하좌우 

[입력]
N x N  ~100

[구현]
int[][] mat <- 각 좌표에서 복구해야 하는 깊이
시작점: 0,0 도착점 N-1,N-1 
각 위치까지 가는 최소 가중치 경로 

프림?-> 반드시 다 세야 하는 정점 개수 cnt가 없어서 안되지 않나 
크루스칼?->여기도 동일한 듯 

역시 dp 

내 위치 기준 i, j
int[][] dp -> fills MAX_VALUE 
상하좌우 보고 
  범위 안에 있으면
  그 위치를 통해서 오는 게 더 빠르다면 
  dp[i][j] = Math.min(dp[ni][nj] + mat[i][j], dp[ni][nj]);
 */
