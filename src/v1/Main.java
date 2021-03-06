package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Connect4Game game = new Connect4Game();
        AI ai = new AI();
        int count = 0;
        while (game.getWinner() == 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //Its the human's turn
            if (game.getTurn() == 0) {
                System.out.println("Its your turn!\n");
                System.out.print("Drop a red chip in your desired column (0, 1, 2, 3, 4 ,5, 6): ");
                int column = Integer.parseInt(br.readLine().trim());
                boolean validInput = false;
                boolean unFilledColumn = false;
                while (!validInput || !unFilledColumn) {
                    while (column > 6 || column < 0) {
                        System.out.print("Please enter a valid column (0, 1, 2, 3, 4, 5 ,6): ");
                        column = Integer.parseInt(br.readLine().trim());
                    }
                    validInput = true;

                    column = 2 * column + 1;

                    while (game.columnFilled(column)) {
                        System.out.print("Please enter a valid column (0, 1, 2, 3, 4, 5 ,6): ");
                        column = Integer.parseInt(br.readLine().trim());
                        column = 2 * column + 1;
                    }
                    unFilledColumn = true;
                }

                game.humanPlay(column);

                if(column == 1) {
                    System.out.println("\nYou dropped a red chip at Column 0\n");
                }
                else {
                    System.out.println("\nYou dropped a red chip at Column " + (column - 1) / 2 + "\n");
                }

                game.printBoard();

                ai.blockedMove(column, game);

                // Calculates for opponents possible connect fours and the more a chip placement in a column can
                // make a connect four, the more points its added
                for (int i = 0; i < 7; i++) {
                    int points = ai.aiCalculations(2 * i + 1, game, 'Y'); // defensive calculations
                    ai.addPoints(points, i);
                }
            }

            //Its the AI's turn
            else {
                String calculationsString = "";
                int column = 0;
                if(count == 0) {
                    System.out.println("The AI goes first!\n");
                }
                else {
                    System.out.println("Its the AI's turn!\n");
                }
                boolean winnableMoveExists = false;
                // updates AI calculations by performing offensive calculations
                for (int i = 0; i < 7; i++) {
                    int points;
                    if (game.columnFilled(2 * i + 1)) {
                        points = -100;
                    }
                    else {
                        points = ai.aiCalculations(2 * i + 1, game, 'R'); //offensive calculations
                    }

                    ai.addPoints(points, i);
                    int currentCalculation = ai.getCalculations().get(i).peek();
                    calculationsString += currentCalculation + " ";

                    if (game.isWinnableMove(game.getNextPositionInCol(2 * i + 1), 2 * i + 1) &&
                            !game.columnFilled(2 * i + 1)) {
                        if (!winnableMoveExists) {
                            winnableMoveExists = true;
                            column = 2 * i + 1;
                        }
                        if (winnableMoveExists && game.getOccupancyAt(game.getNextPositionInCol(2 * i + 1) + 1, 2 * i + 1) == 'Y') {
                            column = 2 * i + 1;
                        }
                    }
                }
                if (!winnableMoveExists) {
                    column = ai.activateBrain();
                    column = 2 * column + 1;
                }

                game.AIPlay(column);
                game.printBoard();

                System.out.println("Current Heuristic Calculation: " + calculationsString + "\n");
                if (column == 1) {
                    System.out.println("AI placed a yellow chip at Column " + 0 + "\n");
                } else {
                    System.out.println("AI placed a yellow chip at Column " + ((column - 1) / 2) + "\n");
                }
            }

            //Changes the turn
            if (game.getTurn() == 0) {
                game.setTurn(1);
            } else {
                game.setTurn(0);
            }

            //Check for Winner
            game.setWinner(game.checkForWinner());

            //Increment Count
            count++;

            //Checks for tie
            if (count == 42) {
                game.setWinner(3);
            }
        }
        //Prints the result of the game
        if (game.getWinner() == 1) {
            System.out.println("You won the game! :D");
        } else if (game.getWinner() == 2) {
            System.out.println("You lost the game, the AI made a connect four! :(");
        } else {
            System.out.println("The game is a draw!");
        }
    }
}