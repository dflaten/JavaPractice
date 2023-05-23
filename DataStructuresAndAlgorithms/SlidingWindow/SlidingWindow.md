# Sliding Window

## Description
This is a computational method which reduces the number of nested loops you
need to solve a problem. It is a varation of the two pointer problem where you
use the two pointers as two ends of the "window" you are currently looking at.

This method is only more efficient if we do not iterate over every item in the
window because that would just give us `O(kn)` efficiency if it did. For
example if we need to find the largest sum made from three consecutive numbers
in an array we could make a window of size three and then move the window up by
one, subtract the value we just moved out of the window and add the value that
we just added into the window. That gives us `O(n` efficiency. 

## How to Know when you can use this method
1. The problem requires repeated computations on a contiguous set of data
elements. (An array or string) such as that the window moves across the
input from one end to the other. Size of the window could be variable or
fixed, repeated steps could be part of the solution or build up to final
solution. 
1. The computations preformed every time the window moves take `O(1)` or are a
slow-growing function such as log of small variable k, where `k <<n`. 
1. The input must support random access. 
1. You don't have to process the entire data. 

## Real World Problems

### Telecommunications
Find the maximum number of users connected to a cellular network's base station
in every k-millisecond sliding window. 

### E-commerce
        Given a dataset of product IDs in the order they were viewed by the user and a
list of products that are likely to be similar, find how many times these
products occur in the dataset. 

### Video Streaming
Given a list of numbers which are the number of buffering events in a given
user session, calculate the median number of buffering events in each
one-minute interval. 

## Example Problems

### Repeated DNA Sequences
Given a String that represents a DNA sequence and a number, k, return all the
contiguous sequences of length k that occur more than once in the String. The
order of the returned subsequences does not matter. If no repeated subsequence
is found, the function should return an empty set. 

**Also Note**
`1 <= s.length <= 10^4`
`s[i]` == `A` or `C` or `G` or `T`

#### Solution

We use a `HashSet<String>` and harness the built in Java functionality to
compute a Hash value for each substring we are evaluating and then iterate
through the string making sure we end our window searching at 
`i <= s.length() - k` so we don't get any out of bounds errors but also look at
every possible substring.

I should probably add some checks at the beggining of the code to check for k
being greater than or equal to the length of the Stringand produce an error if
it does.

``` java
public static List<String> findRepeatedSequences(String s, int k) {
    HashSet<String> repeatedSequenceChecker = new HashSet<String>();
    ArrayList<String> results = new ArrayList<String>();
    for (int i = 0; i <= s.length() - k; i++) {
        String currentSubString = s.substring(i, i + k);
        if(repeatedSequenceChecker.contains(currentSubString) && !results.contains(currentSubString)) {
            results.add(currentSubString);
        } else {
            repeatedSequenceChecker.add(currentSubString);
        }
    }
    return results;
}
```

### Find Maximum in Sliding Window

Given an integer list find the ,aximum values in all the coniguous sub arrays
of size `x`.

**Constrains**
* 1 <= arr.length <= 10^3
* -10^4 arr[i] <= 10^4
* 1 <= w

#### Solution Attempt 1
Use two pointers to create a window starting at `0, w-1`. 

**Solution in Words**
* Move Array into ArrayList
* Handle edge case
* Create a new Array to store the answers. 
* Iterate through the array using two pointers to create a window the length of
  `w`. 
* Sort that window and put the highest value in the answers List.

**Misses**
1. I should first check to see what to do if the array length is less than `w`. 
I don't know if we should return the max in the array or if we should return
nothing or throw an error. 
1. I should have been creating a copy of the sub array instead of sorting it in 
place. 
1. I realized too late this is just using two pointers and not the window since
   I will be touching each element in the list in order to sort them. 

```java
	public static int[] findMaxSlidingWindow(int[] nums, int w) {
		ArrayList<Integer> numsList = new ArrayList();
		for (int x : nums) {
			numsList.add(x);
		}
		int [] solution;
		if (nums.length < w) {
			return new int[0];
		}
		solution = new int[nums.length - w + 1];
		for (int i = 0; i <= nums.length - w; i++) {
			List<Integer> window = numsList.subList(i, i+ w);
			Collections.sort(window);
			solution[i] = window.get(window.size()-1);
		}
		return solution;
	}
```

#### Actual Solution

