import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class test {
	static final int MAX_N = 100000;	//	�������� ���� �ִ� �Է�ġ �Է�, ���� ���� ����
	static final int MAX_D = 17;	// 2^17 = 13�� ������
	static int N, M;		//	N : ����� ����, M : ���� ������ �˰���� ���� ����
	static int parent[][];	// �θ� ����
	static int depth[];		// �� �������� ���� > 0���� ����
	static int tree[];		// �� ����� ���̰� ���Ǿ����� ����
	static ArrayList<Integer> graph[];	// �׷��� ���� : �� �迭�� ArrayList �Ҵ�

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[MAX_D + 1][MAX_N + 1];	//	parent[����][���]
		depth = new int[N+1];
		tree = new int[N+1];
		
		graph = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}		
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// ���� ��� -1 ó�� > ���� �ʿ��Ѱ�?
		for(int i = 1; i <= N; i++) {
			tree[i] = -1;
		}
		
		DFS(1, 0);
		
		System.out.println("ans >> " + parent[3][15]);
		
		M = Integer.parseInt(br.readLine());
					
		for(int i = 1; i <= M; i++) {
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
//	private static void setParent() {
//		
//		for(int i = 1; i < LOG; i++) {
//			for(int j = 1; j <= N; j++) {
//				parent[j][i] = parent[parent[j][i - 1]][i - 1];
//			}
//		}
//		
//	}
	
	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void DFS(int v, int dep) {
		// �������� ������ ��尡 �ƴ� ��� return ó�� 
		if(tree[v] != -1) {
			return;
		}
						
		tree[v] = dep;	// ������ depth�� tree �迭�� �Է�
		
		// A. ������ ����Ǿ� �ִ� ��� �����鿡 ���Ͽ�
		for(int next : graph[v]) {
			if(tree[next] != -1) {	// B. �̹湮 ������ �ƴϸ� PASS
				continue;
			}
			
			parent[0][next] = v;	// C. �θ� ���� ���� : next�� �θ� ������ parent[0][next]�� ����
			
			// DP
			for(int i = 1; i <= MAX_D ; i++) {
				if(parent[i - 1][next] == 0) {	// �θ������� 0�̶�� break
					break;
				}
				// �θ������� 0�� �ƴ� ���
				parent[i][next] = parent[i - 1][ parent[i - 1][next] ];
			}
			
			DFS(next, dep + 1);
		}
	}
	
	// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
	private static int LCA(int a, int b) {
		// A. �� ����� depth ���߱�
			// b�� �� ���� ����
		if(tree[a] > tree[b]) {
			swap(a, b);
		}
		
		// ���� ���̰� �����ϵ��� ����
		for(int i = MAX_D; i >= 0; i--) {
			if(tree[b] - tree[a] >= Math.pow(2, i)) {	// 2�� �� ���� �ö󰡾��ϴ��� Ȯ�� ***
				b = parent[i][b];	// b�� b�� i��° �θ� ����
			}
		}
		
		//	���� b�� ���� ������ �� ���� a�� b�� ���ٸ� a�� ��� �� return
		if(a == b) {
			return a;
		}
		
		// �θ� ���� ���� ��� ������ ���� �Ž��� �ö󰡱�
		for(int i = MAX_D; i >= 0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		// �Ϸ�Ǹ� �θ� ã���� �ϴ� ���� ��� �� return;		
		return parent[0][a];
	}

	private static void swap(int a, int b) {
		int temp;
		temp = a;
		a = b;
		b = temp;
		return;		
	}
}
