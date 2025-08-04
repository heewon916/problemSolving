import java.io.*; 
import java.util.*;
public class Solution_1208 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0805/Test1208.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10; 
		for(int tc=1; tc<=T; tc++) {
			int answer = 0; // 최저점과 최고점의 높이차 
			st = new StringTokenizer(br.readLine());
			int dumps = Integer.parseInt(st.nextToken()); // 덤프횟수
			
			List<Integer> arr = new ArrayList<Integer>(); // 높이 배열 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<100; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			arr.sort((a,b) -> a-b); // 오름차순 정렬: 
			
			int left = 0, right = 99; 
			// 시간복잡도: 1000 * 100 = 10^5
			for(int d=0; d<dumps; d++) {
				if(arr.get(left) == arr.get(right)) {
					// 맨 앞과 맨 뒤의 높이가 같으면 
					answer = 0; 
					break; 
				}
				arr.set(left, arr.get(left)+1);
				arr.set(right, arr.get(right)-1);
				arr.sort((a,b) -> a-b);
//				System.out.println(arr.get(left) + " " + arr.get(right));
			}
			answer = arr.get(right) - arr.get(left);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		

	}

}
