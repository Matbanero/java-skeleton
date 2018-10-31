package answers;

import java.util.Arrays;
import java.util.ArrayList;

public class Question2 {

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {
		int sum1 = Arrays.stream(cashflowIn).sum();
		int sum2 = Arrays.stream(cashflowOut).sum();

		Arrays.sort(cashflowIn);
		Arrays.sort(cashflowOut);

		boolean[][] Q = new boolean[cashflowIn.length + 1][sum1 + 1];
		boolean[][] W = new boolean[cashflowOut.length + 1][sum2 + 1];

		for(int i = 0; i <= sum1; i++){
            Q[0][i] = false;
        }

		for (int j = 0; j <= sum1; j++) {	
			for (int i = 1; i <= cashflowIn.length; i++) {
				
				int x = cashflowIn[i - 1];				
				if (j - x >= 0) {
					Q[i][j] = Q[i - 1][j] || x == j || Q[i - 1][j - x];
				} else {		
					Q[i][j] = Q[i - 1][j] || x == j;
				}
			}
		}

		for(int i = 0; i <= sum2; i++){
            W[0][i] = false;
        }

		for (int j = 0; j <= sum2; j++) {	
			for (int i = 1; i <= cashflowOut.length; i++) {
				
				int x = cashflowOut[i - 1];
				if (j - x >= 0) {
					W[i][j] = W[i - 1][j] || x == j || W[i - 1][j - x];
				} else {		
					W[i][j] = W[i - 1][j] || x == j;
				}
			}
		}

		ArrayList<Integer> possibleAns1 = new ArrayList<>();
		ArrayList<Integer> possibleAns2 = new ArrayList<>();

		for (int i = 0; i <= sum1; i++) {
			if (Q[cashflowIn.length][i]) {
				possibleAns1.add(i);
			}
		}

		for (int i = 0; i <= sum2; i++) {
			if (W[cashflowOut.length][i]) {
				possibleAns2.add(i);
			}
		}

		int minDiff = Integer.MAX_VALUE;

		for (Integer ans1 : possibleAns1) {
			for (Integer ans2 : possibleAns2) {
				int temp = ans1 - ans2;
				if (temp >= 0 && temp < minDiff) {
					minDiff = temp;
				}
			}
		}

		return minDiff;
	}
}
