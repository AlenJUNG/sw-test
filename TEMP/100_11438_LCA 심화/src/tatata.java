import java.util.ArrayList;

public class tatata {
	static ArrayList<Integer> graph[];
	static int tree[];
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		graph = new ArrayList[16];
		tree = new int[8];
		
		for(int i = 1; i <= 15; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= 7; i++) {
			tree[i] = -1;
		}
		
		graph[1].add(2);
		graph[1].add(3);
		graph[2].add(1);
		graph[2].add(4);
		graph[2].add(5);
		graph[3].add(1);
		graph[3].add(6);
		graph[3].add(7);
		
		for(int i = 1; i <= 10 ; i++) {
			if(i == 2) {	// �θ������� 0�̶�� break
				System.out.println("run break");
//				break;
				continue;
			}
			System.out.println("keep going "+i);
		}
		
//		for(int v = 1; v <= 3; v++) {
//			for(int next : graph[v]) {
//				System.out.println("next = " + next);
//				if(tree[next] == -1) {	// B. ���� ������ �湮�� ���� ���ٸ�?
//					System.out.println("continue ���� ����");
////					continue;
//					break;
//				}
//				System.out.println("continue ������");
//				
//			}
//			
//		}
		

	}

}
