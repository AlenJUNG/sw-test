import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hj_201223 {
	static int MAX_N = 100000;	//	�������� ���� �ִ� �Է�ġ �Է�, ���� ���� ����
	static int MAX_D = 17;
	static int N, M;		//	N : ����� ����, M : ���� ������ �˰���� ���� ����
	static int parent[][];	// �θ� ����
	static int depth[];		// �� �������� ���� > 0���� ����
	static int visit[];		// �� ����� ���̰� ���Ǿ����� ����
	static ArrayList<Integer> graph[];	// �׷��� ���� : �� �迭�� ArrayList �Ҵ�

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[MAX_N + 1][MAX_D + 1];
		depth = new int[MAX_D + 1];
		visit = new int[MAX_N + 1];
		
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		setParent();
		
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
	
	

	// ��ü �θ� ���踦 �����ϴ� �Լ�
	private static void setParent() {
		DFS(1, 0);
		
		for(int i = 1; i <= MAX_D; i++) {
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[ parent[j][i - 1] ][ i - 1 ];
			}
		}
		
	}
	
	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void DFS(int v, int dep) {
		visit[v] = 1;	//	�湮üũ
		depth[v] = dep;	// ������ depth�� depth �迭�� �Է�
		
		// A. ������ ����Ǿ� �ִ� ��� �����鿡 ���Ͽ�
		for(int w = 0; w < graph[v].size(); w++) {
			int child_v = graph[v].get(w);
			
			if(visit[child_v] == 0) {	//	B. ���� ������ �湮�� ���� ���ٸ�?
				parent[child_v][0] = v;	//	C. �θ� ���� ����
				DFS(child_v, dep + 1);	//	D. ��� ����
			}
		}		
	}
	
	// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
	private static int LCA(int a, int b) {
		// A. �� ����� depth ���߱�
			// b�� �� ���� ����
		if(depth[a] > depth[b]) {
			swap(a, b);
		}
		
		// ���� ���̰� �����ϵ��� ����
		for(int i = MAX_D ; i >= 0 ; i--) {
			if(depth[b] - depth[a] >= Math.pow(2, i)) {	// 2�� �� ���� �ö󰡾��ϴ��� Ȯ�� ***
				b = parent[b][i];	// b�� b�� i��° �θ� ����
			}
		}
		
		//	���� b�� ���� ������ �� ���� a�� b�� ���ٸ� a�� ��� �� return
		if(a == b) {
			return a;
		}
		
		// �θ� ���� ���� ��� ������ ���� �Ž��� �ö󰡱�
		for(int i = MAX_D ; i >= 0 ; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		// �Ϸ�Ǹ� �θ� ã���� �ϴ� ���� ��� �� return;		
		return parent[a][0];
	}

	private static void swap(int a, int b) {
		int temp;
		temp = a;
		a = b;
		b = temp;
		return;		
	}

}
