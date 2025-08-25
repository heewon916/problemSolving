import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_d4_3289_서로소집합_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			parents = new int[N+1]; 
			for(int j=1; j<N+1; j++) parents[j] = j; 
			
			sb.append("#").append(t).append(" ");
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cmd == 0) { // union 
					union(a,b);
				}else { // find 
					int find_a = find(a), find_b = find(b); 
					if(find_a == find_b) {
						sb.append(1); 
					}else {
						sb.append(0); 
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static int N; 
	static int[] parents; 
	static int find(int a) {
		return parents[a] == a? a: (parents[a] = find(parents[a]));
//		if(parents[a] == a) return a; 
//		else return find(parents[a]);
	}
	static void union(int a, int b) {
		int pa = find(a); 
		int pb = find(b); 
		if(a < b) {
			if(pa != pb) parents[pb] = pa; 			
		} else {
			if(pa != pb) parents[pa] = pb; 	
		}
	}
}
/*
0 -> union 연산 
1 -> find 연산 

parents = [자기 자신으로 세팅]
find 연산 
- 내 위의 부모를 쭉 타고 올라가서 i와 parent[i]값이 동일시 되면 그게 나의 부모가 된다 

union 연산 
- 두 정점 a, b에 대해서 find(a)해서 찾은 부모 노드와 find(b)해서 찾은 부모 노드가 
- 같으면 
	- 합치는 연산 굳이 안 해도 됨 (이미 같은 그룹임) 
- 다르면
	- 숫자가 더 작은 걸 부모로 삼아서, parent[b] = a의 루트노드 

find(a) 
	if(parent[a] == a) return a; // 내가 부모 노드다 
	find(parent[a]); // 아닌 경우에는 계속 타고 올라가면서 검색해야 됨 
	
union(a,b) 
	pa = find(a) 
	pb = find(b) 
	
	if(pa != pb) 
		parent[pb] = pa; 
	

*/