import java.io.*;
import java.util.*;

//https://code0xff.tistory.com/28?category=723754
//https://www.acmicpc.net/problem/11437

public class hj01_Solution {
	static int N, M;	//	N : 노드의 개수, M : 공통 조상을 알고싶은 쌍의 개수
	static int parent[];	// 부모 정보
	static int depth[];		// 각 노드까지의 깊이 > 0부터 시작
	static ArrayList<Integer> wire[];	// 연결정보 : 각 배열에 ArrayList 할당

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		depth = new int[N+1];	// * 0부터 시작하더라도 N+1 만큼 배열 만들어주기
		parent = new int[N+1];
		
//		for(int i = 1; i <= N; i++) {	// 기본에서는 필요 없음
//			parent[i] = -1;
//		}
		
		// 반복 숙달 필요 > graph 배열 생성 후 배열 내 ArrayList
		wire = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향 그래프 연결
			wire[a].add(b);
			wire[b].add(a);
		}
		
		/********************** 입력부 끝 **********************/
		
		// STEP 01 : 모든 노드에 대한 깊이 계산 (DFS stack over flow 에러 시, BFS로 전환)
		BFS(1, 0);	// root 정점 1(depth = 0) 부터 모든 노드에 대한 깊이 계산
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			// STEP 02 : 최소공통조상을 찾을 두 노드 확인
			bw.write(LCA(a, b)+"\n");
		}
		
		/********************** 마지막 입출력 처리 **********************/
		br.close();
		bw.flush();
		bw.close();		

	}
	
	// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
	private static void DFS(int v, int dep, int par) {
		depth[v] = dep;		// 정점의 depth를 depth 배열에 입력
		parent[v] = par;	// 정점의 부모정보를 parent 배열에 입력
		
		// A. 정점과 연결되어 있는 모든 정점들에 대하여
		for(int next : wire[v]) {
			if(next != par) {		// 정점이 부모가 아니면 재귀
				DFS(next, dep + 1, v);
			}
		}
		
	}	
	
	
	private static void BFS(int root, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(root);
		depth[root] = dep;		// 최초 시작점 v의 깊이는 0
		parent[root] = 0;	// 최초 시작점 v의 부모는 0
		
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
	
	// STEP 02 : 최소공통조상을 찾을 두 노드 확인
	private static int LCA(int a, int b) {
		// A. 두 노드의 depth 맞추기
		while(depth[a] != depth[b]) {
			if(depth[a] > depth[b]) {
				a = parent[a];	//	dep가 높은 쪽에 계속 부모 정보 대입
			}else {
				b = parent[b];
			}
		}
//		만약 b의 깊이 조정을 한 직후 a와 b가 같다면 a를 출력 후 return
		if(a == b) {
			return a;
		}
		// B. 두 노드가 같아질 때까지 동시에 부모노드로 거슬러 올라감
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}		
		return a;	// 당연히 b로 return 해도 상관 없음	
	}
}
