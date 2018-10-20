package answers;

import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		int X = numNodes;
		int Y = 0;
		int biggestFrequency = 0;
		int [] frequency = new int[numNodes];

		for (int i = 1; i <= numNodes; i++) {
			int counter = 0;
			for (Edge edge : edgeList) {
				if (edge.getEdgeA() == i || edge.getEdgeB() == i) {
					counter++;
				}
			}
			if (counter > biggestFrequency) {
				biggestFrequency = counter;
			}
			frequency[i - 1] = counter;
		}
		for (int freq : frequency) {
			if (freq == biggestFrequency) {
				X--;
				Y++;
			}
		}

		return X - Y;
	}

}
