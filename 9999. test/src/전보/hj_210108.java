package 전보;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
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
		if(this.distance < others.distance) {
			return -1;
		}
		return 1;
	}		
}

public class hj_210108 {
	static final int INF = (int) 1e9;
	static int N, M, S;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int dis[];
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/전보/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, value));
		}
		
		dis = new int[N + 1];
		
		Arrays.fill(dis, INF);
		
		dijkstra(S, graph, dis);
		
		int cnt = 0;
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			if(dis[i] != 0) {
				cnt++;
				ans = Math.max(ans, dis[i]);
			}
		}
		
		System.out.printf("%d %d\n", cnt, ans);

	}

	private static void dijkstra(int startN, ArrayList<ArrayList<Node>> gra, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(startN, 0));
		d[startN] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int now_idx = node.getIdx();
			int now_dis = node.getDistance();
			
			if(d[now_idx] < now_dis) {
				continue;
			}
			
			for(int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				int temp = d[now_idx] + gra.get(now_idx).get(toIdx).getDistance();
				
				if(temp < d[gra.get(now_idx).get(toIdx).getIdx()]) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Node(gra.get(now_idx).get(toIdx).getIdx(), gra.get(now_idx).get(toIdx).getDistance()));
				}
			}
		}
		
	}

}
