import java.io.*; 
import java.util.*; 

public class Main_bj_2630_색종이만들기 {
    static int N; 
    static int[][] mat; 
    static int blue, white;
    static void divide(int i, int j, int size){
        // 1. 범위 안에 있는 모두 좌표 값이 같은 값인지 확인하기 
        // 이중for문을 범위 안에서 돈다. 
        if(size == 1) {
            if(mat[i][j] == 1) blue++; 
            else white++; 
            return; 
        }
        int sum = 0; 
        for(int r=i; r<i+size; r++){
            for(int c=j; c<j+size; c++){
                sum += mat[r][c];
            }
        }
        
        if(sum == size*size) {
            blue++;
            return; 
        } else if (sum == 0) {
            white++; 
            return; 
        } else{
            divide(i, j, size/2);
            divide(i+size/2, j, size/2);
            divide(i, j+size/2, size/2);
            divide(i+size/2, j+size/2, size/2);
        }
        // 모두 같은 값이면 blue, white 둘 중 하나 ++하고 return 
        // 아니면 size를 반으로 나눠서, 4가지로 나눠 재귀호출한다. 
    }
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        mat = new int[N][N]; 
        blue = 0; 
        white = 0; 
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(0, 0, N);
        sb.append(white).append('\n').append(blue);
        System.out.println(sb.toString());
    }
}
/*
[이해]
NxN N = 2^k 128 (k=1..7 )
모두 같은 색으로 안 이루어져 있으면 같은 크기로 나눠서 각각에 대해 검사한다. 
잘라진 종이가 모두 하얀색이거나 파란색이면 끝 

N <- 한변의 길이 
int[][] mat <- 색종이들 
구해야 하는 것: 하얀색 색종이 개수, 파란색 색종이 개수 

[구현] 
분할정복 
1. 주어진 색종이가 모두 하얀색인지, 파란색인지 검사한다. 
2. 모두 하얀색이거나, 파란색이면 w++; 또는 b++; 
3. 색이 섞여있으면 나눈다. 
    1. 각 영역의 좌표랑 N/2 사이즈에 대해서 1번으로 돌아간다. 
*/