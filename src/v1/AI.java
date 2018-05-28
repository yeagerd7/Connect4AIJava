package v1;
import java.util.Stack;
import java.util.ArrayList;
public class AI
{
	ArrayList<Stack<int>> calculations = new ArrayList<Stack<int>>(); // <-- Where AI Huerstic is stored
	
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
		if(col-3>=0 && col-2 >= 0 && col-1 >= 0 && col >= 0) 
		{
			if(gb.isPlayableMove(row,col-3) && gb.isPlayableMove(row,col-2) && gb.isPlayableMove(row,col-1) && gb.isPlayableMove(row,col) )
			{
				if(gb.getOccupancyAt(row, col-3) != chipColour && gb.getOccupancyAt(row, col-2) != chipColour && gb.getOccupancyAt(row, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour)
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
		if(col-2>=0 && col-1 >= 0 && col >= 0 && col + 1 <= 6) 
		{
			if(gb.isPlayableMove(row,col-2) && gb.isPlayableMove(row,col-1) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row,col+1))
			{
				if(gb.getOccupancyAt(row, col-2) != chipColour && gb.getOccupancyAt(row, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row, col+1) != chipColour)
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
		if(col-1 >= 0 && col >= 0 && col + 1 <= 6 && col + 2 <= 6) 
		{
			if(gb.isPlayableMove(row,col-1) && gb.isPlayableMove(row,col) && gb.isPlayableMove(row,col+1) && gb.isPlayableMove(row,col+2))
			{
				if(gb.getOccupancyAt(row, col-1) != chipColour && gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row, col+1) != chipColour && gb.getOccupancyAt(row, col+2) != chipColour)
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
		if(col >= 0 && col + 1 <= 6 && col + 2 <= 6 && col + 3 <= 6) 
		{
			if(gb.isPlayableMove(row,col) && gb.isPlayableMove(row,col+1) && gb.isPlayableMove(row,col+2) && gb.isPlayableMove(row,col+3))
			{
				if(gb.getOccupancyAt(row, col) != chipColour && gb.getOccupancyAt(row, col+1) != chipColour && gb.getOccupancyAt(row, col+2) != chipColour  && gb.getOccupancyAt(row, col+3) != chipColour)
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
		if(row-3>=0 && row-2 >= 0 && row-1 >= 0 && row >= 0) 
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

