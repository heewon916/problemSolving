import java.io.*; 
import java.util.*; 
public class Solution_d6_1267_작업순서_서울_8반_김희원 {
/*
 * g: 인접리스트로 선언해서, 각 정점(1번~V번)이 갈 수 있는 정점들 저장
 * indegree; g 초기화할 때 g[a].add(b) 이면 indegree[b]++; 이 되고 (진입차수 계산해야 됨) 
 * 
 * 진입차수가 0인 것부터 뽑아서 큐에 넣고, 
 * 그 녀석의 인접 정점들에 가서는 진입차수를 -1씩 뺴주기 
 * 그때 0이 되면 큐에 또 넣어주기 
 * 
 * 시간복잡도: E + V**2 = 10^6 + 10^3 * 3 
 */
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=0; t<10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int[] indegrees = new int[V+1];		// 각 정점의 진입차수 
			List<Integer>[] g = new List[V+1]; 
			for(int i=1; i<V+1; i++) g[i] = new ArrayList<>(); 
			
			st = new StringTokenizer(br.readLine(), " "); 
			for(int i=0; i<E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				g[from].add(to); 
				indegrees[to]++; 
			}

			//위상 정렬 시작 
			List<Integer> answer = new ArrayList<>(); 
			Queue<Integer> q = new LinkedList<>(); 
			// 1. 진입차수가 0인 것부터 뽑아서 큐에 넣고, 
			for(int i=1; i<V+1; i++) {
				if(indegrees[i] == 0) q.add(i); 
			}
			// 2,3 번 과정을 큐가 빌 때까지 진행한다. 
			
			while(!q.isEmpty()) {
				int cur = q.poll(); 
				answer.add(cur);
				for(int adj: g[cur]) {
					indegrees[adj]--; 
					if(indegrees[adj] == 0) q.add(adj);
				}
			}
			// 2. 그 녀석의 인접 녀석들한테 가서 진입차수를 -1씩 빼준다. 
			// 3. 만약 그때 진입차수가 0이 되는 정점들이 있으면 큐에 넣어준다. 
			// 4. 작업순서는 큐에서 뽑는 순서가 작업순서이므로, 뽑을때마다 answer에 기록하자. 
			sb.append("#").append(t+1).append(" "); 
			for(int n : answer) sb.append(n).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}

}
