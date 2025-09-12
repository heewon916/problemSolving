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

        
        System.out.println(maxChain);
    }
}
