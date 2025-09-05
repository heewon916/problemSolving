import java.io.*; 
import java.util.*; 
public class Main_bj_14890_경사로 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 배열 크기 
        int L = Integer.parseInt(st.nextToken()); // 경사로 길이 

        int[][] mat = new int[N][N]; // 배열 
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int j=0; j<N; j++) {
        		mat[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        boolean[][] v = new boolean[N][N]; // 경사로를 뒀는지 체크하는 배열 
        int result = 0; 
        // 한 행씩 검사 
        for(int r=0; r<N; r++){
//            int s = 0, e = 0; 
            boolean able = true; 
//            System.out.println("?검색: 행"+ r);
            for(int e=1; e<N; e++) {
            	if(mat[r][e-1] == mat[r][e]) continue;
            	else if(mat[r][e-1] < mat[r][e]) {
            		// 낮은 곳에서 높은 곳으로 가는 경우 
            		// 1. 높이차가 1보다 크면 경사로 못 둠 
            		if(mat[r][e] - mat[r][e-1] > 1) {
//            			System.out.println("--행: "+ r + " e:" + e + " 높이차 > 1");
            			able = false; 
            			break; 
            		}
            		// 2. 경사로 L 길이만큼 둘 수 있는지 체크 
            		if(e-L < 0) {
//            			System.out.println("--행: "+ r + " e:" + e + " L길이 불가");
            			able = false; 
            			break; 
            		}
            		// 3. 경사로 두는 곳의 높이가 다 같은지 체크 
            		int prevH = mat[r][e-1];
            		for(int i=e-1; i>e-1-L; i--) {
            			if(prevH != mat[r][i]) {
//            				System.out.println("--행: "+ r + " e:" + e + " 경사로 높이 불일치");
            				able = false; 
            				break; 
            			}
            			prevH = mat[r][i]; 
            		}
            		// 4. 이미 경사로 둔 곳인지 체크 
            		for(int i=e-1; i>e-1-L; i--) {
            			if(v[r][i]) {
//            				System.out.println("--행: "+ r + " e:" + e + " 이미 있음");
            				able = false; 
            				break; 
            			}
            		}
            		// 경사로도 못 두면 그 길을 못 가는 길
            		if(!able) {
            			Arrays.fill(v[r], false);
            			continue; 
            		}
            		// 경사로를 둘 수 있는 길이면 
//            		System.out.print("@경사로 두는 중: ");
            		for(int i=e-1; i>e-1-L; i--) {
//            			System.out.print(i + " ");
            			v[r][i] = true; 
            		}
//            		System.out.println();
            	}else if(mat[r][e-1] > mat[r][e]) {
            		// 높은 곳에서 낮은 곳으로 가는 경우 
            		// 1. 높이차가 1보다 크면 경사로 못 둠 
            		if(mat[r][e-1] - mat[r][e] > 1) {
//            			System.out.println("--행: "+ r + " e:" + e + " 높이차 > 1");
            			able = false; 
            			break;
            		}
            		// 2. 경사로 L 길이만큼 둘 수 있는지 체크 
            		if(e+L>N) {
//            			System.out.println("--행: "+ r + " e:" + e + " L길이 불가");
            			able = false; 
            			break; 
            		}
            		// 3. 경사로 두는 곳의 높이가 다 같은지 체크 
            		int prevH = mat[r][e];
            		for(int i=e; i<e+L; i++) {
            			if(prevH != mat[r][i]) {
//            				System.out.println("--행: "+ r + " e:" + e + " 경사로 높이 불일치");
            				able = false; 
            				break; 
            			}
            			prevH = mat[r][i]; 
            		}
            		// 4. 이미 경사로 둔 곳인지 체크 
            		for(int i=e; i<e+L; i++) {
            			if(v[r][i]) {
//            				System.out.println("--행: "+ r + " e:" + e + " 이미 있음");
            				able = false; 
            				break; 
            			}
            		}
            		if(!able) {
            			Arrays.fill(v[r], false);
            			break; 
            		}
//            		System.out.print("@경사로 두는 중: ");
            		for(int i=e; i<e+L; i++) {
//            			System.out.print(i + " ");
            			v[r][i] = true; 
            		}
//            		System.out.println();
            	}
            }
            if(able) {
                result++; 
//                System.out.println("+가능: 행"+ r+ " cnt:"+ result);
            }
//            else {
//            	System.out.println("-불가능: 행" + r);
//            }
        }
        v = new boolean[N][N];
        // 한 열씩 검사한다. 
        for(int c=0; c<N; c++){
//          int s = 0, e = 0; 
          boolean able = true; 
//          System.out.println("?검색: 열"+ c);
          for(int e=1; e<N; e++) {
          	if(mat[e-1][c] == mat[e][c]) continue;
          	else if(mat[e-1][c] < mat[e][c]) {
          		// 낮은 곳에서 높은 곳으로 가는 경우 
          		// 1. 높이차가 1보다 크면 경사로 못 둠 
          		if(mat[e][c] - mat[e-1][c] > 1) {
//          			System.out.println("--열: "+ c + " e:" + e + " 높이차 > 1");
          			able = false; 
          			break; 
          		}
          		// 2. 경사로 L 길이만큼 둘 수 있는지 체크 
          		if(e-L < 0) {
//          			System.out.println("--열: "+ c + " e:" + e + " L길이 불가");
          			able = false; 
          			break; 
          		}
          		// 3. 경사로 두는 곳의 높이가 다 같은지 체크 
          		int prevH = mat[e-1][c];
          		for(int i=e-1; i>e-1-L; i--) {
          			if(prevH != mat[i][c]) {
//          				System.out.println("--열: "+ c + " e:" + e + " 경사로 높이 불일치");
          				able = false; 
          				break; 
          			}
          			prevH = mat[i][c]; 
          		}
          		// 4. 이미 경사로 둔 곳인지 체크 
          		for(int i=e-1; i>e-1-L; i--) {
          			if(v[i][c]) {
//          				System.out.println("--열: "+ c + " e:" + e + " 이미 있음");
          				able = false; 
          				break; 
          			}
          		}
          		// 경사로도 못 두면 그 길을 못 가는 길
          		if(!able) {
          			for(int i=0; i<N; i++) {
          				v[i][c] = false; 
          			}
          			break; 
          		}
          		// 경사로를 둘 수 있는 길이면 
//          		System.out.print("@경사로 두는 중: ");
          		for(int i=e-1; i>e-1-L; i--) {
//          			System.out.print(i + " ");
          			v[i][c] = true; 
          		}
//          		System.out.println();
          	}else if(mat[e-1][c] > mat[e][c]) {
          		// 높은 곳에서 낮은 곳으로 가는 경우 
          		// 1. 높이차가 1보다 크면 경사로 못 둠 
          		if(mat[e-1][c] - mat[e][c] > 1) {
//          			System.out.println("--열: "+ c + " e:" + e + " 높이차 > 1");
          			able = false; 
          			break;
          		}
          		// 2. 경사로 L 길이만큼 둘 수 있는지 체크 
          		if(e+L>N) {
//          			System.out.println("--열: "+ c + " e:" + e + " L길이 불가");
          			able = false; 
          			break; 
          		}
          		// 3. 경사로 두는 곳의 높이가 다 같은지 체크 
          		int prevH = mat[e][c];
          		for(int i=e; i<e+L; i++) {
          			if(prevH != mat[i][c]) {
//          				System.out.println("--열: "+ c + " e:" + e + " 경사로 높이 불일치");
          				able = false; 
          				break; 
          			}
          			prevH = mat[i][c]; 
          		}
          		// 4. 이미 경사로 둔 곳인지 체크 
          		for(int i=e; i<e+L; i++) {
          			if(v[i][c]) {
//          				System.out.println("--열: "+ c + " e:" + e + " 이미 있음");
          				able = false; 
          				break; 
          			}
          		}
          		if(!able) {
          			for(int i=0; i<N; i++) {
          				v[i][c] = false; 
          			}
          			break; 
          		}
//          		System.out.print("@경사로 두는 중: ");
          		for(int i=e; i<e+L; i++) {
//          			System.out.print(i + " ");
          			v[i][c] = true; 
          		}
//          		System.out.println();
          	}
          }
          if(able) {
              result++; 
//              System.out.println("+가능: 열"+ c+ " cnt:"+ result);
          }
//          else {
//          	System.out.println("-불가능: 열" + c);
//          }
        }
        System.out.println(result);
    }
}
