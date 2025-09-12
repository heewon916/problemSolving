import java.io.*;
import java.util.*;

public class Solution_d9_2383_점심식사시간_버전2 {

    static final int M = 1, W = 2, D = 3, C = 4; // M: 이동중, W: 대기중, D: 내려가는 중, C: 완료 

    static class Person implements Comparable<Person> {
        int r, c, /*status,*/ arrivalTime/* , downCnt*/; // 행, 열, 상태, 계단입구도착시간, 내려간계단수 
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
        /*
        void init(){
            arrivalTime = downCnt = 0; 
            status = M; 
        }
            */
        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.arrivalTime, o.arrivalTime);
        }
    }
    static int N, min, cnt; //맵의 크기, 최소 이동 시간, 사람 수 
    static int[][] sList; // 계단 리스트 (계단 : r,c,height) 계단 0, 계단 1
    static ArrayList<Person> pList; // 사람 리스트 
    static int[] selected; // 사람마다 어떤 계단 배정되었는지 여부 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; 
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++){
            N = Integer.parseInt(br.readLine()); // 맵의 크기 
            pList = new ArrayList<>(); 
            sList = new int[2][];
            min = Integer.MAX_VALUE; 

            for(int i=0, k=0; i<N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<N; j++){
                    int c = Integer.parseInt(st.nextToken());
                    if(c == 1) pList.add(new Person(i, j));
                    else if(c > 1) sList[k++] = new int[]{i, j, c};
                }
            }
            
                    cnt = pList.size();
                    selected = new int[cnt]; // 너 어느 계단으로 갈래? 
            
                    // 첫번째 사람부터 계단 어디로 내려보낼지 정하기 
                    divide(0);
                    System.out.println("#"+tc+" "+min);
        }
    }
    private static void divide(int index){ // index에 해당하는 사람의 계단 배정 
        if(index == cnt){ // 배정 끝남
            makeList(); 
            return; 
        }
        
        selected[index] = 0; 
        divide(index+1);
        selected[index] = 1; 
        divide(index+1);
    }

    private static void makeList(){ // 계단 배정에 따른 사람들 리스트에 배치
        ArrayList<Person>[] list = new ArrayList[] {new ArrayList<Person>(), new ArrayList<Person>()};

        for(int i=0; i<cnt; i++){
            Person p = pList.get(i);
            // p.init(); // 사람들의 상태를 새로운 계단 배정 위해 초기화 
            int no = selected[i]; // 사람 i에 배정된 계단 
            
            p.arrivalTime = Math.abs(p.r - sList[no][0]) + Math.abs(p.c - sList[no][1]); 
            list[no].add(p); 
        }

        // 각각의 게단의 사람리스트들을 이용해서 내려가기 구현 
        int timeA = processDown(list[0], sList[0][2]); // 계단 0으로 내려가는 사람들을 처리한다.
        int timeB = processDown(list[1], sList[1][2]); // 계단 1으로 내려가는 사람들을 처리한다. 

        int res = Math.max(timeA, timeB); // 두 계단을 모두 내려가는데 소요되는 시간 
        min = Math.min(min, res);

    }
    private static int processDown(ArrayList<Person> list, int height) {
        if(list.size() == 0) return 0; 
        // size>0인 경우, 사람들이 그 계단으로 내려간다는 뜻이므로, 계단 도착 시간 기준으로 오름차순 정렬해, 순서대로 내려보낸다. 
        Collections.sort(list); 

        int size = list.size() + 3; 
        int[] D = new int[size]; // 3인덱스: 계단입구에 가장 빨리 도착하는 사람0을 의미한다, 4인덱스: 그 다음 도착 ... 
        for (int i = 3; i < size; i++) {
            Person p = list.get(i-3);
            if(D[i-3] <= p.arrivalTime+1){ // 3앞에 있는 사람의 도착시간이, 내가 도착해서 대기하는 시간보다 작거나 같다면 
                D[i] = p.arrivalTime + 1 + height; 
            }else{
                // 대기가 필요한 경우
                D[i] = D[i-3] + height; // 내 앞에 3번째에 있는 사람보다는 height 만큼 내려간 뒤에야 내려갈 수 있게 된다. 
            }
        }
        return D[size-1];


        /*
        // 첫번째 사람부터 내려보내자. 
        int time = list.get(0).arrivalTime; // 첫 번째 사람의 도착시간부터 시뮬레이션 시작 
        int size = list.size(); // 이 계단을 이용하는 사람 수 
        int ingCnt = 0, cCnt = 0; // 내려가고 있는 사람 수, 완료된 사람 수 


        while(true){ // 매분마다 사람들의 상태를 업데이트 
            for(int i=0; i<size; i++){
                Person p = list.get(i); 
                if(p.status == C) continue; 

                if(p.arrivalTime == time){ // 도착했으면 대기 1분을 위해서 상태만 수정 
                    p.status = W; 
                } else if(p.status == W && ingCnt < 3){ // 대기 1분 후에는, 내려갈 수 있는 사람이 되었으니, 계단에 내려가고 있는 사람이 3명 아래면 
                    p.status = D; 
                    p.downCnt = 1; 
                    ++ingCnt; 
                } else if(p.status == D){
                    if(p.downCnt < height) { // 아직 계단 높이만큼 못 내려갔으면 
                        ++p.downCnt; 
                    } else{ // 다 내려간 경우 
                        p.status = C; 
                        --ingCnt; 
                        ++cCnt; 
                    }
                }
            }
            // 완료된 사람의 수 == 모든 사람의 수 -> 끝 break 
            if(cCnt == size) break; 
            ++time; 
        }
        return time; 
        */
    }
}
