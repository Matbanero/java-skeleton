package answers;

public class Question1 {
	// Size of the int which will be used for further operations (used in the problem)
	private final static int INT_SIZE = 17;
	public static int bestMergedPortfolio(int[] portfolios) {
		Node root = new Node();
		int bestResult = 0;

		for (int i = 0; i < portfolios.length; i++) {			
			int result = findBestPortfolio(root, portfolios[i], INT_SIZE - 1);
			if (result > bestResult) {
				bestResult = result;
			}
			insertValue(root, portfolios[i]);
		}
		return bestResult;
	}

	// Avoiding private attributes, and getters and setters for the sake of simplicity
	public static class Node {
		Node left;
		Node right;

		public Node() {
			this.left = null;
			this.right = null;
		}
	}

	public static void insertValue(Node r, int x) {
		for (int i = INT_SIZE - 1; i >= 0; i--) {
			int bit = (x & (1 << i));

			if (bit != 0) {
				if (r.right == null) {
					r.right = new Node();
				}
				r = r.right;
			} else {
				if (r.left == null) {
					r.left = new Node();
				}
				r = r.left;	
			}
		}
	}

	public static int findBestPortfolio(Node r, int portfolio, int position) {
		// Base cases when we got to the end of the binary value of portfolio
		//  or our tri has no leafs.

		if (r == null || position < 0) {
			return 0;
		}

		if (position == 0) {
			if ((portfolio & (1 << position)) == 1) {
				return (r.left == null) ? 0 : 1;
			} else {
				return (r.right == null) ? 0 : 1;
			}
		}

		int bit = 0;
		int result = 0;

		// Recursive case which search for the solutions in deeper leafs
		if ((portfolio & (1 << position)) != 0) {
			if (r.left == null) {
				result = findBestPortfolio(r.right, portfolio, position - 1);
				bit = 0;

			} else {
				result = findBestPortfolio(r.left, portfolio, position - 1);
				bit = 1;

			}

		} else {
			if (r.right == null) {
				result = findBestPortfolio(r.left, portfolio, position - 1);		
				bit = 0;

			} else {
				result = findBestPortfolio(r.right, portfolio, position - 1);
				bit = 1;

			}
		}

		// Resets the position of a bit if bit value is 0, sets it otherwise
		if (bit == 0) {
			result &= ~(1 << position);
		} else {
			result |= (1 << position);
		}

		return result;
	}

}
