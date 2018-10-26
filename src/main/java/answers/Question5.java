package answers;

public class Question5 {

	private static int n;
	private static int [] maxNoAllo;
	private static int [] indexAllo;
	private static int [] bestAllo;
	private static int bestValue = 0;

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		n = allowedAllocations.length;
		maxNoAllo = new int[n];
		indexAllo = new int[n];
		bestAllo = new int[n];


		for (int i = 0; i < n; i++) {
			maxNoAllo[i] = (int)(totalValue / allowedAllocations[i]);
		}

		findBest(0, totalValue, allowedAllocations);
		int numberOfSteps = 0;


		for (int i = 0; i < n; i++) {
			numberOfSteps += bestAllo[i];
		}

		return numberOfSteps;
	}

	private static void findBest(int allocation, int totalValue, int[] allowedAllocations) {
		for (int i = 0; i <= maxNoAllo[allocation]; i++) {
			indexAllo[allocation] = i;
			if (allocation < n - 1) {
				findBest(allocation + 1, totalValue, allowedAllocations);
			} else {
				int currVal = 0;
				int currAllo = 0;
				for (int j = 0; j < n; j++) {
					currVal += indexAllo[j] * allowedAllocations[j];
					currAllo += indexAllo[j] * allowedAllocations[j];
				}

				if (currVal > bestValue && currAllo <= totalValue) {
					bestValue = currVal;
					for (int j = 0; j < n; j++) {
						bestAllo[j] = indexAllo[j];
					}
				}

			}
		}
	}

}
