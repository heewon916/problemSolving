# 목차

- [목차](#목차)
- [크루스칼 vs. 프림 vs. 다익스트라](#크루스칼-vs-프림-vs-다익스트라)
- [크루스칼](#크루스칼)
	- [최소 신장 트리](#최소-신장-트리)
	- [신장 트리](#신장-트리)
- [크루스칼 알고리즘 ~ 간선 중심](#크루스칼-알고리즘--간선-중심)
		- [📌 크루스칼 알고리즘](#-크루스칼-알고리즘)

# 크루스칼 vs. 프림 vs. 다익스트라

> [!important]
> 그래프 → V, E
>
> ✅ 최소 신장트리를 구하는데 (**트리 전체 기준으로** 최소 비용의 인접한 간선을 택한다)
>
> - 정점 중심으로 풀어가는 알고리즘 ⇒ 📌프림
>   - 모든 정점을 포함하는 최소 신장 트리(MST)
>   - 연결리스트 + PQ : `O(E logV)`
>   - 연결리스트만 사용: `O(V^2)`
> - 간선 중심으로 풀어가는 알고리즘 ⇒ 📌크루스칼
>   - 간선리스트 + union-find: `O(E log E)`
>
> ✅ 시작 정점 **start 기준으로** 모든 정점에 갈 수 있는 최소비용 경로 구하기
>
> - 📌다익스트라
>   - 목표: 출발 정점 → 모든 정점 최단 거리

# 크루스칼

## 최소 신장 트리

그래프에서 최소 비용 문제를 해결한다

- **모든 정점을 연결하는 + 간선들의 가중치의 합이 최소가 되는 트리**
- 두 정점 사이의 최소 비용 경로 찾기
  - 가중치가 1가지(모두 동일한 값) : BFS
  - **가중치가 2가지 이상인 경우: BFS는 비효율적**
    ⇒ 잘 알려진 최단경로 알고리즘 - 다익스트라 - 벨만포드 - 플로이드 워샬

## 신장 트리

- n개의 정점으로 이루어진 **무향** 그래프에서 **n개의 정점 + n-1개의 간선**으로 이루어진 트리
  > **❓왜 모든 정점 쌍의 경로가 존재한다는 게 보장될까?**
  >
  > - 트리 특성 상, 조상 노드를 계속 타고 올라가다 보면, 결국 어떤 정점이든 방문할 수 있기 때문이다.
  > - 그래프 O(V, E) ⇒ 트리 O(V, V-1)

이러한 신장트리 중에서도 가중치의 합이 최소가 되는 트리를 최소 신장 트리라고 한다.

> **❓최소 신장 트리를 완탐으로 하게 된다면?**
>
> 즉, 신장트리 1, 2,,… 여러 가지를 만들어보고, 그 중에서 가중치의 합이 최소인 트리를 갖는 건?
>
> - v개의 정점. e개의 간선이 있다고 할 때 e개의 간선 중에서 v-1개의 간선을 선택해야 한다.
> - `eCv-1`번의 연산 ⇒ 신장트리가 가능하다면 ⇒ 최솟값 업데이트
> - `30C15` → 1억 5천만번의 연산을 해야 한다.
> - 따라서 이런 연산 방법은 어렵다.

# 크루스칼 알고리즘 ~ 간선 중심

> [!note]
> ❓ eC(v-1)에서 조합C는 생각하지 않고, 선택 느낌은 살려 구한다면 어떻게 가능할까?
>
> → 그리디, Greedy로 풀어낼 수 있다.
>
> → 전체 E개의 간선 중에서 **최적의 1개의 간선**을 선택하면 남은 간선의 개수는 E-1개이다.
>
> → 남아있는 것들 중에서 **최적의 1개의 간선**을 선택하면, 남는 간선의 개수는 E-2개가 된다.
>
> → 이렇게 **간선을 V-1개 선택**하면 끝이다.
>
> ❓이게 최소비용이 보장되려면?
>
> ⇒ ✅ **전처리: 간선 리스트 가중치 기준으로 오름차순 정렬해두면 된다.**
>
> ❓여기서 신장 트리라는 조건까지 만족하려면?
>
> ⇒ ✅ **union/find 연산을 이용해 신장트리 조건을 만족하는지 체크하면 된다.**

위의 2가지 ✅를 만족하는지 찾는 것이 크루스칼 알고리즘이다.

### 📌 크루스칼 알고리즘

> [!important]
>
> ⭐ 구현 팁:
>
> - `List<int[]> edgeList = new ArrayList<>()` : 간선리스트로, 모든 간선을 한 바구니에 넣는다.
> - `int[] parents` : 각 정점의 부모 노드를 가리킨다.
> - `make()`: parents 배열 초기화
> - `union(int a, int b)`: a와 b의 root를 find로 찾고, 같으면 `false`, 다르면 갱신 후 `true`반환
> - `find(int a)`: a의 부모 노드 찾기 `return parents[a] == a? a: (parents[a] = find(parents[a]))`
> -
>
> 1️⃣최초 모든 간선을 **가중치 기준 오름차순**으로 정렬
>
> 2️⃣가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시킴
> 모든 간선에 대해 `for문`
>
> - `union`을 시도했을 때, `false`를 리턴하면 `continue` (사이클이 존재)
> - `true`를 리턴한 경우, 병합
>   - 두 정점 사이의 간선 **가중치**를 누적합하고
>   - `cnt++`
> - 만약 cnt가 `N-1`이 되면 break
>
> 3️⃣n-1개의 간선이 선택될 때까지 2번을 반복한다.

```java
public class KruskalMain {
	static int N; 		// 정점 개수
	static int[][] g; 	// 간선 정보 배열 - int[]가 edge정보, int[][]는 곧 edge의 1차원 배열: cost도(가중치) 담는다.

	/*==== ssafy_live 간선 클래스 선언 ====*/
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight){
			super();
			this.from = from; this.to = to; this.weight = weight;
		}

		public int compareTo(Edge o){
			// 10 - 20 : 음수 -> 뒤 값이 크다: 그럼 그대로 위치 (교환 안 일어남)
			// 20 - 10 : 양수 -> 앞 값이 크다: 교환
			// 양수 - 음수: 오버플로, 음수 - 양수: 언더플로우가 발생할 수 있다.
			// 리턴타입 int는 양수냐, 음수냐가 중요할 뿐, 값 자체는 중요하지 않다.
			return Integer.compare(this.weight, o.weight); ; 	// 가중치 기준 오름차순 정렬 되도록 비교 결과 리턴
		}
	}
	static Edge[] edgeList;
	/*==== END ====*/

	/*========union-find========*/
	/*step1. p[i] = 짱의 인덱스 번호*/
	static int[] parents; 		// 정점의 짱을 저장한다.
	static void make() {	// 정점 초기화
		// parents = new int[N];
		for(int i=0; i<N; i++) parents[i] = i;
	}

	/*step2. 나의 보스(루트노드)를 리턴한다. */
	static int find(int a) {
		if(a == parents[a]) return a; 	// 내가 대장(루트)
		return parents[a] = find(p[a]); 	// 아닌 경우 현재 a의 부모를 리턴한다. - 호출과 동시에 업데이트해줘야한다.
	}

	/*step3. 병합하기*/
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false; 	// union 불가능 - 같은 조직(그래프)에 속함
		// parents[bRoot] = aRoot; 			// 통합 - 알파벳 기준, 앞쪽에 있는 알파벳에 통합시킨다.

		// 랭크 관리는 아님 !! 한쪽으로 치우치는 걸 방지하기 위한 요소
		if(aRoot > bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;

		return true; 						// union 가능 - 서로 다른 조직
	}
	/*========END========*/

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// step1. 전처리: 입력 받기
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parents = new int[V];
		edgeList = new Edge[E];

		for(int i=0; i<E; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
		}

		// step2. 간선 오름차순 정렬
		Arrays.sort(edgeList);
		make();
		int result = 0; 	// 최소신장트리 비용
		int cnt = 0; 		// 처리된 간선 수


		// step3. 간선 중심으로, from과 to가 합쳐질 수 있는지 확인하고
		// 가능하다면, 가중치에 합 누적, 간선 개수+1
		// 만약 간선 개수가 V-1개가 되면 끝.
		for(Edge e: edgeList){
			if(!union(e.from, e.to)) continue; 		// union 실패: 사이클 발생
			result += e.weight;
			cnt += 1;
			if(cnt == V-1) break;
		}

		System.out.println(result);

		/*=== 담당 강사님 코드=====*/
		/*
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();

		// g = new int[E][3];
		g = new Edge[E]; (from, to, cost)
		for(int i=0; i<E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();

			// g[i] = new int[] {from, to, cost}; // g[i] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};

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
		*/
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
