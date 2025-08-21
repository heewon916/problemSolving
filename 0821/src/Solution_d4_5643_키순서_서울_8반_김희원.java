import java.io.*; 
import java.util.*; 
public class Solution_d4_5643_키순서_서울_8반_김희원 {
/*
 * 학생 번호: 1~N 
 * 키가 모두 다름 
 * a학생 키 < b학생 키 => a->b로 표현 
 * 
 */
	public static void main(String[] args) throws Exception {
		// 각 정점 기준으로 나보다 큰 애들 
		// 각 정점 기준으로 나보다 작은 애들 
		// 각각 세서 2개의 배열에 저장 -> big, small 
		// 나보다 큰 애들은 화살표 따라가면서 세면 되고 
		// 나보다 작은 애들은 화살표 반대 방향 세면 되는데 
		// 화살표 반대 방향 세는 법 
		// 매번 계산하기 번거로울 것 같음 -> 
		// big -> 내 정점 기준으로 갈 수 있는 정점 개수 세기 
		// small -> 다른 정점 기준 내 정점으로 올 수 있다면, 그때 경로 상의 정점 개수 
		// 각 정점을 기준으로 big, small을 각각 했을 때, big + small이 N-1이라면 정답이다. 
		
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			g = new List[N+1];
			int answer = 0; 
			for(int i=1; i<N+1; i++) g[i] = new ArrayList<>(); 
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				g[a].add(b);
			}
			check = new boolean[N+1][N+1]; 
			// 각 정점 u에 대해서 bfs를 미리 다 수행해서, 각 정점에서 어떤 정점으로 갈 수 있는지 미리 체크하고
			for(int i=1; i<N+1; i++) {
				check[i] = bfs(i); 
//				System.out.println(Arrays.toString(check[i]));
				// 자기 자신도 true라고 되어 있다. 
			}
			
			// 거기서 1인 정점 개수를 다 세면 big_count
			for(int i=1; i<N+1; i++) {
				// 각 정점마다 세는 중 
				int big_count = 0; 
				int small_count = 0; 
				for(int j=1; j<N+1; j++) {
					if(check[i][j]) big_count++; 
					else {
						if(check[j][i]) small_count++; 
					}
				}
//				System.out.println("정점 i:" + i + " " + big_count + " " + small_count);
				if(big_count + small_count == N) answer++;
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n"); 
			// 거기서 0인 정점을 v라고 하면, mat[v][u]이 1이면 small_cnt++ 
//			System.out.println(15 * (500*499/2 + 500*(500 + 500*500)));
		}
		System.out.println(sb.toString());
	
	}
	static boolean check[][]; 
	static int N, M; 
	static List<Integer>[] g ;
	// 모든 정점 기준으로 갈 수 있는 애들
	static boolean[] bfs(int start) {
		// target 정점을 시작으로 갈 수 있는 애들 개수 세면 된다. 
		// start 정점을 시작으로 갈 수 있는 애들 개수를 세는 거니까 N
		Queue<Integer> q = new LinkedList<>(); 
		boolean[] visited = new boolean[N+1];
		int cnt = 0; 
		q.add(start);
		visited[start] = true; 
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int a: g[cur]) {
				if(!visited[a]) {
					q.add(a); 
					visited[a] = true; 
					cnt++; 
				}
			}
		}
		return visited; 
	}
}
