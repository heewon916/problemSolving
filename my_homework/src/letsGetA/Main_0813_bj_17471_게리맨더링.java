package letsGetA;

import java.io.*;
import java.util.*;

public class Main_0813_bj_17471_게리맨더링 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 


		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		people = new int[N+1];	// 인구 수 
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<N+1; i++){
			people[i] = Integer.parseInt(st.nextToken());
		}
		// 인접 리스트 
		g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>(); 
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			for(int j=0; j<c; j++){
				g[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	
		b = new int[N+1];
		for(int i=1; i<N+1; i++){
			comb(0, 0, i); 	
			System.out.println(Arrays.toString(b));
			
			
		}
		


	}
	static int N; 
	static int[] people, a, b; 
	static List<Integer>[] g;
	static void checkConnected() {
		List<Integer> li = new ArrayList<>(); 
		for(int i=1; i<N+1; i++) {
			if(b[i] == 1) li.add(i);
		}
		// 한 그룹에 묶인 애들 안에서 
		for(int i=0; i<li.size(); i++) {
			// 
			for(int a: g[i]) {
				if(a != )
			}
		}
	}
	static void comb(int cnt, int start, int depth){
		if(cnt == depth) {
			return; 
		}
		for(int i=start; i<N+1; i++) {
			b[cnt] = 1; 
			comb(cnt+1, i+1, depth);
		}
	}

}
