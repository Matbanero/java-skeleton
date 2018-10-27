package answers;

public class Question1 {
	private final static int MAX_SCORE = 65535;
	// Size of the int which will be used for further operations (used in the problem)
	public static int bestMergedPortfolio(int[] portfolios) {
		int bestResult = 0;

		// Using nested to loop to check n * (n-1) combinations of portfolios
		for (int i = 0; i < portfolios.length; i++) {	
			for (int j = i + 1; j < portfolios.length; j++) {
				// Using binary xor to achieve the question requirement
				int result = portfolios[i] ^ portfolios[j];
				// Check if current result is better than best result
				if (result > bestResult) {
					bestResult = result;
				}
				// If our result is equal to max possible score, then we can stop searching further
				if (result == MAX_SCORE) {
					return bestResult;
				}
			}
		}
		return 0;
	}

}
