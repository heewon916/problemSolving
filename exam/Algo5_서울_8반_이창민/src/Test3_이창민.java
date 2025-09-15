import java.io.*;
import java.util.*;
public class Test3_이창민 {
	static int n, m, start, end; 
	static int[][] g;
	public static void main(String[] args) throws Exception {
		//-------여기에 해결 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		g = new int[n+1][n+1];
		for (int i=0;i<n+1;i++) {
			for (int j=0;j<n+1;j++) {
				if (i==j) {
					g[i][j] = 0;
				}
				
				else {
//					g[i][j] = Integer.MAX_VALUE;
					g[i][j] = -1;
				}
			}
		}
		
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (g[a][b]==-1) {
				g[a][b] = c;
			} else {
				g[a][b] = Math.min(g[a][b], c);
			}
//			g[a][b] = Math.min(g[a][b], c);
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra());
	}
	
	static int dijkstra() {
		int[] dist = new int[n+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0]-o2[0]);
		pq.offer(new int[] {0, start});
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if (dist[now[1]] < now[0]) continue;
			for (int i=1;i<n+1;i++) {
//				if (g[now[1]][i]!=Integer.MAX_VALUE) {
//					if (dist[i] > now[0] + g[now[1]][i]) {
//						dist[i] = now[0] + g[now[1]][i];
//						pq.offer(new int[] {dist[i], i});
//					}
//				}
				if (g[now[1]][i]==-1) continue;
				if (dist[i] > now[0] + g[now[1]][i]) {
					dist[i] = now[0] + g[now[1]][i];
					pq.offer(new int[] {dist[i], i});
				}
			}
		}
		return dist[end];
	}
}
