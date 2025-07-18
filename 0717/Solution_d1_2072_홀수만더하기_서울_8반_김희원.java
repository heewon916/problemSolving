package a0717.camp;

import java.io.*; 
import java.util.*;

class Solution_d1_2072_홀수만더하기_서울_8반_김희원
{
	public static void main(String args[]) throws Exception
	{
		// A형 준비 시 아래 코드 필수 
		// byte stream 으로 읽어들인다. 
		System.setIn(new FileInputStream("res/input_d1_2072.txt"));
		// character stream 으로 읽어들인다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 한 줄씩 읽어들여 토큰별로 잘라낸다. 
		StringTokenizer st = null; 
		// 출력할 때는 StringBuilder를 사용한다. 여기에 모아서 출력한다. 
		StringBuilder sb = new StringBuilder(); 
		// 입력이 String으로 들어오기 때문에 Integer로 바꿔야 한다. 
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			int sum = 0;
			for(int i=0; i<10; i++) {
				int n = Integer.parseInt(st.nextToken());
				if (n%2 == 1) sum += n; 
				
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.print(sb.toString());
		br.close(); 
	}
}