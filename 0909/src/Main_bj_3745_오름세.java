import java.io.*; 
import java.util.*; 
public class Main_bj_3745_오름세 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = null;
		String line = null; 
		while((line = br.readLine()) != null) {
			int N = Integer.parseInt(line.trim());
			int[] arr = new int[N]; 
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<Integer> list = new ArrayList<>(); 
			list.add(arr[0]);
			
			for(int i=1; i<N; i++) {
				int comp = list.get(list.size() - 1); 
				if(comp < arr[i]) list.add(arr[i]);
				else {
					int start = 0, end = list.size()-1;
					int mid = 0; 
					
					while(start <= end) {
						mid = (start+end)/2;
						int cur = list.get(mid);
						if(cur == arr[i]) {
							break; 
						}
						else if(cur < arr[i]) {
							start = mid+1;  
							mid++;
						}else {
							end = mid-1; 
						}
					}
					list.set(mid, arr[i]);
				}
			}
			sb.append(list.size()).append('\n');
//			int[] lis = new int[N];
//			int max = 0; 
//			for(int i=0; i<N; i++) {
//				int pos = Arrays.binarySearch(lis, 0, max, arr[i]);
//				if(pos < 0) pos = -(pos+1);
//				lis[pos] = arr[i]; 
//				// 만약 교체가 아닌, 추가가 발생할 경우, 길이가 늘어난다. 
//				// 즉, 최장길이max와 방금 추가된 곳이 같은 값을 보이면, 그것은 길이가 늘어난 것을 의미한다. 
//				if(max == pos) max++; 
//			}
//			sb.append(max).append('\n'); 	
			
			
		}
		System.out.println(sb.toString());
		
		

	}

}
