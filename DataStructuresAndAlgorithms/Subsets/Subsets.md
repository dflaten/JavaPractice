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

## Problems

### Subsets
Given an array of integers, `nums`, find all possible subsets of `nums`,
including the empty set.

#### Solution Steps
1. Compute the number of possible subsets of the given set using 2^n, where `n`
   is the number of elements.
2. Start a loop from 0 to count the number of subsets and add an empty list to
   the results list in the first iteration. 
3. In each iteration, create a bit mask of length `n` for each element in the
   input set. If the ith bit is set, set[i] will be present in the current
   subset. 
4. After iterating over all the elements in the input set, append the current
   subset to the lit of subsets. 

So for example if n = 3. We will have 8 sub sets. We can use binary
respresentations of indices to represent which elements should be included in
each subset. 

Input: `{13,2,3}`

|                           |     |      |            |            |        |        |       |          |
| ------------------------- | --- | ---- | ---------- | ---------- | -----  | ------ | ----- | -------- |
| **Output Set**            | {}  | {13} | {2}        | {13,2}     | {3}    | {13,3} | {2,3} | {13,2,3} |
| **Binary Representation** | 000 | 001  | 010        | 011        | 100    | 101    | 110   | 111      |

```java
    // TODO: Look into bit masks more to understand how this works and how I
    // can apply it to other problems.
	static int getBit(int num, int bit) {
		// shifts the first operand the specified number of bits to the left
		int temp = (1 << bit);

		// if binary number and current subset count are equal,
		// return 1 else return 0
		temp = temp & num;
		if (temp == 0) {
			return 0;
		}
		return 1;
	}

	static List<List<Integer>> findAllSubsets(int[] nums) {
		List<List<Integer>> setsList = new ArrayList<>();
		if (nums.length != 0) {
			// finds number of subsets by taking power of length of input array
			int subsetsCount = (int) (Math.pow(2, nums.length));

			for (int i = 0; i < subsetsCount; ++i) {
				// Set is created to store each subset
				List<Integer> subset = new ArrayList<>();
				for (int j = 0; j < nums.length; ++j) {
					// if a specific bit is 1, chooses that number from the original set
					// and add it to the current subset
					if (getBit(i, j) == 1) {
						int x = nums[j];
						subset.add(x);
					}
					
				}
				System.out.println("\tCurrent generated subset: "+ subset.toString());
				// all subsets are now pushed back in the hah set list
				setsList.add(subset);
				System.out.println("\tSubsets list: "+ setsList.toString()+"\n");
			}
		} else {
			List<Integer> emptySet = new ArrayList<>();
			setsList.add(emptySet);
		}
		return setsList;
	}
```

### Permutations
Given an input string, return all possible permutations of the String.

**Constraints**: 
* All characters in the input string are unique. 
* 1 <= word.length <= 6

**Example**:
*Input*: `"bad"`
*Output*: `["bad", "bda", "abd", "adb", "dab", "dba"]`

#### Solution

1. Starting from the first index as the current index, recursively compute the
   permutations of the input String.
2. Compute the permutation by swapping the current index with every index in
   the remaining string.
3. Recurse the computation step by incrementing the current index by 1. 
4. If we reach the end of the string, store the current string as a
   permutation.
5. Return the list of all permutations.

```java
import java.util.*;
public class Main{
    // Need method to swap characters of a given word and letter locations.
    public static char [] swapChar(String word, int i, int j) {
        char [] charArray = word.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;

        return charArray;
    }

    // Recursive Method to create permutations. 
    public static void permuteWordRecursively(String word, int currentIndex, ArrayList<String> results) {
        // Base Case
        if (currentIndex == word.length() - 1) {
            results.add(word);
            return;
        }
        // Iterate through the String
        for (int index = currentIndex; index < word.length(); index++) {
            // Swapping the String using the current index and the iterating index
            char [] swappedStr = swapChar(word, currentIndex, index);
            //Recursively call to now swap the swapped string and the current index increased by one.
            // TODO: Understand how and in what order does this generate the unique permutaitons?
            permuteWordRecursively(String.valueOf(swappedStr), currentIndex + 1, results);
        }
    }
    // Main Method to be called and return the permutations.
    public static ArrayList<String> permuteWord(String word) {
        ArrayList<String> permutations = new ArrayList<>();

        permuteWordRecursively(word, 0, permutations);

        return permutations; 
        
    }
}
```
