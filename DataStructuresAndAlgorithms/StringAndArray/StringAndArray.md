# String and Array Problems
Some problems are just simple String and Array problems that can be solved
using basic understanding of these structures and how they work. 

## Examples

### Best Time to Buy and Sell Stock

You are given an array, prices, where prices[i] is the cost of a stock on a
given day. You want to maximize your profit by choosing to buy and sell on the
right days. 

Return the maximum profit you can make. 

Example: [7,1,5,3,6,4]
Output: 5 (Buy on 2(1) sell on day 5(6))

#### Solution
We will track the smallest number and the highest profit as we move through the
array. Because we are moving through in order of days we can always look for
the smallest and calculate the profit using the current number in the array.

```java
    public int maxProfit(int[] prices) {
       int minValue = Integer.MAX_VALUE;
       int profit = 0;
       for (int i = 0; i < prices.length; i++) {
           minValue = Math.min(minValue, prices[i]);
           profit = Math.max(profit, prices[i] - minValue);
       }
       return profit;
    }
```

### Merge 2 Sorted Lists

#### Solution

```java

    /* Problem: Given two sorted arrays, the first of which has enough space in it 
     * to fit the items in the second. Merge the two arrays while keeping all of the
     * elements sorted.
     *
     */
class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Iterate through nums1 and nums2 at the same time using pointers,
        // comparing each item.

        // If the item in num1 is less than the item in num2 we move the pointer 
        // for num1 forward (until we get to the end of the list)
        // If the item in num2 is less than the item in num1 then we take the item
        // in num one and place it in a min stack, then replace the value in num1
        // with the value in num2 and move the num1 and num2 pointers forward.
        // From now on we compare the topitem in the stack(if its not empty) with 
        // the item in num2, and when replacing put the current item in num 1 in 
        // the stack before replacing the value with whatever is smaller.
        // If we get to the end of nums2, place the rest of the values in the 
        // stack in num1.

        PriorityQueue<Integer> minHeap = new PriorityQueue();

        int num1Pointer = 0;
        int num2Pointer = 0;

        while(num1Pointer < m && num2Pointer < n) {
            if(minHeap.isEmpty()) {
                if (nums1[num1Pointer] < nums2[num2Pointer]) {
                    num1Pointer++;
                } else {
                    minHeap.add(nums1[num1Pointer]);
                    nums1[num1Pointer] = nums2[num2Pointer];
                    num1Pointer++;
                    num2Pointer++;
                }
            } else {
                if (minHeap.peek() < nums2[num2Pointer]) {
                    if (num1Pointer < m) {
                        minHeap.add(nums1[num1Pointer]);
                    }
                    nums1[num1Pointer] = minHeap.poll(); 
                    num1Pointer++;
                } else {
                    minHeap.add(nums1[num1Pointer]);
                    nums1[num1Pointer] = nums2[num2Pointer];
                    num1Pointer++;
                    num2Pointer++;
                }
            } 
        }
        // If we got to the end of num2Pointer then we are done. However
        // if we have items left in the heap or list 2 we should add them 
        // to the rest of the list. 
        while (num2Pointer < n) {
              if (!minHeap.isEmpty()) {
                if (minHeap.peek() < nums2[num2Pointer]) {
                    nums1[num1Pointer] = minHeap.poll(); 
                    num1Pointer++;
                } else {
                    nums1[num1Pointer] = nums2[num2Pointer];
                    num1Pointer++;
                    num2Pointer++;
                }
              } else {
                    nums1[num1Pointer] = nums2[num2Pointer];
                    num1Pointer++;
                    num2Pointer++;
              }
        }            
        // Empty the rest of the heap if we need to.
        while (!minHeap.isEmpty()) {
            if (num1Pointer < m) {
               minHeap.add(nums1[num1Pointer]);
               nums1[num1Pointer] = minHeap.poll();
               num1Pointer++;
            } else {
               nums1[num1Pointer] = minHeap.poll(); 
               num1Pointer++;
            }
        }
    }
}
```

#### Much Simpler Solution
We can take advantage of the fact that the 0s at the end of the list can be
ovverrident and sort creating the merged list from the end of the first list.
```java
class Solution {
    public void merge(int [] nums1, int m, int[] nums2, int n) {
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        int sortedIndex = m + n - 1;

        while (nums2Index >= 0) {
            if (nums1Index >= 0 && nums1[nums1Index] > nums2[nums2Index]) {
                nums[sortedIndex--] = nums1[nums1Index--];    
            } else {
                nums1[sortedIndex--] = nums2[nums2Index--];
            }
        }
    }
}
```

### Remove Duplicates

#### Solution 1
Here is my crazy complicated version (which does not work). 

