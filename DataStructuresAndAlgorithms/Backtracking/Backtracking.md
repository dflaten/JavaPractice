# Backtracking

Backtracking is a technique that explores paths to find a solution, building a
solution step by step by increasing values with time and removing choices that
don't contribute to the solution based on some constraints. Unlike recursion
which calls a function until it reaches a base case backtracking tries to
explore all possible paths to a solution. 

Backtracking first explores one possible solution, if the required criteria
have been met with that option, a path that stems from that option is chosen
and we continue exploring that solution. If we reach our solution we return it
otherwise if our condition is violated at some point in the path we backtrack
and explore another path. 

Backtracking is better than brute-force as we don't have to generate all
possible solutions. It provides us with the option to check our required
condition at each possible recursive call. If the condition is met, we continue
that path, if not we take a step back and explore another path. 

## Examples

### Find a path of 1s from top-left to bottom-right in an nxn binary maze

![FindAPath](FindAPath.png "Find a path solution with diagram.")

## How to Know if a Problem is this type?
* While constructing any possible solution, all paths must be explored. Meaning
  if exploring a certain path results in a dead end, we need to move back one
  level and explore all other paths in the solution space. 
* The problem requires us to consider all feasible solutions in order to select
  the best one. While solving such a problem, not a single feasible solution
  may be ignored. 
* The problem requires us to compile a list of all feasible solutions. 
* It does not if while constructing a solution, failing to meet the
  acceptability criteria eliminates all other possibilities within that solution
  so those remaining possibilities need to be checked. 

  Example: Given a set of integers and a target sum, determine if any subset of
  the given set of integers can sum up to the target sum. The nature of the
  problem is such that, once we find a subset that sums up to the target, there
  is no need to continue exploring other subsets. Similarly, if we reach a
  point where adding any additional elements to the current subset would result
  in the sum exceeding the target, there is no need to explore any more
  possibilities for that subset.

## Real World Examples

**Constraint satisfaction problems**: Backtracking is used to solve puzzles
such as Sudoku. 

**Recursive descent parsers**: The compiler takes a path through the grammar
and reaches a point where the incoming tokens no longer match that part of the
grammar. Therefore, the compiler backtracks to a point where there is another
path through the grammar and follows that. This can occur several times until
the compiler finds a grammar path that fits the incoming tokens, in which case
it accepts the construct. If it find no matching path, a syntax error is shown.

## Problems

### N Queens
Given a chessboard of size `n x n` determine how many ways n queens can be
placed on the board such that no two queens attack each other. 

#### Constraints
* A queen can move horizontally, vertically, and diagonally on a chessboard.
  One queen can be attacked by another queen if they both share the same row,
  column, or diagonal
* `1 <= n <= 9`

![Queen Example](QueenExample.png "The input is n=4, the output is 2")

#### Solution

1. Start by placing a queen in the first column of the first row of the chess
   board. 
2. Since you can't place another queen in a row that already has one, search
   for a safe position for the next queen in the next row. 
3. Iterate over the rows to find a safe placement for the queens. Store the
   column number where a queen is placed in a list. 
4. If a safe position is not found, backtrack to the previous valid placement.
   Search for another solution.
5. If a complete solution is found add it to the results array and backtrack to
   find other valid solutions in the same way.

![Queen 1](Queens1.png "Place the first queen first position first row.") 
![Queen 2](Queens2.png "Place the second queen thrid position, 2nd row.")
![Queen 3](Queens3.png "No place for third queen in third row.")
![Queen 4](Queens4.png "Backtrack to 2nd row and place 2nd queen on 4th column.")
![Queen 5](Queens5.png "Place 3rd queen in 2nd column 3rd row.")
![Queen 6](Queens6.png "No place for Queen on 4th row.")
![Queen 7](Queens7.png "No other place for Queen on 3rd row.")
![Queen 8](Queens8.png "No other place for Queen on 2nd row.")
![Queen 9](Queens9.png "Trying 1rst Queen in 2nd column.")
![Queen 10](Queens10.png "2nd Queen in 4th column.")
![Queen 11](Queens11.png "3rd queen on 1rst column.")
![Queen 12](Queens12.png "4th queen on 3rd column. A successful solution!")

```java
import java.util.*;
public class Main{
     
    /** 
     * This function is the main function called to solve the N Queen problem.
    */
    public static int solveNQueens(int n) {
        List<List<Integer>> results = new ArrayList<>();
        // This creates a list of integers size n filled with -1
        List<Integer> solution = new ArrayList<Integer> (Collections.nCopies(n, -1));

        solveNQueensRec(n, solution, 0, results);
        return results.size();
    }
    /** 
     * Recursive function which solves the N Queen Problem. 
     * TODO: Understand how this works
    */
    public static void solveNQueensRec(int n, List<Integer> solution, int row, List<List<Integer>> results) {
        if (row == n) {
            results.add(solution);
            return;
        }

        for (int i = 0; i<n; i++) {
            boolean valid = isValidMove(row, i, solution);
            if (valid) {
                solution.set(row, i);
                solveNQueensRec(n, solution, row + 1, results);
            } 
        }
    }
    /** 
     * This method determines if a queen can be placed at proposedRow, proposedCol
     * with current solution. Move is valid only if no queen in current
     * solution may attack the square at proposedRow and proposedCol.
    */
    public static boolean isValidMove(int proposedRow, int proposedCol, List<Integer> solution) {
        int oldRow = 0;
        int oldCol = 0;
        int diagonalOffset = 0;

        for (int i = 0; i < proposedRow; i++) {
            oldRow = i;
            oldCol = solution.get(i);
            diagonalOffset = proposedRow - oldRow;

            if (oldCol == proposedCol || 
                oldCol == proposedCol - diagonalOffset || 
                oldCol == proposedCol + diagonalOffset) {

                return false;
            }
        }

        return true;
    }
}
```
