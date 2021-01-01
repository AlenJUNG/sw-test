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

public class try2_201224 {
	static int MAX_N = 100000;
	static int MAX_D = 17;
	static int N, M;
	static int parent[][];
	static int depth[];
	static ArrayList<Integer> wire[];
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[MAX_D + 1][MAX_N + 1];	// parent는 max + 1 값으로 생성
		depth = new int[MAX_D + 1];				//	**틀림 : depth의 배열은 N+1 만큼 생성
		
		wire = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		
		for(int i = 1; i <= N - 1; i++) {
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
		if(depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
		for(int i = MAX_D; i >= 0; i--) {	// 바로 for 부터 시작한다, MAX_N가 아니라 MAX_D ** 
			if(depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}
		
		
		if(a == b) {
			return a;
		}
		
		for(int i = MAX_D; i >= 0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		return parent[0][a];
	}

	private static void BFS(int root, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(root);
		parent[0][root] = 0;
		depth[root] = dep;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next : wire[v]) {
				if(next == parent[0][v]) {
					continue;
				}
				
				q.offer(next);
				parent[0][next] = v;
				depth[next] = depth[v] + 1;
				
				// DP 메모제이션 > 틀렸음
				for(int i = 1; i <= MAX_D; i++) {
					if(parent[i - 1][next] == 0) {
						break;
					}
					parent[i][next] = parent[i-1][parent[i-1][next]];
				}
			}
					
		}
	}

}

























