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
