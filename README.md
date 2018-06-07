    First off, I’d like to point out that this implementation of a Connect 4 game with an AI was built off of a preexisting 
Connect 4 game prototype which can be found at the end of this paper.  That specific implementation allows two human players 
to play against each other in the console window.  Some of the code is reused and altered to allow the second player (the one
who places the yellow chips) to be used as an AI that would play against a human player in the console window of the IDE.  The
problem this program solves is creating a Connect 4 game written in Java that allows an AI to be used as the second player for
the human to play against. The Connect 4 AI algorithm doesn’t follow the standard Minimax formula but still uses the same 
mechanic of picking the column with the highest score from a heuristic function which represents the best move for the AI to
take given the current state of the game board.  The AI is composed of a heuristic function that gives a score to each column 
(0 – 6) associated with the game board of your standard 6 x 7 Connect 4 game.  That score for each of the column is calculated 
based on defensive moves (made by the human), offensive moves (made by AI) and blocking moves (also made by the human) which 
are updated in between andafter each round (player move and AI move) of the game.
	  For program the design, the program is designed using three classes; Main.java, Connect4Game.java and AI.java.  Connect4Game
creates the actual game and board for the human and AI to play against each other.  AI creates the actual AI to be played 
against the human player and Main creates the Connect4Game and AI objects, plays the AI against the human and actually runs the
program.  As far as the in-depth explanation of the implementation goes, the AI stores the heuristic score for each column in 
stacks of integers grouped together in an array list.  Each stack represents each of the seven columns labeled from zero to six.  
As stated above, the heuristic scores are calculated defensively and offensively through a generic aiCalculations method that 
takes in a specific column, game object and chip color as its parameters.  The row is determined from a method that finds the 
next available row position in the given column parameter allowing us to have a coordinate to calculate.  Offensive calculations 
are noted when passing in a chip color of ‘R’ and defensive calculations are noted when passing in a chip color of ‘Y’.  
Offensive calculations are looking for connect 4 possibilities for the AI, that don’t include ‘R’ chips but can include ‘Y’ 
chips or blank spaces and every possibility found adds one point to the column.  Defensive calculations are looking for the 
exact opposite; all the connect 4 possibilities for the human player, that don’t include ‘Y’ chips but can include ‘R’ chips or
blank chips and every possibility found adds one point to the column.  The points for each column are all added together, along
with the previous column totals, and pushed onto their respective stacks upon completion of the aiCalculations method.  
    There is another generic method called blockedMove that affects the heuristic calculations for each of the columns that 
operates very similarly to the aiCalculations method with some differences. The main difference it analyzes the last coordinate
(row and column pair) that was played as opposed to the coordinate about to played.  That coordinate is then sent through the
method and it collects all of the connect 4 possibilities associated with the coordinate’s column and other playable coordinate
columns that are now blocked because the human player placed a ‘R’ chip in that specific coordinate. The column stacks are then
popped for every connect 4 possibility that was blocked by the human player upon completion of the blockedMove method.  Also at
the end of blockedMove, upon popping all the columns associated with the now blocked connect 4 possibilities, aiCalculations is
called again in an offensive manner to recalculate the heuristic scores for each of the columns that were popped and only those 
columns once.   
    As far as a typical program walkthrough goes, the program begins by having the AI go first.  The AI uses the aiCalculations
method in the offensive manner to calculate the best possible move for it to take then it will take that move.  The human player
then places a chip, and blockedMove is called to recalculate the column heuristic scores.  The cycle continues until either the
human or the AI makes a sequence of four.  If the AI has the possibility to block a winning move, it will ignore the heuristic 
scores and block the winning move to ensure that the human player cannot win.  However, if the AI has the opportunity to block 
a winning move for the human player as well as take a winning move for itself, it will always take the move to ensure that the
AI wins the game.  If a column becomes filled, the heuristic score for that column is decremented by 100 every iteration of the
program to ensure the AI doesn’t pick that column again.  Another thing to note is if the max heuristic score is tied between 
columns, the AI will randomly pick one of the tied columns with the max heuristic score.  If the board fills up and neither 
the AI or the human player have made a sequence of four, the game will end in a draw. 
	
Initial Connect 4 Game This Program Was Built Off Of:
	- http://www.javaproblems.com/2013/01/creating-connect-four-game-in-java.html


