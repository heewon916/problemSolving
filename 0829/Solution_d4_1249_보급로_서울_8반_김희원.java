import java.io.*;
import java.util.*;

public class Solution_d4_1249_보급로_서울_8반_김희원 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0829\\Input1249.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder(); 

    StringTokenizer st = new StringTokenizer(br.readLine()); 
    int T = Integer.parseInt(st.nextToken());

    for(int t=1; t<=T; t++){
      st = new StringTokenizer(br.readLine()); 
      int N = Integer.parseInt(st.nextToken());

      int[][] dist = new int[N][N];
      for(int i=0; i<N; i++){
        String s = br.readLine(); 
        for(int j=0; j<N; j++){
          dist[i][j] = s.charAt(j) - '0'; 
        }
      }
      int[][] dp = new int[N][N]; 
      for(int i=0; i<N; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);

      int[] dx = {-1, 1, 0, 0};
      int[] dy = {0, 0, -1, 1};
      dp[0][0] = dist[0][0]; 
      PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2])); 
      pq.add(new int[]{0, 0, dp[0][0]});
      while(!pq.isEmpty()){
        int[] cur = pq.poll(); 
        int i = cur[0], j = cur[1], w = cur[2]; 
        for(int d=0; d<4; d++){
          int ni = i+dx[d], nj = j+dy[d]; 
          if(0>ni || ni>=N || 0>nj || nj>=N) continue; 
          if(dp[ni][nj] > dist[ni][nj] + w) {
            dp[ni][nj] = dist[ni][nj] + w;
            pq.add(new int[]{ni,nj,dp[ni][nj]});
          }
        }
      }
      sb.append("#").append(t).append(' ').append(dp[N-1][N-1]).append("\n");
    }
    System.out.println(sb.toString());
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