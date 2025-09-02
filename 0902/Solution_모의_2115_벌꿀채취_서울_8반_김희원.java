import java.io.*; 
import java.util.*; 
public class Solution_모의_2115_벌꿀채취_서울_8반_김희원 {
    static int[][] mat; 
    static int N, M, C, bestScore; 
    static void subset(int cnt, int row, int start, int sum, int doubleScore){
        if(sum > C) return; 
        if(cnt == M){
            // M개 다 봤으면 
            bestScore = Math.max(bestScore, doubleScore);
            return; 
        }
        int curVal = mat[row][start];
        subset(cnt+1, row, start+1, sum+curVal, doubleScore+curVal*curVal);
        subset(cnt+1, row, start+1, sum, doubleScore);
    }
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            mat = new int[N][N]; 
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<N; j++){
                    mat[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // row, start을 기준으로 M칸에 대해서
            // M개의 숫자들에 대해 부분집합을 구한다. 
            // 각 부분집합에서 제곱의 합이 최대인 경우를 구해야 한다. 
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->-Integer.compare(o1[2], o2[2]));
            for(int i=0; i<N; i++){
                for(int j=0; j<=N-M; j++){
                    bestScore = 0; 
                    // i,j 에 대해서 가로로 M칸을 가져오는데 
                    // M칸 안에서 부분집합을 만들고, 그 제곱의 합이 최대인 걸 가져와야 한다. 
                    subset(0, i, j, 0, 0);
                    // System.out.printf("i:%d, j:%d, bestScore:%d\n", i,j, bestScore);
                    pq.add(new int[]{i, j, bestScore});
                }
            }
            
            // pq는 score를 기준으로 내림차순 정렬되어 있기 때문에 
            // poll한 것을 나머지 것들과 매칭하면서 최대값을 구하면 된다. 
            int best = 0; 
            while(!pq.isEmpty()){
                int[] cur = pq.poll(); 
                int row = cur[0], start = cur[1], sum = cur[2]; 
                // System.out.println(row + " " + start + " " + sum);
                boolean able = true; 
                for(int[] next: pq){
                    if(row != next[0]) able = true; 
                    else{
                        if(start+M <= next[1]) able = true; 
                        else able = false; 
                    }
                    if(able){
                        best = best < (sum+next[2]) ? (sum+next[2]) : best; 
                        // System.out.println("#update: " + best);
                    } 
                }
            }
            sb.append('#').append(t).append(' ').append(best).append('\n');
        }
        System.out.println(sb.toString());
    }
}
/*
[이해]
NxN 배열 
가중치가 다름 
최대 가중치의 합 

겹치지 않게 가로로 M칸만 가져간다 
동시에 2명이 
가중치 상한선 C 넘어가면 안된다 

[입력] 
제한시간 3초 
N 10 
M 5 
C 30 

[구현]
가중치가 다른 2차원 배열에서 
가로로 M칸 i, i+M-1까지 가중치를 더해본다. 
n개의 칸이 있으면 m칸을 연속으로 가져갈 경우의 수는 n-m+1 
한 줄에서 발생할 수 있는 경우의 수가 n-m+1 
1줄 선택하면 다른 n-1개의 줄에서 하나의 줄을 골라서, n-m+1을 한다. 
    (n-m+1)*(n-1)*(n-m+1) = n**3 = 10^3 
그럼 추출할 수 있는 M+M칸을 하나의 배열에 묶어서 넣어두고 
그 배열 안에서 
    채취할 수 있는 경우의 수는 2mCm 10C5 252 
    
252000 < 3*10^5 

int[][] mat = new int[n][n]; 
List<int[]> list = new ArrayList<>(); 

하나의 줄에서 m개를 뽑는 경우 n-m+1 
그냥 i, j 기준으로 한 줄에서 i, j 부터 i, j+m-1까지 방문시키고 
i, j+m 부터 연속으로 m개 방문할 수 있는 곳을 찾아다니면 될 것 같은데?

for i in 0..n
    for j in 0..n 
        if(v[i][j]) continue 
        int cur = mat[i][j] 
        for k in 0..m 
            v[i][j+m] = true
            list.add(mat[i][j+m])
        이 상태의 mat에 대해서 
            for r in i.. n 
                for c in j ..n
                     
                    if n-(j+m)+1 < m break 
                     
아니면 행에 대해서 시작지점만 저장해두고, 그 때의 최고 이익만 저장해둔다 
row, start, bestVal

그러고 나서, 겹치지 않는 2개를 골라서 최고 합을 구하면 된다. 
- 우선순위 큐 내림차순 쓰면 될 것 같네 o1[2] 기준으로 
겹치지 않는다고 하는 조건은 
1. 같은 행 일 때 o1의 start+m < o2의 start이거나 
2. 다른 행일 때는 아예 신경 안 써도 됨 

for i in 0..n 
    for j in 0..n-m+2
        int sum = 0
        i행에 대해서 
        j를 돌리는 거니까 
        for k in 0..m
            sum += mat[i][j+k] 
        if(sum <= C)
            pq <- i, j, sum 
            

best = Integer.MAX_VALUE 
while !pq.isEmpty
    int row, start, sum <- poll 
    for(int[] next: pq)
        if(row != next[0]) able = true 
        else // 행이 같으면 
            if start+m < next[1] able = true 
            else able = false 
        if(able) best = best < sum + next[2]? sum+next[2] : best; 

0 1 2 3 4
4-2+1 = n-m+1 경우의 수가 있음  
2칸 
0 1 
1 2 
2 3 
3 4 
 */