package v1;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AI {

    private ArrayList<Stack<Integer>> calculations;

    public AI() {
        calculations = new ArrayList<>();
        for(int i = 0 ; i < 7; i++) {
            calculations.add(i, new Stack<>());
            calculations.get(i).push(0);
	    }
    }

    public ArrayList<Stack<Integer>> getCalculations() {
		return calculations;
	}

	/*
	 * returns the column location with the highest calculation
	 */
	public int activateBrain() {
		ArrayList<Integer> tiedIndexes = new ArrayList<>();
		int placeChipHere = 0;
		int maxCalculation = 0;
		for(int i = 0; i < 7; i++) {
			int calculation = calculations.get(i).peek();
			if(calculation >= maxCalculation) {
				maxCalculation = calculation;
				placeChipHere = i;
			}
		}
		//Checking if any columns have the same heuristic calculation
		for(int i = 0; i < 7; i++) {
			int calculation = calculations.get(i).peek();
			if(calculation == maxCalculation) {
				tiedIndexes.add(i);
			}
		}
		//If any ties do exist for the column heuristic values then one of the ties is chosen at random
		if(tiedIndexes.size() > 1) {
			Random rand = new Random();
			int index = rand.nextInt(tiedIndexes.size() - 1);
			placeChipHere = tiedIndexes.get(index);
		}
		return placeChipHere;
	}

	public void addPoints(int points, int column) {
	    if(calculations.get(column).isEmpty()) {
            calculations.get(column).push(points);
        }
        else {
            int totalPoints = calculations.get(column).peek() + points;
            calculations.get(column).push(totalPoints);
        }
	}

	public void blockedMove(int column, Connect4Game gb) {

		ArrayList<Integer> locationsBlocked = new ArrayList<>();
		locationsBlocked.add(column);

		int row = gb.getNextPositionInCol(column) + 1; //Last position (row) played in column

		// This will test all possible horizontal connect four
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |?|?|?|R|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(column - 6 >= 1 && column - 4 >= 1 && column - 2 >= 1) {
			if((gb.isPlayableMove(row, column - 6) || gb.getOccupancyAt(row, column - 6) == 'Y') &&
			   (gb.isPlayableMove(row, column - 4) || gb.getOccupancyAt(row, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row, column - 2) || gb.getOccupancyAt(row, column - 2) == 'Y')) {
				if(gb.isPlayableMove(row, column - 6)) {
					locationsBlocked.add(column - 6);
				}
				if(gb.isPlayableMove(row, column - 4)) {
					locationsBlocked.add(column - 4);
				}
				if(gb.isPlayableMove(row, column - 2)) {
					locationsBlocked.add(column - 2);
				}
			}
		}
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|?|?|R|?|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if( column - 4 >= 1 && column - 2 >= 1 && column + 2 <= 13) {
			if((gb.isPlayableMove(row, column - 4) || gb.getOccupancyAt(row, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row, column - 2) || gb.getOccupancyAt(row, column - 2) == 'Y') &&
			   (gb.isPlayableMove(row, column + 2) || gb.getOccupancyAt(row, column + 2) == 'Y')) {
				if(gb.isPlayableMove(row, column - 4)) {
					locationsBlocked.add(column - 4);
				}
				if(gb.isPlayableMove(row, column - 2)) {
					locationsBlocked.add(column - 2);
				}
				if(gb.isPlayableMove(row, column + 2)) {
					locationsBlocked.add(column + 2);
				}
			}
		}
		
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|?|R|?|?|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(column - 2 >= 1 && column + 2 <= 13 && column + 4 <= 13) {
			if((gb.isPlayableMove(row, column - 2) || gb.getOccupancyAt(row, column - 2) == 'Y') &&
			   (gb.isPlayableMove(row, column + 2) || gb.getOccupancyAt(row, column + 2) == 'Y') &&
			   (gb.isPlayableMove(row, column + 4) || gb.getOccupancyAt(row, column + 4) == 'Y')) {
				if(gb.isPlayableMove(row, column - 2)) {
					locationsBlocked.add(column - 2);
				}
				if(gb.isPlayableMove(row, column + 2)) {
					locationsBlocked.add(column + 2);
				}
				if(gb.isPlayableMove(row, column + 4)) {
					locationsBlocked.add(column + 4);
				}
			}
		}
		
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|R|?|?|?|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(column + 2 <= 13 && column + 4 <= 13 && column + 6 <= 13) {
			if((gb.isPlayableMove(row, column + 2) || gb.getOccupancyAt(row, column + 2) == 'Y') &&
			   (gb.isPlayableMove(row, column + 4) || gb.getOccupancyAt(row, column + 4) == 'Y') &&
			   (gb.isPlayableMove(row, column + 6) || gb.getOccupancyAt(row, column + 6) == 'Y')) {
				if(gb.isPlayableMove(row, column + 2)) {
					locationsBlocked.add(column + 2);
				}
				if(gb.isPlayableMove(row,column + 4)) {
					locationsBlocked.add(column + 4);
				}
				if(gb.isPlayableMove(row,column + 6)) {
					locationsBlocked.add(column + 6);
				}
			}
		}

		// This will test all possible vertical connect fours
		/* 
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|R|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(row - 3 >= 0 && row - 2 >= 0 && row- 1 >= 0) {
			if((gb.isPlayableMove(row - 3, column) || gb.getOccupancyAt(row - 3, column) == 'Y') &&
			   (gb.isPlayableMove(row - 2, column) || gb.getOccupancyAt(row - 2, column) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column) || gb.getOccupancyAt(row - 1, column) == 'Y')) {
				if(gb.isPlayableMove(row - 3, column)) {
					locationsBlocked.add(column);
				}
			}
		}
		
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|R|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(row - 2 >= 0 && row - 1 >= 0 && row + 1 <= 5) {
			if((gb.isPlayableMove(row - 2, column) || gb.getOccupancyAt(row - 2, column) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column) || gb.getOccupancyAt(row - 1, column) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column) || gb.getOccupancyAt(row + 1, column) == 'Y')) {
				if(gb.isPlayableMove(row - 2, column)) {
					locationsBlocked.add(column);
				}
			}
		}
		
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|R|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 */
		if(row - 1 >= 0 && row + 1 <= 5 && row + 2 <= 5) {
			if((gb.isPlayableMove(row - 1, column) || gb.getOccupancyAt(row - 1, column) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column) || gb.getOccupancyAt(row + 1, column) == 'Y') &&
			   (gb.isPlayableMove(row + 2, column) || gb.getOccupancyAt(row + 2, column) == 'Y')) {
				if(gb.isPlayableMove(row + 1,column)) {
							locationsBlocked.add(column);
				}
			}
		}
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|R|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 */
		if(row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5) {
			if((gb.isPlayableMove(row + 1, column) || gb.getOccupancyAt(row + 1, column) == 'Y') &&
			   (gb.isPlayableMove(row + 2, column) || gb.getOccupancyAt(row + 2, column) == 'Y') &&
			   (gb.isPlayableMove(row + 3, column) || gb.getOccupancyAt(row + 3, column) == 'Y')) {
				if(gb.isPlayableMove(row + 1,column)) {
					locationsBlocked.add(column);
				}
			}
		}
		// Checks all the possibilities of forward slashes connect fours`
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |?|_|_|_|_|_|_|
		*/
		if(column - 6 >= 1 && column - 4 >= 1 && column - 2 >= 1 && row + 3 <= 5 && row + 2 <= 5 && row + 1 <= 5) {
			if((gb.isPlayableMove(row + 3, column - 6) || gb.getOccupancyAt(row + 3, column - 6) == 'Y') &&
			   (gb.isPlayableMove(row + 2, column - 4) || gb.getOccupancyAt(row + 2, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column - 2) || gb.getOccupancyAt(row + 1, column - 2) == 'Y') ) {
				/*
				 * left off putting conditions that say if I can still play a chip here recalculate.. 
				 * don't recalculate a column that has ur piece there bc the opponent didnt block it since
				 * it was placed there already.
				 */
				if(gb.isPlayableMove(row + 3, column - 6)) {
					locationsBlocked.add(column - 6);
				}
				if(gb.isPlayableMove(row + 2, column - 4)) {
					locationsBlocked.add(column - 4);
				}
				if(gb.isPlayableMove(row + 1, column - 2)) {
					locationsBlocked.add(column - 2);
				}
			}
		}
		
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column - 4 >= 1 && column - 2 >= 1  && column + 2 <= 13 && row + 2 <= 5 && row + 1 <= 5 && row - 1 >= 0) {
			if((gb.isPlayableMove(row + 2, column - 4) || gb.getOccupancyAt(row + 2, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column - 2) || gb.getOccupancyAt(row + 1, column - 2) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column + 2) || gb.getOccupancyAt(row - 1, column + 2) == 'Y')) {
				if(gb.isPlayableMove(row + 2, column - 4)) {
					locationsBlocked.add(column - 4);
				}
				if(gb.isPlayableMove(row + 1, column - 2)) {
					locationsBlocked.add(column - 2);
				}
				if(gb.isPlayableMove(row - 1, column + 2)) {
					locationsBlocked.add(column + 2);
				}
			}
		}
		
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column - 2 >= 1  && column + 2 <= 13 && column + 4 <= 13 && row - 2 >= 0 && row - 1 >= 0 && row + 1 <= 5) {
			if((gb.isPlayableMove(row - 2, column + 4) || gb.getOccupancyAt(row - 2, column + 4) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column + 2) || gb.getOccupancyAt(row - 1, column + 2) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column - 2) || gb.getOccupancyAt(row + 1, column - 2) == 'Y')) {
				if(gb.isPlayableMove(row - 2, column + 4)) {
					locationsBlocked.add(column + 4);
				}
				if(gb.isPlayableMove(row - 1, column + 2)) {
					locationsBlocked.add(column + 2);
				}
				if(gb.isPlayableMove(row + 1, column - 2)) {
					locationsBlocked.add(column - 2);
				}
			}
		}
		
		/* 
		* |_|_|_|_|_|_|?|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column + 2 <= 13 && column + 4 <= 13 && column + 6 <= 13 && row - 3 >= 0 && row - 2 >= 0 && row - 1 >= 0) {
			if((gb.isPlayableMove(row - 3, column + 6) || gb.getOccupancyAt(row - 3, column + 6) == 'Y') &&
			   (gb.isPlayableMove(row - 2, column + 4) || gb.getOccupancyAt(row - 2, column + 4) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column + 2) || gb.getOccupancyAt(row - 1, column + 2) == 'Y')) {
				if(gb.isPlayableMove(row - 3, column + 6)) {
					locationsBlocked.add(column + 6);
				}
				if(gb.isPlayableMove(row - 2, column + 4)) {
					locationsBlocked.add(column + 4);
				}
				if(gb.isPlayableMove(row - 3, column + 6)) {
					locationsBlocked.add(column + 6);
				}
			}
		}
		//This will test all of the possibilities of the backwards slash
		/* 
		* |?|_|_|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column - 6 >= 1 && column - 4 >= 1 && column - 2 >= 1 && row - 3 >= 0 && row - 2 >= 0 && row - 1 >=  0) {
			if((gb.isPlayableMove(row - 3, column - 6) || gb.getOccupancyAt(row - 3, column - 6) == 'Y') &&
			   (gb.isPlayableMove(row - 2, column - 4) || gb.getOccupancyAt(row - 2, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column - 2) || gb.getOccupancyAt(row - 1, column - 2) == 'Y')) {
				if(gb.isPlayableMove(row - 3, column - 6)) {
					locationsBlocked.add(column - 6);
				}
				if(gb.isPlayableMove(row - 2, column - 4)) {
					locationsBlocked.add(column - 4);
				}
				if(gb.isPlayableMove(row - 1, column - 2)) {
					locationsBlocked.add(column - 2);
				}
			}
		}
		
		/* 
		* |_|_|_|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column - 4 >= 1 && column - 2 >= 0 && column + 2 <= 13 && row - 2 >= 0 && row - 1 >= 0 && row + 1 <= 5) {
			if((gb.isPlayableMove(row - 2, column - 4) || gb.getOccupancyAt(row - 2, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column - 2) || gb.getOccupancyAt(row - 1, column - 2) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column + 2) || gb.getOccupancyAt(row + 1, column + 2) == 'Y')) {
				if(gb.isPlayableMove(row - 2, column - 4)) {
					locationsBlocked.add(column - 4);
				}
				if(gb.isPlayableMove(row - 1, column - 2)) {
					locationsBlocked.add(column - 2);
				}
				if(gb.isPlayableMove(row + 1, column + 2)) {
					locationsBlocked.add(column + 2);
				}
			}
		}
		
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column - 2 >= 1 && column + 2 <= 13 && column + 4 <= 13 && row - 1 >= 0 && row + 1 <= 5 && row + 2 <= 5) {
			if((gb.isPlayableMove(row - 1, column - 2) || gb.getOccupancyAt(row - 1, column - 2) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column + 2) || gb.getOccupancyAt(row + 1, column + 2) == 'Y') &&
			   (gb.isPlayableMove(row + 2, column + 4) || gb.getOccupancyAt(row + 2, column + 4) == 'Y')) {
				if(gb.isPlayableMove(row - 1, column - 2)) {
					locationsBlocked.add(column - 2);
				}
				if(gb.isPlayableMove(row + 1, column + 2)) {
					locationsBlocked.add(column + 2);
				}
				if(gb.isPlayableMove(row + 2, column + 4)) {
					locationsBlocked.add(column + 4);
				}
			}
		}
		
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|R|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|_|_|?|
		*/
		if(column + 2 <= 13 && column + 4 <= 13 && column + 6 <= 13 && row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5) {
			if((gb.isPlayableMove(row + 1, column + 2) || gb.getOccupancyAt(row + 1, column + 2) == 'Y') &&
			   (gb.isPlayableMove(row + 2, column + 4) || gb.getOccupancyAt(row + 2, column + 4) == 'Y') &&
			   (gb.isPlayableMove(row + 3, column + 6) || gb.getOccupancyAt(row + 3, column + 6) == 'Y')) {
				if(gb.isPlayableMove(row + 1, column + 2)) {
					locationsBlocked.add(column + 2);
				}
				if(gb.isPlayableMove(row + 2, column + 4)) {
					locationsBlocked.add(column + 4);
				}
				if(gb.isPlayableMove(row + 3, column + 6)) {
					locationsBlocked.add(column + 6);
				}
			}
		}

		int popThisColumn;
		int newPoints;
		HashSet<Integer> columnsPopped = new HashSet<>();
		int size = locationsBlocked.size();
		// Pops all of the columns blocked
		for(int i = 0; i < size; i++) {
			popThisColumn = locationsBlocked.get(i);
			if(popThisColumn == 1) {
				popThisColumn = 0;
			}
			else {
				popThisColumn = (popThisColumn - 1) / 2;
			}
			if(!calculations.get(popThisColumn).isEmpty()) {
				calculations.get(popThisColumn).pop(); // pop columns blocked by chip placement
				if(!columnsPopped.contains(popThisColumn)) {
					columnsPopped.add(popThisColumn);
				}
			}
		}
		//Columns that were popped are only recalculated once
		Iterator<Integer> it = columnsPopped.iterator();
		while(it.hasNext()) {
			Integer columnToPop = it.next();
			newPoints = aiCalculations(columnToPop, gb, 'R');// recalculates offensive points
			addPoints(newPoints, columnToPop);
		}
	}// end of blockedMove

	/*
	 * Given a game board and a specific coordinate this method will update a specific columns heuristic
	 */
	public int aiCalculations(int col, Connect4Game gb, char chipColour) {
		int heuristic = 0;
		int row = gb.getNextPositionInCol(col); //Next available position (row) in a column

		// This will test all possible horizontal connect four
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |?|?|?|Y|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(col - 6 >= 1 && col - 4 >= 1 && col - 2 >= 1 && col >= 1) {
			if(gb.isPlayableMove(row, col - 6) && gb.isPlayableMove(row, col - 4) &&
			   gb.isPlayableMove(row, col - 2) && gb.isPlayableMove(row, col)) {
				if(gb.getOccupancyAt(row, col - 6) != chipColour && gb.getOccupancyAt(row, col - 4) != chipColour &&
				   gb.getOccupancyAt(row, col - 2) != chipColour && gb.getOccupancyAt(row, col) != chipColour) {
					heuristic++;
				}
			}
		}
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|?|?|Y|?|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(col - 4 >= 1 && col - 2 >= 1 && col >= 1 && col + 2 <= 13) {
			if(gb.isPlayableMove(row, col - 4) && gb.isPlayableMove(row,col - 2) &&
			   gb.isPlayableMove(row, col) && gb.isPlayableMove(row,col + 2)) {
				if(gb.getOccupancyAt(row, col - 4) != chipColour && gb.getOccupancyAt(row, col - 2) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row, col + 2) != chipColour) {
					heuristic++;
				}
			}
		}
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|?|Y|?|?|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(col - 2 >= 1 && col >= 1 && col + 2 <= 13 && col + 4 <= 13) {
			if(gb.isPlayableMove(row,col - 2) && gb.isPlayableMove(row,col) &&
			   gb.isPlayableMove(row,col + 2) && gb.isPlayableMove(row,col + 4)) {
				if(gb.getOccupancyAt(row, col - 2) != chipColour && gb.getOccupancyAt(row, col) != chipColour &&
				   gb.getOccupancyAt(row, col + 2) != chipColour && gb.getOccupancyAt(row, col + 4) != chipColour) {
					heuristic++;
				}
			}
		}
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|Y|?|?|?|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(col >= 1 && col + 2 <= 13 && col + 4 <= 13 && col + 6 <= 13) {
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row,col + 2) &&
			   gb.isPlayableMove(row,col + 4) && gb.isPlayableMove(row,col + 6)) {
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row, col + 2) != chipColour &&
				   gb.getOccupancyAt(row, col + 4) != chipColour && gb.getOccupancyAt(row, col + 6) != chipColour) {
					heuristic++;
				}
			}
		}
		// This will test all possible vertical connect four
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|Y|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 */
		if(row + 3 <= 5 && row + 2 <= 5 && row + 1 <= 5 && row <= 5) {
			if(gb.isPlayableMove(row + 3,col) && gb.isPlayableMove(row + 2,col) &&
			   gb.isPlayableMove(row + 1,col) && gb.isPlayableMove(row,col) ) {
				if(gb.getOccupancyAt(row + 3, col) != chipColour && gb.getOccupancyAt(row + 2, col) != chipColour &&
				   gb.getOccupancyAt(row + 1, col) != chipColour && gb.getOccupancyAt(row, col) != chipColour) {
					heuristic++;
				}
			}
		}
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|Y|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 */
		if(row + 2 <= 5 && row + 1 <= 5 && row <= 5 && row - 1 >= 0) {
			if(gb.isPlayableMove(row + 2,col) && gb.isPlayableMove(row + 1,col) &&
			   gb.isPlayableMove(row,col) && gb.isPlayableMove(row - 1,col) ) {
				if(gb.getOccupancyAt(row + 2, col) != chipColour && gb.getOccupancyAt(row + 1, col) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row - 1, col) != chipColour) {
					heuristic++;
				}
			}
		}
		/*
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|Y|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(row + 1 <= 5 && row <= 5 && row - 1 >= 0 && row - 2 >= 0) {
			if(gb.isPlayableMove(row + 1,col) && gb.isPlayableMove(row,col) &&
			   gb.isPlayableMove(row - 1,col) && gb.isPlayableMove(row - 2,col)) {
				if(gb.getOccupancyAt(row + 1, col) != chipColour && gb.getOccupancyAt(row, col) != chipColour &&
				   gb.getOccupancyAt(row - 1, col) != chipColour && gb.getOccupancyAt(row - 2, col) != chipColour) {
					heuristic++;
				}
			}
		}
		/*
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|Y|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(row >= 0 && row - 1 >= 0 && row - 2 >= 0 && row - 3 >= 0) {
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row - 1,col) &&
			   gb.isPlayableMove(row - 2,col) && gb.isPlayableMove(row - 3,col)) {
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row - 1, col) != chipColour &&
				   gb.getOccupancyAt(row - 2, col) != chipColour && gb.getOccupancyAt(row - 3, col) != chipColour) {
					heuristic++;
				}
			}
		}
		// This will test all possible back slash connect four
		/* 
		* |?|_|_|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col - 6 >= 1 && col - 4 >= 1 && col - 2 >= 1 && col >= 1 && row - 3 >= 0 && row - 2 >= 0 && row - 1 >= 0 && row >= 0) {
			if(gb.isPlayableMove(row - 3, col - 6) && gb.isPlayableMove(row - 2, col - 4) &&
			   gb.isPlayableMove(row - 1, col - 2) && gb.isPlayableMove(row, col)) {
				if(gb.getOccupancyAt(row - 3, col - 6) != chipColour && gb.getOccupancyAt(row - 2, col - 4) != chipColour &&
				   gb.getOccupancyAt(row - 1, col - 2) != chipColour && gb.getOccupancyAt(row, col) != chipColour) {
					heuristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col - 4 >= 1 && col - 2 >= 1 && col >= 1 && col + 2 <= 13 && row - 2 >= 0 && row - 1 >= 0 && row >= 0 && row + 1 <= 5) {
			if(gb.isPlayableMove(row - 2, col - 4) && gb.isPlayableMove(row - 1, col - 2) &&
			   gb.isPlayableMove(row, col) && gb.isPlayableMove(row + 1, col + 2) ) {
				if(gb.getOccupancyAt(row - 2, col - 4) != chipColour && gb.getOccupancyAt(row - 1, col - 2) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row + 1, col + 2) != chipColour) {
					heuristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col - 2 >= 1 && col >= 2 && col + 2 <= 13 && col + 4 <= 13 && row - 1 >= 0 && row >= 0 && row + 1 <= 5 && row + 2 <= 5) {
			if(gb.isPlayableMove(row - 1, col - 2) && gb.isPlayableMove(row,col) &&
			   gb.isPlayableMove(row + 1, col + 2) && gb.isPlayableMove(row + 2, col + 4)) {
				if(gb.getOccupancyAt(row - 1, col - 2) != chipColour && gb.getOccupancyAt(row, col) != chipColour &&
				   gb.getOccupancyAt(row + 1, col + 2) != chipColour && gb.getOccupancyAt(row + 2, col + 4) != chipColour) {
					heuristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|_|_|?|
		*/
		if(col >= 1 && col + 2 <= 13 && col + 4 <= 13 && col + 6 <= 13 && row >= 0 && row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5) {
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row + 1, col + 2) &&
			   gb.isPlayableMove(row + 2, col + 4) && gb.isPlayableMove(row + 3, col + 6)) {
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row + 1, col + 2) != chipColour &&
				   gb.getOccupancyAt(row + 2, col + 4) != chipColour && gb.getOccupancyAt(row + 3, col + 6) != chipColour) {
					heuristic++;
				}
			}
		}
		// This will test all possible forward slash connect four
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |?|_|_|_|_|_|_|
		*/
		if(col - 6 >= 1 && col - 4 >= 1 && col - 2 >= 1 && col >= 1 && row + 3 <= 5 && row + 2 <= 5 && row + 1 <= 5 && row >= 0) {
			if(gb.isPlayableMove(row + 3, col - 6) && gb.isPlayableMove(row + 2, col - 4) &&
			   gb.isPlayableMove(row + 1, col - 2) && gb.isPlayableMove(row, col)) {
				if(gb.getOccupancyAt(row + 3, col - 6) != chipColour && gb.getOccupancyAt(row + 2, col - 4) != chipColour &&
				   gb.getOccupancyAt(row + 1, col - 2) != chipColour && gb.getOccupancyAt(row, col) != chipColour) {
					heuristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col - 4 >= 1 && col - 2 >= 1 && col >= 1 && col + 2 <= 13 && row + 2 <= 5 && row + 1 <= 5 && row >= 0 && row - 1 >= 0) {
			if(gb.isPlayableMove(row + 2, col - 4) && gb.isPlayableMove(row + 1, col - 2) &&
			   gb.isPlayableMove(row, col) && gb.isPlayableMove(row - 1, col + 2)) {
				if(gb.getOccupancyAt(row + 2, col - 4) != chipColour && gb.getOccupancyAt(row + 1, col - 2) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row - 1, col + 2) != chipColour) {
					heuristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col - 2 >= 1 && col >= 1 && col + 2 <= 13 && col + 4 <= 13 && row + 1 <= 5 && row >= 0 && row - 1 >= 0 && row - 2 >= 0) {
			if(gb.isPlayableMove(row + 1, col - 2) && gb.isPlayableMove(row, col) &&
			   gb.isPlayableMove(row - 1, col + 2) && gb.isPlayableMove(row - 2, col + 4)) {
				if(gb.getOccupancyAt(row + 1, col - 2) != chipColour && gb.getOccupancyAt(row, col) != chipColour &&
				   gb.getOccupancyAt(row - 1, col + 2) != chipColour && gb.getOccupancyAt(row - 2, col + 4) != chipColour) {
					heuristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|?|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col >= 1 && col + 2 <= 13 && col + 4 <= 13 && col + 6 <= 13 && row >= 0 && row - 1 >= 0 && row - 2 >= 0 && row - 3 >= 0) {
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row - 1, col + 2) &&
			   gb.isPlayableMove(row - 2, col + 4) && gb.isPlayableMove(row - 3, col + 6)) {
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row - 1, col + 2) != chipColour &&
				   gb.getOccupancyAt(row - 2, col + 4) != chipColour && gb.getOccupancyAt(row - 3, col + 6) != chipColour) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}// End of aiCalculation
}// End of AI

