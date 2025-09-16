import java.io.*; 
import java.util.*; 
public class Main_bj_15685_드래곤커브 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(null)
    }
}
/*
일단 고민 중.. 
세대 안에서 방향 전환이 어떻게 이루어지는지 규칙은 찾았어. 
예를 들어, 2세대에서 3세대로 간다고 했을 떄,

2세대:

1. 좌표 순서: 0,0/ 1,0/ 1,-1/ 0,-1/ 0, -2

2. 방향 전환: 0 1 2 1

그러면 방향 전환은 시계 방향으로 한 번 돌아서, 3 0 1 0이 되는데, 

기존의 끝점부터 새로운 좌표로 이동하는 게 나으니까 정반대 방향(+2)으로 돌려서 2 3 2 1의 방향이 되는 거지

그래서 총 방향 전환 순서는 0 1 2 1 2 3 2 1 로 만들어서, 좌표들을 하나하나 만들어 큐로 관리

최악의 시간복잡도를 계산해봤는데 N이 최대 20이니까 20세대일 때 방향 전환은 10^6정도, 만들어진 방향 순서에 대해 좌표 추가도 10^6 걸리는데, 이러한 세대가 20개가 있다고 한다면 20 * (2*10^6)이잖아. 근데 시간제한은 1초이고.. 가능할까? 가능할지 아닐지만 알려주라. 어떻게 바꿔야 한다, 이렇게 코드를 작성해라는 알고 싶지 않아
 */