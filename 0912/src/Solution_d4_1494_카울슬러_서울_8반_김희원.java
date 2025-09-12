import java.io.*; 
import java.util.*; 

public class Solution_d4_1494_카울슬러_서울_8반_김희원 {
    static int[] b;
    static boolean[] v; 
    static int[][] map;  
    static int N; 
    static void comb(int cnt, int start){
        /*
         * @param cnt 현재 팀에 포함시킨 지렁이 개수 
         * @param start 어느 인덱스의 지렁이를 팀에 포함시켜야 하는지  
         * ~@param team  몇 번째 팀에 포함시킬 것인지~
         * => team을 나눌 필요 없음. 각 쌍에서 구할 수 있는 출발점 좌표는 전부 다 더해지고, 도착점끼리도 다 더해진다.
         * => 따라서 팀을 그냥 2개로, 출발점과 도착점으로 나누어 전개한다.
         */
        if(cnt == N/2){
            // 모든 지렁이를 포함시켰다! 
            System.out.println(Arrays.toString(b));
            calcVector();
            return; 
        }
        for(int i=start; i<N; i++){
            // if(v[i]) continue; 
            // v[i] = true; 
            b[i] = 1; 
            comb(cnt+1, i+1);
            b[i] = 0; 
            // for(int j=i+1; j<N; j++){
            //     if(v[j]) continue; 
            //     v[j] = true;
            //     b[i] = team/2;
            //     b[j] = team/2; 
            //     comb(cnt+2, i+1, team+2);
            //     v[i] = false; 
            //     v[j] = false;
            // }
        }
    }
    static void calcVector(){
        // 구해진 b의 조합 안에서 
        // 0인 정점들 간의 x, y를 모두 더하고 
        // 1인 정점들 간의 x, y를 모두 더한다
        long zero_sum_x = 0, zero_sum_y = 0; 
        long one_sum_x = 0, one_sum_y = 0; 
        for(int i=0; i<N; i++){
            if(b[i] == 0){
                zero_sum_x += map[i][0]; 
                zero_sum_y += map[i][1]; 
            }else{
                one_sum_x += map[i][0]; 
                one_sum_y += map[i][1];  
            }
        }
        long diff_x = (one_sum_x - zero_sum_x) * (one_sum_x - zero_sum_x);
        long diff_y = (one_sum_y - zero_sum_y) * (one_sum_y - zero_sum_y); 
        min_vector = Math.min(min_vector, Math.abs(diff_x + diff_y));
    }
    static long min_vector; 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][2]; 
            min_vector = Long.MAX_VALUE;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken()); 
            }
            b = new int[N]; // 팀을 마스킹 할겁니다.
            v = new boolean[N]; 
            comb(0, 0);
            System.out.println("#"+tc+" "+min_vector);
        }
    }

}
