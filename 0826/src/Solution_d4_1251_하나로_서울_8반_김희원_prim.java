import java.io.*;
import java.util.*;

public class Solution_d4_1251_하나로_서울_8반_김희원_prim {
    static int N;
    static int[] xs, ys;
    static double E;

    // 두 섬 사이의 거리 제곱(overflow 방지용 long)
    static long dist2(int i, int j) {
        long dx = (long) xs[i] - xs[j];
        long dy = (long) ys[i] - ys[j];
        return dx*dx + dy*dy;
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0826\\Input1251.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            xs = new int[N];
            ys = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) xs[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) ys[i] = Integer.parseInt(st.nextToken());

            E = Double.parseDouble(br.readLine().trim());

            // Prim O(N^2)
            boolean[] visited = new boolean[N];
            long[] minEdge = new long[N];
            Arrays.fill(minEdge, Long.MAX_VALUE);
            minEdge[0] = 0;              // 시작 정점 0

            long sumDist2 = 0;           // L^2 합
            for (int i = 0; i < N; i++) {
                // 1) 미방문 중 minEdge가 가장 작은 정점 u 선택
                int u = -1;
                long min = Long.MAX_VALUE;
                for (int v = 0; v < N; v++) {
                    if (!visited[v] && minEdge[v] < min) {
                        min = minEdge[v];
                        u = v;
                    }
                }

                visited[u] = true;
                sumDist2 += min;         // MST에 u 편입(첫 회차는 0이 더해짐)

                // 2) u를 통해 다른 정점들의 최솟값 갱신
                for (int v = 0; v < N; v++) {
                    if (!visited[v]) {
                        long d2 = dist2(u, v);
                        if (d2 < minEdge[v]) minEdge[v] = d2;
                    }
                }
            }

            long answer = Math.round(sumDist2 * E); // E * Σ(L^2) 반올림
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }
}
