<img width="940" alt="Screenshot 2023-04-19 at 8 18 03 PM" src="https://user-images.githubusercontent.com/97604329/233249630-d8b1c30c-8756-4c30-9679-c2cff8dc0285.png">

## So... *Recursive* Connect Four?
I made this game for fun back in 2021. It's built entirely in Java, using Object-Oriented Programming principles.

## How does it work?
The classes are outlined here.

**Slot**: represents the pieces in the game. Each slot has an integer value which represents its color (red/black). This is used to print the appropriate flag for each piece -- (R) for red and (B) for black.
**Player**: represents the players, each player has an ID (either 0/1) that is used to determine who's turn it is.

**Sys**: the game system (the engine). It contains the board, which is represented as a 2D array of Slot objects. It does the following:
* Initializes the grid and players.
* Checks if the game has ended by:
*  Checks the board diagonally to see if 4 pieces of the same color have been arranged for a winning position.
*  Checks the rows & columns for a winning move.
* Performs a move requested by the user.



