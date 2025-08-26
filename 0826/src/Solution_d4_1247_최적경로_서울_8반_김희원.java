import java.io.*; 
import java.util.*; 
public class Solution_d4_1247_최적경로_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0826\\Input1247.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			min_dist = Integer.MAX_VALUE; 
			n = Integer.parseInt(st.nextToken());
			
			homes = new int[n+2][2]; 
			int x = -1, y = -1 ; 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<n+2; i++) {
				x = Integer.parseInt(st.nextToken()); 
				y = Integer.parseInt(st.nextToken()); 
				homes[i] = new int[] {x, y}; 
			}
			visited = new boolean[n+2];
			b = new int[n+2][2]; 
			b[0] = homes[0]; b[n+1] = homes[1]; 
			perm(1); 
			sb.append("#").append(t).append(" ").append(min_dist).append("\n"); 
		}
		System.out.println(sb.toString());
	}
	static int n; 
	static int[][] homes; 
	static boolean[] visited; 	// homes에서 1부터 n까지의 집들만 파악한다. 
	static int[][] b; 
	static int min_dist = Integer.MAX_VALUE; 
	static void perm(int cnt) {
		if(cnt == n+1) {
			int sum = 0; 
			for(int i=0; i<n+1; i++) {
				sum += Math.abs(b[i][0] - b[i+1][0]) + Math.abs(b[i][1] - b[i+1][1]);
				if(sum > min_dist) return; 
			} 
			if(min_dist > sum) min_dist = sum;
			return; 
		}
		for(int i=2; i<=n+1; i++) {
			if(visited[i]) continue; 
			visited[i] = true; 
			b[cnt] = homes[i]; 
			perm(cnt+1);
			visited[i] = false; 
		}
	}
}
/*
[설계하기] 
homes int[n+2][2]
- 인덱스0: 회사 좌표 
- 인덱스1: 내 집 좌표 
- 인덱스2~n+1: 고객들 좌표 
visited boolean[n+2]
- 고객들의 집 순열에 사용됨 
- 인덱스2~n+1만 사용하자.
b int[n+2][2]
- 인덱스 0: 회사 좌표 고정 
- 인덱스 n+1: 내 집 좌표 고정 
- 그 사이: 고객들 집 순열 

테케 값 받아오고 
각각의 테케에 대해서: 

고객의 수 받아오고, 
총 길이 n+2의 배열 homes에 순열 집어넣기 
- 입력 그대로 받는다. 

각 고객 집을 방문했는지 안 했는지 체크하기 위해서 visited 선언 

b[0], b[n+1] 초기화하기 

perm은 시작 인덱스를 2부터 잡기 
- 그래야 b[cnt] <- homes[i]로 고객의 집 좌표가 들어갈 수 있음 


전역 변수 min_dist <- 최단 경로 
perm함수 (depth) -> void 
전부 방문했으면 맨허튼 거리 계산하기 
	for문 돌려서 i=0~ n까지 i와 i+1 사이의 거리를 모두 구해 누적한다 
	만약에 구한 거리가 min_dist보다 크다면 갱신하지 않고 
	아니라면 갱신한다. 
	return 
아직 모두 방문하지 않았다면 
	homes에 있는 각 집들에 대해서 
		방문했다면 continue 
		방문하지 않았다면 
			방문 처리 하고 
			b에 해당 집 좌표 넣고 b[depth] = homes[i] 
			perm(depth+1) 
			다시 방문처리 해제
[생각하기] 
회사 -> 각 고객의 위치 -> 집 
맨허튼 거리로 계산 

회사 좌표 -> 모든 고객 좌표 -> 집 좌표 
1 + N + 1 
N ~ 10 

최단 경로

bfs나 dfs로 하기엔 인접 영역에 좌표가 없을 수 있다. 
그렇다면 
회사 -> N명의 고객 순열 -> 내 집 

N! = 10! = 30만번 
20초 = 20 * 10^8 = 2 * 10^9

충분히 가능할 듯  
*/