package answers;

import java.util.ArrayList;

public class Question6 {

	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		int dist[] = new int[numServers];
		ArrayList<Integer> servers = new ArrayList<>();
		int source = 0;
		dist[source] = 0;

		for (int i = 0; i < numServers; i++) {
			if (i != source) {
				dist[i] = Integer.MAX_VALUE;
			}
			servers.add(i);
		}

		while (!servers.isEmpty()) {
			int minDist = Integer.MAX_VALUE;
			int selectedServer = 0;
			for (int i = 0; i < servers.size(); i++) {
				if (minDist > dist[i]) {
					minDist = dist[i];
					selectedServer = servers.get(i);
				}
			}
			servers.remove(new Integer(selectedServer));

			for (int i = 0; i < numServers; i++) {
				if (selectedServer == i) {
					continue;
				}
				int newDist = dist[selectedServer] + times[selectedServer][i];
				if (newDist < dist[i]) {
					dist[i] = newDist;
				}
				
			}
		}

		return dist[targetServer];
	}
}
