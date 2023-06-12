# Top K Elements
This pattern helps find some specific `k` number of elements from the given
dataset with optimum time complexity. 

Many problems ask for the top, smallest, most/least frequent `k` elements in an
unsorted list. Normally you would sort the list, taking `O(nlog(n))` time, then
find the `k` elements taking `O(k)` time. However the following pattern can
help us solve the problem in `O(nlog(k))` time without sorting the list first.

This pattern uses MaxHeaps or MinHeaps to do so.

For example: Find the top `k` largest(or smallest) elements of a list. 

1. Insert the first k elements from the given set of elements to the
   max-heap(or min-heap if smallest). 
2. Iterate through the rest of the elements. 
    a. for min-heap, if you find the larger element, remove the top (smallest
    number) of the min-heap and insert the new larger element.
    b. for max-heap, if you find the smaller element, remove the top (largest
    number) of the max-heap and insert the new smaller elemement. 

Iterating gthe complete list takes `O(n)` time, and the heap takes `O(log(k))`
time for insertion however we get `O(1)` acces to the k elements using the
heap.

In picture form: 

![Top3Elements1](Top3Elements1.png "Top3Elements after inserting first 3 items")
![Top3Elements2](Top3Elements2.png "Top3Elements after starting iteration on 4th item")
![Top3Elements3](Top3Elements3.png "Top3Elements after iteration on 5th item")
![Top3Elements4](Top3Elements4.png "Top3Elements after iteration on 6th item")
![Top3Elements5](Top3Elements5.png "Top3Elements after iteration on 7th item")

## How to Know if Problem is of this type

* We need to find the largest, smallest, most frequent, or least frequent
  subset of elements in a sorted list. 
* This may be the requirement of the final solution or an intermediate step
* The input data structure supports random access. 
* The input data is not sorted according to the criteria relevant to solving
  the problem. 
* `k != 1`

## Real World Examples

* *Uber*: Select at least the `n` nearest drivers within the user's vicinity,
  avoiding the drivers that are too far away. 
* *Stocks*: Given the set of IDs of brokers, determine the top K broker's
  performance with the frequently repeated IDs in the given data set.

## Non-real world examples
* Sort the characters of a string by frequency.
* Implement a stack in which the pop operation removes the most frequent
  element.

## Example Problems

### K'th Largest Element in a Stream
Given an infinite stream of integers, `nums`, design a class to find the k'th
largest element in a stream. 

The class should have the following functions, inputs, and return values:

* `Init()`: Takes an array of integers and an integer `k` and initializes the
  class object. 
* `Add(value)`: Takes one integer value, appends it to the stream and calls the
  `ReturnKthLargest()`: Returns an integer value that represents the `kth`
  largest element in the stream. 

#### Constraints
* 1 <= k <= 10^3
* 0 <= nums.length <= 10^3

#### Solution
For `Init()`:
1. Create a MinHeap and push the first k items froms nums onto it. 
2. Iterate throught he remaining items in the list. If the number on the top of
   the heap is < the number you are looking at. Add that number to the list and
   then remove the item on the top of the heap. If it is not, do nothing.
For `Add(value):
1. Add Value to Minheap, remove the now top item from the Heap. Call
   `returnKthLargest ();
For `returnKethLargest()`: 
1. call `peek()` on the minheap and return result. 


```java
class KthLargest {
  PriorityQueue<Integer> minHeapOfTopK;
  // constructor to initialize minHeapOfTopK and add values in it
  public KthLargest(int k, int[] nums) {
    minHeapOfTopK = new PriorityQueue<Integer>();

    //Add first k items to heap.
    for (int i = 0; i < k; i++) {
      minHeapOfTopK.add(nums[i]);
    }

    //Add remaining items.
    for (int j = k; j < nums.length; j++) {
      if (nums[j] > minHeapOfTopK.peek()) {
        minHeapOfTopK.add(nums[j]);
        //Remove whatever is the lowest item now as we want k items in this
        //heap
        minHeapOfTopK.poll();
      }
    }
  }
  // adds element in the minHeapOfTopKHeap
  public int add(int val) {
    if (minHeapOfTopK.peek() < val) {
      minHeapOfTopK.add(val);
        //Remove whatever is the lowest item now as we want k items in this
        //heap
      minHeapOfTopK.poll();
    }
    return minHeapOfTopK.peek();
  }
  // returns kth largest element from minHeapOfTopKHeap
  public int returnKthLargest() {
    
    return minHeapOfTopK.peek();
  }
}
```

Solution above should include null checks/validation on the constructor as
needed. Left those out as I am practicin implementing the algorithm not writing
something that is actual prod code. 

