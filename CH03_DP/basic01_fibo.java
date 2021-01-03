package CH03_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic01_fibo {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		System.out.println("01. fibo 기본 : " + fibo_basic(N));

		// 한 번 계산된 결과를 메모이제이션(Memoization)하기 위한 배열 초기화
		int memoization[] = new int[100];
		System.out.println("02. fibo Top-Down(Memoization) : " + fibo_memo_top_down(N, memoization));
		
		// DP table 배열 set up
		int DP[] = new int[100];
//		DP[0] = 0;	// 이미 0이라 생략
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
	// 피보나치 함수(Fibonacci Function)를 재귀함수로 구현 (탑다운 다이나믹 프로그래밍)
	private static int fibo_memo_top_down(int n, int memo[]) {
		// 종료 조건
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		// 이미 계산한 적 있는 문제라면 그대로 반환
		if (memo[n] != 0) {
			return memo[n];
		}
		// 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
		memo[n] = fibo_memo_top_down(n - 1, memo) + fibo_memo_top_down(n - 2, memo);
		return memo[n];
	}
	
	// 피보나치 함수(Fibonacci Function) 반복문으로 구현(보텀업 다이나믹 프로그래밍)
	private static int fibo_dp_bottom_up(int n, int[] dP) {
		for (int i = 2; i <= n; i++) {
			dP[i] = dP[i - 1] + dP[i - 2];
		}
		return dP[n];
	}

}
