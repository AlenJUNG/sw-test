package CH4_dijkstra;

import java.util.*;
import java.io.*;

class Node {
	int idx;
	int distance;

	public Node(int idx, int distance) {
		this.idx = idx;
		this.distance = distance;
	}

	public int getIdx() {
		return this.idx;
	}

	public int getDistance() {
		return this.distance;
	}
}

public class Answer_dijkstra_기본 {
	public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	// 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)
	// 노드의 개수는 최대 100,000개라고 가정
	public static int N, M, start;
	// Array 생성 01 : 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	// 최단 거리 테이블, 방문여부 체크 배열
	public static int dis[], visited[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/CH4_dijkstra/Answer_dijkstra_기본.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		// Array 생성 02 : Array 안에 Array 생성
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		// 모든 간선 정보를 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			// Array 생성 03 : 2중 Array에 Node 생성 후 add 
			graph.get(from).add(new Node(to, value));
		}

		dis = new int[N + 1];
		visited = new int[N + 1];
		
		// 최단 거리 테이블을 모두 무한으로 초기화
		Arrays.fill(dis, INF);
		
		// 다익스트라 알고리즘을 수행
		dijkstra(start);
		
		// 모든 노드로 가기 위한 최단 거리를 출력
        for (int i = 1; i <= N; i++) {
            // 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
            if (dis[i] == INF) {
                System.out.println("INFINITY");
            }
            // 도달할 수 있는 경우 거리를 출력
            else {
            	System.out.printf("start node(%d) to node(%d) = %d\n", start, i, dis[i]);
            }
        }
	}

	private static void dijkstra(int startN) {
		dis[startN] = 0;
		visited[startN] = 1;
		// from 노드에 연결된 각 노드들의 거리를 dis 배열에 입력
		for (int toIdx = 0; toIdx < graph.get(startN).size(); toIdx++) {
			dis[graph.get(startN).get(toIdx).getIdx()] = graph.get(startN).get(toIdx).getDistance();
		}

		for (int run = 0; run < N - 1; run++) {
			int now_N = getSmallest_N();
			visited[now_N] = 1;

			for (int toIdx = 0; toIdx < graph.get(now_N).size(); toIdx++) {
				int value = dis[now_N] + graph.get(now_N).get(toIdx).getDistance();

				if (value < dis[graph.get(now_N).get(toIdx).getIdx()]) {
					dis[graph.get(now_N).get(toIdx).getIdx()] = value;
				}
			}

		}
	}

	// 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환
	private static int getSmallest_N() {
		int min_value = INF;
		int index = 0; // 가장 최단 거리가 짧은 노드(인덱스)
		for (int v = 1; v <= N; v++) {
			if (dis[v] < min_value && visited[v] == 0) {
				index = v;
			}
		}
		return index;
	}

}
