import java.io.*; 
import java.util.*; 

public class Main_bj_21608_상어초등학교 {
    static class Position implements Comparable<Position>{
        int r,c,like_count, blank_count; 
        public Position(int r, int c, int like_count, int blank_count){
            this.r = r; 
            this.c = c; 
            this.like_count = like_count; 
            this.blank_count = blank_count;
        }
        @Override
        public int compareTo(Position o) {
            // 좋아하는 사람이 많으면 더 앞에 
            // 그 수가 같으면 빈칸 많은 사람을 더 우선순위로 
            // 그 수가 같으면 행, 열 순으로 더 앞쪽에 위치하는 사람을 더 우선순위로 
            if(this.like_count != o.like_count){
                return o.like_count - this.like_count;
            }
            if(this.blank_count != o.blank_count){
                return o.blank_count - this.blank_count; 
            }
            if(this.r != o.r){
                return this.r - o.r; 
            }
            return this.c - o.c; 
        }
        
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] seats = new int[N][N]; // 앉을 자리 인덱스는 0부터 N-1까지 
        int[][] relation = new int[N*N+1][4];
        int[] seq = new int[N*N]; // 순서대로 앉힐 학생 번호들 1~N*N
        for(int i=0; i<N*N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            seq[i] = Integer.parseInt(st.nextToken());
            for(int j=0; j<4; j++){
                relation[seq[i]][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        for(int n=0; n<N*N; n++){
            int std = seq[n]; // 앉힐 학생 번호 
            ArrayList<Position> candidates = new ArrayList<>(); 

            // System.out.println("@@ 앉힐 학생 번호: " + std);
            // NxN 배열 안에 내가 좋아하는 사람 있는지 체크 
            int max_like_count = 0; 
            int[] max_pos = new int[2]; 
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    int like_count = 0; 
                    int blank_count = 0; 
                    // 빈 자리 중에서
                    if(seats[i][j] == 0){
                        // 내 주변에 인접한 자리 중 
                        for(int d=0; d<4; d++){
                            int ni = i+di[d]; 
                            int nj = j+dj[d]; 
                            if(ni<0 || ni>=N || nj<0 || nj>=N) continue; 
                            if(seats[ni][nj] == 0) blank_count++; 
                            for(int k=0; k<4; k++){
                                // 좋아하는 학생 있으면 카운트 
                                if(relation[std][k] == seats[ni][nj]){
                                    like_count++; 
                                    break; 
                                }
                            }
                        }
                        // 가능한 후보군만 넣기 
                        candidates.add(new Position(i, j, like_count, blank_count));
                    }

                    /*
                    // 좋아하는 사람 많음 > 빈 칸 많음 > 행,열 위쪽 
                    if(like_count > max_like_count){
                        max_like_count = like_count; 
                        max_pos[0] = i; max_pos[1] = j; 
                        }else if(like_count == max_like_count){
                            // 빈 칸 많은 자리 찾기 
                            int count_blank = 0; 
                            
                            // 현재 내 위치 기준으로 
                        for(int k=0; k<4; k++){
                            int ni = i+di[k]; 
                            int nj = j+dj[k]; 
                            if(ni<0 || ni>=N || nj<0 || nj>=N) continue; 
                            if(seats[ni][nj] == 0) count_blank++; 
                        }
                        // max_pos 위치 기준으로 
                        for(int k=0; k<4; k++){
                            int ni = max_pos[0]+di[k]; 
                            int nj = max_pos[1]+dj[k]; 
                            if(ni<0 || ni>=N || nj<0 || nj>=N) continue; 
                            if(seats[ni][nj] == 0) count_blank--; 
                        }

                        // count_blank가 음수라면, max_pos 주변에 빈칸이 더 많으니 유지 
                        // 양수라면, 현재 내 위치 주변에 빈칸이 더 많은 거고 
                        // 0이라면, max_pos와 내 위치 중, 행, 열순으로 더 작은 곳 
                        if(count_blank > 0){
                            max_pos[0] = i; 
                            max_pos[1] = j; 
                        }else if(count_blank == 0){
                            // 
                            if(max_pos[0] > i){
                                max_pos[0] = i; 
                                max_pos[1] = j; 
                            }else if(max_pos[0] == i){
                                if(max_pos[1] > j){
                                    max_pos[1] = j; 
                                }
                            }
                        }

                        // System.out.println("후보: " + Arrays.toString(max_pos) + " 좋아하는 학생 수: " +max_like_count);
                        
                    }
                    */
                }
            }

            // 후보군을 정렬하여 가장 적합한 자리에 앉힌다.
            Collections.sort(candidates);

            Position best = candidates.get(0);
            seats[best.r][best.c] = std; 
            // for(int i=0; i<N; i++) System.out.println(Arrays.toString(seats[i]));
        }


        // 만족도 합 구하기 
        // 각 자리에 앉아있는 학생 번호에 대해서 
        // 그 학생이 좋아하는 학생 수를 구하고, 그에 맞는 점수를 부여한다. 
        int ans = 0; 
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int std = seats[i][j]; 
                int count = 0; 
                for(int d=0; d<4; d++){
                    int ni = i+di[d]; 
                    int nj = j+dj[d]; 
                    if(ni<0 || ni>=N || nj<0 || nj>=N) continue; 
                    for(int k=0; k<4; k++){
                        if(relation[std][k] == seats[ni][nj]){
                            count++; 
                            break; 
                        }
                    }
                }
                if(count == 0){
                    ans += 0; 
                }else if(count == 1){
                    ans += 1; 
                }else if(count == 2){
                    ans += 10; 
                }else if(count == 3){
                    ans += 100; 
                }else if(count == 4){
                    ans += 1000; 
                }
            }
        }
        System.out.println(ans);
    }
}
