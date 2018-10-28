package answers;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Question2 {

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {

		cashflowOut = Arrays.stream(cashflowOut).map(i -> -i).toArray();
		int sumStep1 = Arrays.stream(cashflowIn).sum();
		int sumStep2 = Arrays.stream(cashflowOut).sum();

		ArrayList<Integer> total = new ArrayList<>();
		total.addAll(Arrays.stream(cashflowIn).boxed().collect(Collectors.toList()));
		total.addAll(Arrays.stream(cashflowOut).boxed().collect(Collectors.toList()));
		Collections.sort(total);

		int size = sumStep1 - sumStep2;
		int offset = -sumStep2;
		int offVal = -total.get(0);

		boolean[][] Q = new boolean[total.size()][size];

		for (int j = offset; j < size + offset; j++) {	
			for (int i = 0; i < total.size(); i++) {
				
				int x = total.get(i) + offVal;
				if (i == 0) {
					Q[i][j - offset] = x == j - offset;
				} else {
					if (j - offset - x > 0) {
						int aid = j - x - offset;
						boolean aid2 = Q[i-1][aid];
						Q[i][j - offset] = Q[i-1][j - offset] || x == j - offset || aid2;
					} else {
						Q[i][j - offset] = Q[i-1][j - offset] || x == j - offset;
					}
				}
			}
		}

		int i = offset;
		while (!Q[total.size() - 1][i]) {
			i++;
		}
		return i - offset;
	}

}
