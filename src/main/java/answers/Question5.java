package answers;


public class Question5 {

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int shareBank[] = new int[totalValue + 1];

		int count = 0;
		for(int i = 0; i <= totalValue; i++) {
			for(int j = 0; j < allowedAllocations.length; j++) {
				if(allowedAllocations[j] <= i) {
					shareBank[i] = max(shareBank[i], shareBank[i - allowedAllocations[j]] + allowedAllocations[j]);
				}
			}
		}
		int sol = shareBank[totalValue];
		for (int i = allowedAllocations.length; i > 0 && sol > 0; i-- ) {
			if (sol == shareBank[i]) {
				continue;
			} else {
				count++;
				sol = sol - allowedAllocations[i - 1];
			}
		}
		return count;
	}

	private static int max(int i, int j) {
		return (i > j) ? i : j;
	}


}
