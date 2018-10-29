package answers;

public class Question5 {

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int [] shareBank = new int [totalValue+1];
		int sum = 1;

		while (sum <= totalValue) {
			int min = -1;
			for (int i = 0 ; i < allowedAllocations.length ; i++) {
				if (sum >= allowedAllocations[i] && shareBank[sum - allowedAllocations[i]] != -1) {
					int temp = shareBank[sum - allowedAllocations[i]] + 1;
					if (min < 0) {
						min = temp;
					} else {
						min = Math.min(temp, min);
					}
				}
			}
			shareBank[sum] = min;
			sum++;
		}
		return shareBank[totalValue];
	}

}
