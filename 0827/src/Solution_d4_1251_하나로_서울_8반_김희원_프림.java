import java.io.*;
import java.util.*;

public class Solution_d4_1251_하나로_서울_8반_김희원_프림 {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			long[] xs = new long[N]; 
			long[] ys = new long[N]; 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				xs[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				ys[i] = Long.parseLong(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken()); 
			
			long[][] dist = new long[N][N]; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					long dx = xs[i] - xs[j]; 
					long dy = ys[i] - ys[j]; 
					dist[i][j] = dx*dx + dy*dy; 
				}
			}
			
			boolean[] v = new boolean[N]; 
//			int[] prim = new int[N]; 
//			Arrays.fill(prim, Integer.MAX_VALUE);
			long[] prim = new long[N]; 
			Arrays.fill(prim, Long.MAX_VALUE);
			prim[0] = 0;
			
			long mst = 0; 
			int cnt = 0; 
			for(int i=0; i<N; i++) {
				int minVertex = -1; 
				long min = Long.MAX_VALUE; 
				
				for(int j=0; j<N; j++) {
					if(!v[j] && min > prim[j]) {
						min = prim[j]; 
						minVertex = j; 
					}
				}
				
				v[minVertex] = true; 
				mst += min; 
				// System.out.println("minVertex = " + minVertex + " mst = " + mst);
				if(++cnt == N) break;
				
				for(int j=0; j<N; j++) {
					if(!v[j] && prim[j] > dist[minVertex][j]) {
						prim[j] = dist[minVertex][j]; 
					}
				}
			}
			// System.out.println();
			
			long answer = Math.round(mst * E); 
			sb.append("#").append(t).append(" ").append(answer).append("\n"); 
		}
		System.out.println(sb.toString());
		
	}

}
/*
[이해] 
N개의 섬 연결하기 
환경 부담금 = 환경부담세율 E * (각 해저 터널 길이의 제곱)
총 환경 부담금 최소로 지불하고, N개의 섬을 모두 연결 

[입력] 
N 섬의 개수 ~10^3
x좌표 ~10^6
y좌표 ~10^6 
부담세율 E 

[해결] 
부담금이 최소가 되려면 각 해저 터널을 서로 연결하는 최소신장트리를 구해야 함 
입력은 정점 중심으로 들어오고 있음 
프림 ? 

--- 
int[] prim <- 트리에서 각 해저 터널로 가는 최소 비용을 담는다. 
시작정점은 아무거나 골라도 될 듯
각 정점 좌표 int[] xs = new int[N]; ys도 동일함 
각 좌표 간의 거리 미리 구하기 int[][] dist -> (xs[i] - xs[j])*(xs[i] - xs[j]) + (ys[i] - ys[j])*(ys[i] - ys[j])
트리에 포함됐는지 확인하기 boolean[] v = new int[N];  
프림: 
프림 배열의 인덱스 i  <-> xs[i], ys[i] 좌표 
프림 배열은 Integer.MAX_VALUE로 초기화하기 
prim[0] = 0; 
int mst = 0; 트리 비용 값 누적 
int cnt = 0; 트리에 포함된 정점 개수 
for(int i=0; i<N; i++) 
	1. 최소 정점과 최소거리 찾기 
	int minVertex = -1; 
	int min = Integer.MAX_VALUE; 
	for(int i=0; i<N; i++) 
		if(!v[i] && min > prim[i]) 
				min = prim[i]; 
				minVertex = i; 
		
		v[minVertex] = true; 
		mst += min; 
		if(++cnt == N-1) break;
		
		for(int i=0; i<N; i++)
			if(!v[i] && prim[i] > dist[minVertex][i]) 
				prim[i] = dist[minVertex][i]; 
*/
