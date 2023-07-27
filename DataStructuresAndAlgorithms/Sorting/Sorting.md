# Sorting
Sorting is a classic computer science problem which can be approached in many
different ways. Some of the most popular will be outlined here but if you check
[Wikipedia](https://en.wikipedia.org/wiki/Sorting_algorithm) for example you
can see there are more than 100 different ways to sort things in computer
science. 

## Comparison Sorts
Comparison sorts cannot perform better than `O(n log n)` on average.

### Quicksort
Quicksort is an **in-place sorting algorithm** and a divide-and-conquer
algorithm. It chooses a **pivot**, often randomly and checks whether values 
in the array are higher or lower than that pivot. The values lower than the 
pivot should be on the left side and the values higher should be on the right.
The **pivot** is used to partition the list.

Most implementations of quicksort are not stable(relative order of equal sort
items is not preserved). 

#### Performance
Worst-case: `O(n^2`)`
Best-case: `O(n log n)`
Average: `O(n log n)`

#### Implementation

```java
/**
 * This is an implementation of quick sort which updates a list of integers in 
 * place using recursion.
 * Solution: 
 * 1. Create a recursive method that takes the array, a left index, and a right 
 * index as paramters. 
 * 2. Inside the recursive method create a partition method which will first
 * use the item at the right pointer as a pivot to move all items smaller than
 * it to the right via swaps. Once it has moved through all items in the list 
 * it should swap positions with the next place a swap would have been. The 
 * pivot is now in its proper place in the list. The index for this item will be
 * used to split the list into two sub lists where the recursive call is made to 
 * continue sorting the lists until the left and right pointer meet. 
 * 3. Our list is now sorted and can be returned.
 */
public static int[] quickSort(int[] array) {
    quickSortRec(array, 0, array.length - 1);
    return array;
}

private static void quickSortRec(int[] array, int left, int right) {
   //Make sure our right pointer doesn't overtake the left.
   if (left < right) {
      //Here is where we choose the partition for our list.
      // It will be used to split the rest of the list up for sorting.
      int paritionIndex = partition(array, left, right);
      //Recursively we will sort both sides of the list using the partition index 
      //to split
      quickSortRec(array, left, paritionIndex - 1);
      quickSortRec(array, paritionIndex + 1, right);
   }
}

