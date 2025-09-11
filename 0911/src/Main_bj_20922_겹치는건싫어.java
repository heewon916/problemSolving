import java.io.*;
import java.util.*;

public class Main_bj_20922_겹치는건싫어 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] count = new int[100001];
        int[] mat = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            mat[i] = Integer.parseInt(st.nextToken());
        }
        int max_len = 0;
        int len = 0;
        for(int i=0; i<N; i++){
            if(count[mat[i]] <= K) {
                count[mat[i]]++;
                len++;
            }else{
                len = 0;
            }
            System.out.println(len + " " + Arrays.toString(count));
        }
    }
}
/*
[이해]
N개의 원소 수열에서
같은 원소가 K개 이하로 들어있는 최장 + 연속 부분수열

N 2 * 10^5
K 100

개수 세는 배열: 1~10^5까지 저장하고,
i=0부터 방문하면서, count[mat[i]] <= K이하이면 count[mat[i]]++;하고 길이 len++;
아니면 여태까지 끌어온 길이 len = 0으로 초기화하고 max_len에 저장해두기, 그리고

 */