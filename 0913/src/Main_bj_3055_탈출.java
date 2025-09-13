import java.io.*;
import java.util.*; 

public class Main_bj_3055_탈출 {
    static class D{
        int i, j, time;
        public D(int i, int j, int time) {
            this.i = i;this.j = j;this.time = time;
        }
        @Override
        public String toString() {
            return "[i=" + i + ", j=" + j + ", time=" + time + "]";
        } 
    }
    static int R, C; 
    static char[][] map; 
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ArrayDeque<D> dPos = new ArrayDeque<>(); // D의 갈 수 있는 위치 관리; (i, j, time)
        int dx = -1, dy = -1; // 도착점 위치 
        
        map = new char[R][C]; 
        for(int i=0; i<R; i++){
            String in = br.readLine(); 
            for(int j=0; j<C; j++){
                map[i][j] = in.charAt(j);
                if(map[i][j] == 'S') dPos.add(new D(i, j, 0));
                else if(map[i][j] == 'D') {dx = i; dy = j;} 
            }
        }
        boolean[][] v = new boolean[R][C];
        boolean canVisit = false; 
        int ans = 0; 
        while(true){
            // 물을 먼저 퍼트린다. water 큐에 들어있는 모든 물에 대해서 퍼트린다. 
            ArrayDeque<int[]> q = new ArrayDeque<>(); 
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(map[i][j] == '*'){ // 물이면 1번만 인접한 칸에 대해 퍼진다. 
                        for(int d=0; d<4; d++){
                            int ni = i + di[d]; 
                            int nj = j + dj[d]; 
                            if(ni<0 || ni>=R || nj<0 || nj>=C) continue; 
                            if(map[ni][nj] != 'X' && map[ni][nj] != 'D'){
                                q.add(new int[]{ni, nj});
                            }
                        }
                    }
                }
            }
            while(!q.isEmpty()){
                int[] cur = q.poll(); 
                map[cur[0]][cur[1]] = '*'; 
            }
            
            // for(char[] a: map) System.out.println(Arrays.toString(a));
            // 물이 퍼진 map을 기준으로 갈 수 있는 곳들을 찾아서 가야 한다. 
            // dPos에 있는 모든 좌표 기준으로, 갈 수 있는 곳을 큐에 다시 넣는다. 
            boolean canMove = false; // 더 갈 수 있는 곳이 있는지 확인한다. 
            int cur_size = dPos.size(); 
            while(cur_size>0){
                D pos = dPos.poll(); cur_size--;
                v[pos.i][pos.j] = true; 
                // System.out.println("@갈 수 있는 곳:" + pos);
                // 만약 도착점에 갈 수 있게 되면 그 시점의 시간이 정답이 된다.  
                if(pos.i == dx && pos.j == dy) {
                    canVisit = true; 
                    ans = pos.time; 
                    break; 
                }
                // 현재 위치를 기준으로, 주변에 갈 수 있는 곳이 있다면 큐에 넣는다. 
                for(int d=0; d<4; d++){
                    int ni = pos.i + di[d]; 
                    int nj = pos.j + dj[d]; 
                    if(ni<0 || ni>=R || nj<0 || nj>=C) continue; 
                    if(!v[ni][nj] && map[ni][nj] != '*' && map[ni][nj] != 'X'){
                        canMove = true;
                        v[ni][nj] = true; 
                        dPos.add(new D(ni, nj, pos.time+1));
                        // System.out.println("--> 추가된:" + ni+ "," + nj+", time="+(pos.time+1));
                    }
                }
            }
            // 도착점 도착했으니까 break 
            if(canVisit) break; 
            // 더 이상 갈 수 있는 곳이 없는데 canVisit도 false이면 
            if(!canMove) break; 
        }
        
        if(canVisit) System.out.println(ans);
        else System.out.println("KAKTUS");
    }
}
