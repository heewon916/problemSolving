package my_homework.bj;
import java.util.*; 
import java.io.*; 
/**
 * 풀이방식: 문제 자체가 어렵다기 보다는, 구현에 애를 많이 먹었다. 
 * 이 문제를 통해서 배운게 좀 많은데 
 * 1. List, HashMap, ArrayDeque사용까지 
 * 2. 람다식으로 정렬하는 법
 * 3. 조건들을 순차적으로 적용해서 찾아야 할 때 나는 전부 1차원/2차원 배열만 사용해서 구현이 너무 복잡해졌었다. 
 * 근데 List의 contains를 사용한다거나 그러니까 훨씬 쉬웠음. 컬렉션 API 정리가 필요하다고 생각이 들었고, 
 * 어떤 자료구조를 어느 변수에 사용할지 정할 연습을 더 많이 해야 할 것 같다. 
 */

public class Main_21608 {
	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		List<int[]> favList = new ArrayList<>(); 
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[5]; 
			for(int j=0; j<5; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			favList.add(arr);
		}
		
		int[][] res = new int[N][N]; // 결과 배열; 앉으면 학생 번호, 빈 자리는 0 
		
		// 1. 비어있는 곳 중 주변에 좋아하는 애가 많은 곳으로 
		// 2. 그 중에서, 빈 칸이 많은 곳으로 
		// 3. 그것도 여러 개면, 가장 앞쪽의 자리로 
		int idx=0; 
		while(idx < favList.size()) {
			int[] arr = favList.get(idx++);
			int sn = arr[0]; 
			List<Integer> likes = new ArrayList<>(); // sn번호인 학생이 좋아하는 애들 리스트
			for(int i=0; i<4; i++) likes.add(arr[i+1]); 
			List<int[]> avail = new ArrayList<>(); // 학생별로 앉을 수 있는 자리 후보군들 
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(res[i][j] == 0) {
						int liking = 0; 
						int empty = 0; 
						for(int d=0; d<4; d++) {
							int ni = i+dx[d], nj = j+dy[d];
							if(0<=ni&&ni<N&&0<=nj&&nj<N) {
								if(likes.contains(res[ni][nj])) liking++; 
								if(res[ni][nj] == 0) empty++; 
							}
						}
						avail.add(new int[] {liking, empty, i, j});
					}
				}
			}
			// 자리 후보군들을 조건 순서에 맞게 정렬한다. 
			avail.sort((a, b)-> {
				if(b[0] != a[0]) return b[0]-a[0]; // 좋아하는 학생이 많은 순서대로 
				if(b[1] != a[1]) return b[1]-a[1]; // 빈자리 많은 순 
				if(b[2] != a[2]) return a[2]-b[2]; // 앞 번호 순 (행)
				return a[3]-b[3]; // 열 
			});
			// 맨 앞 원소가 결국 그 학생이 앉을 자리임. 
			int x = avail.get(0)[2], y = avail.get(0)[3];
			res[x][y] = sn;
			
			// 결과물 디버깅 
			// for(int[] a: res) System.out.println(Arrays.toString(a));
			// System.out.println();
		}
		// 만족도 체크 
		int answer = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = 0; 
				int sn = res[i][j]; 
				int[] temp = new int[4]; 
				for(int[] arr: favList) {
					if (arr[0] == sn) temp = Arrays.copyOfRange(arr, 1, 5);
				}
				List<Integer> list = new ArrayList<>(); 
				for(int k: temp) list.add(k);
				for(int d=0; d<4; d++) {
					int ni = i+dx[d], nj = j+dy[d];
					if(0<=ni&&ni<N&&0<=nj&&nj<N) {
						if(list.contains(res[ni][nj])) cnt++;
					}
				}
				if(cnt == 0) continue;
				else if(cnt == 1) answer += 1; 
				else if(cnt == 2) answer += 10; 
				else if(cnt == 3) answer += 100;
				else if(cnt == 4) answer += 1000; 
			}
		}
		System.out.println(answer);
	}	
}


