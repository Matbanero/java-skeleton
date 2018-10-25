package answers;

import java.util.ArrayList;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int k = rows[0].length;
		ArrayList<String> possibleSol = new ArrayList<>();
		
		// Testing if there are possible solutions
		for (int i = 0; i < rows.length; i++) {
			int count = 0;
			ArrayList<String> machinesToFix = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				// You can optimize it here so if X is after k - numberMachines then set j to k
				// Test if the current machine is X or not
				if (!rows[i][j].equals("X")) {
					count++;
					machinesToFix.add(rows[i][j]);
					if (count == numberMachines) {
						// Stop the loop
						j = k;
					}
				} else {
					count = 0;
					machinesToFix.clear();
					if (j >= k - numberMachines) {
						// Stop the loop
						j = k;
					}
				}
			}

			if (machinesToFix.size() != 0) {
				possibleSol.addAll(machinesToFix);
			}
		}

		if (possibleSol.size() == 0) {
			return 0;
		} else {
			int sum = 0;

			for (int i = 0; i < numberMachines; i++) {		
				sum += Integer.parseInt(possibleSol.get(i));
			}
			
			int curr = sum;
			for (int i = numberMachines; i < possibleSol.size(); i++) {
				curr += Integer.parseInt(possibleSol.get(i)) - Integer.parseInt(possibleSol.get(i-numberMachines));
				sum = Math.min(sum, curr);
			}

			return sum;
		}

	}

}
