package CH03_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnswerEx01_1로만들기 {
	static int N, DP[], ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		// 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화 
		DP = new int[30001];

		dp_bottom_up(N, DP);

	}
	
	// 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
	private static void dp_bottom_up(int n, int[] dp) {
		for (int i = 2; i <= n; i++) {
			// 현재의 수에서 1을 빼는 경우
			dp[i] = dp[i - 1] + 1;
			
			// 현재의 수가 2로 나누어 떨어지는 경우
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			// 현재의 수가 3으로 나누어 떨어지는 경우
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			// 현재의 수가 5로 나누어 떨어지는 경우
			if (i % 5 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 5] + 1);
			}
		}
		System.out.println(dp[n]);

	}

}
