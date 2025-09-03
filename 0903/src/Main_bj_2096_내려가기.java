import java.io.*; 
import java.util.*; 
public class Main /*_bj_2096_내려가기*/ {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N][N]; 
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int biggest = 0; 
        int smallest = 0;

        // 첫번째 행에서 가장 큰 수와 가장 작은 수를 찾기 
        int maxIdx = -1; 
        int maxVal = 0; 
        for(int i=0; i<N; i++){
            if(maxVal < mat[N-1][i]){
                maxIdx = i; 
                maxVal = mat[N-1][i]; 
            }
        }
        biggest += maxVal;
        int[] d = {-1, 0, 1};
        int next_maxIdx = -1;
        for(int depth=N-2; depth>=0; depth--){
            // 해당 depth에서 가장 큰 수를 찾기
            int next_maxVal = 0; 
            for(int i=0; i<3; i++){
                if(maxIdx+d[i]<0 || maxIdx+d[i]>=N) continue; 
                if(mat[depth][maxIdx+d[i]] > next_maxVal){
                    next_maxIdx = maxIdx+d[i]; 
                    next_maxVal = mat[depth][next_maxIdx];
                }
            }
            maxIdx = next_maxIdx;
            biggest += next_maxVal;
        }
        int minIdx = -1; 
        int minVal = Integer.MAX_VALUE; 
        for(int i=0; i<N; i++){
            if(minVal > mat[N-1][i]){
                minIdx = i; 
                minVal = mat[N-1][i]; 
            }
        }
        smallest += minVal;
        int next_minIdx = -1;
        for(int depth=N-2; depth>=0; depth--){
            // 해당 depth에서 가장 큰 수를 찾기
            int next_minVal = Integer.MAX_VALUE; 
            for(int i=0; i<3; i++){
                if(minIdx+d[i]<0 || minIdx+d[i]>=N) continue; 
                if(mat[depth][minIdx+d[i]] < next_minVal){
                    next_minIdx = minIdx+d[i]; 
                    next_minVal = mat[depth][next_minIdx];
                    // System.out.println("update at" + next_maxIdx + " maxVal: " + next_maxVal);
                }
            }
            minIdx = next_minIdx;
            smallest += next_minVal;
        }
        sb.append(biggest).append(' ').append(smallest);
        System.out.println(sb.toString());
    }
}
