package answers;
import java.util.ArrayList;
import helpers.Edge;
import java.util.*;

public class Question3 {

	private static Map<Integer,List<Integer>> colorMap = new HashMap<>();

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		if (numNodes < 0) {
			return 0;
		}

		int biggestEdge = 0;
		
		for (Edge edge : edgeList) {
            if (colorMap.get(edge.getEdgeA()) == null) {
            	colorMap.put(edge.getEdgeA(), new ArrayList<>()); 
            }	
            colorMap.get(edge.getEdgeA()).add(edge.getEdgeB());

            if (colorMap.get(edge.getEdgeB()) == null) {
            	colorMap.put(edge.getEdgeB(), new ArrayList<>()); 
            }
			colorMap.get(edge.getEdgeB()).add(edge.getEdgeA());
			
			if (edge.getEdgeA() > biggestEdge) {
            	biggestEdge = edge.getEdgeA();
            }
            if (edge.getEdgeB() > biggestEdge) {
            	biggestEdge = edge.getEdgeB();
            }
		}
		
		if (numNodes < biggestEdge) {
			return 0;
		}

        int[] colors = new int[numNodes + 1];
        for (int i = 1; i <= numNodes; i++){
            if (colorMap.get(i) == null) {
            	continue;
            }
            if (i < colors.length && colors[i] == 0 && !colorSearch(i, colors, 1)) {
             	return -numNodes;
            }
        }

        int minSetSize = Integer.MAX_VALUE;

        for (int i = 1; i < colorMap.size(); i++) {
        	if (colorMap.get(i) != null && colorMap.get(i).size() < minSetSize) {
        		minSetSize = colorMap.get(i).size();
        	}
        }
        return numNodes - 2 * minSetSize;
	}

	public static boolean colorSearch(int index, int[] colors, int color) {
        if (colors[index] != 0) {  // do the checks
         return color == colors[index];
        }

        int len = colorMap.get(index).size();
        colors[index] = color;

        for (int i = 0; i < len; i++) {
            if (i < colors.length && !colorSearch(colorMap.get(index).get(i), colors, -1 * color)) {
            	return false;
            }
        }
        return true; 
    }
}
