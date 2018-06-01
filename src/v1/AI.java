package v1;
import java.util.Stack;
import v1.Connect4Game;
import java.util.ArrayList;

public class AI
{
	ArrayList<Stack<Integer>> calculations;

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
	public int activateBrain()
	{
		int placeChipHere = 0;
		for(int i=0; i<7; i++)
		{
			if(calculations.get(i).peek() > placeChipHere)
			{
				placeChipHere = i;
			}
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
	
	// If AI moves are blocked then it pops everything and recalculates
	public void blockedMove(int column, Connect4Game gb) {
		ArrayList<Integer> locationsBlocked = new ArrayList<Integer>();
		locationsBlocked.add(column);
		
		int row = gb.getNextPositionInCol(column) + 1;
		
		// This will test all possible horizontal connect four 
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |?|?|?|R|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(column - 6 >= 1 && column - 4 >= 1 && column - 2 >= 1) 
		{
			if((gb.isPlayableMove(row, column - 6) || gb.getOccupancyAt(row, column - 6) == 'Y') &&
			   (gb.isPlayableMove(row, column - 4) || gb.getOccupancyAt(row, column - 4) == 'Y') && 
			   (gb.isPlayableMove(row, column - 2) || gb.getOccupancyAt(row, column - 2) == 'Y'))
			{
				if(gb.isPlayableMove(row, column - 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 6)
						{
							locationsBlocked.add(column - 6);
						}
					}
				}
				
				if(gb.isPlayableMove(row, column - 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 4)
						{
							locationsBlocked.add(column - 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row, column - 2))
				{
					for(int i = 0 ; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
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
		if( column - 4 >= 1 && column - 2 >= 1 && column + 2 <= 13) 
		{
			if((gb.isPlayableMove(row, column - 4) || gb.getOccupancyAt(row, column - 4) == 'Y') && 
			   (gb.isPlayableMove(row, column - 2) || gb.getOccupancyAt(row, column - 2) == 'Y') && 
			   (gb.isPlayableMove(row, column + 2) || gb.getOccupancyAt(row, column + 2) == 'Y'))
			{
				if(gb.isPlayableMove(row, column - 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 4)
						{
							locationsBlocked.add(column - 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
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
		if(column - 2 >= 1 && column + 2 <= 13 && column + 4 <= 13) 
		{
			if((gb.isPlayableMove(row, column - 2) || gb.getOccupancyAt(row, column - 2) == 'Y') && 
			   (gb.isPlayableMove(row, column + 2) || gb.getOccupancyAt(row, column + 2) == 'Y') && 
			   (gb.isPlayableMove(row, column + 4) || gb.getOccupancyAt(row, column + 4) == 'Y'))
			{
				if(gb.isPlayableMove(row, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row, column + 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 4)
						{
							locationsBlocked.add(column + 4);
						}
					}
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
		if(column + 2 <= 13 && column + 4 <= 13 && column + 6 <= 13) 
		{
			if((gb.isPlayableMove(row, column + 2) || gb.getOccupancyAt(row, column + 2) == 'Y') && 
			   (gb.isPlayableMove(row, column + 4) || gb.getOccupancyAt(row, column + 4) == 'Y') && 
			   (gb.isPlayableMove(row, column + 6) || gb.getOccupancyAt(row, column + 6) == 'Y'))
			{
				if(gb.isPlayableMove(row, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row,column + 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 4)
						{
							locationsBlocked.add(column + 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row,column + 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 6)
						{
							locationsBlocked.add(column + 6);
						}
					}
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
		if(row - 3 >= 0 && row - 2 >= 0 && row- 1 >= 0) 
		{
			if((gb.isPlayableMove(row - 3, column) || gb.getOccupancyAt(row - 3, column) == 'Y') && 
			   (gb.isPlayableMove(row - 2, column) || gb.getOccupancyAt(row - 2, column) == 'Y') && 
			   (gb.isPlayableMove(row - 1, column) || gb.getOccupancyAt(row - 1, column) == 'Y'))
			{
				if(gb.isPlayableMove(row - 3, column))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column)
						{
							locationsBlocked.add(column);
						}
					}
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
		if(row - 2 >= 0 && row - 1 >= 0 && row + 1 <= 5) 
		{
			if((gb.isPlayableMove(row - 2, column) || gb.getOccupancyAt(row - 2, column) == 'Y') && 
			   (gb.isPlayableMove(row - 1, column) || gb.getOccupancyAt(row - 1, column) == 'Y') && 
			   (gb.isPlayableMove(row + 1, column) || gb.getOccupancyAt(row + 1, column) == 'Y'))
			{
				if(gb.isPlayableMove(row - 2, column))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column)
						{
							locationsBlocked.add(column);
						}
					}
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
		if(row - 1 >= 0 && row + 1 <= 5 && row + 2 <= 5) 
		{
			if((gb.isPlayableMove(row - 1, column) || gb.getOccupancyAt(row - 1, column) == 'Y') &&
			   (gb.isPlayableMove(row + 1, column) || gb.getOccupancyAt(row + 1, column) == 'Y') && 
			   (gb.isPlayableMove(row + 2, column) || gb.getOccupancyAt(row + 2, column) == 'Y'))
			{
				if(gb.isPlayableMove(row + 1,column))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column)
						{
							locationsBlocked.add(column);
						}
					}
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
		if(row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5) 
		{
			if((gb.isPlayableMove(row + 1, column) || gb.getOccupancyAt(row + 1, column) == 'Y') && 
			   (gb.isPlayableMove(row + 2, column) || gb.getOccupancyAt(row + 2, column) == 'Y') && 
			   (gb.isPlayableMove(row + 3, column) || gb.getOccupancyAt(row + 3, column) == 'Y'))
			{
				if(gb.isPlayableMove(row + 1,column))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column)
						{
							locationsBlocked.add(column);
						}
					}
				}
			}
		}
		// Checks all the possibilities of forward slashes connect fours`
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |?|_|_|_|_|_|_|
		*/
		if(column - 6 >= 1 && column - 4 >= 1 && column - 2 >= 1 && row + 3 <= 5 && row + 2 <= 5 && row + 1 <= 5)
		{
			if((gb.isPlayableMove(row + 3, column - 6) || gb.getOccupancyAt(row + 3, column - 6) == 'Y') && 
			   (gb.isPlayableMove(row + 2, column - 4) || gb.getOccupancyAt(row + 2, column - 4) == 'Y') && 
			   (gb.isPlayableMove(row + 1, column - 2) || gb.getOccupancyAt(row + 1, column - 2) == 'Y') )
			{
				/*
				 * left off putting conditions that say if I can still play a chip here recalculate.. 
				 * dont recalculate a column that has ur piece there bc the opponent didnt block it since 
				 * it was placed there already.
				 */
				if(gb.isPlayableMove(row + 3, column - 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 6)
						{
							locationsBlocked.add(column - 6);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 2, column - 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 4)
						{
							locationsBlocked.add(column - 4);
						}
					}
				}
					
				if(gb.isPlayableMove(row + 1, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
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
		if(column - 4 >= 1 && column - 2 >= 1  && column + 2 <= 13 && row + 2 <= 5 && row + 1 <= 5 && row - 1 >= 0)
		{
			if((gb.isPlayableMove(row + 2, column - 4) || gb.getOccupancyAt(row + 2, column - 4) == 'Y') && 
			   (gb.isPlayableMove(row + 1, column - 2) || gb.getOccupancyAt(row + 1, column - 2) == 'Y') && 
			   (gb.isPlayableMove(row - 1, column + 2) || gb.getOccupancyAt(row - 1, column + 2) == 'Y'))
			{		
				if(gb.isPlayableMove(row + 2, column - 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 4)
						{
							locationsBlocked.add(column - 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 1, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 1, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
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
		if(column - 2 >= 1  && column + 2 <= 13 && column + 4 <= 13 && row - 2 >= 0 && row - 1 >= 0 && row + 1 <= 5)
		{
			if((gb.isPlayableMove(row - 2, column + 4) || gb.getOccupancyAt(row - 2, column + 4) == 'Y') && 
			   (gb.isPlayableMove(row - 1, column + 2) || gb.getOccupancyAt(row - 1, column + 2) == 'Y') && 
			   (gb.isPlayableMove(row + 1, column - 2) || gb.getOccupancyAt(row + 1, column - 2) == 'Y'))
			{	
				if(gb.isPlayableMove(row - 2, column + 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 4)
						{
							locationsBlocked.add(column + 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 1, column + 2))
				{
					for(int i =0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 1, column - 2))
				{
					for(int i =0 ; i < locationsBlocked.size();i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
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
		if(column + 2 <= 13 && column + 4 <= 13 && column + 6 <= 13 && row - 3 >= 0 && row - 2 >= 0 && row - 1 >= 0)
		{
			if((gb.isPlayableMove(row - 3, column + 6) || gb.getOccupancyAt(row - 3, column + 6) == 'Y') && 
			   (gb.isPlayableMove(row - 2, column + 4) || gb.getOccupancyAt(row - 2, column + 4) == 'Y') && 
			   (gb.isPlayableMove(row - 1, column + 2) || gb.getOccupancyAt(row - 1, column + 2) == 'Y'))
			{	
				if(gb.isPlayableMove(row - 3, column + 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 6)
						{
							locationsBlocked.add(column + 6);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 2, column + 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 4)
						{
							locationsBlocked.add(column + 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 3, column + 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 6)
						{
							locationsBlocked.add(column + 6);
						}
					}
				}
			}
		}
		//This will test all of the possibilities of the backwards slash
		/* 
		* |?|_|_|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/
		if(column - 6 >= 1 && column - 4 >= 1 && column - 2 >= 1 && row - 3 >= 0 && row - 2 >= 0 && row - 1 >=  0)
		{
			if((gb.isPlayableMove(row - 3, column - 6) || gb.getOccupancyAt(row - 3, column - 6) == 'Y') && 
			   (gb.isPlayableMove(row - 2, column - 4) || gb.getOccupancyAt(row - 2, column - 4) == 'Y') && 
			   (gb.isPlayableMove(row - 1, column - 2) || gb.getOccupancyAt(row - 1, column - 2) == 'Y'))
			{	
				if(gb.isPlayableMove(row - 3, column - 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 6)
						{
							locationsBlocked.add(column - 6);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 2, column - 4))
				{
					for(int i =0 ; i < locationsBlocked.size();i++)
					{
						if(locationsBlocked.get(i) == column - 4)
						{
							locationsBlocked.add(column - 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 1, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
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
		if(column - 4 >= 1 && column - 2 >= 0 && column + 2 <= 13 && row - 2 >= 0 && row - 1 >= 0 && row + 1 <= 5)
		{
			if((gb.isPlayableMove(row - 2, column - 4) || gb.getOccupancyAt(row - 2, column - 4) == 'Y') &&
			   (gb.isPlayableMove(row - 1, column - 2) || gb.getOccupancyAt(row - 1, column - 2) == 'Y') && 
			   (gb.isPlayableMove(row + 1, column + 2) || gb.getOccupancyAt(row + 1, column + 2) == 'Y'))
			{	
				if(gb.isPlayableMove(row - 2, column - 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 4)
						{
							locationsBlocked.add(column - 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row - 1, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 1, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
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
		if(column - 2 >= 1 && column + 2 <= 13 && column + 4 <= 13 && row - 1 >= 0 && row + 1 <= 5 && row + 2 <= 5)
		{
			if((gb.isPlayableMove(row - 1, column - 2) || gb.getOccupancyAt(row - 1, column - 2) == 'Y') && 
			   (gb.isPlayableMove(row + 1, column + 2) || gb.getOccupancyAt(row + 1, column + 2) == 'Y') && 
			   (gb.isPlayableMove(row + 2, column + 4) || gb.getOccupancyAt(row + 2, column + 4) == 'Y'))
			{	
				if(gb.isPlayableMove(row - 1, column - 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column - 2)
						{
							locationsBlocked.add(column - 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 1, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 2, column + 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 4)
						{
							locationsBlocked.add(column + 4);
						}
					}
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
		if(column + 2 <= 13 && column + 4 <= 13 && column + 6 <= 13 && row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5)
		{
			if((gb.isPlayableMove(row + 1, column + 2) || gb.getOccupancyAt(row + 1, column + 2) == 'Y') &&
			   (gb.isPlayableMove(row + 2, column + 4) || gb.getOccupancyAt(row + 2, column + 4) == 'Y') && 
			   (gb.isPlayableMove(row + 3, column + 6) || gb.getOccupancyAt(row + 3, column + 6) == 'Y'))
			{		
				if(gb.isPlayableMove(row + 1, column + 2))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 2)
						{
							locationsBlocked.add(column + 2);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 2, column + 4))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 4)
						{
							locationsBlocked.add(column + 4);
						}
					}
				}
				
				if(gb.isPlayableMove(row + 3, column + 6))
				{
					for(int i = 0; i < locationsBlocked.size(); i++)
					{
						if(locationsBlocked.get(i) == column + 6)
						{
							locationsBlocked.add(column + 6);
						}
					}
				}
			}
		}
		
		int popThisColumn;
		int newPoints;
		// Pops all of the columns blocked
		for(int i = 0; i<locationsBlocked.size(); i++)
		{
			popThisColumn = locationsBlocked.get(i);
			if (popThisColumn == 13) {
			    popThisColumn  = 6;
            }
            else if (popThisColumn == 11) {
                popThisColumn  = 5;
            }
            else if (popThisColumn == 9) {
                popThisColumn  = 4;
            }
            else if (popThisColumn == 7) {
                popThisColumn  = 3;
            }
            else if (popThisColumn == 5) {
                popThisColumn  = 2;
            }
            else if (popThisColumn == 3) {
                popThisColumn  = 1;
            }
            else if (popThisColumn == 11) {
                popThisColumn  = 0;
            }
			calculations.get(popThisColumn).pop(); // pop columns blocked by chip placement
			newPoints = aiCalculations(popThisColumn*2+1, gb, 'Y');// recalculates offensive points
			addPoints(newPoints, popThisColumn);
		}
		
	}// end of blockedMove
	
	/*
	 * Given a game board and a specific coordinate
	 * this method will update a specific columns heuristic
	 */
	public int aiCalculations(int col, Connect4Game gb, char chipColour)
	{
		int hueristic = 0;
		int row = gb.getNextPositionInCol(col);
		
		// This will test all possible horizontal connect four 
		/* 
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |?|?|?|Y|_|_|_|
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|_|_|_|_|
		 */
		if(col - 6 >= 1 && col - 4 >= 1 && col - 2 >= 1 && col >= 1) 
		{
			if(gb.isPlayableMove(row, col - 6) && gb.isPlayableMove(row, col - 4) && 
			   gb.isPlayableMove(row, col - 2) && gb.isPlayableMove(row,col) )
			{
				if(gb.getOccupancyAt(row, col - 6) != chipColour && 
				   gb.getOccupancyAt(row, col - 4) != chipColour && 
				   gb.getOccupancyAt(row, col - 2) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour)
				{
					hueristic++;
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
		if(col - 4 >= 1 && col - 2 >= 1 && col >= 1 && col + 2 <= 13) 
		{
			if(gb.isPlayableMove(row, col - 4) && gb.isPlayableMove(row,col - 2) && 
			   gb.isPlayableMove(row, col) && gb.isPlayableMove(row,col + 2))
			{
				if(gb.getOccupancyAt(row, col - 4) != chipColour && 
				   gb.getOccupancyAt(row, col - 2) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row, col + 2) != chipColour)
				{
					hueristic++;
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
		if(col - 2 >= 1 && col >= 1 && col + 2 <= 13 && col + 4 <= 13) 
		{
			if(gb.isPlayableMove(row,col - 2) && gb.isPlayableMove(row,col) && 
			   gb.isPlayableMove(row,col + 2) && gb.isPlayableMove(row,col + 4))
			{
				if(gb.getOccupancyAt(row, col - 2) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row, col + 2) != chipColour && 
				   gb.getOccupancyAt(row, col + 4) != chipColour)
				{
					hueristic++;
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
		if(col >= 1 && col + 2 <= 13 && col + 4 <= 13 && col + 6 <= 13) 
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row,col + 2) && 
			   gb.isPlayableMove(row,col + 4) && gb.isPlayableMove(row,col + 6))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row, col + 2) != chipColour && 
				   gb.getOccupancyAt(row, col + 4) != chipColour  && 
				   gb.getOccupancyAt(row, col + 6) != chipColour)
				{
					hueristic++;
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
		if(row + 3 <= 5 && row + 2 <= 5 && row + 1 <= 5 && row <= 5) 
		{
			if(gb.isPlayableMove(row + 3,col) && gb.isPlayableMove(row + 2,col) && 
			   gb.isPlayableMove(row + 1,col) && gb.isPlayableMove(row,col) )
			{
				if(gb.getOccupancyAt(row + 3, col) != chipColour && 
				   gb.getOccupancyAt(row + 2, col) != chipColour && 
				   gb.getOccupancyAt(row + 1, col) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour)
				{
					hueristic++;
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
		if(row + 2 <= 5 && row + 1 <= 5 && row <= 5 && row - 1 >= 0) 
		{
			if(gb.isPlayableMove(row + 2,col) && gb.isPlayableMove(row + 1,col) && 
			   gb.isPlayableMove(row,col) && gb.isPlayableMove(row - 1,col) )
			{
				if(gb.getOccupancyAt(row + 2, col) != chipColour && 
				   gb.getOccupancyAt(row + 1, col) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row - 1, col) != chipColour)
				{
					hueristic++;
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
		if(row + 1 <= 5 && row <= 5 && row - 1 >= 0 && row - 2 >= 0) 
		{
			if(gb.isPlayableMove(row + 1,col) && gb.isPlayableMove(row,col) && 
			   gb.isPlayableMove(row - 1,col) && gb.isPlayableMove(row - 2,col))
			{
				if(gb.getOccupancyAt(row + 1, col) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row - 1, col) != chipColour && 
				   gb.getOccupancyAt(row - 2, col) != chipColour)
				{
					hueristic++;
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
		if(row >= 0 && row - 1 >= 0 && row - 2 >= 0 && row - 3 >= 0) 
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row - 1,col) && 
			   gb.isPlayableMove(row - 2,col) && gb.isPlayableMove(row - 3,col))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row - 1, col) != chipColour && 
				   gb.getOccupancyAt(row - 2, col) != chipColour && 
				   gb.getOccupancyAt(row - 3, col) != chipColour)
				{
					hueristic++;
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
		if(col - 6 >= 1 && col - 4 >= 1 && col - 2 >= 1 && col >= 1 && 
		   row - 3 >= 0 && row - 2 >= 0 && row - 1 >= 0 && row >= 0)
		{
			if(gb.isPlayableMove(row - 3, col - 6) && gb.isPlayableMove(row - 2, col - 4) && 
			   gb.isPlayableMove(row - 1, col - 2) && gb.isPlayableMove(row, col))
			{
				if(gb.getOccupancyAt(row - 3, col - 6) != chipColour &&
				   gb.getOccupancyAt(row - 2, col - 4) != chipColour &&
				   gb.getOccupancyAt(row - 1, col - 2) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour)
				{
					hueristic++;
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
		if(col - 4 >= 1 && col - 2 >= 1 && col >= 1 && col + 2 <= 13 &&
		   row - 2 >= 0 && row - 1 >= 0 && row >= 0 && row + 1 <= 5)
		{
			if(gb.isPlayableMove(row - 2, col - 4) && gb.isPlayableMove(row - 1, col - 2) && 
			   gb.isPlayableMove(row, col) && gb.isPlayableMove(row + 1, col + 2) )
			{
				if(gb.getOccupancyAt(row - 2, col - 4) != chipColour && 
				   gb.getOccupancyAt(row - 1, col - 2) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row + 1, col + 2) != chipColour)
				{
					hueristic++;
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
		if(col - 2 >= 1 && col >= 2 && col + 2 <= 13 && col + 4 <= 13 && 
		   row - 1 >= 0 && row >= 0 && row + 1 <= 5 && row + 2 <= 5)
		{
			if(gb.isPlayableMove(row - 1, col - 2) && gb.isPlayableMove(row,col) && 
			   gb.isPlayableMove(row + 1, col + 2) && gb.isPlayableMove(row + 2, col + 4))
			{
				if(gb.getOccupancyAt(row - 1, col - 2) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour &&
				   gb.getOccupancyAt(row + 1, col + 2) != chipColour && 
				   gb.getOccupancyAt(row + 2, col + 4) != chipColour)
				{
					hueristic++;
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
		if(col >= 1 && col + 2 <= 13 && col + 4 <= 13 && col + 6 <= 13 && 
		   row >= 0 && row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5)
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row + 1, col + 2) && 
			   gb.isPlayableMove(row + 2, col + 4) && gb.isPlayableMove(row + 3, col + 6))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row + 1, col + 2) != chipColour && 
				   gb.getOccupancyAt(row + 2, col + 4) != chipColour && 
				   gb.getOccupancyAt(row + 3, col + 6) != chipColour)
				{
					hueristic++;
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
		if(col - 6 >= 1 && col - 4 >= 1 && col - 2 >= 1 && col >= 1 &&
		   row + 3 <= 5 && row + 2 <= 5 && row + 1 <= 5 && row >= 0)
		{
			if(gb.isPlayableMove(row + 3, col - 6) && gb.isPlayableMove(row + 2, col - 4) &&
			   gb.isPlayableMove(row + 1, col - 2) && gb.isPlayableMove(row, col))
			{
				if(gb.getOccupancyAt(row + 3, col - 6) != chipColour &&
				   gb.getOccupancyAt(row + 2, col - 4) != chipColour && 
				   gb.getOccupancyAt(row + 1, col - 2) != chipColour &&
				   gb.getOccupancyAt(row, col) != chipColour)
				{
					hueristic++;
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
		if(col - 4 >= 1 && col - 2 >= 1 && col >= 1 && col + 2 <= 13 && 
		   row + 2 <= 5 && row + 1 <= 5 && row >= 0 && row - 1 >= 0)
		{
			if(gb.isPlayableMove(row + 2, col - 4) && gb.isPlayableMove(row + 1, col - 2) &&
			   gb.isPlayableMove(row, col) && gb.isPlayableMove(row - 1, col + 2))
			{
				if(gb.getOccupancyAt(row + 2, col - 4) != chipColour &&
				   gb.getOccupancyAt(row + 1, col - 2) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row - 1, col + 2) != chipColour)
				{
					hueristic++;
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
		if(col - 2 >= 1 && col >= 1 && col + 2 <= 13 && col + 4 <= 13 &&
		   row + 1 <= 5 && row >= 0 && row - 1 >= 0 && row - 2 >= 0)
		{
			if(gb.isPlayableMove(row + 1, col - 2) && gb.isPlayableMove(row, col) && 
			   gb.isPlayableMove(row - 1, col + 2) && gb.isPlayableMove(row - 2, col + 4))
			{
				if(gb.getOccupancyAt(row + 1, col - 2) != chipColour && 
				   gb.getOccupancyAt(row, col) != chipColour && 
				   gb.getOccupancyAt(row - 1, col + 2) != chipColour && 
				   gb.getOccupancyAt(row - 2, col + 4) != chipColour)
				{
					hueristic++;
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
		if(col >= 1 && col + 2 <= 13 && col + 4 <= 13 && col + 6 <= 13 &&
		   row >= 0 && row - 1 >= 0 && row - 2 >= 0 && row - 3 >= 0)
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row - 1, col + 2) &&
			   gb.isPlayableMove(row - 2, col + 4) && gb.isPlayableMove(row - 3, col + 6))
			{
				if(gb.getOccupancyAt(row, col) != chipColour &&
				   gb.getOccupancyAt(row - 1, col + 2) != chipColour && 
				   gb.getOccupancyAt(row - 2, col + 4) != chipColour &&
				   gb.getOccupancyAt(row - 3, col + 6) != chipColour)
				{
					hueristic++;
				}
			}
		}
		
		return hueristic;
	}// End of aiCalculation
	
	
}// End of AI
