import java.io.*; 
import java.util.*; 
public class Solution_d4_1251_하나로_서울_8반_김희원_kruskal {
	static int n; 
	static int[] parents; 
	static class Edge implements Comparable<Edge>{
		int x, y; 
		long weight; 
		Edge(int x, int y, long weight) {
			this.x = x; this.y = y; this.weight = weight; 
		}
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight); 
		}
	}
	static void make() {
		parents = new int[n]; 
		for(int i=0; i<n; i++) parents[i] = i; 
	}
	static int find(int a) {
		return parents[a] == a? a: (parents[a] = find(parents[a]));
	} 
	static boolean union(int i, int j) {
		int aroot = find(i); int broot = find(j);
		if(aroot == broot) return false; 
		if(aroot > broot) parents[aroot] = broot;
		else parents[broot] = aroot; 
		return true; 
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0826\\Input1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			// step1. 입력 전처리 
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int[] xs = new int[n]; 
			int[] ys = new int[n]; 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<n; i++) xs[i] = Integer.parseInt(st.nextToken()); 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<n; i++) ys[i] = Integer.parseInt(st.nextToken()); 
			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken());
			
			// step2. g <- (i, j, w) 두 정점 간의 가중치 w 저장 
			List<Edge> g = new ArrayList<>();  // 오름차순 값으로 정렬 
			long w = 0; 
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					w = (long)(xs[i]-xs[j])*(xs[i]-xs[j]) + (long)(ys[i]-ys[j])*(ys[i]-ys[j]);
					g.add(new Edge(i, j, w)); 
//					System.out.println("@add: " + i + "," + j + ", " + w);
				}
			}
			// step3. 크루스칼을 위해 간선 가중치 기준 정렬 
			Collections.sort(g);
			long result = 0; 
			int cnt = 0; 
			make(); 
			
			// step4. 두 정점이 병합 가능하다면 합치고 가중치 증가 & 신장트리가 만들어지면 break 
			for(Edge e: g) {
				if(!union(e.x, e.y)) continue; 
//				System.out.println("@@union: " + e.x + ", " + e.y);
				result += e.weight; 
				if(++cnt == n-1) break; 
			}
			sb.append("#").append(t).append(" ").append(Math.round(result * E)).append("\n"); 
		}
		System.out.println(sb.toString());

	}

}
/*
[입력] 
4
0 0 400 400
0 100 0 100
1.0
----
테스트 개수 
n <- 섬의 개수 
xs
ys
E <- 계수  
제한시간 20초 
N ~ 10^3
X, Y ~ 10^6 
int 4byte 
long 8byte 

[이해] 
N개의 모든 섬을 연결 

환경 부담세율 E, 해저터널 길이 L 
환경 부담금 = E * L * L 

총 환경 부담금 최소로 지불 

[해결]
결국 해저터널의 길이가 최소여야 하는 것
각 섬들 간의 거리L을 미리 계산한다면 
- N * N = 10^6 

L이 결국 가중치가 되고, L의 합, 결국 가중치의 합이 최소인 경로를 찾는 것 
정점 중심의 최소 가중치 합 

[설계] - 
int[] xs = new int[N]; 
int[] ys = new int[N]; 
for(int i=0; i<N; i++)
	xs[i] = Integer.parseInt(st.nextToken()); 

xs, ys에서 인덱스 0을 하나의 정점으로 보겠다. 
g에 {i, j, w}를 저장 -> i와 j는 정수, w는 실수 => 공존 안됨 


static class Edge implements Comparable<Edge>{
	int x, y; 
	double weight; 
	static Edge(int x, int y, double weight)
		this.x = x; this.y = y; this.weight = weight; 
	public int compareTo(Edge o)
		return Integer.compare(this.weight, o.weight); 
}

List<Edge> g = new ArrayList<>();  // 오름차순 값으로 정렬 
double w = 0; 
for(int i=0; i<n; i++) 
	for(int j=i; j<n; j++) 
		w = Math.sqrt((xs[i]-xs[j])*(xs[i]-xs[j]) + (ys[i]-ys[j])*(ys[i]-ys[j]));
		g.add(new Edge(i, j, w)); 
Collections.sort(g);		// 가중치 기준으로 정렬하고 

for(int i=0; i< g.size(); i++) 
	if(!union(g[i].from, g[i].to)) continue; 
	result += g[i].weight; 
	
*/