import java.io.*; 
import java.util.*; 

public class Solution_d4_1494_카울슬러_서울_8반_김희원 {
    static int[] b;
    static boolean[] v; 
    static int[][] map;  
    static int N; 
    static void comb(int cnt, int start, int team){
        /*
         * @param cnt 현재 팀에 포함시킨 지렁이 개수 
         * @param start 어느 인덱스의 지렁이를 팀에 포함시켜야 하는지  
         * @param team  몇 번째 팀에 포함시킬 것인지
         */
        if(cnt == N){
            // 모든 지렁이를 포함시켰다! 
            System.out.println(Arrays.toString(b));
            return; 
        }
        for(int i=start; i<N; i++){
            if(v[i]) continue; 
            v[i] = true; 
            b[i] = team/2;
            comb(cnt+1, i+1, team+1);
            v[i] = false; 
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][2]; 
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken()); 
            }
            b = new int[N]; // 팀을 마스킹 할겁니다.
            v = new boolean[N]; 
            comb(0, 0, 0);

        }
    }

}
