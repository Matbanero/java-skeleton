package answers;

import java.util.HashSet;
import java.util.Set;

public class Question1 {
	private static int BIT = 31;
	public static int bestMergedPortfolio(int[] portfolios) {
		int bestSolution = 0;
		int sent = 0;
		
		for (int i = BIT; i >= 0; i--) {
			sent = sent | (1 << i);
			
			Set<Integer> portfolioSet = new HashSet<>();
			for (int p : portfolios) {
				portfolioSet.add(p & sent);
			}

			int temp = bestSolution | (1 << i);
			for (int pre : portfolioSet) {
				if (portfolioSet.contains(temp ^ pre)) {
					bestSolution = temp;
					break;
				}
			}
		}

		return bestSolution;
	}
}
