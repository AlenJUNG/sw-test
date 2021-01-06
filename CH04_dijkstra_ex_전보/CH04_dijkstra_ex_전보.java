package CH04_dijkstra_ex_전보;

import java.io.*;
import java.util.*;

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

	@Override
	public int compareTo(Node others) {
		if (this.distance < others.distance) {
			return -1;
		}
		return 1;
	}

}

public class CH04_dijkstra_ex_전보 {
	static int INF = (int) 1e9;
	static int N, M, start;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int dis[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/CH04_dijkstra_ex_전보/input.txt"));
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

		int cnt = 0;
		int time = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			if (0 < dis[i] && dis[i] != 1e9) {
				cnt++;
			}
			time = Math.max(time, dis[i]);

		}

		System.out.println(cnt + " " + time);

	}

	private static void dijkstra(int startN) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(startN, 0));
		dis[startN] = 0;

		while (!pq.isEmpty()) {
			Node v = pq.poll();
			int now = v.getIdx();
			int cost = v.getDistance();

			if (dis[now] < cost) {
				continue;
			}

			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				int value = dis[now] + graph.get(now).get(toIdx).getDistance();

				if (value < dis[graph.get(now).get(toIdx).getIdx()]) {
					dis[graph.get(now).get(toIdx).getIdx()] = value;
					pq.offer(new Node(graph.get(now).get(toIdx).getIdx(), graph.get(now).get(toIdx).getDistance()));
				}
			}
		}
	}
}
