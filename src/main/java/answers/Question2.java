package answers;

import java.util.Arrays;

public class Question2 {

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {
		int sumStep1 = Arrays.stream(cashflowOut).sum();
		int result = calculateDiff(cashflowIn.length, sumStep1, cashflowIn);
		result = calculateDiff(cashflowOut.length, result, cashflowOut);

		return result;
	}

	private static int calculateDiff(int streamLen, int streamSum, int[] cashflow) {
		boolean cashStep1[][] = new boolean[streamLen + 1][streamSum + 1];

		for (int i = 0; i <= cashStep1.length; i++) {
			cashStep1[i][0] = true;
		}

		for (int i = 1; i <= streamSum; i++) {
			cashStep1[0][i] = false;
		}

		for (int i = 1; i <= cashStep1.length; i++) {
			for (int j = 1; j <= streamSum; j++) {
				cashStep1[i][j] = cashStep1[i - 1][j];

				if (cashflow[i - 1] <= j) {
					cashStep1[i][j] |= cashStep1[i - 1][j - cashflow[i - 1]];
				}
			}
		}

		int diff = Integer.MAX_VALUE;

		for (int i = streamSum / 2; i >= 0; i--) {
			if (cashStep1[cashflow.length][i] == true) {
				diff = streamSum - 2 * i;
			}
		}

		return diff;
	}

}
