package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
	    Connect4Game game = new Connect4Game();
	    while(game.getWinner() == 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Its the human's turn
            int count = 0;
	        if(game.getTurn() == 0) {
	            System.out.println("\nIts your turn!");
	            System.out.print("Drop a red chip in your desired column (0, 1, 2, 3, 4 ,5, 6): ");
	            int column = Integer.parseInt(br.readLine().trim());
	            while(column > 6 || column < 0) {
	                System.out.print("Please enter a valid column (0, 1, 2, 3, 4, 5 ,6): ");
                    column = Integer.parseInt(br.readLine().trim());
                }
                column = 2 * column + 1;
	            game.humanPlay(column);
	            System.out.println("\nYou dropped a red chip!\n");
	            game.printBoard();
            }
            //Its the AI's turn
            else {
                System.out.println("\nIts the AI's turn!");
                game.AIPlay();
	        }

            //Changes the turn
            if(game.getTurn() == 0) {
	            game.setTurn(1);
            }
            else {
	            game.setTurn(0);
            }

            //Check for Winner
            game.setWinner(game.checkForWinner());

            count++;
            //Checks for tie
            if(count == 48) {
	            game.setWinner(3);
            }
        }
        //Prints the result of the game
        if(game.getWinner() == 1) {
	        System.out.println("You won the game! :D");
        }
        else if(game.getWinner() == 2) {
	        System.out.println("The lost the game! :(");
        }
        else {
	        System.out.println("The game is a draw!");
        }
    }
}
