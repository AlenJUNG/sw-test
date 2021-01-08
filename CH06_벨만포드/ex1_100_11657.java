package CH06_벨만포드;

import java.util.*;
import java.io.*;

public class ex1_100_11657 {
	static final int INF = (int) 1e9;
	static int N, M, S;
	static ArrayList<Node> graph = new ArrayList<Node>();
	static long dis[];
	
	static class Node{
		int from;
		int to;
		int value;
		public Node(int from, int to, int value) {
			this.from = from;
			this.to = to;
			this.value = value;
		}
	}	
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/CH06_벨만포드/Answer_100_11657_타임머신.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = 1;
		
		dis = new long[N+1];
		
		for(int i = 0 ; i <= N; i++) {
			dis[i] = INF;
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(from, to, value));
		}
		
		dis[1] = 0;
		
		for(int round = 1; round <= N; round++) {
			for(Node node : graph) {
				int from = node.from;
				int to = node.to;
				int value = node.value;
				
				if(dis[to] > dis[from] + value) {
					dis[to] = dis[from] + value;
				}
			}
		}
			
		boolean iscycle = false;
		
		for(Node node : graph) {
			int from = node.from;
			int to = node.to;
			int value = node.value;
			
			if(dis[to] > dis[from] + value) {
				iscycle = true;
				break;
			}
		}
		
		if(iscycle) {
			System.out.println(-1);
		}else{
			for(int i = 2; i <= N; i++) {
				if(dis[i] == INF) {
					System.out.println(-1);
				}else {
					System.out.println(dis[i]);
				}
				
			}
		}
	}
}
