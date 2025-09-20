import java.io.*;
import java.util.*;

public class Solution_d3_22683_나무베기 {
    static int K, N;
    static int[][] map;
    static boolean[][] v;
    static int[] diffy = { -1, 1, 0, 0 };
    static int[] diffx = { 0, 0, -1, 1 };
    static int minTurns;

    static boolean cntTrees(ArrayList<int[]> path) {
        int trees = 0; // 경로 상에 장애물인 나무 개수
        boolean able = true; // 해당 경로를 사용할 수 있는지; 벨 수 있는 횟수보다 나무가 많으면 갈 수 없는 경로다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (v[i][j] && map[i][j] == 'T') {
                    trees++;
                    // path.add(new int[] { i, j });
                }
                if (trees > K) {
                    able = false;
                    break;
                }
            }
        }
        return able;

    }
    static void cntTurns(ArrayList<int[]> path){
        // 회전 횟수 세보자.
        int turns = 0; // 현재 경로를 따라 갈 때, 조작 횟수를 최소로 구해야 한다.
        // => 항상 내 위치에서 다음 위치로 갈 때 최소 회전 횟수를 고집하면 된다.
        // 그리디 방식임
        int[] prev = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            int[] now = path.get(i);
            int diff_x = now[0] - prev[0];
            int diff_y = now[1] - prev[1];
            if (diff_x == 1)
                turns += 2;
            else if (diff_y == 1 || diff_y == -1)
                turns++;
            prev = now;
        }
        if (minTurns > turns)
            minTurns = turns;
        return;
    }
    static void dfs(int y, int x, ArrayList<int[]> path) {
        if (map[y][x] == 'Y') {
            // 방문한 좌표들에 대해서, 그 경로 상에 장애물이 몇 개가 있는지 세본다.
            // 그 때 K보다 작거나 같다면, 회전 횟수도 센다.
            boolean able = cntTrees(path);
            if (!able) return; // 사용할 수 없는 경로는 더이상 살펴보지 않는다.
            cntTurns(path);
            return; 
        }
        v[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int ny = y + diffy[d];
            int nx = x + diffx[d];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N)
                continue;
            if (!v[ny][nx]) {
                v[ny][nx] = true; // 경로에 포함시킴
                path.add(new int[] { ny, nx });
                dfs(ny, nx, path);
                v[ny][nx] = false;
                path.remove(path.size() - 1);
            }
        }
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
            minTurns = Integer.MAX_VALUE; // 최소 회전 횟수 => 답
            int sy = -1, sx = -1;
            // int dy = -1, dx = -1;
            for (int i = 0; i < N; i++) {
                String in = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = in.charAt(j);
                    if (map[i][j] == 'X')
                        sy = i;
                    sx = j;
                    // if(map[i][j] == 'Y') dy = i; dx = j;
                }
            }
            ArrayList<int[]> path = new ArrayList<>();
            path.add(new int[] { sy, sx });
            dfs(sy, sx, path);
            sb.append('#').append(tc).append(' ').append(minTurns).append('\n');
        }
        System.out.println(sb);
    }
}
