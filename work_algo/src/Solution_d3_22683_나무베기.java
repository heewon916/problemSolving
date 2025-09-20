import java.io.*;
import java.util.*;

/**
 * 다익스트라는 최단 경로 구하는 애라고 고정 관념 가지면 안된다.
 * 간선의 가중치를 최소로 하는
 * 출발 정점에서 다른 모든 정점으로의 최소 가중치를 찾을 수 있게 해준다.
 * 따라서 간선의 가중치라는 것은 여기서 회전 횟수가 될 것이고,
 * 우선순위 큐에 다시 넣을지 말지는, 나무를 여태 몇 개 베어왔는지에 있을 것이다.
 */
public class Solution_d3_22683_나무베기 {
    static int K, N;
    static int[][] map;
    static boolean[][] v;
    static int[] diffy = { -1, 1, 0, 0 };
    static int[] diffx = { 0, 0, -1, 1 };

    /**
     * 한 칸 이동 시 발생 비용: 회전 횟수 -> 어디로 가느냐에 따라 회전 횟수가 다르다.
     * 각 지점마다의 상태: y, x, 나무 베어온 횟수, 회전 횟수
     * 
     * 가중치 최소: 회전 횟수 최소
     * -> 우선순위 큐에서 회전 횟수가 최소이도록 정렬한다.
     * 
     * State 클래스 -> 각 좌표마다 가질 상태
     * dist[][] -> 초기값: 무한대
     * v[][] -> 방문확인
     * PQ -> 정렬 기준: 회전 횟수
     * 이웃한 지점들에 대해 계산할 때 조건
     * 1. 방문 아직 안했고
     * 2. 나를 통해서 갈 때 가중치가 더 줄고
     * 3. 나무 벤 횟수가 K보다 작거나 같으면
     * -> pq에 offer
     * 
     */

    static class State {
        int y, x;
        int rmTreeCnt; // 여태까지 몇 개의 나무를 베어 왔는지; K개 초과하면 그 지점 더는 못 본다. 
        int turnCnt; // 여태까지 회전해온 횟수
        int dir; // 현재 내가 바라보고 있는 방향

        public State(int y, int x, int rmTreeCnt, int turnCnt, int dir) {
            this.y = y;
            this.x = x;
            this.rmTreeCnt = rmTreeCnt;
            this.turnCnt = turnCnt;
            this.dir = dir; 
        }

    }

    static int cntTurn(int y, int x, int ny, int nx, int d) {
        // 현재 내가 바라보고 있는 방향 = d 
        // ny, nx로 향하기 위해 몇 번 회전해야 하는가? 
        int diff_x = nx - x;
        int diff_y = ny - y;
        if (diff_y == -1 || diff_y == 1)
            return 1;
        else if (diff_x == 1)
            return 2;
        else
            return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            v = new boolean[N][N];

            int sy = -1, sx = -1;
            int dy = -1, dx = -1;
            for (int i = 0; i < N; i++) {
                String in = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = in.charAt(j);
                    if (map[i][j] == 'X') {
                        sy = i;
                        sx = j;
                    }
                    if (map[i][j] == 'Y') {
                        dy = i;
                        dx = j;
                    }
                }
            }

            int[][][] dist = new int[N][N][K]; // 조작 횟수를 담는다: 전진도 1, 나무 벨 경우도 포함, 회전 횟수도 포함 
            for (int i = 0; i < N; i++)
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[sy][sx][0] = 0;
            PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.turnCnt, o2.turnCnt));
            pq.add(new State(sy, sx, 0, 0));
            boolean able = false;
            while (!pq.isEmpty()) {
                State cur = pq.poll();
                int y = cur.y, x = cur.x;
                if (v[y][x])
                    continue;
                v[y][x] = true;

                if (map[y][x] == 'Y') {
                    able = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = y + diffy[d];
                    int nx = x + diffx[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                        continue;
                    int turn = cntTurn(y, x, ny, nx);
                    if (!v[ny][nx] && cur.rmTreeCnt < K && dist[ny][nx] > dist[y][x] + turn) {
                        int dt = map[ny][nx] == 'T' ? 1 : 0; // 나무 베는 횟수
                        dist[ny][nx] = (dist[y][x] + turn) + dt + 1; // 현재 상태 + 회전횟수 + 나무베면 1 + 전진
                        pq.offer(new State(ny, nx, cur.rmTreeCnt + dt, dist[ny][nx]));
                    }
                }
            }
            if (!able)
                dist[dy][dx] = -1;

            sb.append('#').append(tc).append(' ').append(dist[dy][dx]).append('\n');
        }
        System.out.println(sb);
    }
}
