import java.util.*; 
import java.io.*; 
public class Test2 {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			minScore = Integer.MAX_VALUE; 
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); // M개를 맞춤 = depth 
			K = Integer.parseInt(st.nextToken()); // 카운터가 K가 되면 카운터는 0, 점수는 2배 
			b = new int[N];
			int count = 0;
			int score = 0; 
			
			for(int i=N-1; i>=0; i--) {
				if(R == 0) break; 
				if(i+1 == R) {
					for(int j=i; j>=0; j--) b[j] = 1; 
				}
				count++; 
				if(count == K) continue; 
				b[i] = 1; R--; 
			}
			
//			System.out.println(Arrays.toString(b));
			
			count = 0; 
			for(int i=0; i<N; i++) {
				if(b[i] == 1) {
					count++; 
					score++;
				}else {
					count = 0; 
				}
				if(count == K) {
					score = score * 2; 
					count = 0; 
				}
				System.out.println(count + " " + score);
			}
			sb.append("#").append(tc).append(" ").append(score).append("\n");
//			System.out.println();
		}
		System.out.println(sb.toString());

	}
	static int N, R, K, minScore = Integer.MAX_VALUE ; 
	static int[] b;
}
//	static void subset(int cnt, int start, int score, int counter) {
//		if(score > minScore) return; 
//		if(cnt == R) {
//			if(counter == K) score = score*2; 
//			minScore = Math.min(minScore, score);
//			return; 
//		}
//		if(start == N) return;
//		if(counter == K) {
//			score = score *2; 
//			counter = 0; 
//		}
//		subset(cnt+1, start+1, score+1, counter+1);
//		subset(cnt, start+1, score, 0);
//	}
//}

//		if(start == N) {
//			if(cnt == R) {
//				// 맞춘 문제가 R개가 되면 
//				if(counter == K) score = score*2; 
//				minScore = Math.min(minScore, score);
//			}
//			return; 
//		}
//		if(counter == K) {
//			// 현재 문제 맞춘 경우 
//			subset(cnt+1, start+1, score*2+1, 0); 
//			// 현재 문제 못 맞춘 경우 
//			subset(cnt, start+1, score*2, 0);
//		}else {
//			// 현재 문제 맞춘 경우 
//			subset(cnt+1, start+1, score+1, counter+1); 
//			// 현재 문제 못 맞춘 경우 
//			subset(cnt, start+1, score, 0);
//		}	
/*
----
output: 
10 
6
2
---
30
8 7 3
3 3 3
5 2 3
9 9 3
8 8 3
8 5 6
18 14 3
19 19 5
15 15 7
44 42 14
48 44 12
31 31 23
55 49 5
93 85 8
81 78 13
193 164 6
57 50 7
113 111 36
276 274 68
84 78 8
295 271 10
141 108 4
317 316 67
350 342 31
358 355 69
347 317 10
499 255 2
425 342 5
346 337 31
428 428 302
*/