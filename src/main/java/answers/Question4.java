package answers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int k = rows[0].length;
		ArrayList<Integer> possibleSol = new ArrayList<>();
		
		// Testing if there are possible solutions
		for (int i = 0; i < rows.length; i++) {
			
			PriorityQueue<Integer> machinesToFix = new PriorityQueue<>();
			PriorityQueue<Integer> bestCandidates = new PriorityQueue<>();
			int minSum = Integer.MAX_VALUE;

			for (int j = 0; j < k; j++) {
				// Test if the current machine is X or not
				if (!rows[i][j].equals("X")) {
					machinesToFix.add(Integer.parseInt(rows[i][j]));
					
					if (machinesToFix.size() == k) {
						for (int m = 0; m < numberMachines; m++) {
							bestCandidates.add(machinesToFix.poll());
						}

					} else if (machinesToFix.size() >= numberMachines) {
						// To many priority queues but hey...
						PriorityQueue<Integer> temp = new PriorityQueue<>(machinesToFix);
						PriorityQueue<Integer> tempToCopy = new PriorityQueue<>(machinesToFix);

						int tempSum = 0;
						for (int m = 0; m < numberMachines; m++) {
							tempSum += temp.poll();
						}

						if (tempSum < minSum) {
							minSum = tempSum;
							bestCandidates.clear();
							for (int m = 0; m < numberMachines; m++) {
								bestCandidates.add(tempToCopy.poll());
							}					
						}
					}

				} else {	
					machinesToFix.clear();					
					if (j >= k - numberMachines) {
						// Stop the loop - it is impossible to get another solution
						j = k;
					}
				}
			}		

			if (bestCandidates.size() != 0) {
				for (int j = 0; j < numberMachines; j++) {
					possibleSol.add(bestCandidates.poll());
				}
			}
		}

		if (possibleSol.size() == 0) {
			return 0;
		} else {
			int sum = 0;

			for (int i = 0; i < numberMachines; i++) {
				sum += possibleSol.get(i);
			}
			
			int curr = sum;
			for (int i = numberMachines; i < possibleSol.size(); i++) {
				curr += possibleSol.get(i) - possibleSol.get(i-numberMachines);
				sum = Math.min(sum, curr);
			}

			return sum;
		}

	}

}
