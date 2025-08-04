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