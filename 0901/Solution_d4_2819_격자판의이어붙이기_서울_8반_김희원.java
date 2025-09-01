import java.util.*; 
import java.io.*; 

public class Solution_d4_2819_격자판의이어붙이기_서울_8반_김희원{
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] v;
    static void bfs(int i, int j, String res){
        Queue<int[]> q = new ArrayDeque<>(); 
        q.add(new int[]{i, j});
        int moves = 6; 
        while(!q.isEmpty() && moves > 0){
            int[] cur = q.poll(); 
            int cx = cur[0], cy = cur[1]; 
            for(int d=0; d<4; d++){
                int nx = cx + dx[d], ny = cy + dy[d]; 
                if(nx<0 || nx>=4 || ny<0 || ny>=4) continue; 
                if()
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            int[][] mat = new int[4][4];
            for(int i=0; i<4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<4; j++){
                    mat[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            Set<Integer> set = new HashSet<>();
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    v = new boolean[4][4];
                    bfs(i, j, "");
                }
            }
             
        }
    }
}
/*
[이해]
4x4 격자판 
0-9 숫자 
임의의 위치에서 시작 
상하좌우로 이동 가능 
범위 안에서 인접한 위치로 6번 이동
중복 방문이 가능하다  
각 칸에 적혀있는 숫자 이어붙여서 7자리 수 

구해야 하는 것: 서로 다른 일곱자리 수의 개수 

[입력]
제한시간 4초 

[설계]
구할 수 있는 경우의 수: 16개의 숫자 중에서 7개를 중복 순열. 그 중에서 분명 중복되는 숫자가 있을 것
16의 7승: 268,435,456
4초 = 4 * 10^8

모든 조합 구하는 거 안될 것이다. 

아니면 만들 수 있는 수를 모두 만들어 두고, 그걸 set으로 관리하자. 

[설계]
1. 모든 조합의 경우를 만들기 
한 칸에 대해 인접한 곳을 가면서 방문한 숫자를 만들고, 
그 길이가 7이 되면 멈춰서 set에 있는지 확인한다. 
없으면 추가하고 있으면 넘어간다 
 */