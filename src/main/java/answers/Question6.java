package answers;

import java.util.ArrayList;

public class Question6 {

	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		int dist[] = new int[numServers];
		ArrayList<Integer> servers = new ArrayList<>();

		for (int i = 0; i < numServers; i++) {
			dist[i] = times[0][i];
			servers.add(i);
		}

		dist[0] = 0;
		while (!servers.isEmpty()) {
			int minDist = Integer.MAX_VALUE;
			int selectedServer = 0;
			for (int i = 1; i < servers.size(); i++) {
				if (minDist > dist[i]) {
					minDist = dist[i];
					selectedServer = i;
				}
			}
			servers.remove(new Integer(selectedServer));

			if (selectedServer == targetServer) {
				return dist[selectedServer];
			}

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

		return -1;
	}
}
