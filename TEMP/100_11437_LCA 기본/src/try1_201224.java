import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class try1_201224 {
	static int N, M;
	static int parent[], depth[];
	static ArrayList<Integer> wire[];
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		depth = new int[N + 1];
		
		wire = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			wire[a].add(b);
			wire[b].add(a);
		}
		
		BFS(1, 0);
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			bw.write(LCA(a, b)+"\n");
		}
		
		br.close();
		bw.flush();
		bw.close();

	}

	private static int LCA(int a, int b) {
		while(depth[a] != depth[b]) {
			if(depth[b] > depth[a]) {
				b = parent[b];
			}else {
				a = parent[a];
			}
		}
		
		if(a == b) {
			return a;
		}
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

	private static void BFS(int root, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(root);
		parent[root] = 0;
		depth[root] = dep;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next : wire[v]) {	// ** 틀렸음 wire[v]에 대한 next 주의
				if(parent[v] == next) {
					continue;
				}
				
				q.offer(next);
				parent[next] = v;
				depth[next] = depth[v] + 1;
			}			
		}		
	}
}
