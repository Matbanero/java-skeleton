package answers;

public class Question5 {
	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int [] shareBank = new int [totalValue+1];
		int sum = 0;

		while (++sum <= totalValue) {
			int min = -1;
			for (int i = 0 ; i < allowedAllocations.length ; i++) {
				if (sum >= allowedAllocations[i] 
						&& shareBank[sum - allowedAllocations[i]] != -1) {
					int temp = shareBank[sum - allowedAllocations[i]] + 1;
				
					min = min < 0 ? temp : (Math.min(temp, min));
					
				}
			}
			shareBank[sum] = min;
		}
		return shareBank[totalValue];
	}

}
