package CH03_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic01_fibo {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		System.out.println("01. fibo �⺻ : " + fibo_basic(N));

		// �� �� ���� ����� �޸������̼�(Memoization)�ϱ� ���� �迭 �ʱ�ȭ
		int memoization[] = new int[100];
		System.out.println("02. fibo Top-Down(Memoization) : " + fibo_memo_top_down(N, memoization));
		
		// DP table �迭 set up
		int DP[] = new int[100];
//		DP[0] = 0;	// �̹� 0�̶� ����
		DP[1] = 1;
		System.out.println("03. fibo Bottom-Up(DP table) : " + fibo_dp_bottom_up(N, DP));
	}

	private static int fibo_basic(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fibo_basic(n - 1) + fibo_basic(n - 2);
	}
	// �Ǻ���ġ �Լ�(Fibonacci Function)�� ����Լ��� ���� (ž�ٿ� ���̳��� ���α׷���)
	private static int fibo_memo_top_down(int n, int memo[]) {
		// ���� ����
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		// �̹� ����� �� �ִ� ������� �״�� ��ȯ
		if (memo[n] != 0) {
			return memo[n];
		}
		// ���� ������� ���� ������� ��ȭ�Ŀ� ���� �Ǻ���ġ ��� ��ȯ
		memo[n] = fibo_memo_top_down(n - 1, memo) + fibo_memo_top_down(n - 2, memo);
		return memo[n];
	}
	
	// �Ǻ���ġ �Լ�(Fibonacci Function) �ݺ������� ����(���Ҿ� ���̳��� ���α׷���)
	private static int fibo_dp_bottom_up(int n, int[] dP) {
		for (int i = 2; i <= n; i++) {
			dP[i] = dP[i - 1] + dP[i - 2];
		}
		return dP[n];
	}

}
