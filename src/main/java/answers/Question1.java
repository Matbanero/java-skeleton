package answers;

public class Question1 {
	public static int bestMergedPortfolio(int[] portfolios) {
		// TODO comment it better
		return partitionPortoflios(portfolios, 0, portfolios.length - 1, 0, portfolios.length - 1, 0, 14);
	}

	private static int partitionPortoflios(int[] portfolios, int lsmall, int lbig, int rsmall, int rbig,
											int portVal, int bit) {
		
		// Base case
		if (bit < 0) {
			return portVal;
		}

		int sent = 1 << bit;
		int j = lsmall;
		int m = rsmall;

		for (int i = lsmall; i <= lbig; i++) {
			if ((portfolios[i] & sent) != 0) {
				int tempInd = j++;
				int temp = portfolios[i];
				portfolios[i] = portfolios[tempInd];
				portfolios[tempInd] = temp;
			}
		}

		for (int i = rsmall; i <= rbig; i++) {
			if ((portfolios[i] & sent) != 0) {
				int tempInd = m++;
				int temp = portfolios[i];
				portfolios[i] = portfolios[tempInd];
				portfolios[tempInd] = temp;
			}
		}

		// Recursive case, similar to qsort
		if (j > lsmall && j <= lbig) {
			int result = 0;
			if (m > rsmall) {
				result = partitionPortoflios(portfolios, j, lbig, rsmall, m-1, portVal * 2 + 1, bit - 1);
			}
			if (m <= rbig) {
				result = Math.max(result, partitionPortoflios(portfolios, lsmall, j-1, m, rbig, portVal * 2 + 1, bit - 1));
			}
			return result;
		} else if (j <= lsmall) {
			if (m > rsmall) {
				return partitionPortoflios(portfolios, lsmall, lbig, rsmall, m-1, portVal * 2 + 1, bit - 1);
			} else {
				return partitionPortoflios(portfolios, lsmall, lbig, rsmall, rbig, portVal * 2, bit - 1);
			}
		} else {

			if (m <= rbig) {
				return partitionPortoflios(portfolios, lsmall, lbig, m, rbig, portVal * 2 + 1, bit - 1);
			} else {
				return partitionPortoflios(portfolios, lsmall, lbig, rsmall, rbig, portVal * 2, bit - 1);
			}
		}

	}
}
