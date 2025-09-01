import java.util.*;
import java.io.*;

public class Solution_d4_2819_격자판의이어붙이기_서울_8반_김희원 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            mat = new int[4][4]; 
            for(int i=0; i<4; i++){
                st = new StringTokenizer(br.readLine(), "   ");
                for(int j=0; j<4; j++){
                    mat[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            set = new HashSet<>(); 
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    bfs(i, j, String.valueOf(mat[i][j]), 1);
                }
            }
            System.out.println("#" + t + " " + set.size());
        }
    }
    static Set<String> set;
    static int[][] mat; 
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static void bfs(int i, int j, String s, int cnt){
        if(cnt == 7){
            // System.out.println(s);
            set.add(s);
            return; 
        }
        for(int d=0; d<4; d++){
            int ni = i+di[d];
            int nj = j+dj[d]; 
            if(ni<0 || ni>=4 || nj<0 || nj>=4) continue;
            
            bfs(ni, nj, s+mat[ni][nj], cnt+1);
            
        }
    }
}
/*
[이해]
4*4 배열 
한 칸에 대해서 인접한 4방향을 6번 
4*4 * 4^6 = 2^16 ~= 1000 * 32 

[설계] 
R = 7인 중복을 허용한 부분집합 
Set<String> set = new HashSet<>(); 
boolean[][] v = new boolean[4][4]; 
    
bfs(int i, int j, StringBuilder sb, int cnt) 
    if cnt == 7 
        set.add(sb.toString)
        clear = true
    
    for(int d=0; d<4; d++)
        int ni = i+di[d], nj = j+dj[d]
        if(범위 벗어나면) continue 
        v[ni][nj] = true; 
        dfs(ni, nj, sb.append(mat[ni][nj]), cnt+1)
        v[ni][nj] = false 
        
 */