import java.util.*; 
import java.io.*; 

public class Solution_9229 {
	static int N, limit; 
	static int[] arr, used = new int[2];
	static List<Integer> availComb = new ArrayList<Integer>(); 
	static void comb(int cnt, int start, int weight) {
		if(weight > limit) {
			return;
		}
		if(cnt == 2) {
//			System.out.println(availComb.toString());
			availComb.add(weight);
			return ;
		}
		for(int i=start; i<N; i++) {
			used[cnt] = arr[i];
			comb(cnt+1, i+1, weight+arr[i]);
		}
		return; 
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*투포인터로 풀이할 때*/
		// 1. 오름차순 정렬 
		// 2. 왼쪽 포인터, 오른쪽 포인터 
		// 3. i<j일 때까지, 포인터 위치 왼쪽은 ++, 오른쪽은 -- 하면서 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        // 이중 반복문으로만 
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] snacks = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }

            int answer = -1;
            for(int i = 0; i < N - 1; i++) {
                for(int j = i + 1; j < N; j++) {
                    int sum = snacks[i] + snacks[j];
                    if(sum <= M && sum > answer) {
                        answer = sum;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
        
        /* 투포인터 알고리즘 
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] snacks = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(snacks); // 정렬

            int left = 0, right = N - 1;
            int answer = -1;

            while(left < right) {
                int sum = snacks[left] + snacks[right];
                if(sum > M) {
                    right--;
                } else {
                    // sum <= M
                    if(sum > answer) answer = sum;
                    left++;
                }
            }
            

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
        */
        
		/* 조합 + 완전탐색으로 풀 때 => O(N^2); N이 커지면 풀이 불가능 
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N개의 과자봉지 무게제한M 들고 갈 수 있는 최대 무게합 -> 정확히 두 봉지만 사려고 함. 
		// NC2했을 때 무게의 조합 중, 최대 조합을 구하면 됨. 
		// M<= 2* 10^6
		st = new StringTokenizer(br.readLine()); 
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=tc; t++) {
			int answer = 0; // 들고 갈 수 있는 과자 2봉지의 최대 무게 
			st = new StringTokenizer(br.readLine(), " "); 
			N = Integer.parseInt(st.nextToken()); 
			limit = Integer.parseInt(st.nextToken());
			
			arr = new int[N]; // 과자 봉지별 무게 
			st = new StringTokenizer(br.readLine(), " "); 
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 순서가 없는 순열 = 조합 
			comb(0, 0, 0);
			availComb.sort((a,b) -> b-a);
			if(availComb.size() > 0) {
				answer = availComb.get(0);
//				System.out.println(answer);
			} else {
				answer = -1; 
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
			availComb.clear();
		}
		System.out.println(sb.toString());
		*/
	}
}


//4
//3 45
//20 20 20
//6 10
//1 2 5 8 9 11
//4 100
//80 80 60 60
//4 20
//10 5 10 16