package answers;


// TODO: COME BACK HERE!!!!

public class Question6 {

	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		int shortestDistance = Integer.MAX_VALUE;
		int dist[] = new int[numServers];
		boolean shortesPathVisited[] = new boolean[numServers];
		dist[0] = 0;
		shortesPathVisited[0] = false;

		// Initializing the distances and set of shorthest paths
		for (int i = 1; i < numServers; i++) {
			if (times[i] != times[0]) {
				// Set distance to "infinity", it represents that we don't know real distance
				// from source to given server
				dist[i] = Integer.MAX_VALUE;
				shortesPathVisited[i] = false;
			}
		}

		for (int i = 0; i < numServers - 1; i++) {
			// Finds the shortest path to the server (first server in first iteration)
			int k = minDist(dist, shortesPathVisited);
			shortesPathVisited[k] = true;

			for (int j = 0; j < numServers; j++) {
				if (!shortesPathVisited[j] && times[i][j] != 0 
						&& 	dist[i] + times[i][j] < dist[j]) {
						dist[j] = dist[i] + times[i][j];
					}
				if (dist[j] < shortestDistance) {
					shortestDistance = dist[j];
				}
			}
		}

		return shortestDistance;
	}


	public static int minDist(int dist[], boolean shortesPathVisited[]) {
		
		int min = Integer.MAX_VALUE;
		// If all paths are visited or there is no smaller distance return -1
		int min_i = -1;

		for (int i = 0; i < dist.length; i++) {
			if (shortesPathVisited[i] == false && dist[i] <= min) {
				min = dist[i];
				min_i = i;
			}
		}
		
		return min_i;
	}
}
