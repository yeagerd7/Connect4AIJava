package v1;
import java.util.Stack;
import v1.Connect4Game;
import java.util.ArrayList;

public class AI
{
	ArrayList<Stack<Integer>> calculations = new ArrayList<Stack<Integer>>(); // <-- Where AI Huerstic is stored
	
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
	
	public void addPoints(int points, int column)
	{
		int totalPoints = calculations.get(column).peek() + points;
		calculations.get(column).push(totalPoints);
	}
	
	// If AI moves are blocked then it pops everything and recalculates
	public void blockedMove(int column)
	{
		for(int i = 0; i<7; i++)
		{
			calculations.get(i).pop();
		}
	}
	
	/*
	 * Given a game board and a specific coordinate
	 * this method will update a specific columns hueristic
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
		 * |_|_|_|_|_|_|_|
		 * |_|_|_|Y|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 * |_|_|_|?|_|_|_|
		 */
		if(row - 3 >= 0 && row-2 >= 0 && row-1 >= 0 && row >= 0) 
		{
			if(gb.isPlayableMove(row-3,col) && gb.isPlayableMove(row-2,col) && gb.isPlayableMove(row-1,col) && gb.isPlayableMove(row,col) )
			{
				if(gb.getOccupancyAt(row-3, col) != chipColour && gb.getOccupancyAt(row-2, col) != chipColour && gb.getOccupancyAt(row-1, col) != chipColour && gb.getOccupancyAt(row, col) != chipColour)
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
		 * |_|_|_|_|_|_|_|
		 */
		if(row-2 >= 0 && row-1 >= 0 && row >= 0 && row + 1 <= 5) 
		{
			if(gb.isPlayableMove(row-2,col) && gb.isPlayableMove(row-1,col) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row + 1,col) )
			{
				if(gb.getOccupancyAt(row-2, col) != chipColour && gb.getOccupancyAt(row-1, col) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row + 1, col) != chipColour)
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
		 * |_|_|_|_|_|_|_|
		 */
		if(row-1 >= 0 && row >= 0 && row + 1 <= 5 && row + 2 <= 5) 
		{
			if(gb.isPlayableMove(row-1,col) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row + 1,col) && gb.isPlayableMove(row + 2,col))
			{
				if(gb.getOccupancyAt(row-1, col) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row + 1, col) != chipColour && gb.getOccupancyAt(row + 2, col) != chipColour)
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
		 * |_|_|_|_|_|_|_|
		 */
		if(row >= 0 && row + 1 <= 5 && row + 2 <= 5 && row + 3 <= 5) 
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row + 1,col) && gb.isPlayableMove(row + 2,col) && gb.isPlayableMove(row + 3,col))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row + 1, col) != chipColour && gb.getOccupancyAt(row + 2, col) != chipColour && gb.getOccupancyAt(row + 3, col) != chipColour)
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
		* |_|_|_|_|_|_|_|
		*/
		if(col-3>=0 && col-2 >= 0 && col-1 >= 0 && col >= 0 && row+3 <= 5 && row+2 <= 5 && row+1 <= 5 && row >= 0)
		{
			if(gb.isPlayableMove(row+3,col-3) && gb.isPlayableMove(row+2,col-2) && gb.isPlayableMove(row+1,col-1) && gb.isPlayableMove(row,col) )
			{
				if(gb.getOccupancyAt(row+3, col-3) != chipColour && gb.getOccupancyAt(row+2, col-2) != chipColour && gb.getOccupancyAt(row+1, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour)
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
		* |_|_|_|_|_|_|_|
		*/
		if(col-2 >= 0 && col-1 >= 0 && col >= 0 && col+1 <= 6 && row+2 <= 5 && row+1 <= 5 && row >= 0 && row-1 >= 0)
		{
			if(gb.isPlayableMove(row+2,col-2) && gb.isPlayableMove(row+1,col-1) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row-1,col+1) )
			{
				if(gb.getOccupancyAt(row+2, col-2) != chipColour && gb.getOccupancyAt(row+1, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row-1, col+1) != chipColour)
				{
					hueristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|_|_|_|
		*/
		if(col-1 >= 0 && col >= 0 && col+1 <= 6 && col+2 <= 6 && row+1 <= 5 && row >= 0 && row-1 >= 0 && row-2 >= 0)
		{
			if(gb.isPlayableMove(row+1,col-1) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row-1,col+1) && gb.isPlayableMove(row-2,col+2))
			{
				if(gb.getOccupancyAt(row+1, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row-1, col+1) != chipColour && gb.getOccupancyAt(row-2, col+2) != chipColour)
				{
					hueristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|_|_|?|_|
		* |_|_|_|_|_|_|?|
		*/
		if(col >= 0 && col+1 <= 6 && col+2 <= 6 && col+3 <= 6 && row >= 0 && row-1 >= 0 && row-2 >= 0 && row-3 >= 0)
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row-1,col+1) && gb.isPlayableMove(row-2,col+2) && gb.isPlayableMove(row-3,col+3))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row-1, col+1) != chipColour && gb.getOccupancyAt(row-2, col+2) != chipColour && gb.getOccupancyAt(row-3, col+3) != chipColour)
				{
					hueristic++;
				}
			}
		}
		// This will test all possible forward slash connect four 
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |?|_|_|_|_|_|_|
		*/// <-- LEFT OFF HERE
		if(col-3>=0 && col-2 >= 0 && col-1 >= 0 && col >= 0 && row-3 >= 0 && row-2 >= 0 && row-1 >= 0 && row >= 0)
		{
			if(gb.isPlayableMove(row-3,col-3) && gb.isPlayableMove(row-2,col-2) && gb.isPlayableMove(row-1,col-1) && gb.isPlayableMove(row,col))
			{
				if(gb.getOccupancyAt(row-3, col-3) != chipColour && gb.getOccupancyAt(row-2, col-2) != chipColour && gb.getOccupancyAt(row-1, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour)
				{
					hueristic++;
				}
			}
		}
		/* 
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		* |_|_|_|_|?|_|_|
		* |_|_|_|Y|_|_|_|
		* |_|_|?|_|_|_|_|
		* |_|?|_|_|_|_|_|
		* |_|_|_|_|_|_|_|
		*/// <-- LEFT OFF HERE
		if(col-2 >= 0 && col-1 >= 0 && col >= 0 && col+1 <= 6 && row-2 >= 0 && row-1 >= 0 && row >= 0 && row+1 <= 5)
		{
			if(gb.isPlayableMove(row-2,col-2) && gb.isPlayableMove(row-1,col-1) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row+1,col+1))
			{
				if(gb.getOccupancyAt(row-2, col-2) != chipColour && gb.getOccupancyAt(row-1, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row+1, col+1) != chipColour)
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
		* |_|_|_|_|_|_|_|
		*/// <-- LEFT OFF HERE
		if(col-1 >= 0 && col >= 0 && col+1 <= 6 && col+2 <= 6 && row-1 >= 0 && row >= 0 && row+1 <= 5 && row+2 <= 5)
		{
			if(gb.isPlayableMove(row-1,col-1) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row+1,col+1) && gb.isPlayableMove(row+2,col+2))
			{
				if(gb.getOccupancyAt(row-1, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row+1, col+1) != chipColour && gb.getOccupancyAt(row+2, col+2) != chipColour)
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
		* |_|_|_|_|_|_|_|
		*/// <-- LEFT OFF HERE
		if(col >= 0 && col+1 <= 6 && col+2 <= 6 && row-1 >= 0 && row >= 0 && row+1 <= 5 && row+2 <= 5 && row+3 <= 5)
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row+1,col+1) && gb.isPlayableMove(row+2,col+2) && gb.isPlayableMove(row+3,col+3))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row+1, col+1) != chipColour && gb.getOccupancyAt(row+2, col+2) != chipColour && gb.getOccupancyAt(row+3, col+3) != chipColour)
				{
					hueristic++;
				}
			}
		}
		
		return hueristic;
	}// End of aiCalculation
	
	
}// End of AI
