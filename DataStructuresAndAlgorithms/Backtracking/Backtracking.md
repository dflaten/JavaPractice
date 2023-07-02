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

