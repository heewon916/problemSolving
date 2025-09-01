# 목차

- [목차](#목차)
- [다익스트라](#다익스트라)
	- [구현 1. 인접 리스트](#구현-1-인접-리스트)
	- [구현 2. 인접 행렬](#구현-2-인접-행렬)
	- [구현 3. 인접 행렬 + 우선순위 큐](#구현-3-인접-행렬--우선순위-큐)
	- [구현 4. 인접리스트 + 우선순위 큐](#구현-4-인접리스트--우선순위-큐)

# 다익스트라

> [!note]
> 📌 최단경로란
> - 간선의 가중치가 있는 그래프에서 두 정점 사이의 경로들 중에 간선의 가중치의 합이 최소인 경로 
>
> ✅ 하나의 시작 정점에서 끝 정점까지의 최단 경로 
> - 가중치가 1가지 -> **BFS**
> - 가중치가 n가지 
> 	- 음의 가중치 없음 => **다익스트라** :: 정줌 중심으로 풀어나감 
> 	- 음의 가중치 있음 => **벨만포드** :: 간선 중심으로 풀어나감 
>
> ✅ 모든 정점들에 대한 최단 경로 
> - **플로이드 워샬** 알고리즘 

> [!note]
> ✅ 정의
>
> - **시작정점`s`가 고정**되어 있을 때,
> - 그 정점에서 **모든 다른 정점까지의 최단 경로**를 구하는 알고리즘이다.
> - ⭐핵심: `dist[v]` = `시작정점 s에서 v까지의 최소 비용`
>
> 1.  가중치가 양수인 그래프
> 2.  그리디 + **우선순위 큐(힙)**
> 3.  **BFS처럼 한 칸씩 퍼져나감 + 누적비용(가중치 합)이 최소인 경로**를 찾는다.

> [!tip]
> ⭐ 구현 팁: 인접리스트 + PQ 사용
>
> - `int[] dist` 시작 정점에서 각 정점까지의 최단 거리 갱신 배열
> - `PriorityQueue<int[]> pq <- new int[]{i, dist[i]}`
> - `List<int[]>[] g` 인접리스트
>   - 정점 u의 인접정점 v와 가중치를 저장한다
>   - `g[u]` <- `{v, w}`
> - `boolean[] v` 각 정점을 방문했는지 안 했는지 체크한다 
>

> [!important]
> ✅ 풀이 순서 
> - 시작 정점 s 기준으로 `dist[s] = 0; pq.add(new int[]{s, dist[s]});` 
> - pq가 빌 때까지 `cur <- pq.poll()` 반복 
> 	- `minVertex`와 `min` 값 갱신 
>  	- 만약 이미 방문한 정점이면 `continue`
> 	- 아닌 경우, 방문 처리 
> 	- 만약 모든 정점을 포함했으면 `break`
> 	- `minVertex`의 인접 정점 `int[] jc`에 대해서 
> 		- 방문 안 했고 && `dist[jc[0]] > (min + jc[1])`이면 
> 			- `dist[jc[0]] = min + jc[1];`
> 			- `pq.add(new int[] {jc[0], dist[jc[0]]})` 


## 구현 1. 인접 리스트

```java
package a0808.subset;
import java.io.*;
import java.util.*;

public class DijkstraMain {
	/*
	 * 다익스트라: 하나의 정점에서, 각 정점으로 가는 최소비용을 구하기
	 * - 가중치에 음수가 없어야 한다.
	 */
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<int[]>[] g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		boolean[] v = new boolean[N];
		int[] dist = new int[N];			// 다익스트라: 출발정점A -> 각 정점까지의 최소비용을 기억한다. <-> prim: 해당 정점을 MST 조직에 넣는데 필요한 최소비용을 기억한다.
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int c = sc.nextInt();
				if(c == 0) continue;
				g[i].add(new int[] {j, c});
			}
		}
		/*** 1. dist배열 초기화 ***/
		dist[0] = 0; 						// 출발정점 A = 0이라고 하자. /*실제로는 start*/
		for(int i=0; i<N; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE; 	// 0번 정점 -> 각 정점으로 갈 때 드는 최소비용 저장

			for(int j=0; j<N; j++) {
				if(!v[j] && min > dist[j]) {
							minVertex = j;
							min = dist[j];
				}
			}
			v[minVertex] = true;
			if(minVertex == N-1) break;		/*N-1은 실제로 도착정점(E) 의미*/
			for(int[] jc: g[minVertex]) {
											/*변경사항: 출발정점(0)->minVertex까지의 비용 + minVertex->j까지의 비용 (경유지를 경유해서 가는 값이 더 적으면 갱신)*/
				if(!v[jc[0]] && dist[jc[0]] > min + jc[1]) {
								dist[jc[0]] = min + jc[1];
				}
			}
		}
		System.out.println(dist[N-1]);		 // 0->N-1정점으로 가는 최소비용 /*실제로는 dist[End]*/
		sc.close();
	}

}
/*
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

8
     9
0 ------> 4 : 직항
  2    6
0 -> 2 -> 4 : 경우
 */
```

## 구현 2. 인접 행렬

```java
package a0808.subset;
import java.io.*;
import java.util.*;

public class DijkstraMatrixMain {
	/*
	 * 다익스트라 행렬로 구현
	 * 0번 -> 4번 정점으로 가자
	 */
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				g[i][j] = sc.nextInt();
			}
		}

		dist[0] = 0;
		for(int i=0; i<N; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;

			for(int j=0; j<N; j++) {
				if(!v[j] && min > dist[j]) {
							minVertex = j;
							min = dist[j];
				}
			}

			v[minVertex] = true;
			if(minVertex == N-1) break;
			for(int j=0; j<N; j++) {
				if(!v[j] && g[minVertex][j] != 0
						 && dist[j] > min + g[minVertex][j]) {
							dist[j] = min + g[minVertex][j];
				}
			}
		}

		System.out.println(dist[N-1]);
		System.out.println(Arrays.toString(dist));
		sc.close();
	}

}

/*
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

8
     9
0 ------> 4 : 직항
  2    6
0 -> 2 -> 4 : 경우
 */
```

## 구현 3. 인접 행렬 + 우선순위 큐

```java
package a0808.subset;
import java.io.*;
import java.util.*;

public class DijkstraMatrixPqMain {
	/*
	 * 다익스트라 우선순위 큐로 구현
	 */
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				g[i][j] = sc.nextInt();
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));
		dist[0] = 0; 								// 0번 정점부터 출발
		pq.offer(new int[] {0, dist[0]});
		while(!pq.isEmpty()) {
			int[] vc = pq.poll();
			int minVertex = vc[0]; 			/*우선순위 큐로 바뀜*/
			int min = vc[1];

			if(v[minVertex]) continue; 		/*방문한 적 있으면*/
			v[minVertex] = true;

			if(minVertex == N-1) break;
			for(int j=0; j<N; j++) {
				if(!v[j] && g[minVertex][j] != 0 				/*0->minVertex + minVertex->j*/
						 && dist[j] > min + g[minVertex][j]) {
							dist[j] = min + g[minVertex][j];
							pq.offer(new int[] {j, dist[j]});
				}
			}

		}

		System.out.println(dist[N-1]);
		sc.close();
	}

}
/*
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

8
     9
0 ------> 4 : 직항
  2    6
0 -> 2 -> 4 : 경우
 */
```

## 구현 4. 인접리스트 + 우선순위 큐

```java
package a0808.subset;
import java.io.*;
import java.util.*;

public class DijkstraListPqMain {
	/*
	 * 다익스트라: 우선순위 큐로 구현
	 */
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<int[]>[] g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		boolean[] v = new boolean[N];
		int[] dist = new int[N]; 			/*변경: dist*/
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int c = sc.nextInt();
				if(c == 0) continue;
				g[i].add(new int[] {j, c});
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));	/* 비용이 적은 순으로 오름차순 정렬됨. */
		dist[0] = 0;
		pq.offer(new int[] {0, dist[0]});	/*변경*/
		while(!pq.isEmpty()) {
			int[] vc = pq.poll();
			int minVertex = vc[0];
			int min = vc[1];

			if(v[minVertex]) continue;
			v[minVertex] = true;
			if(minVertex == N-1) break;		/*변경*/

			for(int[] jc: g[minVertex]) {

				if(!v[jc[0]] && dist[jc[0]] > min + jc[1]) {	/*변경*/
								dist[jc[0]] = min + jc[1];
								pq.offer(new int[] {jc[0], min + dist[jc[0]]});
				}
			}
		}
		System.out.println(dist[N-1]);
		sc.close();
	}

}
/*
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

8
     9
0 ------> 4 : 직항
  2    6
0 -> 2 -> 4 : 경우
 */
```
