package CH02_이진탐색;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AnswerEx01_부품찾기 {
	static int N, M, n_num[], m_num[];

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/CH02_이진탐색/AnswerEx01_부품찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		N = Integer.parseInt(br.readLine());
		n_num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			n_num[i] = Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(n_num);
				
		M = Integer.parseInt(br.readLine()); 
		m_num = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int x = 1; x <= M; x++) {
			m_num[x-1] = Integer.parseInt(st.nextToken()); 
			binarySearch(0, N-1, m_num[x-1]);
		}	
		
		
		
	}

	private static void binarySearch(int start, int end, int target) {
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(n_num[mid] == target) {
				System.out.println("yes");
				return;
			}else if(n_num[mid] > target) {
				end = mid - 1;
				
			}else {
				start = mid + 1;
			}
		}
		System.out.println("no");
		return;
	}
}
