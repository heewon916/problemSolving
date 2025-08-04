import java.util.*; 
import java.io.*; 

public class Solution_1233 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0804/Test1233.txt"));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 숫자 8이 4번 정점에 위치 -> 4 8 
		// 연산자 /이 2번 정점에 위치 -> 2 / 4 5 (왼쪽, 오른쪽 자식) 
		// 연산이 불가능한 경우 -> 리프 노드에 연산자가 있을 때/ 피연산자 2개의 부모 노드가 피연산자일 때
		int T = 10; 
		for(int tc=1; tc<=T; tc++) {
			int answer = 1; 
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; i++) {

				st = new StringTokenizer(br.readLine(), " ");
				int tokenCount = st.countTokens();
				
				if(tokenCount == 4) {
					// not leaf node ->  피연산자 불가능 
					st.nextToken();
					String op = st.nextToken();
					st.nextToken();
					st.nextToken();
					if(op.matches("-?\\d+")) answer = 0; 
				}else if(tokenCount == 2){
					// leaf node -> 연산자 불가능 
					st.nextToken();
					String value = st.nextToken();
					if(!value.matches("-?\\d+")) answer = 0; 
				} else {
					answer = 0; 
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
/*
 * 배운 점:
 * String.matches("-?\\d+) -> 해당 문자열이 숫자인지 아닌지 판단해준다. 
 */
