# Subsets
The subsets pattern is used to find permutations/combinations of elements in a
data structure. The input could be a `Set`, `Array`, or `List` of elements. We
then make a series of subsets, which could also be a `Set`, `Array` or `List` 
from the elements in the input based on the conditions the problem provides. 

Backtracking is used to generate the subsets. As a reminder backtracking is a
technique where you use recursive calling to find solution sets by building a
solution setep by step. It uses the depth-first search approach.

## Quick Examples 

### Find all unique subsets in an array that may contain duplicate elements

1. Sort the Array.
2. Traverse each element of the array. If we have not seen the current element
   add it to all the existing subsets to make new subsets. Otherwise add the
   element only to the subsets of the previous iteration to make new subsets.

*Input*: `[4,5,5]`

Intialize: `{}`

First Iteration, current = 4: `{}`, `{4}`

Second Iteration, current = 5: `{}, {4}, {5}, {4,5}`

Third, final, Iteration, current = 5: `{}, {4}, {5}, {4,5}, {5,5}, {4,5,5}`


### Return all possible permutations of an array of distinct integers

1. Swap the `current = 1` integer with each of the others. If there are 3
   integers in total we now have 3 permutations. Each permutation will act as
   the seed for further permutations. 
2. At the next step in each permutation swap the `current = 2` integer with
   each of the remaining integers. This will result in 2 more permutations.
3. Proceed untilt he current equals the total number of integers. That is we
   can't swap any more.

*Input*: `[1,2,3]`

First iteration, `i = 0`, `swap(array[0], array[0]`: `[1,2,3], [1,3,2]`

Second Iteration, `i = 1`, `swap(array[0], array[1]`: `[2,1,3], [2,3,1]`

Third Itereation, `i = 2`, `swap[array[0], array[2]`: `[3,1,2], [3,2,1]`

## Does my problem match this pattern?

1. Yes If the problem requires creating permutations or combinations of elements
   from the input data structure as a final solution or an intermediate step.

## Real World Examples

*Movie combinations*: Create combination of movies chosen from the given list
of genres in a specific sequence. 

*Movie Viewing orders*: Generate all possible permutations of a given list of
movies to provide options for a movie marathon. 

*Total cost of Shipping items*: An equation calculating the toal cost of items
neededs to be devided into subsets of items.  
