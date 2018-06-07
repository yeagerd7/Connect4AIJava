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
are updated in between and after each round (player move and AI move) of the game.
	
Initial Connect 4 Game This Program Was Built Off Of:
	- http://www.javaproblems.com/2013/01/creating-connect-four-game-in-java.html


