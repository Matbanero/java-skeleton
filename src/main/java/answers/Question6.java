package answers;

import java.util.PriorityQueue;

public class Question6 {

	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		PriorityQueue<Integer> servers = new PriorityQueue<>();
		int dist[] = new int[numServers];
		int source = 0;
		dist[source] = 0;
		int timeFromSource = times[0][targetServer];
		

		for (int i = 0; i < numServers; i++) {
			if (times[i][targetServer] <= timeFromSource) {
				servers.add(i);
			}
			if (i != source) {
				dist[i] = Integer.MAX_VALUE;
			}
		}

		// If there is only one server in queue (source server) return it's time
		// as a shortest one to target.
		if (servers.size() == 1) {
			return timeFromSource;
		}

		while (!servers.isEmpty()) {
			
			int selectedServer = servers.poll();

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
