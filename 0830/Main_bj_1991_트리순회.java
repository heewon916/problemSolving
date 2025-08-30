import java.io.*; 
import java.util.*; 

public class Main_bj_1991_트리순회 {
    static class Node{
        char left, right; 
        public Node(char left, char right){
            this.left = left; 
            this.right = right; 
        }
    }
    static Map<Character, Node> tree; 
    static String answer = "";
    static void preorder(char node){
        if(node == '.') return; 
        answer += node; 
        preorder(tree.get(node).left);
        preorder(tree.get(node).right);
    }
    static void inorder(char node){
        if(node == '.') return; 
        inorder(tree.get(node).left);
        answer += node; 
        inorder(tree.get(node).right);
    }
    static void postorder(char node){
        if(node == '.') return; 
        postorder(tree.get(node).left);
        postorder(tree.get(node).right);
        answer += node; 
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        tree = new HashMap<>(); 
        // 입력 받은 알파벳에 - 'A' 해서 int로 받으면 됨 

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            char me = st.nextToken().charAt(0); 
            char left = st.nextToken().charAt(0); 
            char right = st.nextToken().charAt(0); 
            tree.put(me, new Node(left, right));
        }
        preorder('A');
        answer += '\n';
        inorder('A');
        answer += '\n';
        postorder('A');
        answer += '\n';
        System.out.println(answer);
    }
}
/*
[배운 점]
1. 트리를 표현할 때 Map<Character, Node> map = new HashMap<>()으로 표현할 수 있다 
2. map에 요소를 추가할 때는 map.put(현재 노드, 양쪽 자식 노드 추가를 위해 new Node(left, right));
 */