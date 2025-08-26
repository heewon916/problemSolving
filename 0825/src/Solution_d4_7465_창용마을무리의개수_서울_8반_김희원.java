import java.io.*;
import java.util.*;

public class Solution_d4_7465_창용마을무리의개수_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1]; 
			for(int i=1; i<=n; i++) {
				parents[i] = i; 
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			Set<Integer> answer = new HashSet<>();
			for(int i=1; i<n+1; i++) {
				answer.add(find(i));
			}
			sb.append("#").append(t).append(" ").append(answer.size()).append("\n");
		}
		System.out.println(sb.toString());
	}
	static int[] parents; 
	static int find(int a) {
		return parents[a] == a? a: (parents[a] = find(parents[a])); 
	}
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(a < b) {
			parents[pb] = pa; 			 
		}else {
			parents[pa] = pb; 			
		}
	}

}
/*
n: 사람 수 
m: 관계 수 
서로를 알고 있는 두 사람의 번호 

무리의 개수를 계산한다. 
- 루트 노드의 개수를 세자 

parents 초기값: 자기 자신이 루트 노드이다 

find(int a) 
	return parents[a] == a? a: (parents[a] = find(parents[a]); 
	
union(int a, int b) 
	 int pa = find(a) 
	 int pb = find(b) 
	 
	 if(a < b)
	 	parents[pb] = pa; 
	 else 
	 	parents[pa] = pb; 
	 	
각 관계를 읽어서 무리 짓고 
각 노드의 루트 노드를 읽기? Set ?
*/