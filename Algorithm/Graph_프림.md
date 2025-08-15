# 프림 

```java
// 기본코드 
public class KruskalMain {	
	static int N; 		// 정점 개수 
	static int[][] g; 	// 간선 정보 배열 - int[]가 edge정보, int[][]는 곧 edge의 1차원 배열: cost도(가중치) 담는다.
	
	/*========union-find========*/
	/*step1. p[i] = 짱의 인덱스 번호*/
	static int[] p; 		// 정점의 짱을 저장한다. 
	static void make() {	// 정점 초기화 
		p = new int[N]; 
		for(int i=0; i<N; i++) p[i] = i; 
	}

	/*step2. 나의 보스(루트노드)를 리턴한다. */
	static int find(int a) {
		if(p[a] == a) return a; 	// 내가 대장(루트)
		return p[a] = find(p[a]); 	// 아닌 경우 현재 a의 부모를 리턴한다. - 호출과 동시에 업데이트해줘야한다. 
	}

	/*step3. 병합하기*/
	static boolean union(int a, int b) {
		int aRoot = find(a); 
		int bRoot = find(b); 
		if(aRoot == bRoot) return false; 	// union 불가능 - 같은 조직(그래프)에 속함
		p[bRoot] = aRoot; 					// 통합 - 알파벳 기준, 앞쪽에 있는 알파벳에 통합시킨다. 
		return true; 						// union 가능 - 서로 다른 조직
	}
	/*========END========*/
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt(); 
		
		g = new int[E][3]; 	// g = new Edge[E]; (from, to, cost)
		for(int i=0; i<E; i++) {
			int from = sc.nextInt(); 
			int to = sc.nextInt();
			int cost = sc.nextInt(); 
			
			g[i] = new int[] {from, to, cost}; // g[i] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
			
		}
		Arrays.sort(g, (o1, o2)->Integer.compare(o1[2], o2[2])); // cost 비교; 오름차순 정렬 
		make(); 
		int mst = 0, cnt = 0; 	// mst: 가중치의 합, cnt: 선택된 간선의 개수(정점이 N개일때, MST를 위해서는 N-1개) 
		// System.out.println("p = " + Arrays.toString(p)); 		// 따로 추가한 디버깅 코드 
		for(int[] edge: g) {
			// System.out.println("p = " + Arrays.toString(p));	// 따로 추가한 디버깅 코드 
			if(union(edge[0], edge[1])) { 	// from, to 선택 후 통합 
				mst += edge[2]; 
				cnt++; 
			}
			if(cnt == N-1) break; 
		}
		
		// System.out.println("p = " + Arrays.toString(p));	// 따로 추가한 디버깅 코드 
		System.out.println(mst);
		sc.close(); 
	}
}
/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6 
2 3 1
2 4 3
3 4 1

output==>10
=== 
디버깅 코드 
p = [0, 1, 2, 3, 4]
p = [0, 1, 2, 3, 4]
p = [0, 1, 2, 2, 4]
p = [0, 1, 2, 2, 2]
p = [0, 1, 1, 2, 2]
p = [0, 1, 1, 2, 1]
p = [0, 0, 1, 2, 1]
*/
```

Matrix 
```java
public class PrimMatrix {
	/*
	 * prim[]
	 * - 인덱스는 정점 번호 
	 * - 각 정점을 MST에 포함시키는 최소 가중치가 최종적으로 들어있다. 
	 * prim[i] = 0; i부터 시작정점 
	 *
	 *
	 * 1. minVertex, min을 찾는다.prim[] 기반으로 최소 비용에 해당되는 정점을 찾는다. 
	 * - 방문하지 않은 정점 중에서만
	 * - minVertex를 찾는다. 
	 * 2. 방문 처리한 뒤에는, 순환이 알아서 사라진다. (사이클) 
	 * - v와 mst를 갱신한다.
	 * 3. mst가 다 만들어지지 않았다면, prim[]을 다시 갱신한다. 
	 * 
	 * 0번 정점 다 돌고 나면 
	 * - minVertex = 1, min = 5가 된다. 
	 * 1번 정점 다 돌고 나면 
	 * - 이미 mst에 포함된 정점에는 관심 없음. 
	 * - minVertex 
	 */
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 
		int[][] g = new int[N][N]; 
		boolean[] v = new boolean[N];
		int[] prim = new int[N]; 
		Arrays.fill(prim, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int c = sc.nextInt(); 
				if(c == 0) continue; 
				g[i][j] = sc.nextInt();
			}
		}
		int mst = 0, count = 0; 
		prim[0] = 0; 								// 0번 정점부터 출발 
		for(int i=0; i<N; i++) {
			/*step1.*/
			int minVertex = -1; 
			int min = Integer.MAX_VALUE; 			// 제일 작은 비용 찾기 
													// prim에서 최소비용에 해당하는 값min, 정점 찾아내고 
			for(int j=0; j<N; j++) { 				// 모든 정점을 다 돌면서, 최소 비용에 해당하는 정점번호와 비용을 찾는다. 
				if(!v[j] && min > prim[j]) {
							minVertex = j; 
							min = prim[j]; 
				}
			}
						
			v[minVertex] = true; 					// 그 곳을 v에 사용했다고 말하고, mst에 비용 추가하고 
			mst += min; 
			
			System.out.println("minVertex = " + minVertex + " min = " + min + " " + Arrays.toString(v));
			
			
			if(count++ == N-1) break;						// mst가 완성됐으면 끝 
			for(int j=0; j<N; j++) {						// 완성이 덜 됐으면 prim 다시 갱신하러 감 
				if(!v[j] && g[minVertex][j] != 0 			/*matrix여서 바뀐 부분*/
						 && prim[j] > g[minVertex][j]) {
							prim[j] = g[minVertex][j];
				}
			}
			System.out.println("@@@ prim: " + Arrays.toString(prim));
		}
		// 
		System.out.println(mst);
		sc.close(); 
	}

}
/*
정점 중심 가중치
행렬과 리스트 
5
0 5 10 8 7
5 0 5 3 6
10 5 0 1 3
8 3 1 0 1 
7 6 3 1 0 
----
output: 10 
==== 
디버깅 코드 
minVertex = 0 min = 0 [true, false, false, false, false]
@@@ prim: [0, 5, 10, 8, 7]
minVertex = 1 min = 5 [true, true, false, false, false]
@@@ prim: [0, 5, 5, 3, 6]
minVertex = 3 min = 3 [true, true, false, true, false]
@@@ prim: [0, 5, 1, 3, 1]
minVertex = 2 min = 1 [true, true, true, true, false]
@@@ prim: [0, 5, 1, 3, 1]
minVertex = 4 min = 1 [true, true, true, true, true]
 */
```

