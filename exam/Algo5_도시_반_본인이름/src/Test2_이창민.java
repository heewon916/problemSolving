import java.io.*;
import java.util.*;
public class Test2_이창민 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+1];
		for (int i=1;i<n+1;i++) {
			arr[i] = i;
		}

		Stack<Integer> stack = new Stack<>();
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			

			for (int idx=a;idx<=b;idx++) {
				stack.add(arr[idx]);
			}
			
			for (int idx=a;idx<=b;idx++) {
				arr[idx] = stack.pop();
//				System.out.println(arr[idx]);
			}
		}
		
		for (int i=1;i<n+1;i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}

}
