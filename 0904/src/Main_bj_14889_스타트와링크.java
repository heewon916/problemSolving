import java.io.*;
import java.util.*;

public class Main_bj_14889_스타트와링크 {
	static int[] b;
	static int[][] mat; 
	static int N; 
	static int min_score_diff; 
	static void comb(int cnt, int start) {
		/*
		 * @param cnt 현재까지 선택된 사람의 수 
		 * @param start 다음 선택을 시작할 인덱스 
		 */
		if(cnt == (int)N/2) {
			// 팀이 모두 나뉜 상태 
			/*
			 * 하나의 팀은 N/2명이어야 한다. 
			 * !!!!! cnt == N이 아니라, cnt == (int)N/2이다. 
			 */
//			System.out.println(Arrays.toString(b));
			int team0_score = 0, team1_score = 0; 
			// 한 사람마다 다른 사람이 있는지 체크하고, 있으면 점수를 더한다
			for(int i=0; i<N; i++) {
				if(b[i] == 0) {
					for(int j=0; j<N; j++) {
						if(b[j] == 0) team0_score += mat[i][j]; 
					}
				}else {
					for(int j=0; j<N; j++) {
						if(b[j] == 1) team1_score += mat[i][j]; 
					}
				}
			}
//			System.out.println(team0_score + " " + team1_score);
			if(Math.abs(team1_score - team0_score) < min_score_diff) {
				min_score_diff = Math.abs(team1_score - team0_score); 
//				System.out.println("@update: " + min_score_diff);
			}
			return; 
		}
		for(int i=start; i<N; i++) {
			b[i] = 1; 
//			System.out.printf("comb(%d, %d)\n", cnt+1,i+1);
			comb(cnt+1, i+1);
			b[i] = 0; 
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        mat = new int[N][N]; 
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int j=0; j<N; j++) {
        		mat[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        min_score_diff = Integer.MAX_VALUE; 
        b = new int[N]; 
        comb(0, 0);
        System.out.println(min_score_diff);
	}

}
/* 다른 풀이: 
- 행별, 열별로 점수를 따로 기록하고, 모든 능력치의 합을 저장해둔다. 
- 팀1에 사람을 1명씩 넣을 때마다, 그 사람으로 인한 능력치를 빼준다. 
public class Main {
	static int[] sumRow;
	static int[] sumCol;
	static boolean[] visit;
	static int N;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    N=Integer.parseInt(br.readLine());
	    int sum=0;
	    sumRow=new int[N];
	    sumCol=new int[N];
	    visit=new boolean[N];
	
	    StringTokenizer st;
	    for (int i=0; i<N; i++){
	        st=new StringTokenizer(br.readLine());
	        for (int j=0; j<N; j++){
	            int status=Integer.parseInt(st.nextToken());
	            sum+=status;
	            sumRow[j]+=status;
	            sumCol[i]+=status;
	        }
	    }
	
	    dfs(0, 0, sum);
	    System.out.println(min);
	    
	    br.close();
	}
	static void dfs(int pos, int depth, int diff){
	    if (depth==N/2){
	        min=Math.min(min, Math.abs(diff));
	        return;
	    }
	
	    for (int i=pos; i<N; i++){
	        if (!visit[i]){
	            visit[i]=true;
	            dfs(i+1, depth+1, diff-sumCol[i]-sumRow[i]);
	            visit[i]=false;
	        }
	    }
	}
}
*/