# 프림 알고리즘 

1. 그리디가 사용되는 
2. 정점 중심 
3. 가중치가 다른 그래프의 최소 비용 알고리즘 
  
> [!important]
>
>- **하나의 정점에 연결**되어 있고 **트리에 포함 안된 간선들** 중에서  (비트리 정점들)
>    - 트리에 포함 안된 간선들 중에서 고르기 때문에 사이클이 발생하지 않는다.
>- **최소 비용의 간선**을 선택한다.
>- 위의 2과정을 반복하며
>- **모든 정점이 선택될 때까지** 최소 신장 트리를 만든다.

어찌보면 서로소가 2개인 집합을 유지한다. 

- union-find를 썼다는 얘기는 아니고,
- 트리에 포함된 정점과 트리에 아직 속하지 않은 정점들 2개의 집합으로 나뉜다는 뜻이다.
- 비트리 정점이 공집합이 되면 끝

시작정점 제외 

다른 정점으로 가는 간선의 길이를 Integer.MAX_VALUE로 설정해두고 시작정점 기준으로 인접한 정점에 대해서 간선 길이를 업데이트한다.

포인트는, **트리 자체에 인접한 정점 중 가장 최소 가중치를 갖는 간선을 선택**하는 것이다. 
- 트리 내부에 특정 정점과 인접한 정점에 대해서만 확장할 수 있는 것이 아니다.

> [!important]
> ### 슈도 코드
>
> G: 그래프 
> 
> start: 시작 정점 
> 
> minEdge[]: 각 정점 기준으로 타 정점과의 최소 간선 비용 
> 
> - `minEdge[start] = 0` 이고 다른 정점들은 Integer.MAX_VALUE로 초기화되어 있다 (왜냐하면 시작 정점 외에 다른 정점은 아직 트리에 포함이 안 되었으므로 무한대 거리를 갖는다)
> - **mst 트리와 각 비트리정점과의 거리 중**에서, 최솟값이 누적된다.
>
> 이후, 시작정점과 인접한 정점들은 그 간선 값으로 갱신된다. 그 중에서 가장 최소 가중치를 갖는 정점이 트리에 포함된다. 
> 
> ---
> prim(G, start): 
> 
> 1. result ← MST 비용, cnt ← 처리한 정점 수 
> 2. visited[] ← MST에 포함된 정점 여부 (false로 초기화되어있다.) 
> 3. 전처리 
>     1. 모든 정점에 대해서 minEdge[u] = Integer.MAX_VALUE; 로 초기화해준다 
>     2. 그 중에서 시작 정점과 트리와의 거리는 0이므로, minEdge[start] = 0; 으로 초기화해준다 
> 4. 모든 정점을 포함할 때까지 아래 과정을 반복한다.
>     1. **step1 최소 간선비용을 가진 정점 찾고 트리에 포함시키기** 
>         1. u ← mst에 포함 안된 (방문 안된) 최소 간선 비용을 가진 정점을 찾는다. 
>         2. u는 방문처리 = MST에 포함시킨다. 
>         3. 포함시킨 u의 간선 값 누적 result += minEdge[u]  
>     2. **step2 종료조건** 
>         1. 만약에 ++cnt 가 N개가 되면 모두 포함시킨 것이므로 break 
>     3. **step3 아직 모두 포함이 안 됐다면** 
>         1. u의 인접 정점 중에서 (v)
>             1. **방문하지 않았고, u→v 비용이 minEdge[v] 보다 작다면 
>            (트리에서 v로 갈 수 있는 값보다 u에서 v로 가는 게 더 쉽다면)** 
>                 1. 트리에서 v로 가는 간선 비용 갱신; minEdge[v] = u→v 비용으로 갱신


prim[]
인덱스는 정점 번호
- 각 정점을 MST에 포함시키는 최소 가중치가 최종적으로 들어있다.
prim[i] = 0; i부터 시작정점

1. minVertex, min을 찾는다. prim[] 기반으로 최소 비용에 해당되는 정점을 찾는다.
   - 방문하지 않은 정점 중에서만 minVertex를 찾는다.
2. 방문 처리한 뒤에는, 순환이 알아서 사라진다. (사이클)
   - v와 mst를 갱신한다.
3. mst가 다 만들어지지 않았다면, prim[]을 다시 갱신한다.
   - 0번 정점 다 돌고 나면
   - minVertex = 1, min = 5가 된다.
4. 1번 정점 다 돌고 나면
- 이미 mst에 포함된 정점에는 관심 없음.
- minVertex

*/
```java
import java.io.*;
import java.util.*;

public class PrimMain {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// step1. 전처리: List<int[]>[] g, boolean[] v, int[] prim
		List<int[]>[] g = new List[N]; 	// 각 정점별 인접한 노드 저장해두는, 인접 리스트 
		for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		boolean[] v = new boolean[N];		// 트리에 포함됐는지 여부 
		int[] prim = new int[N]; 				// 프림 ~= minEdge
		Arrays.fill(prim, Integer.MAX_VALUE);		// prim을 모두 무한대 값으로 초기화한다. 
		for(int i=0; i<N; i++) {	
			for(int j=0; j<N; j++) {
				int c = sc.nextInt();
				if(c == 0) continue; 				// 자기 자신에 대한 가중치는 생략
				g[i].add(new int[] {j, c}); // i->j: (j, 비용)
			}
		}

		// step2. 
		int mst = 0, cnt = 0;		// mst: 트리 간선 비용, cnt: 트리에 포함된 정점 개수 -> break 조건에 해당한다 
		prim[0] = 0; 						// 0번 정점부터 출발
		for(int i=0; i<N; i++) {
			// step3. 최소 간선비용을 가진 정점 찾고 트리에 포함시키기
			int minVertex = -1;
			int min = Integer.MAX_VALUE; 	
			for(int j=0; j<N; j++) { 			// 방문 안 한 && 트리에 인접한 정점 중에서 가장 최소 비용을 가진 정점 찾기 
				if(!v[j] && min > prim[j]) {
							minVertex = j;
							min = prim[j];
				}
			}
			v[minVertex] = true; 					// 트리에 포함시킨다. -> 방문 처리, 비용 누적 
			mst += min;
			// step4. mst가 완성됐으면 끝
			if(cnt++ == N-1) break;			
			// step5. 방금 막 포함된 정점 minVertex에 인접한 정점 중에서, 
			// 방문 안 했고 && 트리가 보는 시점보다 인접함으로써 얻는 간선 비용이 더 적으면 갱신 필요함. 
			for(int[] jc: g[minVertex]) {	
				if(!v[jc[0]] && prim[jc[0]] > jc[1]) {
								prim[jc[0]] = jc[1];
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
