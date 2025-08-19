import java.io.*;
import java.util.*;

public class Solution_d2_14510_나무높이 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0819\\Test14510.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] mat = new int[N];
			int maxHeight = Integer.MIN_VALUE; 
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				mat[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, mat[i]);
			}
			for(int i=0; i<N; i++) {
				if(mat[i] < maxHeight) pq.add(mat[i]);
			}
			System.out.println(pq.toString());
			int day = 0; 
			int add = 0; 
			while(!pq.isEmpty()) {
				int cur = pq.poll();
				day++;
				if(cur >= maxHeight) continue; 
				if(day%2 == 1) add = 1; 
				else add = 2; 
				pq.add(cur + add);
			}				
			
			sb.append("#").append(tc).append(" ").append(day).append("\n");
		}
		System.out.println(sb.toString());
	}
}
