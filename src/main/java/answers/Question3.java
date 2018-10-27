package answers;
import java.util.ArrayList;
import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		ArrayList<Integer> setA = new ArrayList<>();
		ArrayList<Integer> setB = new ArrayList<>();

		for (Edge e : edgeList) {
			if (!setA.contains(e.getEdgeA()) && !setB.contains(e.getEdgeA()) && !setA.contains(e.getEdgeB())) {
				setA.add(e.getEdgeA());
			}
			if (!setB.contains(e.getEdgeB()) && !setA.contains(e.getEdgeB()) && ! setB.contains(e.getEdgeA())) {
				setB.add(e.getEdgeB());
			}
		}

		// if ((setA.size() + setB.size()) < numNodes) {
		return numNodes - 2 * Math.min(setA.size(), setB.size());
		// }

		// return Math.abs(setA.size() - setB.size());
	}
}
