package CH03_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnswerEx01_1�θ���� {
	static int N, DP[], ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		// �ռ� ���� ����� �����ϱ� ���� DP ���̺� �ʱ�ȭ 
		DP = new int[30001];

		dp_bottom_up(N, DP);

	}
	
	// ���̳��� ���α׷���(Dynamic Programming) ����(���Ҿ�)
	private static void dp_bottom_up(int n, int[] dp) {
		for (int i = 2; i <= n; i++) {
			// ������ ������ 1�� ���� ���
			dp[i] = dp[i - 1] + 1;
			
			// ������ ���� 2�� ������ �������� ���
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			// ������ ���� 3���� ������ �������� ���
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			// ������ ���� 5�� ������ �������� ���
			if (i % 5 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 5] + 1);
			}
		}
		System.out.println(dp[n]);

	}

}
