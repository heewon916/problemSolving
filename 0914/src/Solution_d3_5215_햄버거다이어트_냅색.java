import java.io.*; 
import java.util.*; 
public class Solution_d3_5215_햄버거다이어트_냅색 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); 
        int T = Integer.parseInt(st.nextToken());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine()," ");
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[][] K = new int[N+1][L+1]; 
            int[][] info = new int[N+1][2]; // 점수(가치), 칼로리(무게)

            for(int i=1; i<=N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                info[i][0] = Integer.parseInt(st.nextToken());
                info[i][1] = Integer.parseInt(st.nextToken());
            }
            
            for(int i=1; i<=N; i++){
                for(int w=0; w<=L; w++){
                    if(w >= info[i][1]){
                        K[i][w] = Math.max(K[i-1][w-info[i][1]]+info[i][0], K[i-1][w]);
                    }else{
                        K[i][w] = K[i-1][w]; 
                    }
                }
            }
            
            sb.append('#').append(tc).append(' ').append(K[N][L]).append('\n');
        }
        System.out.println(sb);
    }
}
