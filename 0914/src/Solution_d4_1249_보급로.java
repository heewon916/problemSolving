import java.io.*; 
import java.util.*; 

public class Solution_d4_1249_보급로 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N]; 
            for(int i=0; i<N; i++){
                String in = br.readLine(); 
                for(int j=0; j<N; j++){
                    map[i][j] = in.charAt(j) -'0'; 
                }
            }

            boolean[][] v = new boolean[N][N]; 
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            int[][] dist = new int[N][N];
            for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[0][0] = 0; 
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2])); // {i, j, cost}
            pq.add(new int[]{0, 0, dist[0][0]});
            while(!pq.isEmpty()){
                int[] cur = pq.poll(); 
                int minX = cur[0], minY = cur[1]; 
                int min = cur[2]; 
                
                if(v[minX][minY]) continue; 
                v[minX][minY] = true; 

                if(minX == N-1 && minY == N-1) break;
                // 현재 정점에 인접한 상하좌우 중에서 아직 방문 안했고, 나를 경유해 가는 게 빠르다면 dist값 갱신 && pq에 offer; 
                for(int d=0; d<4; d++){  
                    int nx = minX + dx[d]; 
                    int ny = minY + dy[d]; 
                    if(nx < 0 || nx>=N || ny<0 || ny>=N) continue; 
                    if(!v[nx][ny] && dist[nx][ny] > min + map[nx][ny]){
                        dist[nx][ny] = min + map[nx][ny];
                        pq.offer(new int[]{nx, ny, dist[nx][ny]});
                    }
                }
            }
            
            sb.append('#').append(tc).append(' ').append(dist[N-1][N-1]).append('\n');
        }
        System.out.println(sb);
    }
}
