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

public class Answer_dijkstra_�⺻ {
	public static final int INF = (int) 1e9; // ������ �ǹ��ϴ� ������ 10���� ����
	// ����� ����(N), ������ ����(M), ���� ��� ��ȣ(Start)
	// ����� ������ �ִ� 100,000����� ����
	public static int N, M, start;
	// Array ���� 01 : �� ��忡 ����Ǿ� �ִ� ��忡 ���� ������ ��� �迭
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	// �ִ� �Ÿ� ���̺�, �湮���� üũ �迭
	public static int dis[], visited[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/CH4_dijkstra/Answer_dijkstra_�⺻.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		// Array ���� 02 : Array �ȿ� Array ����
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		// ��� ���� ������ �Է¹ޱ�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			// Array ���� 03 : 2�� Array�� Node ���� �� add 
			graph.get(from).add(new Node(to, value));
		}

		dis = new int[N + 1];
		visited = new int[N + 1];
		
		// �ִ� �Ÿ� ���̺��� ��� �������� �ʱ�ȭ
		Arrays.fill(dis, INF);
		
		// ���ͽ�Ʈ�� �˰����� ����
		dijkstra(start);
		
		// ��� ���� ���� ���� �ִ� �Ÿ��� ���
        for (int i = 1; i <= N; i++) {
            // ������ �� ���� ���, ����(INFINITY)�̶�� ���
            if (dis[i] == INF) {
                System.out.println("INFINITY");
            }
            // ������ �� �ִ� ��� �Ÿ��� ���
            else {
            	System.out.printf("start node(%d) to node(%d) = %d\n", start, i, dis[i]);
            }
        }
	}

	private static void dijkstra(int startN) {
		dis[startN] = 0;
		visited[startN] = 1;
		// from ��忡 ����� �� ������ �Ÿ��� dis �迭�� �Է�
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

	// �湮���� ���� ��� �߿���, ���� �ִ� �Ÿ��� ª�� ����� ��ȣ�� ��ȯ
	private static int getSmallest_N() {
		int min_value = INF;
		int index = 0; // ���� �ִ� �Ÿ��� ª�� ���(�ε���)
		for (int v = 1; v <= N; v++) {
			if (dis[v] < min_value && visited[v] == 0) {
				index = v;
			}
		}
		return index;
	}

}
