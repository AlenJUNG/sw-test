import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://code0xff.tistory.com/28?category=723754
// https://www.acmicpc.net/problem/11437

public class Answer_Basic {
	private static int[] parent;
	private static int[] depth;
	private static BufferedReader br;
	private static StringTokenizer st;
	private static ArrayList<Integer>[] con;
	private static int N;
	private static int a;
	private static int b;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = null;

		N = Integer.parseInt(br.readLine().trim());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = -1;
		}
		depth = new int[N + 1];
		con = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			con[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			con[a].add(b);
			con[b].add(a);
		}
		
		dfs(1, 1, 0);
		
		int M = Integer.parseInt(br.readLine().trim());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine().trim());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			int aDepth = depth[a];
			int bDepth = depth[b];

			while (aDepth > bDepth) {
				a = parent[a];
				aDepth--;
			}
			while (bDepth > aDepth) {
				b = parent[b];
				bDepth--;
			}
			while (a != b) {
				a = parent[a];
				b = parent[b];
			}
			System.out.println(a);
		}
	}

	private static void dfs(int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;
		for (int nxt : con[cur]) {
			if (nxt != p) {
				dfs(nxt, d + 1, cur);
			}
		}
	}
}