Matrix + PQ
```java
public class PrimMatrixPqMain {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 
		int[][] g = new int[N][N]; 
		boolean[] v = new boolean[N];
		int[] prim = new int[N]; 
		Arrays.fill(prim, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				g[i][j] = sc.nextInt();
			}
		}

		int mst = 0, count = 0; 
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));
		prim[0] = 0; 								// 0번 정점부터 출발 
		pq.offer(new int[] {0, prim[0]});
		while(!pq.isEmpty()) {
			int[] vc = pq.poll();
			int minVertex = vc[0]; 			/*우선순위 큐로 바뀜*/
			int min = vc[1]; 				

			if(v[minVertex]) continue; 		/*방문한 적 있으면*/
			v[minVertex] = true; 			
			mst += min; 					 
			if(count++ == N-1) break;			
			for(int j=0; j<N; j++) {						// 완성이 덜 됐으면 prim 다시 갱신하러 감 
				if(!v[j] && g[minVertex][j] != 0 			/*matrix여서 바뀐 부분*/
						 && prim[j] > g[minVertex][j]) {
							prim[j] = g[minVertex][j];
							pq.offer(new int[] {j, prim[j]});
				}
			}

		}
		
		System.out.println(mst);
		sc.close(); 
	}

}
/*
정점 중심 가중치
행렬과 리스트 
5
0 5 10 8 7
5 0 5 3 6
10 5 0 1 3
8 3 1 0 1 
7 6 3 1 0 
----
output: 10 
==== 
디버깅 코드 
minVertex = 0 min = 0 [true, false, false, false, false]
@@@ prim: [0, 5, 10, 8, 7]
minVertex = 1 min = 5 [true, true, false, false, false]
@@@ prim: [0, 5, 5, 3, 6]
minVertex = 3 min = 3 [true, true, false, true, false]
@@@ prim: [0, 5, 1, 3, 1]
minVertex = 2 min = 1 [true, true, true, true, false]
@@@ prim: [0, 5, 1, 3, 1]
minVertex = 4 min = 1 [true, true, true, true, true]
 */
```

List + PQ 
```java
public class PrimListPqMain {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 
		List<int[]>[] g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>(); 
		boolean[] v = new boolean[N];
		int[] prim = new int[N]; 		
		Arrays.fill(prim, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int c = sc.nextInt(); 
				if(c == 0) continue; 		
				g[i].add(new int[] {j, c}); 
			}
		}
		int mst = 0, cnt = 0; 				/*최소힙으로 만들어보자*/
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));	/* 비용이 적은 순으로 오름차순 정렬됨. */
		prim[0] = 0; 						
		pq.offer(new int[] {0, prim[0]});	/*변경*/
		while(!pq.isEmpty()) {
			int[] vc = pq.poll();
			int minVertex = vc[0]; 			/*우선순위 큐로 바뀜*/
			int min = vc[1]; 				

			if(v[minVertex]) continue; 		/*방문한 적 있으면*/
			v[minVertex] = true; 			
			mst += min; 					 
			if(cnt++ == N-1) break;			
			
			for(int[] jc: g[minVertex]) {	
											
				if(!v[jc[0]] && prim[jc[0]] > jc[1]) {
								prim[jc[0]] = jc[1];
								pq.offer(new int[] {jc[0], prim[jc[0]]});	/*변경됨*/
				}
			}
		}
		System.out.println(mst);
		sc.close(); 
	}

}
/*
정점 중심 가중치
행렬과 리스트 
5
0 5 10 8 7
5 0 5 3 6
10 5 0 1 3
8 3 1 0 1 
7 6 3 1 0 
----
output: 10 
==== 
디버깅 코드 
@line55:System.out.println("minVertex = " + minVertex + " min = " + min + " " + Arrays.toString(v));
@line61: System.out.println("@@@ prim: " + Arrays.toString(prim));
minVertex = 0 min = 0 [true, false, false, false, false]
@@@ prim: [0, 5, 10, 8, 7]
minVertex = 1 min = 5 [true, true, false, false, false]
@@@ prim: [0, 5, 5, 3, 6]
minVertex = 3 min = 3 [true, true, false, true, false]
@@@ prim: [0, 5, 1, 3, 1]
minVertex = 2 min = 1 [true, true, true, true, false]
@@@ prim: [0, 5, 1, 3, 1]
minVertex = 4 min = 1 [true, true, true, true, true]
 */
```