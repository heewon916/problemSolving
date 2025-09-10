import java.io.*;
import java.util.*;
public class Solution_d2_14510_나무높이_서울_8반_김희원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] trees = new int[N];
            int oneCount = 0;
            int twoCount = 0;
            int max = 0;
            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0; i<N; i++){
                trees[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, trees[i]);
            }

            for(int i=0; i<N; i++){
                int diff = Math.abs(trees[i] - max);
                oneCount += diff%2;
                twoCount += diff/2;
            }
            int days = 0;
            // 둘 중 하나라도 남으면 계속 해야 된다
            // 짝수날에는 2밖에 처리가 안된다.
            // 홀수날에는 1,2 모두 처리가 가능한데, 특히 1은 처리를 다했는데 2가 남았을 때 홀수일을 버리면 최소일수가 안 나오기 때문에 2를 1,1로 나눠 진행한다.
            // 단, oneCount=0, twoCount=1일 때는 3일(1,0,1)이 아닌 (0,2) 2일로 끝내는 게 효율적이다.
            // 따라서, 2를 1,1로 나눠 진행하는 경우는 two가 one 갯수보다 2이상일 때만 진행한다.
            // 4일 소요 0 2 0 2
            // 3일 소요 1 2 1
            while(oneCount > 0 || twoCount > 0){
                days++;
                if(days%2 == 0){
                    // 짝수날이면
                    if(twoCount > 0) twoCount--;
                }else{
                    // 홀수날이면
                    if(oneCount > 0) oneCount--;
                    else if(twoCount - oneCount > 1 ){
                        // oneCount는 다 했는데, 짝수일에 해야 하는 일들만 남았으면
                        // 그 twoCount 하나를 1,1로 분리하는 게 낫다.
                        twoCount--;
                        oneCount++;
                    }
                }
            }
//            while(oneCount < twoCount){
//                twoCount--;
//                oneCount+=2;
//            }
           sb.append('#').append(tc).append(' ').append(days).append('\n');
        }
        System.out.println(sb.toString());
	}

}
