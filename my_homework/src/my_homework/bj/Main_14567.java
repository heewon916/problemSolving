package my_homework.bj;
import java.io.*; 
import java.util.*; 

public class Main_14567 {
	static int N, M; 
	static List<Integer>[] graph; 
	static int[] indegrees; 
	static void addEdge(int a, int b) {
		graph[a].add(b);
		indegrees[b]++; 
	}
	static int[] topologicalSort() {
		Queue<Integer> queue = new LinkedList<>(); 
		int[] result = new int[N];
		
		// 1. 진입차수 0인 곳 찾기 
		for(int i=0; i<N; i++) {
			if(indegrees[i] == 0) queue.add(i);
			result[i] = 1; 
		}
		
		// 2. 진입차수가 0인 곳 삭제하고, 연결됐던 곳 indegree--
		while(!queue.isEmpty()) {
			int cur = queue.poll(); 

			for(int next: graph[cur]) {
				indegrees[next]--; // cur의 인접노드들의 진입차수 -1 
				
				result[next] = Math.max(result[next], result[cur]+1); // 현재 값이랑, 다른 데서 계산된 것 중에서 최댓값으로 저장해야 됨. (경로가 여러개일 수 있어서) 
				
				if(indegrees[next] == 0) queue.add(next);
			}
		}
		return result; 
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 

		indegrees = new int[N]; // 진입차수 
		graph = new ArrayList[N];  // 그래프 
		for(int i=0; i<N; i++) {
			graph[i] = new ArrayList<>(); 
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			addEdge(a-1,b-1);
		}
		
		int[] result = topologicalSort(); 
		for(int i=0; i<N; i++)
			System.out.print(result[i] + " ");
	}

}

/**
 * 풀이방식 
 * 1. 처음에는 union-find인 줄 알았음. 근데 위상정렬이라는 게 적용된다는 거 알고 알고리즘 찾아봄.  
 * 2. 생성해야 할 변수들 - 진입차수 저장 배열/ 그래프 배열(배열리스트구현체)
 * 3. 위상정렬 알고리즘 
 * -1) 간선들 그래프에 저장 & 진입차수 계산 
 * -2) 진입차수가 0인 곳 큐에 저장 
 * -3) 큐가 빌 때까지; 0인 곳 pop -> 연결된 노드들의 진입차수--; -> 그렇게 변경된 진입차수 중에서 0인 곳 큐에 다시 push 
 * 
 * 
 * 첫 오류: 메모리 초과 ->:: topo() 함수에서 0인 곳 다시 집어넣을 때, for문 안 돌려도 되는데 for문을 돌려서 q가 터짐. 
 */
