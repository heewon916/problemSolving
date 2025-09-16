import java.io.*;
import java.util.*;

public class Solution_d3_3499_퍼펙트셔플_서울_8반_김희원{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            int mid = (N + 1) / 2;

            for (int i = 0; i < N; i++) {
                if (i < mid) {
                    list1.add(st.nextToken());
                } else {
                    list2.add(st.nextToken());
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");

            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    sb.append(list1.get(i / 2)).append(" ");
                } else {
                    sb.append(list2.get(i / 2)).append(" ");
                }
            }

            System.out.println(sb.toString().trim());
        }
    }
}