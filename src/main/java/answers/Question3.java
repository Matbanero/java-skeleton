package answers;
import java.util.ArrayList;
import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {

		boolean graph[][] = new boolean[numNodes + 1][numNodes + 1];
		ArrayList<Integer> setA = new ArrayList<>();
		ArrayList<Integer> setB = new ArrayList<>();

		// Performing Binary Search on the graph to get minimal value of nodes required to be removed,
		// to separate all the nodes.
		for (Edge e : edgeList) {
			graph[e.getEdgeA()][e.getEdgeB()] = true;
			graph[e.getEdgeB()][e.getEdgeA()] = true;
			if (!setA.contains(e.getEdgeA()) && !setB.contains(e.getEdgeA())) {
				setA.add(e.getEdgeA());
			}
			if (!setB.contains(e.getEdgeB()) && !setA.contains(e.getEdgeB())) {
				setB.add(e.getEdgeB());
			}
		}

		return setA.size() - setB.size();
	}
}
