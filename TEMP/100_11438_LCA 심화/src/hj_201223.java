import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hj_201223 {
	static int MAX_N = 100000;	//	문제에서 정점 최대 입력치 입력, 향후 변경 가능
	static int MAX_D = 17;
	static int N, M;		//	N : 노드의 개수, M : 공통 조상을 알고싶은 쌍의 개수
	static int parent[][];	// 부모 정보
	static int depth[];		// 각 노드까지의 깊이 > 0부터 시작
	static int visit[];		// 각 노드의 깊이가 계산되었는지 여부
	static ArrayList<Integer> graph[];	// 그래프 정보 : 각 배열에 ArrayList 할당

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
			
			// STEP 02 : 최소공통조상을 찾을 두 노드 확인
			bw.write(LCA(a, b)+"\n");
		}
		
		// ** 마지막 입출력 처리 잊지 말기
		br.close();
		bw.flush();
		bw.close();			

	}
	
	

	// 전체 부모 관계를 설정하는 함수
	private static void setParent() {
		DFS(1, 0);
		
		for(int i = 1; i <= MAX_D; i++) {
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[ parent[j][i - 1] ][ i - 1 ];
			}
		}
		
	}
	
	// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
	private static void DFS(int v, int dep) {
		visit[v] = 1;	//	방문체크
		depth[v] = dep;	// 정점의 depth를 depth 배열에 입력
		
		// A. 정점과 연결되어 있는 모든 정점들에 대하여
		for(int w = 0; w < graph[v].size(); w++) {
			int child_v = graph[v].get(w);
			
			if(visit[child_v] == 0) {	//	B. 연결 정점을 방문한 적이 없다면?
				parent[child_v][0] = v;	//	C. 부모 정보 저장
				DFS(child_v, dep + 1);	//	D. 재귀 수행
			}
		}		
	}
	
	// STEP 02 : 최소공통조상을 찾을 두 노드 확인
	private static int LCA(int a, int b) {
		// A. 두 노드의 depth 맞추기
			// b가 더 깊도록 설정
		if(depth[a] > depth[b]) {
			swap(a, b);
		}
		
		// 먼저 깊이가 동일하도록 설정
		for(int i = MAX_D ; i >= 0 ; i--) {
			if(depth[b] - depth[a] >= Math.pow(2, i)) {	// 2의 몇 제곱 올라가야하는지 확인 ***
				b = parent[b][i];	// b에 b의 i번째 부모값 대입
			}
		}
		
		//	만약 b의 깊이 조정을 한 직후 a와 b가 같다면 a를 출력 후 return
		if(a == b) {
			return a;
		}
		
		// 부모가 같지 않은 경우 조상을 향해 거슬러 올라가기
		for(int i = MAX_D ; i >= 0 ; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		// 완료되면 부모가 찾고자 하는 조상 출력 후 return;		
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
