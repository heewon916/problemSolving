import java.io.*; 
import java.util.*; 

public class Solution_d4_3124_최소스패닝트리_서울_8반_김희원 {
	static int[] parents;
	static int v, e; 
	static List<int[]> g; 
	static void make() {
		parents = new int[v+1]; 
		for(int i=0; i<v; i++) parents[i] = i; 
	}
	static int find(int a){
		return parents[a] == a ? a : (parents[a] = find(parents[a]));
	}
	static boolean union(int a, int b){
		int aRoot = find(a); int bRoot = find(b); 
		if(aRoot == bRoot) return false;
		if(aRoot < bRoot) parents[bRoot] = aRoot; 
		else parents[aRoot] = bRoot; 
		return true; 
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for(int t=1; t<=T; t++){
			st = new StringTokenizer(br.readLine(), " ");
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			g = new ArrayList<>(); 
			for(int i=0; i<e; i++){
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				g.add(new int[]{a, b, w});
			}
			Collections.sort(g, (a, b) -> Integer.compare(a[2], b[2]));
			long result = 0; 
			make(); 
			for(int[] a: g){
				if(!union(a[0], a[1])) continue; 
				result += a[2]; 
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

}
/*
[조건] 

[이해] 
그래프의 최소 신장 트리 구하기 
- 모든 정점을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리 

[입력] 
T <- ~10 
V <- ~10^5
E <- 2 * 10^5 

a, b, w a와 b가 w인 가중치 간선으로 연결되어있다는 뜻 
w는 음수일 수도 있고, 절대값은 10^6을 넘지 않는다. 

[설계] 
간선 중심이니까 크루스칼 
v, e <- 정점 개수, 간선 개수 

전역변수 parents int[] 
make()
	parents[i] = i로 초기화 

union(int a, int b) -> boolean 
	int aRoot = find(a); int bRoot = find(b); 
	if(aRoot == bRoot) return false;
	if(aRoot < bRoot) parents[bRoot] = aRoot; 
	else parents[aRoot] = bRoot; 
	return true; 
	
find(int a)
	return parents[a] == a? a: (parents[a] = find(parents[a]));

List<int[]> g = new ArrayList<>(); 

입력 받을때: a, b, w
g,add(new int[]{a, b, w})

Collections.sort(g, (a,b) -> Integer.compare(a[2], b[2]));
for(int[] a: g)
	if(!union(a[0], a[1])) continue; 
	result += a[2]; 
*/