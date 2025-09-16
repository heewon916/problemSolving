import java.io.*;
import java.util.*;

public class Solution_d3_5607_조합_서울_8반_김희원 {

    // 문제에서 주어진 소수 P. long 타입으로 선언하여 오버플로우 방지
    static final long P = 1234567891L;

    /**
     * @brief 거듭제곱 함수 (분할 정복을 이용한 O(logN) 알고리즘)
     *
     * @param base 밑 (a)
     * @param exp 지수 (b)
     * @return (base^exp) % P 값
     */
    public static long power(long base, long exp) {
        long result = 1; // 최종 결과값을 저장할 변수
        base %= P; // 밑을 P로 나눈 나머지로 초기화하여 중간 계산 오버플로우 방지

        // 지수(exp)가 0보다 클 때까지 반복
        while (exp > 0) {
            // 지수가 홀수이면 현재 base를 결과에 곱함
            if (exp % 2 == 1) {
                result = (result * base) % P;
            }
            // base를 제곱하고 지수는 절반으로 나눔 (ex: a^10 = (a^2)^5)
            base = (base * base) % P;
            exp /= 2;
        }
        return result;
    }

    /**
     * @brief 페르마의 소정리를 이용한 조합 계산
     *
     * 페르마의 소정리: p가 소수이고 a가 p의 배수가 아닐 때, a^(p-1) ≡ 1 (mod p)
     * 이를 변형하면, a^(p-2) ≡ a^(-1) (mod p)가 성립합니다.
     * 즉, a의 모듈러 곱셈 역원 (a^-1)은 a^(p-2)와 같습니다.
     *
     * 조합 공식: C(N, R) = N! / (R! * (N-R)!)
     * 모듈러 연산에서는 나눗셈을 그대로 사용할 수 없으므로, 곱셈 역원을 사용합니다.
     *
     * C(N, R) ≡ N! * (R!)^-1 * ((N-R)!)^-1 (mod P)
     * ≡ N! * (R!)^(P-2) * ((N-R)!)^(P-2) (mod P)
     * 위 공식을 사용하여 계산합니다.
     *
     * @param args 프로그램 실행 인자 (사용하지 않음)
     * @throws IOException 입출력 예외 처리
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // N이 최대 1,000,000이므로 팩토리얼 배열을 미리 계산하여 저장
            long[] fact = new long[N + 1];
            fact[0] = 1; // 0! = 1
            for (int i = 1; i <= N; i++) {
                // i! = (i-1)! * i 를 모듈러 연산으로 계산
                fact[i] = (fact[i - 1] * i) % P;
            }

            // 분자: N! mod P
            long numerator = fact[N];

            // 분모: R! mod P 와 (N-R)! mod P 를 곱한 값
            long denominator = (fact[R] * fact[N - R]) % P;

            // 페르마의 소정리를 이용해 분모의 모듈러 곱셈 역원을 구함
            // (R! * (N-R)!)^-1 mod P = (R! * (N-R)!)^(P-2) mod P
            long denominator_inverse = power(denominator, P - 2);

            // 최종 결과: (분자 * 분모의 역원) mod P
            long result = (numerator * denominator_inverse) % P;
            
            System.out.printf("#%d %d\n", t, result);
        }
    }
}