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
				if(i == totalValue) {
					count++;
				}
			}
		}
		return count;
	}

	private static int max(int i, int j) {
		return (i > j) ? i : j;
	}


}
