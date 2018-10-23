package answers;

import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {

		
		int graph[][] = new int[numNodes + 1][numNodes + 1];

		// Performing Binary Search on the graph to get minimal value of nodes required to be removed,
		// to separate all the nodes.
		for (Edge e : edgeList) {
			graph[e.getEdgeA()][e.getEdgeB()] = 1;
			graph[e.getEdgeB()][e.getEdgeA()] = 1;
		}

		int l = 1;
		int r = numNodes;

		while (r > l) {
			int mid = (l + r) >> 1;
			if (hasRelation(numNodes, mid, edgeList.length, graph) == false) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}

		return numNodes - (2 * l);
	}

	public static boolean hasRelation(int numNodes, int subsetSize, int numberOfEdges, int[][] graph) {
		int set = (1 << subsetSize) - 1;
		int limit = (1 << numNodes);

		int[][] visited = new int[numNodes + 1][numNodes + 1];

		while (set < limit) {
			visited = new int[numNodes + 1][numNodes + 1];

			int coveredRelations = 0;

			for (int j = 1, e = 1; j < limit; j = j << 1, e++) {
				if ((set & j) == 1) {
					// Will mark all the edges (relations) going from this exchange (node)
					for (int i = 1; i <= numNodes; i++) {
						if ((graph[e][i] == 1) && (visited[e][i] == 0)) {
							visited[e][i] = 1;
							visited[i][e] = 1;
							coveredRelations++;
						}
					}
				}
			}

			if (coveredRelations == numberOfEdges) {
				return true;
			}

			int previousBit = set & -set;
			int previousCombination = set + previousBit;
			set = (((previousCombination ^ set) >> 2) / previousBit) | previousCombination;
		}	
		return false;	
	}
}
