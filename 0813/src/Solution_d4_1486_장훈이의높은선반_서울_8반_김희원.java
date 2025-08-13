import java.io.*;
import java.util.*; 
public class Solution_d4_1486_장훈이의높은선반_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0813/Test1486.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); 	// N명의 점원 
			B = Integer.parseInt(st.nextToken());	// 선반의 높이 
			
			st = new StringTokenizer(br.readLine(), " ");
			H = new int[N];
			for(int i=0; i<N; i++) H[i] = Integer.parseInt(st.nextToken());
			
			minDiff = Integer.MAX_VALUE;	/*subset함수 호출 전에 초기화하는 것이 더 낫다*/
			// 점원들을 쌓아서 만들 수 있는 높이 중 1) B이상이고, 2) 그 중에서 가장 낮은 탑 
			subset(0, 0);
			sb.append("#").append(tc).append(" ").append(minDiff-B).append("\n");
		}
		System.out.println(sb.toString());
	}
	// 몇 개를 선택해야 할지 모름 
	// 부분집합 subset느낌 
	// i번째 점원의 키를 선택했을 때 
	// i번째 점원의 키를 선택하지 않았을 때 
	// 그리고 i의 범위가 N이 되면 멈춰야 함. 
	// 선택해온 점원들의 키 합이 B를 넘으면 차이 계산 -> 가장 작은 차이를 구한다. 
	static int minDiff = Integer.MAX_VALUE; 
	static int N, B; 
	static int[] H; 
	static void subset(int i, int h) {
		/*리팩토링*/
	    if (h >= B + minDiff) {					// 현재의 최적해보다 더 높은 높이면 더 볼 필요가 없다
	        return;
	    }

	    if (i == N) {							// 기저 조건: 모든 점원을 다 확인했을 때
	        if (h >= B) {						// 합이 B 이상이라면, 최솟값 갱신
	            minDiff = Math.min(minDiff, h - B);
	        }
	        return;
	    }
	    subset(i + 1, h + H[i]);				// 1. index번째 점원을 탑에 포함하는 경우
	    subset(i + 1, h);						// 2. index번째 점원을 탑에 포함하지 않는 경우
		/*
		if(i >= N && h<B) return; 
		// i가 N보다 작거나 같고  
		if(h < B) { // 높이가 B보다 작은 경우
			subset(i+1, h+H[i]);
			subset(i+1, h);
		}else { // 높이가 B 이상인 경우 
			// minDiff = Math.min(h-B, minDiff);
			minDiff = h; // 좀 더 직관적인 코드 
		}
		*/
	}
}
/*
subset 구현 시, 나의 의문: 
"마지막 점원까지 보기도 전에 sum >= B일 수도 있지 않아? 점원 1명만으로도 B의 높이를 뛰어넘을 수 있잖아. 왜 굳이 끝까지 가?"

제미나이의 답변: 
"유효한 탑 높이를 언제, 어떻게 평가하는가"**에서 차이가 있을 뿐, 모든 경우의 수를 탐색하여 최적해를 찾는다는 본질은 같습니다.

리팩토링된 코드의 해설: 
h가 B를 넘든 말든 신경 쓰지 않고, 일단 끝까지 간다.
예를 들어, H[0] = 20; B = 16; 이라고 하자. 
내 방식으로 하면 바로 else문으로 빠지고 부분집합을 더 이상 안 봄 

근데 리팩토링된 코드를 보면 
subset(0,0) -> subset(1,20)으로 온 상태에서 2~N번째 점원을 모두 미포함하는 경로를 타서 결국 마지막까지 갔다고 하자. 
그럼 결국 index == N에 걸리고, h>=B이면 minDiff를 계산하게 된다. 

따라서 결국 같은 코드이다. 
===

또 다른 나의 의문: 그럼 내 코드는 H = {20, 5, 10}이라고 했을 때 바로 minDiff = h; 로 빠지니까 더이상 재귀가 안 발생? 

제미나이의 답변: 
subset(0,0) 으로 함수가 호출되기 시작하기 때문에 그 중 하나인 subset(1,20)이 됐을 뿐. subset(1, 0)이 있다. 
*/
