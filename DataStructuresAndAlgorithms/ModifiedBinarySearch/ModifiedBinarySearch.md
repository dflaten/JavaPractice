# Modified Binary Search

This pattern is an extension of the traditional binary search algorithm.

## Traditional Binary Search
This search algorithm is used for searching for a target value, i, in a sorted 
list that supports direct addressing (or random access). It follows a divide and
conqueror pattern which reduces the search space with each iteration. Three
pointers(indexes) are used: 

1. `start` - instantiated at the beggining of the list.
2. `end` - instantiated at the end of the list.
3. `middle` - instantiated in the middle of the list. 

Here are the steps assuming ascending order:
1. Set the first values of the indexes listed above. Middle can be calculated
   as the `start + end / 2`.
2. Check to see if `middle.value == i`. If so return true.
3. Compare `i` to `middle.value`. If `i` is less than `middle.value` update
   the `end` to `middle` and find the new middle. If `i` is greater than
   `middle.value` update the `start` to `middle` and find the new `middle`.
4. Continue steps 2/3 until you find the value you are looking for or if
   `start >= end`. If the latter return false. 

Binary Search operates at `O(log(n))` time since we divide the list in half at
each step.

## Modified Binary Search

The modified binary search pattern involves taking the traditional binary
search pattern and applying conditions or transformations. Some common
variations include: 

1. Binary Search on a modified array: The array might be sorted and then
   rotated around some unknown pivot. Or some element sin a sorted array might 
   be modified based on a condition. To handle these types of scenarios we can
   modify the basic binary search technique to identify anomalies in the
   sorted order.
2. Binary Search with multiple conditions: For example, finding a target range
   rather than a target value, finding the leftmost or right most occurrence of 
   a target value. 

## Example
![FindFirstAndLastPositionOfElement](FindFirstAndLastPositionOfElement.png "Find the first and last potition of element in array.")

![IntegerSquareRootOfNumber](IntegerSquareRootOfNumber.png "Find the square root of a number.")

## How to Know if Problem Matches this pattern
* The problem requires us to find a target value (or first or last occurrence)
  in a sorted list/array
* Can use this pattern when segments of an input are sorted, even if the whole
  list is not sorted. For example we can find a target in a sorted array that
  has been rotated around an arbitrary pivot. 
* The input array must support direct addressing.
* The sorted order must be in a way that is relevant for the search. For
  example if we are looking for a specific date in a list of dates sorted
  alphabetically this pattern will not work. 
* The solution must require us to find a particular value or range of values.

## Real World Problems
* *Dictionary(As in a book)*: We can use classic binary search to find words 
  quickly inside a dictionary. If we wanted to find all words that had a common
  prefix we could use a modified binary search. 
* *Debugging with minimal support*: If a code script consists of `n` lines, and
  there is a bug somewhere in the script. Binary Search is performed to find
  the bug by dividing the code based on line numbers. 
* *Student Documents*: Given a list of students sorted by their scores on a
  test, find all the students that scored between 40% and 55%. 


