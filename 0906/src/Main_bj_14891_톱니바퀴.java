import java.io.*; 
import java.util.*; 
public class Main_bj_14891_톱니바퀴 {
	static class Chain{
		LinkedList<Integer> states = new LinkedList<>();
		int turn_d = 0; // 내가 회전하는 방향 
		int num = -1; // 내 톱니 인덱스 
		public Chain(String str, int idx) {
			this.num = idx; 
			for(int i=0; i<str.length(); i++) {
				states.add(str.charAt(i) - '0');
			}
		}

		@Override
		public String toString() {
			return "turn_idx" + num + " states=" + states + "], turn_dir = " + turn_d;
		} 
		
	}
	static void checkTurn(int turn_num, List<Chain> turn_list, boolean[] v) {
		Chain me = chains.get(turn_num-1);
//		System.out.println("@나: " + me + "--탐색시작--");
		int me_idx = me.num-1; 
		int l_idx = me_idx-1; 
		int r_idx = me_idx+1; 
		if(l_idx >= 0) {
			// 왼쪽에 이웃한 톱니가 존재할 때 
			if(!v[l_idx]) {
				Chain left = chains.get(l_idx);
//				System.out.print("@너의 왼쪽: " + left);
				if(me.states.get(6) != left.states.get(2)) {
					// 다른 극이면 left도 회전하게 된다. 
					left.turn_d = me.turn_d == 1? -1: 1; 
					turn_list.add(left);
					v[l_idx] = true; 
//					System.out.println(" 회전 가능\n");
					checkTurn(left.num, turn_list, v);
				} 
//				else System.out.println(" 회전 불가능\n");				
			}
		}
		if(r_idx < 4){
			// 오른쪽에 이웃한 톱니가 존재할 때 
			if(!v[r_idx]) {
				Chain right = chains.get(r_idx);
//				System.out.print("@너의 오른쪽: " + right);
				if(me.states.get(2) != right.states.get(6)) {
					// 다른 극이면 right도 회전하게 된다. 
					right.turn_d = me.turn_d == 1? -1: 1; 
					turn_list.add(right);
					v[r_idx] = true; 
//					System.out.println(" 회전 가능\n");
					checkTurn(right.num, turn_list, v); 				
				}
//				else System.out.println(" 회전 불가능\n");
				
			}
		}
		return; 
		
	}
	static List<Chain> chains; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		chains = new ArrayList<>(); 
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			chains.add(new Chain(st.nextToken(), i+1));
		}
//		for(Chain c: chains) System.out.println(c);
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());	// 회전 수 
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int turn_num = Integer.parseInt(st.nextToken()); // 회전할 톱니 인덱스 	
			int turn_dir = Integer.parseInt(st.nextToken()); // 회전 방향: -1은 반시계 1은 시계 
			
			// 회전하는 톱니번호 리스트: {톱니번호, 회전방향} 
			List<Chain> turn_list = new ArrayList<>(); 
			
			Chain me = chains.get(turn_num-1);
			me.turn_d = turn_dir; 
			turn_list.add(me);	// 현재 톱니 일단 넣고, 이웃도 회전하는지 확인하러 간다
			
			boolean[] visited = new boolean[4]; // 이웃한 톱니들이 도는지 확인할 때, 중복 방문 방지\
			visited[turn_num-1] = true; 
			
			// turn_idx에 이웃한 톱니들이 회전할 수 있는지 확인한다. 
			checkTurn(turn_num, turn_list, visited);
//			for(Chain c: turn_list) System.out.println("@회전예정 리스트: " + c);
			
			// turn_list에 대해서 회전을 시킨다. 
			/*
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 * A) List<Integer> states = new LinkedList<>();로 선언 시 
			 * c.states.addFirst(c.states.removeLast()); 이게 안 되었던 이유
			 * removeLast(), addFirst() 메소드는 링크드리스트 클래스에만 선언되어 있다. 
			 * 하지만 A와 같이 선언하면 states는 List인터페이스 타입이 되어서 List에서 선언한 메서드만 사용할 수 있다.
			 * => 이유: 컴파일러는 변수의 실제 객체가 아닌, 선언된 타입을 기준으로 문법 검사를 수행하기 때문이다.   
			 * => 인터페이스에는 오버라이딩 개념이 없다. 오로지 구현을 뜻한다. 
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 */
			for(Chain c: turn_list) {
				if(c.turn_d == 1) {
					// 시계 방향 회전이면, 맨 뒤에 있는 걸 앞으로 데리고 온다. 
//					int last = c.states.remove(c.states.size()-1);
//					c.states.add(0, last);
					c.states.addFirst(c.states.removeLast());
					c.turn_d = 0; 
				}else {
					// 반시계 방향 회전이면, 맨 앞에 있는 걸 뒤로 보낸다. 
//					int first = c.states.remove(0);
//					c.states.add(c.states.size(), first);
					c.states.addLast(c.states.removeFirst());
					c.turn_d = 0; 
				}
			}
			
//			System.out.print("---회전 한 차례 완료-- \n 결과물\n");
//			for(Chain c: chains) System.out.println(c);
//			System.out.println("--------");
		}
		int[][] scores = {{0,1}, {0,2}, {0,4}, {0,8}}; 
		int result = 0; 
		for(Chain c: chains) {
			if(c.states.get(0) == 0) {
				// N극이면 
				result += scores[c.num-1][0]; 
			}else {
				// S극이면 
				result += scores[c.num-1][1]; 
			}
		}
		System.out.println(result);

	}

}
/*
톱니바퀴의 상태는 linkedlist로 관리한다. 
반시계 방향 회전 시 0을 맨 뒤로 보낸다. 
시계 방향 회전 시 N-1을 맨 앞으로 갖고 온다. 

회전 톱니 번호 i, 회전방향 d라고 하면 (d=-1이 반시계, 1이 시계) 
1. 회전하는 톱니 번호만 찾고, 회전은 다 찾은 뒤에 회전시킨다. 
회전하는 톱니번호는 리스트로 관리한다. new int[]{톱니번호, 회전 방향}
모두 찾은 뒤에, for each문으로 톱니를 돌려주면 된다. 
-- 
i와 이웃한 톱니 중에서 극이 반대인 톱니를 찾아야 한다. 
왼쪽 
i.get(6) != (i-1).get(2) 이면 회전한다. 
- i가 1로 돌면, i-1은 -1로 
- list.add(new int[]{톱니번호, 회전방향}) 
- 회전 시키기 전에, i-1의 이웃에 대해서도 확인해야 한다. 

i.get(2) != (i+1).get(6)이면 회전한다. 
- i가 1로 돌면, i-1은 -1로 

그 외의 경우에는 회전하지 않는다. 

*/