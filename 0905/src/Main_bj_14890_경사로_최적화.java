import java.io.*;
import java.util.*;

public class Main_bj_14890_경사로_최적화 {

    // 한 줄(행 또는 열)이 경사로를 놓을 수 있는 길인지 판별하는 메서드
    public static boolean checkPath(int[] path, int N, int L) {
        boolean[] v = new boolean[N]; // 경사로 설치 여부 체크

        for (int i = 0; i < N - 1; i++) {
            int current = path[i];
            int next = path[i + 1];

            if (current == next) {
                continue;
            } else if (current < next) { // 낮은 곳에서 높은 곳으로
                if (next - current > 1) {
                    return false;
                }

                // 경사로 설치 가능성 검사
                for (int j = 0; j < L; j++) {
                    int checkIndex = i - j;
                    if (checkIndex < 0 || path[checkIndex] != current || v[checkIndex]) {
                        return false;
                    }
                }
                // 경사로 설치
                for (int j = 0; j < L; j++) {
                    v[i - j] = true;
                }

            } else { // 높은 곳에서 낮은 곳으로
                if (current - next > 1) {
                    return false;
                }

                // 경사로 설치 가능성 검사
                for (int j = 1; j <= L; j++) {
                    int checkIndex = i + j;
                    if (checkIndex >= N || path[checkIndex] != next || v[checkIndex]) {
                        return false;
                    }
                }
                // 경사로 설치
                for (int j = 1; j <= L; j++) {
                    v[i + j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = 0;

        // 행 검사
        for (int r = 0; r < N; r++) {
            int[] path = mat[r];
            if (checkPath(path, N, L)) {
                result++;
            }
        }

        // 열 검사
        for (int c = 0; c < N; c++) {
            int[] path = new int[N];
            for (int r = 0; r < N; r++) {
                path[r] = mat[r][c];
            }
            if (checkPath(path, N, L)) {
                result++;
            }
        }

        System.out.println(result);
    }
}