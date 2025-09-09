import java.io.*; 
import java.util.*; 
public class Solution_d3_3307_최장증가수열 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); 
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N+1];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<N+1; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int[] lis = new int[N+1];
			Arrays.fill(lis, 1);
			
			int max = 0; 
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<i; j++) {
					if(arr[j] < arr[i] && lis[i] < lis[j] + 1) {
						lis[i] = lis[j] + 1; 
						max = Math.max(max, lis[i]);
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb.toString());

	}

}
/*
for i in 1..n
	lis[i] = 1 
	for j in 1..i-1
		if a_j < a_i and lis[i] < lis[j]+1
			lis[i] = lis[j] + 1
			

*/