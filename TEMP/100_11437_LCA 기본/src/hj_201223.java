import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hj_201223 {
//	static int MAX = 50001;	//	�������� ���� �ִ� �Է�ġ �Է�, ���� ���� ����
	static int N, M;	//	N : ����� ����, M : ���� ������ �˰���� ���� ����
	static int parent[];	// �θ� ����
	static int depth[];		// �� �������� ���� > 0���� ����
	static int visit[];		// �� ����� ���̰� ���Ǿ����� ����
	static ArrayList<Integer> graph[];	// �׷��� ���� : �� �迭�� ArrayList �Ҵ�

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
//		parent = new int[MAX];	
//		depth = new int[MAX];
//		visit = new int[MAX];
		
		parent = new int[N+1];	
		depth = new int[N+1];	// * 0���� �����ϴ��� N+1 ��ŭ �迭 ������ֱ�
		visit = new int[N+1];
		
		// �ݺ� ���� �ʿ� > graph �迭 ���� �� �迭 �� ArrayList
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// �ݺ� ���� �ʿ� > list�� �׷��� �����
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// ����� �׷��� ����
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// STEP 01 : ��� ��忡 ���� ���� ���
		DFS(1, 0);	// root ���� 1(depth = 0) ���� ��� ��忡 ���� ���� ���
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
			bw.write(LCA(a, b)+"\n");
		}
		
		// ** ������ ����� ó�� ���� ����
		br.close();
		bw.flush();
		bw.close();		

	}
	
	

	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void DFS(int v, int dep) {
		visit[v] = 1;	//	�湮üũ
		depth[v] = dep;	// ������ depth�� depth �迭�� �Է�
		
		// A. ������ ����Ǿ� �ִ� ��� �����鿡 ���Ͽ�
		for(int w = 0; w < graph[v].size(); w++) {
			int child_v = graph[v].get(w);
			
			if(visit[child_v] == 0) {	//	B. ���� ������ �湮�� ���� ���ٸ�?
				parent[child_v] = v;	//	C. �θ� ���� ����
				DFS(child_v, dep + 1);	//	D. ��� ����
			}
		}		
	}
	
	// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
	private static int LCA(int a, int b) {
		// A. �� ����� depth ���߱�
		while(depth[a] != depth[b]) {
			if(depth[a] > depth[b]) {
				a = parent[a];	//	dep�� ���� �ʿ� ��� �θ� ���� ����
			}else {
				b = parent[b];
			}
		}
		// B. �� ��尡 ������ ������ ���ÿ� �θ���� �Ž��� �ö�
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}		
		return a;		
	}
}
