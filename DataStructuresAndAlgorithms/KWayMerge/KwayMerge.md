# K-way Merge

This pattern helps to solve problems involving a list of sorted arrays. Here is
what it looks like: 

1. Insert the first element of each array in a min-heap. 
2. Remove the smallest element from the heap and add it to the merged array.
3. Keep track of which array each element comes from.
4. Then insert the mext element of the same array into the heap. 
5. Repeat steps 2 to 4 to fill the merged array in sorted order. 

Here is how it works: 

In this first picture you can see what the min heap and the merged array looks
like  after the first time you get to step 4. 

![K-wayMerge](kwaymerge1.png "K-way merge, after first time it gets to step 4.")

In this picture you can see what it looks like the second time it gets to step
4.

![K-wayMerge](kwaymerge2.png "K-way merge, second time it gest to step 4.")

## How to Know if a problem matches this pattern

Yes, if both these conditions are fulfilled:
* The problem involves a set of sorted arrays, or a matrix of sorted rows or 
sorted columns that need to be merged, either for the final solution, or as an 
intermediate step.

* The problem asks us to find the `kth` smallest or largest element in a set 
of sorted arrays or linked lists.

No, if either of these conditions are fulfilled:

* The input data structures are neither arrays, nor linked lists.
* The data is not sorted, or it’s sorted but not according to the criteria 
relevant to solving the problem.

## Real-world problems
Many problems in the real world use the k-way merge pattern. Let’s look at some examples.

1. *Merge tweets in twitter feed:* Sometimes we need to implement a module that 
adds a user’s Tweets into an already populated Twitter feed in chronological order.

1. *Used in external sorting procedures:* When an algorithm is processing huge 
amounts of data, it needs to repeatedly fetch it from external storage because 
RAM capacity is fixed. To overcome the speed limitation of external storage, 
k-way merges are used in external sorting. Let’s consider a case where we need 
to perform six merges. A binary merge requires three merge passes while a 
6-way merge only requires one pass. K-way merge reduces the number of accesses 
to external storage, which in turn greatly improves performance when dealing 
with large amounts of data.

## Examples

### Merge Sorted Array
Given two sorted integer arrays, `nums1` and `nums2`, and the number of data 
elements in each array, `m` and `n`, implement a function that merges the 
second array into the first one. You have to modify `nums1` in place.

#### Constraints
* `nums1.length = m + n`
* `nums2.length = n`
* 0 <= `m`, `n` <= 200
* 1 <= `m + n` <= 200

#### Solution

First Pass:
```java
public static int[] mergeSorted(int[] nums1, int m, int[] nums2, int n) {
  int p1 = m-1;
  int p2 = n-1;
  int p = nums1.length - 1;

  while(p2 >= 0) {
  // If the value at p1 is greater than the value at p2, set the value at
  // p equal to p1 and decrement p1 and p by 1
  if(nums1[p1] > nums2[p2]) {
     if(p2 < 0) {
          break;
     }
     nums1[p] = nums1[p1];
     if(p1 < p){
        nums1[p1] = Integer.MIN_VALUE;
     }
     p--;
     p1--;
  // else if the value at p2 is greater than the value at p1, set the value at 
  // p equal to p2 and decrement p2 and p by 1
  } else if (nums2[p2] >= nums1[p1]) {
     nums1[p] = nums2[p2];
     p--;
     p2--;
  }
  }
  return nums1;
}
```

Corrected Implementation:
```java
public static int[] mergeSorted(int[] nums1, int m, int[] nums2, int n) {
  int p1 = m-1;
  int p2 = n-1;
  int p = nums.length - 1;
  // Iterate through the length of nums1 as it is the larger array where
  // all our numbers will end up.
  while(p >= 0) {
    // If p2 is <0 that means we've compared all the numbers in both
    // arrays and we can stop.
    if (p2 < 0) {
     break;
   }
   // If p1 is 0 or less we have compared all the numbers in p1 so we 
   // should stop. Otherwise we should compare to see which is bigger. 
   // nums1[p1] or nums2[p2]
   if(p1 >=0 && nums1[p1] > nums2[p2]) {
    nums1[p] = nums1[p1];
    p--;
    p1--;
   // We use an else statement because if we either have nums2[p2] 
   // greater than nums1[p1] or we have finished all the p1 numbers
   // and we just need to fill in the rest of p2 
  } else {
   nums1[p] = nums2[p2];
   p--;
   p2--;
 }
}
return nums1;
}
```

### Kth Smallest Number in M Sorted Lists
Given an m number of sorted lists in ascending order and an integer, `k`, find
the kth smallest number amoung all the given lists. 

Although there can be repeating values in the lists, each element is considered
unique and therefore contributes to calculating the kth smallest number. 

If 'k' is greater than the total number of elements in the input lists. return
the greatest element from all the lists and if there are no elements in the
input lists, return 0. 

#### Constraints
* 1 <= `m` <= 300
* 0 <= 'list[i].length <= 300 
* 1 <= `k` <= 10^9

#### Example
L1 = [2,6,8]
L2 = [3,6,10]
L3 = [5,8,11]

k = 5

Output = 6 because it is the 6th number in the whole list:

[2,3,5,6,6,8,8,10,11]

#### Solution
*Brute Force*
1. Brute Force way would be to merge all items into a new array. 
2. Return the k'th item from that Array

