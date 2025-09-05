import java.io.*; 
import java.util.*; 

public class Main_bj_14888_연산자끼워넣기 {
	static int max_res, min_res;
	static List<Integer> exps; 
	static int N; 
	static List<Integer> arr; 
	static void calc(int[] b) {
		// arr 배열과 b를 이용해서 계산을 수행한다. 
		// 앞에서부터 차례대로 수행한다 
		int idx = 0; 
		int result = 0; 
		int x = arr.get(0); 
		for(int i=1; i<N; i++) {
			int y = arr.get(i); 
			int exp = b[idx++]; 
			switch(exp) {
			case 0: 
				// 덧셈 
				result = x+y; 
				break; 
			case 1: 
				// 뺄셈 
				result = x-y; 
				break; 
			case 2: 
				// 곱셈 
				result = x * y; 
				break; 
			case 3: 
				// 나눗셈 
				// x가 음수일 때만 전체 결과가 음수가 된다. 
				result = (x<0)? -((-x)/y) : x/y; 
				break;
			}
			x = result; 
			System.out.println("@next x=" + x);
		}
		if(max_res < result) {
			max_res = result; 
		}
		if(min_res > result) {
			min_res = result; 
		}
		return; 
	}
	static void makeExp(int depth, int start, int[] b) {
		/*
		 * @param depth 연산자 개수 - N-1이 되면 수식을 계산해야 한다. 
		 * @param start 숫자 선택할 인덱스 시작점 
		 */
		if(depth == N-1) {
			System.out.println(Arrays.toString(b));
			calc(b);
			return; 
		}
		// exps에서 연산자들의 숫자를 나열한다 
		for(int i=start; i<N-1; i++) {
			b[depth] = exps.get(i);
			makeExp(depth+1, i+1, b);
		}
	}	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>(); 
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
//		for(int a: arr) System.out.print(a + " ");
		
		exps = new ArrayList<>(); 
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++){
				exps.add(i);
			}
		}
//		for(int a: exps) System.out.print(a + " ");
		System.out.println();
		max_res = 0; 
		min_res = Integer.MAX_VALUE; 
		int[] b = new int[N-1]; 
		makeExp(0, 0, b);
		System.out.println(max_res+ " " + min_res);
	}

}