```java
	// function to clean up the window
	public static Deque<Integer> cleanUp(int i, Deque<Integer> currentWindow, int[] nums) {
		// remove all the indexes from currentWindow whose corresponding values are smaller than or equal to the current element
		while (currentWindow.size() != 0 && nums[i] >= nums[currentWindow.getLast()]) {
			System.out.println("\t\tAs nums[" + i + "] = " + nums[i] + " is greater than or equal to nums[" + currentWindow.getLast() + "] = " + nums[currentWindow.getLast()] + ",");
			System.out.println("\t\tremove " + currentWindow.getLast() + " from currentWindow");
			currentWindow.removeLast();
		}

		// returning the altered currentWindow
		return currentWindow;
	}

	// function to find the maximum in all possible windows
	public static int[] findMaxSlidingWindow(int[] nums, int w) {
		// if the input array is empty, return an empty array
		if (nums.length == 0) {
			return new int[0];
		}

		// if window size is greater than the array size, set the window size to the array size
		if (w > nums.length) {
			w = nums.length;
		}

		// initializing variables
		int [] output = new int[nums.length - w + 1];
		Deque<Integer> currentWindow = new ArrayDeque<>();

		System.out.println("\n\tFinding the maximum in the first window:");

		// iterate over the first w elements
		for (int i = 0; i < w; i++) {
			System.out.println("\tAdding nums[" + i + "] = " + nums[i] + " to the first window:\n\t\tInitial state of currentWindow: " + currentWindow);

			// for every element, remove the previous smaller elements from currentWindow
        	currentWindow = SlidingWindowMaximum.cleanUp(i, currentWindow, nums);

			// append the index of the current element to currentWindow
			currentWindow.add(i);
			System.out.println("\t\tFinal state of currentWindow: " + currentWindow);
		}

		// appending the maximum element of the current window to the output list
		output[0] = nums[currentWindow.getFirst()];

		System.out.println("\n\tFinding the maximum in the remaining windows:");

		// iterate over the remaining input list
		for (int i = w; i < nums.length; i++) {
			System.out.printf("\tAdding nums[%d] = %d to currentWindow:\n\t\tInitial state of currentWindow: %s\n", i, nums[i], currentWindow);
			
			// for every element, remove the previous smaller elements from currentWindow
			cleanUp(i, currentWindow, nums);

			// remove first index from the currentWindow if it has fallen out of the current window
			if (!currentWindow.isEmpty() && currentWindow.getFirst() <= (i - w)) {
				System.out.printf("\t\tIndex %d has fallen out of the current window,\n", currentWindow.getFirst());
				System.out.println("\t\tso, remove it");
				currentWindow.removeFirst();
			}

			// append the index of the current element to currentWindow
			System.out.printf("\t\tAppending %d to currentWindow\n", i);
			currentWindow.add(i);
			System.out.printf("\t\tFinal state of currentWindow: %s\n", currentWindow);

			// adding the maximum element of the current window to the output list
			output[i - w + 1] = nums[currentWindow.getFirst()];
		}

		// return the array containing output
		return output;
	}
```

### Minimum Window Subsequence

Given to strings, `a` and `b`, find the shortest substring in `a` such that `b`
is a subsequence of that substring.

A *substring* is defined as a contiguous sequence of characters within a string.
A *subsequence* is a sequence that can be derived from another sequence by
deleting zero or more elements without changing the order of ther remaining
elements. 

If there are no substrings that cover the characters return an empty string.

If there are multiple substrings that meet the requirements, return the left
most one.

#### Example
`a` = "abbcb"
`b` = "ac"

In this example "abbc" is a substring of `a`, from which we can derive `b` by
removing the two bs from "abbc". The function should return "abbc" since that
is the shortest subsequence that can be dereived from it. 


#### Solution
First iterate over `a` until all the characters from `b` have been found in the
same order and mark the end of the current window. Then iterate backward over
`a` until all of the characters of `b` have been found in the reverse order and
mark the start of the current window. 

If the window you now have is smaller than the shortest subsequence found so
far update it. 

Repeat the process every time from the second character of the current window
until the end of `a` has been reached. 

Then return the minimum window subsequence. 

```java
  public static String minWindow(String s, String t) {
        int sizeOfS = s.length();
        int sizeOfT = t.length();

        //Start with an impossibly large number
        float minimumSubsequenceLength = Float.POSITIVE_INFINITY;

        int indexOfS = 0;
        int indexOfT = 0;

        int start = 0;
        int end = 0;
        String minSubsequence = "";

        //Iterate through the entire first String
        while (indexOfS < sizeOfS) {
            if (s.charAt(indexOfS) == t.charAt(indexOfT)) {
                if (indexOfT == 0) {
                    start = indexOfS;
                }
                //Move the second string ahead one
                indexOfT += 1;

                // Handle the final match of the second string meaning we 
                // have found a subsequence!
                if (indexOfT == sizeOfT) {
                    end = indexOfS;
                    if ((end - start + 1) < minimumSubsequenceLength) {
                        minimumSubsequenceLength = end - start + 1;
                        minSubsequence = s.substring(start, end + 1);
                    }
                    // Starting next iteration at the beginning of this sub
                    // sequence to potentially identify any sub sequences inside
                    // the found subsequence.
                    indexOfS = start;
                    indexOfT = 0;
                }
            }
            indexOfS += 1;
        }
        return minSubsequence;
    }
```

### Minimum Window Substring
We are given two strings, s and t, find the minimum window substring of t in
is.

The minimum window substring of t in s is defined as follows:

1. It is the shortest substring of s that includes all of the characters
   present in t. 
1. The frequency of each character in this substring that belongs to t should
   be equal to or greater than its frequency in t. 
1. The order of the characters does not matter here. 

Constraints: 
Strings `s` and `t` consist of uppercasee and lowercase English characters.



