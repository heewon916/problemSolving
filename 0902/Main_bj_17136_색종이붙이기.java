import java.io.*; 
import java.util.*; 
public class Main_bj_17136_색종이붙이기 {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = null;  
        mat = new int[10][10]; 
        for(int i=0; i<10; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<10; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[2], o2[2]));
        int[] dx = {0, 0, 1, 1};
        int[] dy = {0, 1, 0, 1};

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                for(int width=6; width>0; width--){
                    boolean able = checkRectangle(i, j, width);
                    if(able) pq.add(new int[]{i, j, width});
                }
            }
        }
        while(!pq.isEmpty()){
            int[] cur = pq.poll(); 
            int i = cur[0], j = cur[1], w = cur[2]; 

            for(int r=i; r<i+w; r++){
                for(int c=j; c<j+w; c++){
                    mat[r][c] = -1; 
                }
            }

            for(int[] arr: pq){
                if(checkDeleteCond(arr[0], arr[1], arr[2])){
                    pq.remove(arr);
                }
            }
            for(int[] arr: pq){
                System.out.println(Arrays.toString(arr));
            }
        }
    }
    static int[][] mat; 
    static boolean checkDeleteCond(int i, int j, int w){
        for(int r=i; r<i+w; r++){
            for(int c=j; c<j+w; c++){
                if(mat[r][c] == -1) return true; 
            }
        }
        return false; 
    }
    static boolean checkRectangle(int i, int j, int width){
        // i, j 기준으로 width 크기의 정사각형이 전부 1인지 체크하자. 
        for(int r=i; r<i+width; r++){
            for(int c=j; c<j+width; c++){
                if(mat[r][c] == 0) return false; 
            }
        }
        return true; 
    }
}
/*
[이해]
10x10 배열 
1인 칸은 모두 덮여야 한다 
0이 적힌 칸에는 색종이 불가능 

1이 적힌 모든 칸을 붙이는데 필요한 색종이 최소 개수 

[설계]
i, j 기준으로 
i,j i,j+1
i+1,j i+1,j+1

for d 0..4
    int nx = x+dx[d]*width 

모든 i, j에 대해서 mat[i][j]가 1인 경우 
width=1부터 5까지 가능한 경우가 있는지 확인하고, 
가능한 경우에는 가장 큰 width랑 함께 i, j 좌상단 지점을 저장해두자. 

그렇게 width 큰 순서대로 pq에 저장하고 

pq를 순서대로 뽑으면서
    pq에 의해 1인 곳들은 모두 방문처리한다. 
        만약 해당 width 색종이 개수가 0이 되면 
            그 위치들을 
        mat[i..][j..] = -1; 
    
    이 녀석으로 인해서 다른 시작점이 포함하고 있는 영역도 들어가있었다면 그 시작점들은 모두 삭제 


*/