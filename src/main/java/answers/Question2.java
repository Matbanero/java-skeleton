package answers;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Question2 {

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {
		int minOut = 0;
		for (int i = 0; i < cashflowOut.length; i++) {
			if (cashflowOut[i] > minOut) {
				minOut = cashflowOut[i];
			}
		}
		final int offset = minOut + 1;

		cashflowOut = Arrays.stream(cashflowOut).map(i -> -i).toArray();
		ArrayList<Integer> total = new ArrayList<>();
		total.addAll(Arrays.stream(cashflowIn).boxed().collect(Collectors.toList()));
		total.addAll(Arrays.stream(cashflowOut).boxed().collect(Collectors.toList()));
		Collections.sort(total);
		// Shifting numbers to get rid of negatives
		total.replaceAll(i -> i + offset);

		int sum = total.stream().mapToInt(i -> i).sum();

		boolean[][] Q = new boolean[total.size() + 1][sum + 1];

		for(int i = 0; i <= sum; i++){
            Q[0][i] = false;
        }

		for (int j = 0; j <= sum; j++) {	
			for (int i = 1; i <= total.size(); i++) {
				
				int x = total.get(i - 1);
				
				if (j - x >= 0) {
					Q[i][j] = Q[i - 1][j] || x == j || Q[i - 1][j - x];
				} else {		
					Q[i][j] = Q[i - 1][j] || x == j;
				}
			}
		}

		
		for (int i = 1; i <= total.size(); i++) {
			for (int j = 0; j <= sum; j++) {		
				int dummy = (total.size() - i) * offset + j;
				if (dummy > sum) {
					continue;
				} else {
					if (Q[total.size()][dummy]) {
							return j;
					}	
				}
			}
		}
		return -1;
	}

}
