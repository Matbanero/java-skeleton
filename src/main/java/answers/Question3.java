package answers;

import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		int [][] graph = new int[numNodes][numNodes];
		int [] nodesDegree = new int[numNodes];
		int X = numNodes;
		int Y = 0;

		for (Edge e : edgeList) {
			graph[e.getEdgeA() - 1][e.getEdgeB() - 1] = 1;
			graph[e.getEdgeB() - 1][e.getEdgeA() - 1] = 1;
		}

		int topDegree = 0;
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (graph[i][j] == 1) {
					nodesDegree[i]++;
				}
			}
			if (nodesDegree[i] > topDegree) {
				topDegree = nodesDegree[i];
			}
		}

		do {
			for (int i = 0; i < numNodes; i++) {
				if (nodesDegree[i] == topDegree) {
					X--;
					Y++;
					for (int j = 0; j < numNodes; j++) {
						graph[i][j] = 0;
						graph[j][i] = 0;
						nodesDegree[j]--;
					}
					nodesDegree[i] = 0;
				}
			}

			topDegree = 0;
			for (int i = 0; i < numNodes; i++) {
				if (nodesDegree[i] > topDegree) {
					topDegree = nodesDegree[i];
				}
			}

		} while (topDegree != 0);
		
		return X-Y;
	}

}
