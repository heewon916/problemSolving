import java.io.*; 
import java.util.*; 

public class Solution_모의_2477_차량정비소 {
    static class Counter{
        ArrayList<Integer> visited = new ArrayList<>(); 
        boolean using = false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()); // 접수 창구 개수 
            int M = Integer.parseInt(st.nextToken()); // 정비 창구 개수 
            int K = Integer.parseInt(st.nextToken()); // 고객 수 
            int A = Integer.parseInt(st.nextToken()); // 지갑 놓고 간 접수창구
            int B = Integer.parseInt(st.nextToken()); // 지갑 놓고 간 정비창구 

            int applyTime[] = new int[N+1]; // 각 접수창구에서 걸리는 시간 
            int fixTime[] = new int[M+1];// 각 정비창구에서 걸리는 시간 
            int arriveTime[] = new int[K+1]; // 각 고객 도착 시간 
            
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                applyTime[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++){
                fixTime[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++){
                arriveTime[i] = Integer.parseInt(st.nextToken());
            }

            Counter[] apply = new Counter[N+1]; // 각 접수 창구 
            Counter[] fix = new Counter[M+1]; // 각 정비 창구 

            int doneCnt = 0; // 업무를 마친 고객 수     
        }
    }
}
