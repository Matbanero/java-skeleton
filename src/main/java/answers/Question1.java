package answers;

public class Question1 {
	private static int BIT = 32;
	public static int bestMergedPortfolio(int[] portfolios) {

		int n = portfolios.length;
		int result = 0;
		Node root = new Node();
		insertPortfolio(0, root);
		for (int i = 0; i < n; i++) {
			result = Math.max(result, query(portfolios[i], root));
			insertPortfolio(portfolios[i], root);
		}
		return result;
	}

	private static void insertPortfolio(int portfolio, Node r) {
		for (int i = BIT; i >= 0; i--) {
			int bit = (portfolio & (1 << i));
			bit = bit > 0 ? 1 : 0;
			if (r.getChild(bit) == null) {
				r.setChild(bit);
			}
			r = r.getChild(bit);
		}
	}

	private static int query(int portfolio, Node r) {
		int comb = 0;
		for (int i = BIT; i >= 0; i--) {
			int bit = (portfolio & (1 << i));
			bit = bit > 0 ? 1 : 0;
			int notBit = bit == 0 ? 1 : 0;
			if (r.getChild(notBit) != null) {
				r = r.getChild(notBit);
				comb |= (1 << i);
			} else {
				r = r.getChild(bit);
			}
		}
		return comb;
	}

	private static class Node {
		private Node[] children = new Node[2];
		public Node() {
			this.children[0] = null;
			this.children[1] = null;
		}

		public Node getChild(int n) {
			return this.children[n];
		}

		public void setChild (int n) {
			this.children[n] = new Node();
		}
	}
}
