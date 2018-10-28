package answers;

public class Question5 {

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int shareBank[] = new int[totalValue + 1];
		shareBank[0] = 0;
		int allocations[] = new int[totalValue + 1];
		allocations[0] = -1;
		int count = 0;
		
		for(int i = 1; i <= totalValue; i++) {
			allocations[i] = allocations[i-1];
			for(int j = 0; j < allowedAllocations.length; j++) {
				if(allowedAllocations[j] <= i) {
					allocations[i] = j;
					shareBank[i] = Math.max(shareBank[i], shareBank[i - allowedAllocations[j]] + allowedAllocations[j]);
				}
			}
		}
		System.out.println(shareBank[totalValue]);
		int k = totalValue;

		while (k >= 0) {
			int x = allocations[k];
			if (x == -1) break;
			count++;
			k -= allowedAllocations[allocations[k]];
		}

		return count;
	}

}
