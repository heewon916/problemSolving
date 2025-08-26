# 프림

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
		List<int[]>[] g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		boolean[] v = new boolean[N];
		int[] prim = new int[N]; // 프림 ~= minEdge
		Arrays.fill(prim, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int c = sc.nextInt();
				if(c == 0) continue; 		// 자기 자신에 대한 가중치는 생략
				g[i].add(new int[] {j, c}); // i->j: j, 비용
			}
		}
		int mst = 0, cnt = 0;
		prim[0] = 0; 						// 0번 정점부터 출발
		for(int i=0; i<N; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE; 	// 제일 작은 비용 찾기
											// prim에서 최소비용에 해당하는 값min, 정점 찾아내고
			for(int j=0; j<N; j++) { 		// 모든 정점을 다 돌면서, 최소 비용에 해당하는 정점번호와 비용을 찾는다.
				if(!v[j] && min > prim[j]) {
							minVertex = j;
							min = prim[j];
				}
			}
			v[minVertex] = true; 			// 그 곳을 v에 사용했다고 말하고, mst에 비용 추가하고
			mst += min;
			if(cnt++ == N-1) break;			// mst가 완성됐으면 끝
			for(int[] jc: g[minVertex]) {	// 완성이 덜 됐으면 prim 다시 갱신하러 감
											// minVertex와 연결된 정점 중 - 방문 안함 && prim값보다 내 cost가 더 작을 때 prim값갱신
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
