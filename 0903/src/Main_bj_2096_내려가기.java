import java.io.*; 
import java.util.*; 
public class Main_bj_2096_내려가기 {
    static Stack<List<int[]>> stk;
    static int N; 
    static int[] findBigSmall(Stack<List<int[]>> stk, int big_col_idx, int small_col_idx){
        List<int[]> line = stk.pop(); // {i, j, num}
        int big_val = 0;
        int small_val = Integer.MAX_VALUE; 

        for(int i=0; i<N; i++){
            int[] cur = line.get(i); // r, c, num 
            if(big_val < cur[2] && Math.abs(big_col_idx-cur[1]) <= 1) {  // 내 줄에서 가장 큰 수 찾는 중 
                big_val = cur[2]; 
                big_col_idx = cur[1]; 
            }
            if(small_val > cur[2] && Math.abs(small_col_idx-cur[1]) <= 1) {// 내 줄에서 가장 작은 수 찾는 중 
                small_val = cur[2]; 
                small_col_idx = cur[1]; 
            }
        }
        return new int[]{big_val, small_val,big_col_idx, small_col_idx};
    }
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // int[][] mat = new int[N][N]; 
        stk = new Stack<>(); 
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            List<int[]> li = new ArrayList<>();
            for(int j=0; j<N; j++){
                // mat[i][j] = Integer.parseInt(st.nextToken());
                li.add(new int[]{i, j, Integer.parseInt(st.nextToken())});
            }	
            stk.add(li);
        }
        // 가장 맨 밑에 있는 줄부터 나올 거임 
        // 각 줄마다, 가장 큰 수랑 가장 작은 수 구하기 
        int big_col_idx = -1; 
        int small_col_idx = -1; 
        int biggest = 0, smallest = 0; 
        
        // 맨 밑에 줄 먼저 뽑아서 구해야 함. 
        List<int[]> line = stk.pop(); // {i, j, num}
        int big_val = 0, small_val = Integer.MAX_VALUE; 

        for(int i=0; i<N; i++){
            int[] cur = line.get(i);
            if(big_val < cur[2]){
                big_col_idx = i; 
                big_val = cur[2]; 
            }
            if(small_val > cur[2]){
                small_col_idx = i; 
                small_val = cur[2]; 
            }
        }
        biggest += big_val; 
        smallest += small_val;

        while(!stk.isEmpty()){
            int[] values = findBigSmall(stk, big_col_idx, small_col_idx);
            big_col_idx = values[2]; 
            small_col_idx = values[3]; 
            smallest += values[1]; 
            biggest += values[0];
        }
        /*
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
            */
        sb.append(biggest).append(' ').append(smallest);
        System.out.println(sb.toString());
    }
}