*More Efficient Solution*
1. Push the first element of each list in the min-heap.
2. Remove the top (root) of the min-heap. 
3. If the popped element has the next element in its list. push the next
   element onto the min heap. 
4. If k elements have been removed from the heap, return the last popped
   element. 

My solution below. This needs some tweaking to work but is pretty close:
```java
  public static int kSmallestNumber(List<List<Integer>> lists, int k) {

    // Edge Cases
    if (lists.stream().mapToBoolean(List::isEmpty()).reduce(true,(a,b) -> a && b)) {
      return 0
    }
    if (k > lists.stream().mapToInt(List::size).sum();) {
      return findLargestNumber(lists);
    }
    // FindSmallestKthNumber
    // Will use a Heap of Integer arrays sorted by the smallest number in each list with the format:
    // [a,b,c] where:
    // a = smallest number in the list
    // b = which list in the `lists` from the input a is from
    // c = the index of a in b
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
    int numberOfPoppedItems = 0;
    int [] lastItemPolled = new int [];
   //Push the first Element of Each List onto the min heap.
    for (int a = 0; a < lists.size(); a++) {
      minHeap.offer(new int [] {lists.get(a).get(0), 0, 0});
    }
    
    int numbersChecked = 0;
    int smallestNumber = 0;

    while (true) {
      int i = 0;
      lastItemPolled = minHeap.poll();
      numberOfPoppedItems++;

      if(numberOfPoppedItems == k) {
        break;
      }
       // get the smallest number from top of heap and its corresponding list and index
      int[] smallest = minHeap.poll();
      smallestNumber = smallest[0];
      int listIndex = smallest[1];
      int numIndex = smallest[2];

      // if there are more elements in list of the top element,
      // add the next element of that list to the min-heap
      if (numIndex + 1 < lists.get(smallest[1]).size()) {
        kthSmallest.offer(new int[] {lists.get(listIndex).get(numIndex + 1), listIndex, numIndex + 1});
      }

    }

    return lastItemPolled[0];
    
  }

  public static findLargestNumber(List<List<Integer>> lists) {
    int largestNumber = Integer.MIN_VALUE;
    for (int i = 0; i < lists.size(); i++) {
      List<Integer> current = lists.get(i);
      if (current.get(current.size()-1) > largestNumber) {
        largestNumber = current.get(current.size()-1);
      }
    }
    return largestNumber;
  }
```

Working Solution: 
```java
public static int kSmallestNumber(List<List<Integer>> lists, int k) {
    // storing the length of lists to use it in a loop later
    int listLength = lists.size();
    // declaring a min-heap to keep track of smallest elements
    PriorityQueue<int[]> kthSmallest = new PriorityQueue<>((a, b) -> a[0] - b[0]);

    for (int index = 0; index < listLength; index++) {
        // if there are no elements in the input lists, continue
        if (lists.get(index).size() == 0) {
            continue;
        } else {
            // placing the first element of each list in the min-heap
            kthSmallest.offer(new int[] {lists.get(index).get(0), index, 0});
        }
    }

    // set a counter to match if our kth element
    // equals to that counter, return that number
    int numbersChecked = 0, smallestNumber = 0;
    while (!kthSmallest.isEmpty()) {  // iterating over the elements pushed in our min-heap
        // get the smallest number from top of heap and its corresponding list and index
        int[] smallest = kthSmallest.poll();
        smallestNumber = smallest[0];
        int listIndex = smallest[1];
        int numIndex = smallest[2];
        numbersChecked++;

        if (numbersChecked == k) {
            break;
        }

        // if there are more elements in list of the top element,
        // add the next element of that list to the min-heap
        if (numIndex + 1 < lists.get(smallest[1]).size()) {
            kthSmallest.offer(new int[] {lists.get(listIndex).get(numIndex + 1), listIndex, numIndex + 1});
        }
    }

    // return the Kth number found in input lists
    return smallestNumber;
}
```

### Merge 2 Linked Lists
Given two sorted Linked Lists merge them into one large sorted Linked List.
This is a simplified version of the Merge K LinkedLists problem.

#### Constraints
* The number of nodes in boths lists is in the range `[0,50]`. 
* `-100 <= Node.val <= 100`
* Both lists are sorted smallest to largest.

#### Example
  **Input**: `list1 = [1,2,4], list2 = [1,3,4]`

  **Output**: `[1,1,2,3,4,4]`

#### Solution
This problem was solved by using a `resultNode` which would always point to the
new next. Then we just iterated through both lists and took the smallest item
from each list to add it to the result until at least one of the lists are
empty (adding the other list to the tail).

```java
 ListNode mergeTwoLists(ListNode listA, ListNode listB) {

    // Create result Node which will always point at the head of the new list
    // with next.
    ListNode resultNode = new ListNode();

    ListNode tail = resultNode;

    while (true) {
      // Check if listA is empty or traversed
      if (listA == null) {
        tail.next = listB;
        break;
      }
      // Check if listB is empty or traversed
      if (listB == null) {
        tail.next = listA;
        break;
      }

       
      // Check which listdata is lesser, it will be appended to
      // tail.next
      if (listA.val <= listB.val) {
        tail.next = listA;
        listA = listA.next;
      } else {
        tail.next = listB;
        listB = listB.next;
      }

      //update tail
      tail = tail.next;
    }
    //returning the Result List
    return resultNode.next;
  }
```
