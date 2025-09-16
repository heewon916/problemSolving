import java.io.*;
import java.util.*;

public class Solution_d3_3499_퍼펙트셔플_서울_8반_김희원{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            ArrayDeque<String> left = new ArrayDeque<>(); 
            ArrayDeque<String> right = new ArrayDeque<>(); 
            st = new StringTokenizer(br.readLine(), " ");
            int mid = (N%2 == 0)? N/2: N/2+1; 
            for(int i=0; i<N; i++){
                if(i < mid) left.offer(st.nextToken());
                else right.offer(st.nextToken());
            }
            // for(String str: left) System.out.print(str + " ");
            // System.out.println();
            // for(String str: right) System.out.print(str +" ");
            ArrayList<String> ans = new ArrayList<>(); 
            int len = 0; 
            while(len <= N){
                if(len%2 == 0) {
                    String l = left.poll(); 
                    if(l != null) ans.add(l);
                }
                else {
                    String l = right.poll(); 
                    if(l != null) ans.add(l);
                }

                len++; 
            }
            sb.append('#').append(tc).append(' ');
            for(String s: ans) sb.append(s).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }
}