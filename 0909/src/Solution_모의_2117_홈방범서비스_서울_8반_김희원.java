import java.io.*; 
import java.util.*; 
public class Solution_모의_2117_홈방범서비스_서울_8반_김희원 {
	static int N, homePay, max_house_count; 
	static int[][] map;
/*
    static boolean[][] v;
    static PriorityQueue<int[]> pq;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int i, int j, int k) {
        // 시작점 i, j에 대해서 depth=k-1번까지 퍼질 수 있다.
        int house_cnt = 0; // 집 개수
        int max_depth = k-1;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i*10000 + j*100 + 0);
        v[i][j] = true;
        while(!q.isEmpty()) {
            int cur = q.poll(); // cur = i*10000 + j*100 + depth; 2자리수씩 쓰니까, 두자리로 잡는게 안전한다
            int depth = cur%100;
            cur /= 100;
            int y = cur%100;
            int x = cur/100;

            //!!!!! 먼저 깊이 제거를 해줘야 한다.
            if(depth > max_depth) break;
            if(map[x][y] == 1) house_cnt++;
            for(int d=0; d<4; d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];
                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                if(!v[nx][ny]) {
                    v[nx][ny] = true;
                    q.add(nx*10000 + ny*100 + (depth+1));
                }
            }
        }

        int cost = house_cnt * homePay - (k*k + (k-1)*(k-1));
        if(cost < 0) return;
        max_house_count = Math.max(max_house_count, house_cnt);
        return;

    }
*/
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			homePay = Integer.parseInt(st.nextToken());
			
			map = new int[N][N]; 
			
			int total_home_count = 0; 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) total_home_count++;
				}
			}

			max_house_count = 0; 

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 각 좌표에 대해 K를 달리해 계산한다. 
					for(int k=1; k<=2*N+1; k++) {
                        // bfs로 영역 구하기
//						v = new boolean[N][N];
//						bfs(i, j, k);
                        // 어떻게 해도 손해인 경우는 무조건 break
						if(k*k+(k-1)*(k-1) > total_home_count*homePay) break;
						// 다이아몬드 직접 합산하기 
						// 중심 i,j에 대해서 맨허튼 거리가 k인 애들에 대해 집 개수 직접 세기
						int homeCnt = 0; 
						for(int dx=-(k-1); dx<=(k-1); dx++) {
							int x = i+dx; 
							if(x<0 || x>=N) continue;
                            // 한 행에서 읽을 열의 개수를 조정해야 한다.
                            int gap = dx<0? 2*(k+dx)-1: 2*(k-dx)-1;
                            for(int dy=-(gap/2); dy<=(gap/2); dy++){
                                int y = j + dy;
                                if(y<0 || y>=N) continue;
                                if(map[x][y] == 1) homeCnt++;
                            }
						}
                        // 이익 계산하기
                        int cost = homePay*homeCnt - ((k-1)*(k-1)+k*k);
                        if(cost < 0) continue;
                        max_house_count = Math.max(max_house_count, homeCnt);

					}
				}
			}
			sb.append('#').append(tc).append(' ').append(max_house_count).append('\n');
		}
		System.out.println(sb.toString());
	}
}
