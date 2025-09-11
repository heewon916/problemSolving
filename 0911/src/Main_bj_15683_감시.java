import java.io.*;
import java.util.*;
public class Main_bj_15683_감시 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] cctv = new int[8][2]; // 최대 8개의 cctv; 좌표 x,y,번호
        int cnt = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>=1 && map[i][j] <=5) {
                    cctv[cnt][0] = i;
                    cctv[cnt][1] = j;
                    cctv[cnt][2] = map[i][j];
                    cnt++;
                }
            }
        }

        // 각 cctv에 대해서 감시할 수 있는 영역을 최대로 항상 만든다.
        // 그때 감시 안된 영역을 세면 되지 않을까?

        // 상 하 좌 우 순으로 나열되어 있다
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        ArrayList<Integer> one = new ArrayList<>();
        for(int d=1; d<=4; d++) one.add(d);

        ArrayList<int[]> two = new ArrayList<>();
        two.add(new int[]{1,2});
        two.add(new int[]{3,4});

        ArrayList<int[]> three = new ArrayList<>();
        three.add(new int[]{1,4});
        three.add(new int[]{2,4});
        three.add(new int[]{2,3});
        three.add(new int[]{1,3});

        ArrayList<int[]> four = new ArrayList<>();
        four.add(new int[]{1,3,4});
        four.add(new int[]{1,2,4});
        four.add(new int[]{2,3,4});
        four.add(new int[]{1,2,3});

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<cnt; i++){
            int x = cctv[i][0];
            int y = cctv[i][1];
            int type = cctv[i][2];

            // cctv마다 차례대로 어떤 방향을 선택할 것인지 그걸 모은다?

        }
    }
}