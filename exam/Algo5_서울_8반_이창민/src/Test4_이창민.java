import java.io.*;
import java.util.*;
public class Test4_이창민 {
	static int n;
	static List<Integer>[] g;
	static boolean[] selected;
	static int result;
	public static void main(String[] args) throws Exception {
		//-------여기에 해결 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		n = Integer.parseInt(br.readLine());
		g = new List[n+1];
		for (int i=0;i<n+1;i++) g[i] = new ArrayList<>();
		
		for (int i=0;i<n-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			g[a].add(b);
			g[b].add(a);
		}
		
		result = Integer.MAX_VALUE;
		selected = new boolean[n+1];
		recur(1);
		System.out.println(result);
	}
	
	static void recur(int depth) {
		if (depth==n+1) {

			if (check()) {
				int tmp = 0;
				for (int i=1;i<n+1;i++) {
					if (selected[i]) tmp++;
				}
				result = Math.min(result, tmp);
			}
			return;
		}
		
		selected[depth] = true;
		recur(depth+1);
		selected[depth] = false;
		recur(depth+1);
	}
	
	static boolean check() {
		boolean[] v = new boolean[n+1];
		
		for (int i=1;i<n+1;i++) {
			if (selected[i]) {
				v[i] = true;
				for (int x:g[i]) {
					v[x] = true;
				}
			}
		}
		
		
		for (int i=1;i<n+1;i++) {
			if (!v[i]) return false;
		}
		return true;
	} 

}
