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
        for(int i=cnt-1; i>=0; i--){
            int x = cctv[i][0], y = cctv[i][1], type = cctv[i][2];
            if(type == 1){
                // 한 방향만으로 갈 수 있다.
                int able_max_dir = -1;
                int able_cnt_max = 0;
                for(int d=0; d<4; d++){
                    int nx = x+dx[d];
                    int ny = y+dy[d];
                    int able_cnt = 0;
                    while(0<=nx && nx<N && 0<=ny&& ny<M){
                        // 범위 안에 있으면
                        // 끝까지 가자

                        // 벽을 만나면 그 방향은 끝
                        if(map[nx][ny] == 6) break;

                        // 벽이 없으면 그냥 쭉 가기
                        nx = nx + dx[d];
                        ny = ny + dy[d];
                        able_cnt++;
                    }
                    if(able_cnt > able_cnt_max) {
                        able_max_dir = d;
                    }
                }
                // 최대인 방향으로 방문 처리
                int nx = x, ny = y;
                map[nx][ny] = -1; // 현재 위치 cctv자리도 방문처리
                while(0<=nx && nx<N && 0<=ny&& ny<M){
                    if(map[nx][ny] == 6) break;
                    map[nx][ny] = -1;
                    nx = nx + dx[able_max_dir];
                    ny = ny + dy[able_max_dir];
                }
            }else if(type == 2){
                // 행 또는 열로만 셀 수 있다.
                int x_dir_cnt = 0;
                int y_dir_cnt = 0;
                // 가로 방향
                for(int d=0; d<4; d++){
                    if(d == 2 || d == 3){
                        // 좌, 우 방향일 때
                        int nx = x+dx[d], ny = y+dy[d];
                        while(0<=nx && nx<N && 0<=ny&& ny<M){
                            if(map[nx][ny] == 6) break;
                            x_dir_cnt++;
                            nx += dx[d];
                            ny += dy[d];
                        }
                    }else{
                        // 상, 하 방향일 때
                        int nx = x+dx[d], ny = y+dy[d];
                        while(0<=nx && nx<N && 0<=ny&& ny<M){
                            if(map[nx][ny] == 6) break;
                            y_dir_cnt++;
                            nx += dx[d];
                            ny += dy[d];
                        }
                    }
                }

                if(x_dir_cnt <= y_dir_cnt){
                    // 세로로 cctv 쏠 때가 더 효과적이면
                    for(int d=0; d<2; d++){
                        int nx = x+dx[d], ny = y+dy[d];
                        while(0<=nx && nx<N && 0<=ny&& ny<M){
                            if(map[nx][ny] == 6) break;
                            map[nx][ny] = -1;
                            nx += dx[d];
                            ny += dy[d];
                        }
                    }
                }else{
                    // 가로로 cctv 쏠 때가 더 효과적이면
                    for(int d=2; d<4; d++){
                        int nx = x+dx[d], ny = y+dy[d];
                        while(0<=nx && nx<N && 0<=ny&& ny<M){
                            if(map[nx][ny] == 6) break;
                            map[nx][ny] = -1;
                            nx += dx[d];
                            ny += dy[d];
                        }
                    }
                }
            }else if(type == 3){
                int[] max_dir = new int[2];
                int max_count = 0;
                for(int d=0; d<4; d++){
                    int count = 0;
                    int other_d = (d+1)%4;
                    int nx = x+dx[d];
                    int ny = y+dy[d];
                    while(0<=nx && nx<N && 0<=ny&& ny<M){
                        if(map[nx][ny] == 6) break;
                        nx += dx[d];
                        ny += dy[d];
                        count++;
                    }
                    nx = x+dx[other_d];
                    ny = y+dy[other_d];
                    while(0<=nx && nx<N && 0<=ny&& ny<M){
                        if(map[nx][ny] == 6) break;
                        nx += dx[d];
                        ny += dy[d];
                        count++;
                    }
                    if(max_count < count){
                        max_dir[0] = d;
                        max_dir[1] = other_d;
                    }
                }

                int nx = x+dx[max_dir[0]];
                int ny = y+dy[max_dir[0]];
                while(0<=nx && nx<N && 0<=ny&& ny<M){
                    if(map[nx][ny] == 6) break;
                    nx += dx[max_dir[0]];
                    ny += dy[max_dir[0]];
                    map[nx][ny] = -1;
                }
                nx = x+dx[max_dir[1]];
                ny = y+dy[max_dir[1]];
                while(0<=nx && nx<N && 0<=ny&& ny<M){
                    if(map[nx][ny] == 6) break;
                    nx += dx[max_dir[1]];
                    ny += dy[max_dir[1]];
                    map[nx][ny] = -1;
                }
            }else if(type == 4){

            }else if(type == 5){

            }
        }

    }
}