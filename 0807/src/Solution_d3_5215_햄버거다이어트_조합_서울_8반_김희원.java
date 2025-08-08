import java.util.*;
import java.io.*; 
/*
 * 문제 유형: [조합] - 부분집합 - dp 
 * 
 * 0807 헤맨 이유: 문제 자체 이해가 느렸음. 나는 maxCal, maxScore 다 고려하면서 재귀해야 한다고 파악함. 
 * 게다가 재귀할 때 가지치기 하는 부분을 어떻게 해야할지 꽤나 갈팡질팡함. 
 * 중간에는 cal < limit 일때만 재귀할 수 있도록 해서 문제 발생 
 * 게다가 maxCal은 계산조차 안해도 되는데 조건에 넣어서 아예 다르게 풂.
 * 
 * comb라고 꼭 배운 함수 자체를 쓸 필요는 없음. 
 * 이 경우에는 R이 정해진 게 아니기 때문에, 
 * 오히려 선택했을 때와 안했을 때를 둘다 호출해야 함. 
 * 
 * 재귀는 진짜.. 리턴을 언제 어떻게 끊어낼지 금방 파악하는 연습이 필요할 듯;; 
 */

public class Solution_d3_5215_햄버거다이어트_조합_서울_8반_김희원 { 
	static int N, limit; 
	static List<int[]> info;
//	static List<Integer> maxScoreL = new ArrayList<>();  // -> 이걸 쓸 거면, for문에서 maxScoreL을 비워주는 게 필요했따. 
	static int maxScoreComb; 
	static int[] b; 
	static void comb(int cnt, int start, int cal/*, int[] b*/, int depth, int score ) {
		if(cal > limit) return; 
		if(cnt == depth) {
			maxScoreComb = Math.max(score, maxScoreComb);
			return; 
		}
		for(int i=start; i<N; i++) {
//			b[cnt] = info.get(i)[1];
			comb(cnt+1, i+1, cal+info.get(i)[1], depth, score+info.get(i)[0]);
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0808/Test5215.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			
			info = new ArrayList<>(); 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				info.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			// 주어진 제한 칼로리 이하의 조합 중에서 
			// 가장 맛에 대한 점수가 높은 햄버거의 점수를 출력
			int overallMaxScore = -1; 
			for(int i=1; i<=N; i++) {
				maxScoreComb = -1; 
				comb(0, 0, 0, i, 0);
//				maxScoreL.add(maxScore1);
//				maxScoreL.clear(); 
				overallMaxScore = Math.max(maxScoreComb, overallMaxScore);
			}
//			maxScoreL.sort((a,b) -> -Integer.compare(a, b)); 
			sb.append("#").append(tc).append(" ").append(overallMaxScore).append("\n");
		}
		System.out.println(sb.toString());
	}

}
/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
*/
