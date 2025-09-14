/*
 * 최장증가부분수열 
 * 이진탐색으로 삽입할 위치 결정 시, NlogN 걸림 
 * 제한시간 1초 
 * N 10^5 
 * 50번이면 충분 
 */

import java.io.*;
import java.util.*; 

public class Main_bj_3745_오름세 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        StringTokenizer st = null; 
        String in; 
        while((in = br.readLine()) != null && !in.trim().isEmpty()){
            int N = Integer.parseInt(in.trim());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int tail[] = new int[N]; // 증가 부분수열 관리

            int maxLen = 0; 
            for(int i=0; i<N; i++){
                int pos = Arrays.binarySearch(tail, 0, maxLen, arr[i]);
                if(pos < 0) pos = -(pos + 1);
                tail[pos] = arr[i]; 
                if(maxLen == pos) maxLen++; 
            }

            sb.append(maxLen).append('\n');
        }
        System.out.println(sb);

        br.close();
    }
}
