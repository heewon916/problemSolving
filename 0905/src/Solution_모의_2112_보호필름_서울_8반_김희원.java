import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_모의_2112_보호필름_서울_8반_김희원 {
    static int D, W, K;
    static int[][] film;
    static int minTreatments;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minTreatments = K; // 최소값은 최대 K번 약품 투여하는 경우보다 작을 수 없음

            if (checkPerformance()) {
                minTreatments = 0;
            } else {
                solve(0, 0);
            }

            System.out.println("#" + tc + " " + minTreatments);
        }
    }

    private static void solve(int row, int treatedCount) {
        if (treatedCount >= minTreatments) {
            return;
        }

        if (row == D) {
            if (checkPerformance()) {
                minTreatments = Math.min(minTreatments, treatedCount);
            }
            return;
        }
        
        // 1. 현재 행에 약품 투여하지 않는 경우
        solve(row + 1, treatedCount);

        // 2. 현재 행에 약품 A(0)를 투여하는 경우
        int[] originalRow = film[row];
        int[] treatedRowA = new int[W];
        film[row] = treatedRowA;
        solve(row + 1, treatedCount + 1);
        film[row] = originalRow; // 상태 복원

        // 3. 현재 행에 약품 B(1)를 투여하는 경우
        int[] treatedRowB = new int[W];
        Arrays.fill(treatedRowB, 1);
        film[row] = treatedRowB;
        solve(row + 1, treatedCount + 1);
        film[row] = originalRow; // 상태 복원
    }

    private static boolean checkPerformance() {
        if (K == 1) {
            return true;
        }

        for (int j = 0; j < W; j++) {
            boolean isColValid = false;
            int consecutiveCount = 1;

            for (int i = 0; i < D - 1; i++) {
                if (film[i][j] == film[i + 1][j]) {
                    consecutiveCount++;
                } else {
                    consecutiveCount = 1;
                }
                if (consecutiveCount >= K) {
                    isColValid = true;
                    break;
                }
            }
            if (!isColValid) {
                return false;
            }
        }
        return true;
    }
}