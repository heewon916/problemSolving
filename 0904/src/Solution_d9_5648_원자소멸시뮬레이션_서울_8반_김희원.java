import java.io.*; 
import java.util.*; 

public class Solution_d9_5648_원자소멸시뮬레이션_서울_8반_김희원 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 4001 x 4001 크기의 2차원 배열(mat) 생성
            // 좌표를 2배로 확장하여 정수 격자로 변환하고, 음수 좌표를 없애기 위해 2000을 더함
            // -1000 ~ 1000 범위의 좌표를 0 ~ 4000 범위로 변환
            int[][] mat = new int[4001][4001];
            
            // 원자들을 저장할 덱(ArrayDeque) 초기화
            // {y좌표, x좌표, 방향, 에너지} 
            ArrayDeque<int[]> q = new ArrayDeque<>(); 
            
            
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                // x, y 좌표를 읽어와 2배로 곱하고 2000을 더하거나 빼서 격자 좌표로 변환
                // x는 그대로, y는 부호를 반대로 처리
                int x = 2000 + Integer.parseInt(st.nextToken()) * 2; 
                int y = 2000 - Integer.parseInt(st.nextToken()) * 2;
                int d = Integer.parseInt(st.nextToken()); 
                int k = Integer.parseInt(st.nextToken()); 
                
                mat[y][x] = k; 
                q.offer(new int[]{y, x, d, k});
            } 
            
            // 충돌로 인해 소멸된 원자들의 에너지 합을 저장할 변수
            int energy_sum = 0; 
            
            while(!q.isEmpty()){
                int[] cur = q.poll(); 
                int cur_y = cur[0]; 
                int cur_x = cur[1]; 
                int cur_d = cur[2]; 
                int cur_k = cur[3]; 

                // 현재 원자의 위치에 다른 원자가 충돌하여 이미 에너지가 증가했는지 확인
                // mat[cur_y][cur_x] > cur_k 이면, 현재 위치에 이미 다른 원자들이 도착하여
                // 에너지가 합산된 상태라는 것을 의미함. 
                // 이 경우, 이전에 큐에 들어간 다른 원자들이 이미 처리된 후 현재 원자가 도착한 것
                // 따라서 현재 위치의 모든 에너지를 소멸 에너지에 합산하고 해당 위치를 0으로 초기화
                // 이후 현재 원자는 더 이상 이동하지 않고 다음 원자로 넘어감
                if(mat[cur_y][cur_x] > cur_k){
                    energy_sum += mat[cur_y][cur_x]; 
                    mat[cur_y][cur_x] = 0; 
                    continue; 
                }
                
                // 이동 후의 새로운 좌표를 계산
                int ny = cur_y; 
                int nx = cur_x; 
                
                // 방향에 따라 좌표를 1만큼 이동
                switch(cur_d){
                    case 0: ny -= 1; break; // 상
                    case 1: ny += 1; break; // 하
                    case 2: nx -= 1; break; // 좌
                    case 3: nx += 1; break; // 우
                }
                
                // 새로운 좌표가 격자 범위를 벗어나는지 확인
                // 범위를 벗어나면 해당 원자는 소멸
                if(nx < 0 || nx > 4000 || ny < 0 || ny > 4000) {
                    mat[cur_y][cur_x] = 0; // 기존 위치를 0으로 초기화
                    continue; // 다음 원자로 넘어감
                }
                
                // 이동하려는 칸(ny, nx) 에너지가 0인지
                if(mat[ny][nx] == 0){
                    // 빈 곳이면 원자를 이동시키고 덱에 다시 추가
                    mat[ny][nx] = cur_k; // 새로운 위치에 에너지 저장
                    mat[cur_y][cur_x] = 0; // 기존 위치는 0으로 초기화
                    q.offer(new int[]{ny, nx, cur_d, cur_k}); // 이동한 원자를 덱에 추가
                } else {
                    // 이동하려는 칸에 이미 다른 원자가 있다면 충돌 발생
                    mat[ny][nx] += cur_k; // 기존 에너지에 현재 원자의 에너지를 더함
                    mat[cur_y][cur_x] = 0; // 기존 위치는 0으로 초기화
                    // 충돌한 원자는 덱에 다시 추가하지 않음 (한 턴에 한 칸만 이동하기 때문)
                    // 다음 턴에 다른 원자가 이 위치로 오면 충돌 처리가 반복됨
                }
            }
            sb.append('#').append(t).append(' ').append(energy_sum).append('\n'); 
        }
    
        System.out.println(sb.toString());
    }
}