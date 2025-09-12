import java.io.*; 
import java.util.*;

public class Main_bj_11559_puyopuyo {
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static char[][] map;
    static int chain; 
    static int maxChain; 
    static class Position{
        int i, j;
        char color; 
        public Position(int i, int j, char color){
            this.i = i; 
            this.j = j; 
            this.color = color; 
        }
    }
    static void bfs(int i, int j, char color, ArrayList<Position> bombList){
        ArrayDeque<Position> q = new ArrayDeque<>(); 
        // ArrayList<Position> list = new ArrayList<>(); 
        boolean[][] v = new boolean[12][6];
        v[i][j] = true; 
        q.add(new Position(i, j, color));
        // int count = 1; 
        bombList.add(new Position(i, j, color));
        while(!q.isEmpty()){
            Position cur = q.poll(); 
            int ci = cur.i; 
            int cj = cur.j; 
            char cColor = cur.color; 
            for(int d=0; d<4; d++){
                int ni = ci+di[d]; 
                int nj = cj+dj[d]; 
                if(ni<0 || ni>=12 || nj<0 || nj>=6) continue; 
                if(map[ni][nj] == cColor && !v[ni][nj]){
                    // count++; 
                    // System.out.println("@@count: " + count);
                    v[ni][nj] = true; 
                    bombList.add(new Position(ni, nj, cColor));
                    System.out.println("@@add: " + ni + " "+ nj + " "+ cColor);
                }
            }
        }
        // for(Position p: list) System.out.println("dfs안에 p: " +  p.i + " "+ p.j + " "+ p.color);
        // if(list.size() >= 4){
        //     // list에 들은 i,j의 위치 모두 '.'로 바꾸자 
        //     chain++; 
        //     for(Position p: list){
        //         map[p.i][p.j] = '.';
        //     }
        //     System.out.println("#연쇄발생: 터트린 직후: ");
        //     for(char[] a: map) System.out.println(Arrays.toString(a));
        // }else{
        //     System.out.println(chain+" "+maxChain);
        //     maxChain = Math.max(chain, maxChain);
        //     chain = 0; 
        // }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; 

        map = new char[12][6]; 
        for(int i=0; i<12; i++){
            String str = br.readLine(); 
            for(int j=0; j<6; j++){
                map[i][j] = str.charAt(j); 
            }
        }

        chain = 0; 
        maxChain = 0; 
        ArrayList<Position> bombList = new ArrayList<>(); 
        for(int i=11; i>=0; i--){
            for(int j=0; j<6; j++){
                if(map[i][j] == '.') continue;
                char color = map[i][j]; 
                System.out.println("@탐색시작: "+ i+","+j+" color:" + color);
                // 주변에 같은 색 있는지 체크 
                // 4개 이상 있다면, 걔네 모두 '.' 빠꿔버린다.
                // boolean[][] v = new boolean[12][6];  
                bfs(i, j, color, bombList); 
                for(char[] a: map) System.out.println(Arrays.toString(a));
                // 공중에 떠 있는 애들 떨귀 
                // 한 열씩 확인하자. (밑에서부터)
                for(int c=0; c<6; c++){
                    ArrayDeque<Integer> q = new ArrayDeque<>(); 
                    for(int r=11; r>=0; r--){
                        char cur = map[r][c];
                        if(cur == '.') q.offer(r);
                        else{
                            if(q.size() > 0){
                                int set_r = q.poll(); 
                                map[set_r][c] = cur; 
                                map[r][c] = '.'; 
                            }
                        }
                    }
                }
                // 내려오고 나서 또 터질 게 있는지 확인 
                bfs(i, j, color, bombList);
                System.out.println("- 아래로 떨어지고 터진 뒤: ");
                for(char[] a: map) System.out.println(Arrays.toString(a));
            }
        }
        System.out.println(maxChain);
    }
}
