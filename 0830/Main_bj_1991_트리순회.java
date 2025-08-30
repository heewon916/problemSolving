import java.io.*; 
import java.util.*; 

public class Main_bj_1991_트리순회 {
    static class Node{
        String me, left, right; 
        public Node(String me, String left, String right){
            this.me = me; 
            this.left = left; 
            this.right = right; 
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        // 입력 받은 알파벳에 - 'A' 해서 int로 받으면 됨 

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String me = st.nextToken(); 
            String left = st.nextToken(); 
            String right = st.nextToken(); 
            Node root = Node(me, left, right);
        }

    }
}
