import java.io.*; 
import java.util.*; 
public class Main_bj_3745_오름세 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1]; 
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		

	}

}