```java
class Solution {
    public int removeDuplicates(int[] nums) {

      // Pointer 1 - End of Evaluated List
      // Pointer 2 - End Of Duplicates
      // Pointer 3 - Current item.
      
      int current = 0;
      int endOfDuplicates = 0;
      int endOfEvaluatedList = 0;

      while(endOfDuplicates < nums.length - 1 && current < nums.length - 1) {
          if (nums[current] == nums[current + 1]) {
              current++;
              endOfDuplicates = current + 1;
              //Move the endOfDuplicates to the end
              while (nums[endOfDuplicates] == nums[current]) {
                 endOfDuplicates++; 
              }
              //Move the current item to the one that needs to be replaced
              current++;
              //Replace it
              nums[current] = nums[endOfDuplicates];
              //Move the end of the List to the current item.
              endOfEvaluatedList = current;
              //Move the current Item to the item next in the list after the duplicate
              current = endOfDuplicates + 1;
          } else {
              current++;
              endOfEvaluatedList++;
          }
      }
      // Return number of items in the list.
      return endOfEvaluatedList + 1;


    }
}
```

#### Solution 2
Solution that actually works. 

```java
   /** 
     * 0. Declare a pointer, endOfDedupe as 1 (second item in array)
     * 1. Iterate through the list starting at the second element using index i. 
     * 2. Compare item at i with the previous, if they are not the same put the item at i
     * at the endOfDedupe position and increase endOfDedupe. 
     * 3. Else just keep iterating through the array until you reach the end.
     * 4. Return endOfDedupe.
     */
    public int removeDuplicates(int[] nums) {
        int endOfDedupe = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[endOfDedupe] = nums[i];
                endOfDedupe++;
            }
        }
        return endOfDedupe;
    }
```

### Find the longest contiguous subarray
What is the length of the longest contiguous subarray containing non-negative 
prime numbers that are even and happy numbers?

#### Solution Outline
1. Iterate through the list. 
2. Determine if the current number is prime, even, and a happy number. If it is
   not move to the next number.
3. If it is set the `currentLongest` to 1 and then calculate the next highest 
   prime number that is even and a happy number. Compare that value with the 
   next value in the list. If it is equal then currentLongest++ and calculate
   the next value to compare. Do this until you get to the end of the array or 
   the next value is not contiguous. Then update your `longestSubarray` if it
   is less than `currentLongest`. 

#### Solution Code

```javascript
/**
Problem:
What is the length of the longest contiguous subarray containing non-negative 
prime numbers that are even and happy numbers?

Solution Outline:
First there is a contradiction in the problem. You can not have a consecutive 
sequence of prime number. as the only even prime number is 2. If we take out the 
even part of the problem the following algorithm will work. 

1. Iterate through the list. 
2. Determine if the current number is prime, even, and a happy number. If it is
   not move to the next number.
3. If it is set the `currentLongest` to 1 and then calculate the next highest 
   prime number that a happy number. Compare that value with the 
   next value in the list. If it is equal then currentLongest++ and calculate
   the next value to compare. Do this until you get to the end of the array or 
   the next value is not contiguous. Then update your `longestSubarray` if it
   is less than `currentLongest`. 
*/
// Function to check if a number is prime
function isPrime(num) {
    const squareRootOfNum = Math.floor(Math.sqrt(num));
    let prime = num != 1;
    for (let i = 2; i < squareRootOfNum + 1; i++) {
        if (num % i == 0) { 
            prime = false;
            break;
        }
    }
    return prime;
  }
  
  // Function to check if a number is a happy number
  // A happy number is a number which eventually reaches 1
  // when replaced by the sum of the squares of each digit.
  function isHappyNumber(num) {
    const seen = new Set();
    while (num !== 1 && !seen.has(num)) {
      seen.add(num);
      num = num
        .toString()
        .split('')
        .map((digit) => parseInt(digit) ** 2)
        .reduce((acc, cur) => acc + cur, 0);
    }
    return num === 1;
  }
  
  function findLongestSubarray(arr) {
    let maxLen = 0;
    let currentLen = 0;
  
    for (let i = 0; i < arr.length; i++) {
      if (isPrime(arr[i]) && isHappyNumber(arr[i])) {
        //For now we are just making sure the items are prime and happy
        //if by "contiguous" we mean the next prime/happy number we would
        //generate the next prime/happy number and make sure the next item
        //in the list is that value. 
        currentLen++;
        maxLen = Math.max(maxLen, currentLen);
      } else {
        currentLen = 0;
      }
    }
  
    return maxLen;
  }
  
  // Example usage
  const numbers = [31, 12, 4, 13, 19, 23, 28, 7, 14, 82];
  const longestSubarrayLength = findLongestSubarray(numbers);
  console.log("Length of the longest subarray:", longestSubarrayLength);
```

