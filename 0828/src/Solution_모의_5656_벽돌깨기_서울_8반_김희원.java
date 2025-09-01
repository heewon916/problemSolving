import java.io.*; 
import java.util.*; 

public class Solution_모의_5656_벽돌깨기_서울_8반_김희원 {
	static int N, W, H;
    static int[][] origin;
    static int answer;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            origin = new int[H][W];
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    origin[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            dfs(0, origin, count(origin));
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb.toString());
    }

    // 깊이 d에서 열 선택
    static void dfs(int d, int[][] board, int remain) {
        if (remain == 0) { // 다 깼다
            answer = 0;
            return;
        }
        if (d == N) { // 구슬 다 사용
            answer = Math.min(answer, remain);
            return;
        }
        if (answer == 0) return; // 최적 도달

        for (int col = 0; col < W; col++) {
            // 현재 열에서 맞을 첫 벽돌 찾기
            int rHit = firstBrickRow(board, col);
            if (rHit == -1) { // 아무것도 못 맞추면 그대로 다음 단계
                dfs(d + 1, board, remain);
                continue;
            }

            // 보드 복사 후 시뮬레이션
            int[][] next = cloneBoard(board);
            int broken = explode(next, rHit, col); // BFS 폭발
            applyGravity(next); // 중력
            dfs(d + 1, next, remain - broken);
            if (answer == 0) return; // 가지치기
        }
    }

    // 해당 열에서 첫 벽돌 행 인덱스
    static int firstBrickRow(int[][] b, int c) {
        for (int r = 0; r < H; r++) if (b[r][c] != 0) return r;
        return -1;
    }

    // BFS로 폭발, 깨진 벽돌 개수 반환
    static int explode(int[][] b, int sr, int sc) {
        int broken = 0;
        Deque<int[]> q = new ArrayDeque<>();
        if (b[sr][sc] > 0) {
            q.add(new int[]{sr, sc, b[sr][sc]});
            b[sr][sc] = 0;
            broken++;
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], power = cur[2];
            // power가 1이면 이미 자기 자신만 터진 상태
            for (int d = 0; d < 4; d++) {
                int nr = r, nc = c;
                for (int step = 1; step < power; step++) {
                    nr += dr[d]; nc += dc[d];
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
                    if (b[nr][nc] == 0) continue;
                    if (b[nr][nc] > 1) q.add(new int[]{nr, nc, b[nr][nc]});
                    b[nr][nc] = 0;
                    broken++;
                }
            }
        }
        return broken;
    }

    // 중력 적용
    static void applyGravity(int[][] b) {
        for (int c = 0; c < W; c++) {
            int write = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (b[r][c] != 0) {
                    int val = b[r][c];
                    b[r][c] = 0;
                    b[write--][c] = val;
                }
            }
        }
    }

    // 보드 복사
    static int[][] cloneBoard(int[][] b) {
        int[][] nb = new int[H][W];
        for (int i = 0; i < H; i++) System.arraycopy(b[i], 0, nb[i], 0, W);
        return nb;
    }

    // 남은 벽돌 수 세기
    static int count(int[][] b) {
        int cnt = 0;
        for (int r = 0; r < H; r++)
            for (int c = 0; c < W; c++)
                if (b[r][c] != 0) cnt++;
        return cnt;
    }
}
/*
[이해] 
구슬 N번 쏨 
그래프 W x H 배열 
0이 빈 공간 

구슬 이동: 좌우로만 
벽돌: 1-9 숫자 
맞은 벽돌: 벽돌 숫자-1 만큼 상하좌우로 동시에 제거 

N, W, H 입력 주어짐 
N개의 벽돌을 떨어뜨려 최대한 많은 벽돌을 깨뜨려야 한다. 
-> 남은 벽돌을 가장 적게 만들자 

N 2~4
W 2~12 
H 2~15

[설계]

 */