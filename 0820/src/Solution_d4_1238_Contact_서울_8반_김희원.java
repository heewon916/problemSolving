import java.io.*; 
import java.util.*; 
public class Solution_d4_1238_Contact_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		/*
		 * bfs + visited
		 * 방향 그래프 
		 * 연락 받은 사람 중 가장 큰 번호 반환 
		 * 입력: from-to쌍의 반복 존재 -> 이미 있다면 받지 않는 걸로 
		 */
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0820\\Test1238.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int inputLen = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			g = new List[101];
			for(int i=1; i<101; i++) g[i] = new ArrayList<>(); 
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<inputLen/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				boolean inputAgain = false; 
				for(int adj: g[from]) {
					if(adj == to) inputAgain = true; 
				}
				if(!inputAgain) {
					g[from].add(to);
				}
			}
			
//			for(int i=1; i<101; i++) {
//				if(g[i].size() > 0) System.out.println("i: " + g[i]);
//			}
			
			bfs(start);
			sb.append("#").append(tc).append(" ").append(maxCalledNum).append("\n");
			maxCalledNum = 0; 
		}
		System.out.println(sb.toString());

	}
	static List<Integer>[] g;
	static int maxCalledNum = 0; 
	static void bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>(); 
		boolean[] visited = new boolean[101];
		int[] contactDepth = new int[101];
		int maxDepth = 0; 
		q.add(start);
		visited[start] = true; 
		contactDepth[start] = 0; 
		while(!q.isEmpty()) {
			int cur = q.poll(); 
			visited[cur] = true;
			for(int adj: g[cur]) {
				if(!visited[adj]) {
					q.add(adj);
					visited[adj] = true; 
					contactDepth[adj] = contactDepth[cur] + 1; 
				}
			}
		}
		for(int i=1; i<101; i++) {
			if(contactDepth[i] > maxDepth) {
				maxDepth = contactDepth[i]; 
			}
		}
//		System.out.println("@maxDepth: " + maxDepth);
		for(int i=1; i<101; i++) {
			if(contactDepth[i] == maxDepth) {
				maxCalledNum = Math.max(maxCalledNum, i);
//				System.out.println("@maxCalledNum: " + maxCalledNum);
			}
		}
	}

}
