import java.io.*; 
import java.util.*; 
public class Main_bj_5567_결혼식_서울_8반_김희원 {
	static List<Integer>[] g;
	static boolean[] v;
	static int N, M, count = 0; 
	/* 최적화되지 않은 bfs 
	static void bfs(int i) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(i);
		v[i] = true; 
		for(int a: g[i]) {
			if(v[a]) continue; 
			v[a] = true; 
			q.offer(a);
			count++; 
		}
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int a: g[cur]) {
				if(v[a]) continue; 
				v[a] = true; 
				count++; 
			}
		}
	}
	*/
	
	static void bfs(int start) {
	    ArrayDeque<int[]> q = new ArrayDeque<>();
	    q.offer(new int[]{start, 0}); // 깊이를 함께 저장하는 방법이!! 
	    v[start] = true;

	    while (!q.isEmpty()) {
	        int[] now = q.poll();
	        int cur = now[0];
	        int depth = now[1];

	        if (depth >= 2) continue;
	        // break 하면 안되는 이유:
	        // 바로 멈춰버리면 큐에 이미 들어있는 나머지 "친구의 친구"를 모두 체크하지 못할 수 있다.

	        for (int next : g[cur]) {
	            if (v[next]) continue;
	            v[next] = true;
	            count++;
	            q.offer(new int[]{next, depth + 1});
	        }
	    }
	}

	public static void main(String[] args) throws Exception{
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동기 수 <= 500
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 관계 
		v = new boolean[N+1];
		g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>(); 
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			g[a].add(b); 
			g[b].add(a);
		}
		bfs(1);
		System.out.println(count);

	}

}
