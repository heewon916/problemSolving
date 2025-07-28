import java.io.*; 
import java.util.*; 

public class Main_15686 {
	static int N, M; 
	static int[][] mat; 
	static int[][] chickenPos; 
	static int[][] housePos; 
	static int chickenCount; 
	static int houseCount; 
	static int min = Integer.MAX_VALUE; 
	static int[] selected = new int[13];
	static void comb(int start, int cnt) {
		// start 조합의 시작 인덱스/ cnt 현재까지 뽑은 치킨집 개수/ 
		// chickenCount 치킨집 총 개수/ M 뽑아야 하는 총 개수/ select 뽑은 치킨집의 인덱스 저장 배열
		// 리턴: 만들어진 조합에 대해서, 도시의 치킨 거리 계산하기 
		if(cnt == M) {
			int cityMin = 0; 
			for(int i=0; i<houseCount; i++) {
				int hx = housePos[i][0]; 
				int hy = housePos[i][1]; 
				int tempMin = Integer.MAX_VALUE; 
				for(int j=0; j<M; j++) {
					int selectedChick = selected[j];
					int tempDist = calcDist(hx, hy, chickenPos[selectedChick][0], chickenPos[selectedChick][1]);
					tempMin = (tempMin < tempDist)? tempMin: tempDist; 
				}
				cityMin += tempMin; 
			}
			min = (min < cityMin)? min: cityMin; 
		}
		for(int i=start; i<chickenCount; i++) {
			selected[cnt] = i; // i번째 치킨집을 선택 
			comb(i+1, cnt+1);
		}
	}
	static int calcDist(int x, int y, int r, int c) {
		return Math.abs(x-r) + Math.abs(y-c);
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mat = new int[N][N]; 
		chickenPos = new int[13][2]; // 치킨집 위치 저장 
		chickenCount = 0; 
		housePos = new int[2*N][2]; // 집 위치 저장 
		houseCount = 0; 
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] == 2) {
					chickenPos[chickenCount++] = new int[]{i, j}; 
				} else if(mat[i][j] == 1) {
					housePos[houseCount++] = new int[]{i, j}; 	
				}
			}
		}
		
		// 집 별로 최소 거리인 치킨집 찾기 
		// 그 중에서 M개만 살려야 해 && 도시의 치킨집 거리 MIN으로 만드는 
		// 어느 치킨집을 살릴 것인지 조합 문제로 가는데 
		// 그럼 chickenCount C M 조합이지 
		comb(0, 0); 
		System.out.println(min);
		
	}

}
