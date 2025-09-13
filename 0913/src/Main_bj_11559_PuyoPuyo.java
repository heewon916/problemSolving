import java.io.*;
import java.util.*;

public class Main_bj_11559_PuyoPuyo {
    static char[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] v; 

    static class Chain{
        int i, j; 
        char color;
        public Chain(int i, int j, char color) {
            this.i = i;
            this.j = j;
            this.color = color;
        }
        @Override
        public String toString() {
            return "Chain [i=" + i + ", j=" + j + ", color=" + color + "]";
        } 
        
    }
    static ArrayList<Chain> bfs(int i, int j, char color){
        ArrayDeque<Chain> q = new ArrayDeque<>();// bfs 탐색용 큐 
        ArrayList<Chain> bombs = new ArrayList<>(); // 같은 색의 그룹 
        v[i][j] = true; 
        q.offer(new Chain(i, j, color));
        bombs.add(new Chain(i, j, color));
        while(!q.isEmpty()){
            Chain c = q.poll();
            // 현재 내 주변에 같은 색이고, 방문 아직 안 한 좌표 모두 넣기 
            for(int d=0; d<4; d++){
                int ni = c.i + di[d]; 
                int nj = c.j + dj[d]; 
                if(ni<0 || ni>=12 || nj<0 || nj>=6) continue; 
                if(!v[ni][nj] && map[ni][nj] == c.color){
                    q.offer(new Chain(ni, nj, color));
                    v[ni][nj] = true; 
                    bombs.add(new Chain(ni, nj, color));
                }
            }
        }
        if(bombs.size() >= 4){
            return bombs; 
        }
        return  null;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new char[12][6]; 
        for(int i=0; i<12; i++){
            String input = br.readLine(); 
            for(int j=0; j<6; j++){
                map[i][j] = input.charAt(j); 
            }
        }
        int bombCnt = 0; // 연쇄 횟수 
        while(true){
            boolean doBomb = false; // 메번 터트릴 게 있는지 체크해줘야 한다. 
            ArrayList<Chain> bombList = new ArrayList<>(); // 터질 뿌요 좌표들을 하나로 모은다. 
            v = new boolean[12][6];
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    // 같은 색의 뿌요를 찾아 전부 방문했을 때, 4개 이상이면 bombList에 추가 
                    if(!v[i][j] && map[i][j] != '.'){
                        ArrayList<Chain> tmp = bfs(i, j, map[i][j]); 
                        // 찾아온 한 그룹의 크기가 4이상이면 터트릴 수 있다. 
                        if(tmp != null){
                            bombList.addAll(tmp);
                            doBomb = true; 
                        }
                    }
                }
            }
            // 터질 그룹이 없으면 멈춰야 한다. 
            if(!doBomb) break; 

            // 터트릴 bomb이 있으면 모두 '.'로 바꾼다. 
            for(Chain c: bombList){
                map[c.i][c.j] = '.';
            }
            
            bombCnt++; 

            // 남은 뿌요들을 아래로 모두 내린다. 
            for(int c=0; c<6; c++){
                // 열별로 이 작업을 수행해 준다. 
                ArrayList<Character> gravity = new ArrayList<>(); 
                for(int r=11; r>=0; r--){
                    if(map[r][c] != '.') gravity.add(map[r][c]);
                }

                // 일단 비우고 
                for(int r=11; r>=0; r--){
                    map[r][c] = '.';
                }

                // 아래서부터 채운다.
                int r = 11;
                for(int i=0; i<gravity.size(); i++){
                    map[r--][c] = gravity.get(i); 
                }
            }
        }

        StringBuilder sb = new StringBuilder(); 
        sb.append(bombCnt);
        System.out.println(sb.toString());
        

    }
}
