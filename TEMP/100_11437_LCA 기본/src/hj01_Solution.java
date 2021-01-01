import java.io.*;
import java.util.*;

//https://code0xff.tistory.com/28?category=723754
//https://www.acmicpc.net/problem/11437

public class hj01_Solution {
	static int N, M;	//	N : ����� ����, M : ���� ������ �˰���� ���� ����
	static int parent[];	// �θ� ����
	static int depth[];		// �� �������� ���� > 0���� ����
	static ArrayList<Integer> wire[];	// �������� : �� �迭�� ArrayList �Ҵ�

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		depth = new int[N+1];	// * 0���� �����ϴ��� N+1 ��ŭ �迭 ������ֱ�
		parent = new int[N+1];
		
//		for(int i = 1; i <= N; i++) {	// �⺻������ �ʿ� ����
//			parent[i] = -1;
//		}
		
		// �ݺ� ���� �ʿ� > graph �迭 ���� �� �迭 �� ArrayList
		wire = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// ����� �׷��� ����
			wire[a].add(b);
			wire[b].add(a);
		}
		
		/********************** �Էº� �� **********************/
		
		// STEP 01 : ��� ��忡 ���� ���� ��� (DFS stack over flow ���� ��, BFS�� ��ȯ)
		BFS(1, 0);	// root ���� 1(depth = 0) ���� ��� ��忡 ���� ���� ���
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
			bw.write(LCA(a, b)+"\n");
		}
		
		/********************** ������ ����� ó�� **********************/
		br.close();
		bw.flush();
		bw.close();		

	}
	
	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void DFS(int v, int dep, int par) {
		depth[v] = dep;		// ������ depth�� depth �迭�� �Է�
		parent[v] = par;	// ������ �θ������� parent �迭�� �Է�
		
		// A. ������ ����Ǿ� �ִ� ��� �����鿡 ���Ͽ�
		for(int next : wire[v]) {
			if(next != par) {		// ������ �θ� �ƴϸ� ���
				DFS(next, dep + 1, v);
			}
		}
		
	}	
	
	
	private static void BFS(int root, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(root);
		depth[root] = dep;		// ���� ������ v�� ���̴� 0
		parent[root] = 0;	// ���� ������ v�� �θ�� 0
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next : wire[v]) {
				if(next == parent[v]) {
					continue;
				}
				
				depth[next] = depth[v] + 1;
				parent[next] = v;
				q.offer(next);
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
//		���� b�� ���� ������ �� ���� a�� b�� ���ٸ� a�� ��� �� return
		if(a == b) {
			return a;
		}
		// B. �� ��尡 ������ ������ ���ÿ� �θ���� �Ž��� �ö�
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}		
		return a;	// �翬�� b�� return �ص� ��� ����	
	}
}
