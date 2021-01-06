package CH04_dijkstra_LV2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
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

	// �Ÿ�(���)�� ª�� ���� ���� �켱������ �������� ����
	@Override
	public int compareTo(Node other) {
		if (this.distance < other.distance) {
			return -1;
		}
		return 1;
	}

}

public class Answer_dijkstra_��ȭ {
	static final int INF = (int) 1e9; // ������ �ǹ��ϴ� ������ 10���� ����
	// ����� ����(N), ������ ����(M), ���� ��� ��ȣ(Start)
	// ����� ������ �ִ� 100,000����� ����
	static int N, M, start;
	// Array ���� 01 : �� ��忡 ����Ǿ� �ִ� ��忡 ���� ������ ��� �迭
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int dis[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/CH04_dijkstra_LV2/Answer_dijkstra_��ȭ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		dis = new int[N + 1];

		Arrays.fill(dis, INF);

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
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// ���� ���� ���� ���� �ִ� ��δ� 0���� �����Ͽ�, ť�� ����
		pq.offer(new Node(startN, 0));
		dis[startN] = 0;

		while (!pq.isEmpty()) { // ť�� ������� �ʴٸ�
			// ���� �ִ� �Ÿ��� ª�� ��忡 ���� ���� ������
			Node v = pq.poll();
			int now = v.getIdx(); // ���� ���
			int cost = v.getDistance(); // ���� �������� ���
			// ���� ��尡 �̹� ó���� ���� �ִ� ����� ����
			if (dis[now] < cost) {
				continue;
			}
			// ���� ���� ����� �ٸ� ������ ������ Ȯ��
			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				int value = dis[now] + graph.get(now).get(toIdx).getDistance();
				// ���� ��带 ���ļ�, �ٸ� ���� �̵��ϴ� �Ÿ��� �� ª�� ���
				if (value < dis[graph.get(now).get(toIdx).getIdx()]) {
					dis[graph.get(now).get(toIdx).getIdx()] = value;
					pq.offer(new Node(graph.get(now).get(toIdx).getIdx(), value));
				}
			}
		}

	}

}
