import java.io.*; 
import java.util.*;
/*
 *  시간복잡도: O(N*M) 
 */
public class Main_bj_1325_효율적인해킹_서울_8반_김희원 {
	static int N, M, max_count; 
	static List<Integer>[] g; 
	static int dfs(int start, int cnt) { 
		for(int a: g[start]) {
			max_count = Math.max(dfs(a, cnt+1), max_count); 
		}
		return cnt; 
	}
	static int bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[] v = new boolean[N+1];
		q.offer(start);
		v[start] = true; 
		int count = 0; 
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int a: g[cur]) {
				if(v[a])continue; 
				v[a] = true; 
				count++; 
				q.offer(a);
			}
		}
		return count; 
	}
	public static void main(String[] args) throws Exception{
		
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// N <= 10^4 M <= 10^5
		
		g = new List[N+1]; for(int i=0; i<N+1; i++) g[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken()); 
			int from = Integer.parseInt(st.nextToken()); 
			g[from].add(to);
		}
		
		List<int[]> list = new ArrayList<>(); 
		for(int i=1; i<N+1; i++) {
			list.add(new int[] {i, bfs(i)});
//			max_count = -1; 
//			dfs(i, 0); 
//			list.add(new int[] {i, max_count});

		}
		list.sort((a,b) -> -Integer.compare(a[1], b[1]));

		
		max_count = list.get(0)[1];
		for(int[] a: list) {
			if(a[1] == max_count) sb.append(a[0]).append(" ");
			else if(a[1] < max_count) break; 
		}
		System.out.println(sb.toString());
	}

}
