# 그래프 

## 인접 행렬 
```java
public class GraphMatrixMain{
	static int N;
	static int[][] g;// 인접행렬
	static boolean[] v;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 7 입력 들어옴
		int E = sc.nextInt(); // 8 
		
		g = new int[N][N];  // 인접행렬 
		v = new boolean[N]; // 방문 처리 
		
		for(int i=0; i<E; i++) {
			int from = sc.nextInt(); 
			int to = sc.nextInt(); 
			g[from][to] = 1; 
			g[to][from] = 1; 
		}
		// for(int[] a: g) System.out.println(Arrays.toString(a));
//		dfs(0); 
		bfs(0); 
		sc.close(); 
	}
	static void dfs(int i) { // i축에서 j축으로 인접해서 나가는 방향 
		v[i] = true; 
		System.out.print((char)(i+'A') + " "); 
		for(int j=0; j<N; j++) { // 앞에서부터 dfs: A B D F E C G 
			if(g[i][j] != 0 && !v[j]) {
				dfs(j); 
			}
		}
//		for(int j=N-1; j>=0; j--) { // 뒤에서부터 dfs: A C E F G D B 
//			if(g[i][j] != 0 && !v[j]) {
//				dfs(j); 
//			}
//		}
	}
	static void bfs(int i) { // i축에서 j축으로 인접해서 나가는 방향 
		ArrayDeque<Integer> q = new ArrayDeque<>();
		v[i] = true; 
		q.offer(i);
		while(!q.isEmpty()) { 
			i = q.poll(); 
			System.out.print((char)(i+'A') + " "); 
//			for(int j=0; j<N; j++) { //  A B C D E F G 
			for(int j=N-1; j>=0; j--) { // A C B E D F G 
				if(g[i][j] != 0 && !v[j]) {
					v[j] = true; 
					q.offer(j);
				}
			}
			
		}
	}
}
/* 무방향 그래프 
....A0
.../.\
..B1.C2
./.\./
D3..E4
..\.|
...F5-G6

7
8
0 1
0 2	
1 3
1 4
2 4
3 5
4 5
5 6

x 0 1 2 3 4 5 6
0 0 1 1 . . . .
1 1 0 . 1 1 . .
2 1 . 0 . 1 . .
3 . 1 . 0 . 1 .
4 . 1 1 . 0 1 .
5 . . . 1 1 0 1
6 . . . . . 1 0

=dfs=========
A B D F E C G : 0->N
A C E F G D B : N->0
=bfs=========
A B C D E F G : 0->N
A C B E D F G : N->0
*/
```
## 인접 리스트 
```java
public class GraphListMain{
	static int N;
	static List<Integer>[] g; // 인접 리스트 
	static boolean[] v;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 7 입력 들어옴
		int E = sc.nextInt(); // 8 
		
		g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		v = new boolean[N]; // 방문 처리 
		
		for(int i=0; i<E; i++) {
			int from = sc.nextInt(); 
			int to = sc.nextInt(); 
			g[from].add(to); 
			g[to].add(from); 
		}
		
		for(List<Integer> a: g) System.out.println(a); System.out.println();
//		dfs(0); 
//		bfs(0); 
		sc.close(); 
	}
	static void dfs(int i) { // i축에서 j축으로 인접해서 나가는 방향 
		v[i] = true; 
		System.out.print((char)(i+'A') + " "); 
		for(int j:g[i]) {
			if(!v[j])
			dfs(j);
		}
	}
	static void bfs(int i) { // i축에서 j축으로 인접해서 나가는 방향 
		ArrayDeque<Integer> q = new ArrayDeque<>();
		v[i] = true; 
		q.offer(i);
		while(!q.isEmpty()) {
			i = q.poll();
			System.out.print((char)(i+'A')+" ");
			for(int j: g[i]) {
//			for(int j=N-1;j>=0;j--) {
				if(!v[j]) {
					v[j]=true;
					q.offer(j);
				}
			}
		}
	}
}
/* 
 * 모두 인접해 있으면, 인접행렬이 낫고, 
 * 그렇지 않으면, 인접리스트가 낫다
....A0
.../.\
..B1.C2
./.\./
D3..E4
..\.|
...F5-G6

// 노드는 7개, 간선 정보는 8개 



=dfs=========
A B D F E C G : 0->N
A C E F G D B : N->0
=bfs=========
A B C D E F G : 0->N
A C B E D F G : N->0

0: [1, 2]
1: [0, 3, 4]
2: [0, 4]
3: [1, 5]
4: [1, 2, 5]
5: [3, 4, 6]
6: [5]
*/
```