import java.io.*; 
import java.util.*;
public class Solution_d4_1249_보급로{
    public static void main(String[] args){

    }
}

/*
[이해]
구해야 하는 것: 출발지 s에서 도착지까지 복구시간이 가장 짧은 경로에 대한 총 복구 시간 
이동 경로는 상하좌우 
출발정점이 정해져 있으니까 다익스트라가 아닐까? 

[입력]
정점개수 N 100

[구현] 
int[][] dist <- 출발정점 s에 대해서 갈 수 있는 최소 거리 배열 
boolean[][] visited <- 방문 처리하기 
PQ <- i, j, dist[i][j] 

dist는 전부 MAX로 채우기 
dist[sx][sy] = 0 

pq가 빌 때까지 
    int[] cur <- poll 
    int minVx <- cur[0] 
    int minVy <- cur[1] 
    int min <- cur[2] 

    if(visited[minVx][minVy]) continue; 
    visited[minVx][minVy] = true; 

    for d 0..4 
        int nx = minVx + dx[d]
        int ny = minVy + dy[d] 
        if(범위 벗어나면) continue 
        if(!visited[nx][ny] && dist[nx][ny] > min + mat[nx][ny])
            dist[nx][ny] = min + mat[nx][ny]
            pq.offer(new int[]{nx, ny, dist[nx][ny]})
 */