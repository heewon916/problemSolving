# 위상정렬 


> 💡관련문제 
> - 백준 14567 선수과목 
## BFS로 구현하기 
```java
/**
 * 위상정렬: 선수과목 문제 
 * 1번과 2번 과목을 들어야만 3번 과목을 들을 수 있다. 
 * 가능한 경우의 수 2가지: 
 * 1 2 3 
 * 2 1 3 
 * 
 * 진입차수의 메모이제이션 1차원 배열 
 * 1. 진입차수가 0인 녀석부터 큐에 넣는다(bfs). 
 * 2. 연결된 녀석들의 진입차수-1 
 * 3. 이 중에서 진입차수가 0인 녀석들을 다시 큐에 넣고 반복한다. 
 * 4. 큐가 비게 되면 끝. 
 */
public class TopologicalSortBfsList {
	static int V, E; 		// V 정점의 개수 E 간선의 개수 
	static List<Integer>[] g; // 그래프 인접리스트 
	static int[] indegree; 	// 진입차수 개수  
	
	static void bfs() { 	
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i=1; i<V+1; i++) { 	// 1 2 3
//		for(int i=V; i>=1; i--) {	// 2 1 3
			if(indegree[i] == 0) q.offer(i); 
		}

		while(!q.isEmpty()) {
			int i = q.poll();
			System.out.print(i+" ");
			
			for(int j: g[i]) { 	// 인접한 노드들의 
				// indegree[j]--;	// 진입차수--; 
				if(--indegree[j] == 0) q.offer(j); 
			}
		}
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt(); 
		E = sc.nextInt(); 
		g = new List[V+1]; for(int i=0; i<V+1; i++) g[i] = new ArrayList<>(); 
		indegree = new int[V+1];
		
		for(int i=0; i<E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt(); 
			g[from].add(to);
			indegree[to]++;			// 진입차수 증가 
		}
		
		bfs(); 
		sc.close();
		
	}

}

/*
주어지는 입력정보: 
3 2
1 3
2 3

그래프로 나타낸다면: 
 1
  \
2--3

인접행렬로 나타낸다면: 
  1 2 3
1 . . 1
2 . . 1
3 . . .
 */
```

## DFS로 구현하기 
```java
/**
 * 위상정렬: 선수과목 문제 
 * 1번과 2번 과목을 들어야만 3번 과목을 들을 수 있다. 
 * 가능한 경우의 수 2가지: 
 * 1 2 3 
 * 2 1 3 
 * 
 * 위상정렬 dfs로 구현할 때: 
 * 순서가: 2 3 1 4 5 일 때 
 * 1 2 3 4 5 이렇게 배열이 있다고 한다면 
 * 
 * 방문하지 않은 1번 정점부터 연결된 정점으로 이동 
 * -> 4 방문 -> 5 방문 => 더 이상 갈 곳 없음 => stack에 push ( 5-> 4-> 1 순으로 push) 
 * 
 * 다 된 경우, 방문 안한 정점 중부터 다시 dfs 
 * 2 방문 -> 3 방문 => 연결된 정점 중 방문 안 한 정점 없음. => stack push (3 -> 2순으로 push) 
 * 
 * 스택 결과: (top) 2 3 1 4 5 (맨 밑) 
 * -> pop하면 지켜야 할 순서가 그대로 나온다. 
 */
public class TopologicalSortDfsList {
	static int V, E; 		// V 정점의 개수 E 간선의 개수 
	static List<Integer>[] g; // 그래프 인접리스트 
	static boolean[] v; 	// 방문 처리 
	static ArrayDeque<Integer> stack = new ArrayDeque<>(); 
	
	static void dfs(int i) { 	
		v[i] = true; 
		for(int j: g[i]) {
			if(!v[j]) dfs(j); 
		}
		stack.push(i); 
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt(); 
		E = sc.nextInt(); 
		g = new List[V+1]; for(int i=0; i<V+1; i++) g[i] = new ArrayList<>(); 
		v = new boolean[V+1]; 
		
		for(int i=0; i<E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt(); 
			g[from].add(to);
		}
		for(int i=1; i<V+1; i++) {
			if(!v[i]) dfs(i);
		}
		while(!stack.isEmpty()) System.out.print(stack.pop() + " ");
		sc.close();
		
	}

}

/*
주어지는 입력정보: 
3 2
1 3
2 3

그래프로 나타낸다면: 
 1
  \
2--3

인접행렬로 나타낸다면: 
  1 2 3
1 . . 1
2 . . 1
3 . . .
 */
```