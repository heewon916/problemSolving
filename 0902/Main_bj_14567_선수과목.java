import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_bj_14567_선수과목 {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        StringTokenizer st = null; 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] g = new int[N+1][N+1]; 
        int[] indegrees = new int[N+1];
        int[] semesters = new int[N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " "); 
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            g[a][b] = 1; 
            indegrees[b]++; 
        }
        ArrayDeque<Integer> q = new ArrayDeque<>(); 
        for(int i=1; i<N+1; i++){
            if(indegrees[i] == 0) {
                q.add(i);
                semesters[i] = 1; 
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll(); 
            for(int i=1; i<N+1; i++){
                if(g[cur][i] == 1) {
                    g[cur][i] = 0; 
                    indegrees[i]--; 
                    if(indegrees[i] == 0) {
                        q.offer(i);
                        semesters[i] = semesters[cur]+1; 
                    }
                }
            }
        }

        for(int a: semesters){
            System.out.printf("%d ", a);
        }
    }
}   
/*
[이해]
위상정렬 
N 10**3
M 5*10**5
선수과목 목록 
a, b -> g[a].add(b) 

위상정렬 

진입차수 계산하기 
그 중에서 0인 것만 따로 q에 넣어둔다. 

 */