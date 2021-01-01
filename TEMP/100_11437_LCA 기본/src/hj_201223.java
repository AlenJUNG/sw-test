import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hj_201223 {
//	static int MAX = 50001;	//	문제에서 정점 최대 입력치 입력, 향후 변경 가능
	static int N, M;	//	N : 노드의 개수, M : 공통 조상을 알고싶은 쌍의 개수
	static int parent[];	// 부모 정보
	static int depth[];		// 각 노드까지의 깊이 > 0부터 시작
	static int visit[];		// 각 노드의 깊이가 계산되었는지 여부
	static ArrayList<Integer> graph[];	// 그래프 정보 : 각 배열에 ArrayList 할당

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
		depth = new int[N+1];	// * 0부터 시작하더라도 N+1 만큼 배열 만들어주기
		visit = new int[N+1];
		
		// 반복 숙달 필요 > graph 배열 생성 후 배열 내 ArrayList
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// 반복 숙달 필요 > list로 그래프 만들기
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향 그래프 연결
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// STEP 01 : 모든 노드에 대한 깊이 계산
		DFS(1, 0);	// root 정점 1(depth = 0) 부터 모든 노드에 대한 깊이 계산
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			// STEP 02 : 최소공통조상을 찾을 두 노드 확인
			bw.write(LCA(a, b)+"\n");
		}
		
		// ** 마지막 입출력 처리 잊지 말기
		br.close();
		bw.flush();
		bw.close();		

	}
	
	

	// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
	private static void DFS(int v, int dep) {
		visit[v] = 1;	//	방문체크
		depth[v] = dep;	// 정점의 depth를 depth 배열에 입력
		
		// A. 정점과 연결되어 있는 모든 정점들에 대하여
		for(int w = 0; w < graph[v].size(); w++) {
			int child_v = graph[v].get(w);
			
			if(visit[child_v] == 0) {	//	B. 연결 정점을 방문한 적이 없다면?
				parent[child_v] = v;	//	C. 부모 정보 저장
				DFS(child_v, dep + 1);	//	D. 재귀 수행
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
		// B. 두 노드가 같아질 때까지 동시에 부모노드로 거슬러 올라감
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}		
		return a;		
	}
}
