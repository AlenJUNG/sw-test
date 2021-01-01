package CH02_����Ž��;
// �߿� : Ž���ϰ��� �ϴ� �迭�� �̹� �����迭���� �����ϰ� ����

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Answer01_�ݺ��� {
	static int N, Target, arr[], result;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src\\CH02_����Ž��\\basic_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// ������ ����(n)�� ã���� �ϴ� ��(target)�� �Է¹ޱ� 
		N = Integer.parseInt(st.nextToken());
		Target = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		
		// ��ü ���� �Է¹ޱ� > "1 ~ N" or "0 ~ N-1"
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// ���� Ž�� ���� ��� ��� 
        int result = binarySearch(arr, Target, 1, N);
        
        if (result == -1) {
            System.out.println("���Ұ� �������� �ʽ��ϴ�.");
        }
        else {
            System.out.println(result);
        }
	}

	// ���� Ž�� �ҽ��ڵ� ����(�ݺ���)
	public static int binarySearch(int arr[], int target, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			// ã�� ��� �߰��� �ε��� ��ȯ
			if (arr[mid] == target) {
				return mid;
			// �߰����� ������ ã���� �ϴ� ���� ���� ��� ���� Ȯ��
			} else if (arr[mid] > target) {
				end = mid - 1;
				// �߰����� ������ ã���� �ϴ� ���� ū ��� ������ Ȯ��
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

}