private static int partition( int[] array, int left, int right) {
  // Choosing the pivot here, since we don't know the order of the list we can 
  // pick anything in the partion but should be consistent TODO: Verify why.
  int pivot = array[right];
  int leftForPartition = left - 1;
  // For each item in the partition
  // j is our pointer moving right through the partition
  for (int j = left; j < right; j++) {
    // If the item is less than or equal to the pivot, swap the item to the left.
    if (array[j] <= pivot) {
      leftForParition++;
      int swapTemp = array[leftForPartition];
      array[leftForPartition]  = array[j];
      array[j] = swapTemp;
    }
  }
  //This final Swap is to get the pivot to its proper place in the list. 
  int swapTemp = array[leftForPartition + 1];
  array[leftForPartition + 1] = array[right];
  array[right] = swapTemp;

  return leftForPartition + 1;
}
```

### Selection Sort
Selection sort is an **in-place** sorting algorithm. It is simple but generally
less efficient than many other sorting algorithms. However it can be useful
when your auxiliary memory is limited. Auxiliary storage is memory not directly
accessible by the CPU, in modern computers this would be hard drive disks or
solid-state drives. 

Selection sort divides the input list into two parts: a sorted sublist of items
built up from the left to right at the front or left of the list and a sublist
of the remaining items that are made up by the rest of the list. The
left(sorted) sublist starts empty with the right (unsorted) list full of all
the elements. You loop through the unsorted list searching for the largest(or
smallest) item in the list to place it in the sorted part of the list. Then
find the next largest(or smallest) item in the list and place it next in the
list. This continues until you are at the end of the list.

#### Performance
Worst-case: `O(n^2)` comparisons, `O(n)` swaps
Best-case: `O(n^2)` comparisons, `O(1)` swap
Average: `O(n^2)` comparisons, `O(n)` swaps
Worse-case space complexity: `O(1)` auxiliary

#### Implementation
```java
/** 
 * Selection Sort: 
 * 1. Iterate through entire list.
 * 2. For the first iteration find the smallest item and put it at the start of the list.
 * 3. For the remaining iterations, find the next smallest item and place it in the next position of list.
 * 4. Once you get to the end of the list return the now sorted array.
*/
public static int[] selectionSort(int[] array) {
    // Iterate through all the items in the list. We skip the last item in the 
    // list (array.length - 1) because that one will always be in the right place
    // since by the time we get to it all the other items are sorted.
    for (int i = 0; i < array.length - 1; i ++) {
        // Choose the first element in our sublist as the lowest, will update
        // as new lowest is found
        int smallest = i;
        // Iterate through the remaining items in the list to find the next lowest.
        for (int j = i + 1; j < array.length; j++) {
            if(array[j] < array[smallest]) {
                smallest = j;
            }
        }
        int temp = array[i];
        array[i] = array[smallest];
        array[smallest] = temp;
    }
    return array;
}
```

### Heap Sort
This type of sort can be thought of as an improved version of Selection Sort.
Like Selection Sort Heap Sort divides the list into a sorted and unsorted
region, and iteratively shrinks the unsorted region by extracting the largest
or smallest number from it and inserts it into the sorted region. Unlike
selection sort, heapsort does not waste time with a linear-time scan of the
unsorted region but instead maintains a heap of the unsorted region to more
quickly find the largest element in each step.

#### Implementation Approaches

##### Simpler Approach

Implementation of Heap Sort can be fairly simple if you don't mind allocating
extra space for the Heap. This type of Implementation would look like: 

1.) Create a Max Or Min Heap of the items you are sorting. For example Java's
`PriorityQueue` would do this with one line of code (example minheap): 

```java
PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> a[0] - b[0]);
```

Then just add all items in the array to the MinHeap and then replace items in
the array by poping the top item off of the Queue until it is empty. 

This would result in a `O(n log(n)` + `O(n)` performance or `O(n log(n)`.

##### More Efficient Approach
More efficiently you could create a heap structure in your code implementing
those operations without needing to create a new Data Structure. 

TODO: Implement this in Java

```
procedure heapsort(a, count) is
    input: an unordered array a of length count
 
    (Build the heap in array a so that largest value is at the root)
    heapify(a, count)

    (The following loop maintains the invariants that a[0:end] is a heap and 
    every element beyond end is greater than everything before it (so 
    a[end:count] is in sorted order))

    end ← count - 1
    while end > 0 do
        // (a[0] is the root and largest value. The swap moves it in front of 
        // the sorted elements.)
        swap(a[end], a[0])
        (the heap size is reduced by one)
        end ← end - 1
        (the swap ruined the heap property, so restore it)
        siftDown(a, 0, end)

procedure heapify(a, count) is
    //(start is assigned the index in 'a' of the last parent node)
    //(the last element in a 0-based array is at index count-1; find the parent of that element)
    start ← iParent(count-1)
    
    while start ≥ 0 do
        //(sift down the node at index 'start' to the proper place such that all nodes below
         //the start index are in heap order)
        siftDown(a, start, count - 1)
        //(go to the next parent node)
        start ← start - 1
    //(after sifting down the root all nodes/elements are in heap order)

// (Repair the heap whose root element is at index 'start', assuming the 
// heaps rooted at its children are valid)

procedure siftDown(a, start, end) is
    root ← start

    while iLeftChild(root) ≤ end do    (While the root has at least one child)
        child ← iLeftChild(root)   (Left child of root)
        swap ← root                (Keeps track of child to swap with)

        if a[swap] < a[child] then
            swap ← child
        (If there is a right child and that child is greater)
        if child+1 ≤ end and a[swap] < a[child+1] then
            swap ← child + 1
        if swap = root then
            (The root holds the largest element. Since we assume the heaps rooted at the
             children are valid, this means that we are done.)
            return
        else
            Swap(a[root], a[swap])
            root ← swap 
```

#### General Performance
Worst-case: `O(n log(n))`
Best-case: `O(n log(n))` with distinct keys or `O(n)` with equal keys.
Average performance: `O(n log(n))`
Worst-case space complexity: `O(n)` total `O(1)` auxiliary

