package answers;
import java.util.ArrayList;
import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		ArrayList<Integer> setA = new ArrayList<>();
		ArrayList<Integer> setB = new ArrayList<>();

		for (Edge e : edgeList) {
			if (!setA.contains(e.getEdgeA()) && !setB.contains(e.getEdgeA())) {
				setA.add(e.getEdgeA());
			}
			if (!setB.contains(e.getEdgeB()) && !setA.contains(e.getEdgeB())) {
				setB.add(e.getEdgeB());
			}
		}
		return Math.abs(setA.size() - setB.size());
	}
}
