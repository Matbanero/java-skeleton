package answers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int k = rows[0].length;
		ArrayList<Integer> possibleSol = new ArrayList<>();
		
		// Testing if there are possible solutions
		for (int i = 0; i < rows.length; i++) {
			
			int count = 0;
			ArrayList<String> machinesToFix = new ArrayList<>();
			PriorityQueue<Integer> bestCandidates = new PriorityQueue<>();
			
			for (int j = 0; j < k; j++) {
				// Test if the current machine is X or not
				if (!rows[i][j].equals("X")) {
					count++;
					machinesToFix.add(rows[i][j]);
					
					if (machinesToFix.size() == k) {
						for(String m : machinesToFix) {
								bestCandidates.add(Integer.parseInt(m));
						}
					}

				} else {
					
					if (machinesToFix.size() >= numberMachines) {
						
						for(String m : machinesToFix) {
							bestCandidates.add(Integer.parseInt(m));
						}	
					}
					
					count = 0;
					machinesToFix.clear();
					
					if (j >= k - numberMachines) {
						// Stop the loop
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
					// Parse int was here
				sum += possibleSol.get(i);
			}
			
			int curr = sum;
			for (int i = numberMachines; i < possibleSol.size(); i++) {
				// parse ints were here
				curr += possibleSol.get(i) - possibleSol.get(i-numberMachines);
				sum = Math.min(sum, curr);
			}

			return sum;
		}

	}

}
