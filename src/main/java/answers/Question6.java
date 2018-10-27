package answers;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

public class Question6 {
	private static int[] dist;
	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		dist = new int[numServers];
		
		PriorityQueue<Integer> servers = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer serv1, Integer serv2) {
				// if (dist[serv1] < Integer.MAX_VALUE && dist[serv2] < Integer.MAX_VALUE) {
				// 	return new Integer(dist[serv1] + times[serv1][targetServer]).compareTo(
				// 				new Integer(dist[serv2] + times[serv2][targetServer]));
				// } else {
					return new Integer(dist[serv1]).compareTo(
								new Integer(dist[serv2]));
				// }
			}
		});

		int timeFromSource = times[0][targetServer];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		servers.add(0);
		
		for (int i = 0; i < numServers; i++) {
			if ((times[i][targetServer] < timeFromSource)
				 && (times[0][i] < timeFromSource)) {
				servers.add(i);
			}
		}

		// If there is only one server in queue (source server) return it's time
		// as a shortest one to target.
		if (servers.size() == 1) {
			return timeFromSource;
		}

		// Otherwise do the dijikstra search
		while (!servers.isEmpty()) {
			int selectedServer = servers.poll();

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
		return Math.min(dist[targetServer], timeFromSource);
	}
}
