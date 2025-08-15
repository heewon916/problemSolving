# 크루스칼 

```java
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