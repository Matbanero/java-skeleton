package answers;
import java.util.ArrayList;

public class Question5 {

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int maxVal = 0;
		int minVal = totalValue;
		for (int i = 0; i < allowedAllocations.length; i++) {
			if (allowedAllocations[i] > maxVal) {
				maxVal = allowedAllocations[i];
			}

			if (allowedAllocations[i] < minVal) {
				minVal = allowedAllocations[i];
			}
		}

		System.out.println("Max weight: " + maxVal + "\nMin weight: " + minVal);

		
		int n = allowedAllocations.length;
		int w[] = new int[allowedAllocations.length + 1];
		int p[] = new int[allowedAllocations.length + 1];
		
		for (int i = 1; i <= allowedAllocations.length; i++) {
			w[i] = allowedAllocations[i - 1];
			p[i] = allowedAllocations[i - 1];
		}

		int g[] = new int[totalValue + maxVal + 1];
		int d[] = new int[totalValue + maxVal + 1];

		for (int i = 1; i <= totalValue + maxVal; i++) {
			g[i] = 0;
			d[i] = n;
		}


		for (int i = 1; i <= n; i++) {
			if (g[w[i]] < p[i]) {
				g[w[i]] = p[i];
				d[w[i]] = i;
			}
		}

		int optimal = 0;
		int optCap = 0;
		for (int y = minVal; y <= totalValue; y++) {
			if (g[y] <= optimal) {
			 	continue;
			}

			optimal = g[y];
			optCap = y;
			for (int i = 1; i <= d[y]; i++) {
				if (g[y + w[i]] < g[y] + p[i]) {
					g[y + w[i]] = g[y] + p[i];
					d[y + w[i]] = i;
				}
			}

			System.out.println("\nOptimal solution: " + optimal);
			System.out.println('\n');
			for (int k = 0; k < g.length; k++) {
				System.out.println(k + ": " + g[k]);
			}
			System.out.println('\n');

			for (int k = 0; k < g.length; k++) {
				System.out.println(k + ": " + d[k]);
			}
			System.out.println('\n');
		}

		
		int i = d[optCap];
		ArrayList<Integer> sol = new ArrayList<>();
		int solSum = 0;
		while (solSum != optimal) {
			solSum += w[i];
			sol.add(w[i]);
			i = d[optCap - w[i]];
		}
		return sol.size();
	}

}
