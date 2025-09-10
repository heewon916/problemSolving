import java.io.*; 
import java.util.*; 

public class Solution_d4_5643_키순서_서울_8반_김희원 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            int[][] D = new int[N+1][N+1];
            for(int i=1; i<N+1; i++){
                D[i][i] = 0; 
            } 
            // 플로이드 워샬 어케 씀...? 
            // for(int k=...main(/);
        }
    }
}
