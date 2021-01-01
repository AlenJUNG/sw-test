package CH02_이진탐색;
// 중요 : 탐색하고자 하는 배열이 이미 순차배열임을 가정하고 진행

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Answer01_반복문 {
	static int N, Target, arr[], result;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src\\CH02_이진탐색\\basic_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 원소의 개수(n)와 찾고자 하는 값(target)을 입력받기 
		N = Integer.parseInt(st.nextToken());
		Target = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		
		// 전체 원소 입력받기 > "1 ~ N" or "0 ~ N-1"
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 이진 탐색 수행 결과 출력 
        int result = binarySearch(arr, Target, 1, N);
        
        if (result == -1) {
            System.out.println("원소가 존재하지 않습니다.");
        }
        else {
            System.out.println(result);
        }
	}

	// 이진 탐색 소스코드 구현(반복문)
	public static int binarySearch(int arr[], int target, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			// 찾은 경우 중간점 인덱스 반환
			if (arr[mid] == target) {
				return mid;
			// 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
			} else if (arr[mid] > target) {
				end = mid - 1;
				// 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

}
