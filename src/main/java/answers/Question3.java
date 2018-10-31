package answers;
import java.util.ArrayList;
import helpers.Edge;
import java.util.*;

public class Question3 {
	
	private static boolean res = true;
	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
	
		if (numNodes < 0) {
			return 0;
		}

		if (edgeList.length == 0) {
			return numNodes;
		}

		int biggestEdge = 0;
		 List<List<Integer>> graph = new ArrayList<>();
        
        for(int i = 0; i < numNodes; i++) {
        	graph.add(new ArrayList<Integer>());   
		}
		
		for (int i = 0; i < edgeList.length; i++) {
			if (edgeList[i].getEdgeA() > biggestEdge) {
            	biggestEdge = edgeList[i].getEdgeA();
            }
            if (edgeList[i].getEdgeB() > biggestEdge) {
            	biggestEdge = edgeList[i].getEdgeB();
            }
		}

        // If there is less nodes than edges 'show' - it is clearly an error
        if (numNodes < biggestEdge) {
			return 0;
		}

        // Populate graph
        for(Edge edge : edgeList) {
            graph.get(edge.getEdgeA() - 1).add(edge.getEdgeB() - 1);
            graph.get(edge.getEdgeB() - 1).add(edge.getEdgeA() - 1);
        }

        // Split nodes to two sets (with use of edges)
        Set<Integer> setA=new HashSet<>();
        Set<Integer> setB=new HashSet<>();

        // Use DFS to 'color' the nodes
        for(int i = 0; i < numNodes; i++) {
            if (!setA.contains(i) && !setB.contains(i)) {
            	DFS(setA, setB, graph, i);
            }
        }

        // If the graph is bipartite return score
        if (res) {  	
	        return numNodes - 2 * Math.min(setA.size(), setB.size());
        } else {
        	return 0;
        }
    }
    
    public static void DFS(Set<Integer> a, Set<Integer> b, List<List<Integer>> graph, int root) {
        
        // If we can't color graph return (one base case)
        if (!res) {
        	return;
        }

        // If there is no root node in both a and b, add root to a
        if (!a.contains(root) && !b.contains(root)) {
        	a.add(root);
        }


		// For all the nodes adjecent to the root progress DFS
        for(int i = 0; i < graph.get(root).size(); i++) {
            if (a.contains(root) && a.contains(graph.get(root).get(i))) {
            	res = false;

            } else if (b.contains(root) && b.contains(graph.get(root).get(i))) {
            	res = false;

            } else if (a.contains(root) && !b.contains(graph.get(root).get(i))) {
                b.add(graph.get(root).get(i));
                DFS(a, b, graph, graph.get(root).get(i));

            } else if (b.contains(root) && !a.contains(graph.get(root).get(i))) {
                a.add(graph.get(root).get(i));
                DFS(a, b, graph, graph.get(root).get(i));
            }
        }
    }
}
