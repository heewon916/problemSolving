import java.io.*;
import java.util.*; 

public class Main_bj_9205_맥주마시면서걸어가기 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] nodes = new int[N+2][2]; // 0은 출발지, N+1은 도착지, 그 중간은 편의점들 
            for(int i=0; i<N+2; i++){
                st = new StringTokenizer(br.readLine(), " ");
                nodes[i][0] = Integer.parseInt(st.nextToken());
                nodes[i][1] = Integer.parseInt(st.nextToken());
            }

            boolean[] v = new boolean[N+2]; // 방문 여부 
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
            pq.offer(new int[]{0, 20}); // 정점 번호, 남은 맥주 개수 
            // v[0] = true; 
            boolean canGo = false; 
            while(!pq.isEmpty()){
                int[] cur = pq.poll(); // 정점 번호, 남은 맥주 개수 
                int n = cur[0], left = cur[1]; 
                // System.out.println("@지금 나: " + nodes[n][0] + ", " + nodes[n][1] + "위치, 남은 맥주개수:" + left);
                
                if(v[n]) continue; 
                v[n] = true; 

                // 현재 내 위치에서 도착지 갈 수 있는지 체크 
                int needBeers = calcBeerNeed(nodes[n][0], nodes[n][1], nodes[N+1][0], nodes[N+1][1]);
                
                // System.out.println("--도착지가능?" + (needBeers <= left) + " 필요한 맥주개수" + needBeers );
                if(needBeers <= left){
                    // System.out.println("===가능함==break하겠음");
                    canGo = true; 
                    break; 
                }
                if(left <= 0) {
                    // System.out.println("===불가능함===break하겠음");
                    canGo = false; 
                    break; 
                }

                // 도착지 못가면, 편의점 갈 수 있는 곳 체크 
                // 방문 안 했고, 가는 거리 동안 맥주가 동나지 않으면 offer

                for(int i=1; i<=N; i++){
                    needBeers = calcBeerNeed(nodes[n][0], nodes[n][1], nodes[i][0], nodes[i][1]);
                    // System.out.println(i+"편의점 가는데 필요한 맥주 개수: " + needBeers);
                    if(!v[i] && needBeers <= left){
                        // System.out.println("++편의점 어디로 가능?" + i);
                        pq.offer(new int[]{i, 20});
                        //!!!!!주의!!!!//
                        // 여기서 break하면 안된다. 다른 모든 편의점들을 살펴봐야 함 
                        // break를 한다고 하면 => A 가능하네! offer후 break => A를 봤는데, 페스티벌로 못 가는데 pq도 비어서 sad가 출력될 수 있다. 
                        // 실제로는 A,B 모두 갈 수 있었고, B가 페스티벌로 이어지는 길일 수 있다.
                    } 
                }
            }
            if(canGo) sb.append("happy\n");
            else sb.append("sad\n");
        }
        System.out.println(sb);
    }
    static int calcBeerNeed(int x, int y, int a, int b){
        int distance = Math.abs(x-a) + Math.abs(y-b);
        return distance%50 == 0? distance/50: distance/50 + 1; 
    }
}
