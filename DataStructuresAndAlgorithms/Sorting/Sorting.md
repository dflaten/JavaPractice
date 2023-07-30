# Sorting
Sorting is a classic computer science problem which can be approached in many
different ways. Some of the most popular will be outlined here but if you check
[Wikipedia](https://en.wikipedia.org/wiki/Sorting_algorithm) for example you
can see there are more than 50 different ways to sort things in computer
science. A sorting algorithm is considered **stable** if the relative order of
equal sort items is not preserved during the sort (at least in most
implementations). 

| Sorting Algorithm | Average Runtime| Worst Runtime| Memory | Stable|
| ------------- | ------------- | ------------- | ------------- | ------------- |
| [Quicksort](#Quicksort)| `O(n log n)`| `O(n^2)`|`O(log n)` | No |
| [Selection Sort](#Selection-Sort)| `O(n^2)`| `O(n^2)`| `O(1)`| No |
| [Heap Sort](#Heap-Sort)| `O(n log n)`| `O(n log n)`|`O(1)`| No |
| [Merge Sort](#Merge-Sort)| `O(n log n)`| `O(n log n)`|`O(n)`| Yes |
| [Insertion Sort](#Insertion-Sort)| `O(n^2)`| `O(n^2)`|`O(1)`| Yes |
| [Bubble Sort](#Bubble-Sort)| `O(n^2)`| `O(n^2)`|`O(1)`| Yes |

## Comparison Sorts
Comparison sorts cannot perform better than `O(n log n)` on average.

### Quicksort
Quicksort is an **in-place sorting algorithm** and a divide-and-conquer
algorithm. It chooses a **pivot**, often randomly and checks whether values 
in the array are higher or lower than that pivot. The values lower than the 
pivot should be on the left side and the values higher should be on the right.
The **pivot** is used to partition the list.

Most implementations of quicksort are not stable.

#### Performance
* Worst-case: `O(n^2`)`
* Best-case: `O(n log n)`
* Average: `O(n log n)`

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
### Merge Sort
Merge sort (also known as mergesort) is an efficient general purpose sorting
algorithm that competes with quick sort as the "best" sorting algorithm for
most purposes. Most of the time implementations of merge sort produce a stable
sort. 

Merge sort is a divide-and-conqueror algorithm that conceptually works like so:

1. Divide the unsorted list into n sublists, each containing one element(a list
   of one element is considered sorted). 

2. Repeatedly merge sublists to produce new sorted sublists until there is one
   sublist remaining. This will be the sorted list. 

#### Implementation 
```java
/** 
 * Merge Sort: 
 * 1. Split the list, sortMe, up into n lists where n is the size of sortMe
 * 2. Merge the lists together in the correct order
 * 3. Return the now sorted Array
*/
public static void mergeSort(int[] sortMe, int size) {
   if (size < 2) {
    return;
   }
   int mid = size / 2;
   int [] leftHalf = new int[mid];
   int [] rightHalf = new int[size - mid];
   // Make a copy for the left half
   for (int i = 0; i < mid; i++) {
       leftHalf[i] = sortMe[i];
   }
   // Make a copy for the right half
   for (int j = mid; j < size; j++) {
       rightHalf[j - mid] = sortMe[j];
   }
   // Recursively call mergeSort on both halves
   mergeSort(leftHalf, mid);
   mergeSort(rightHalf, size - mid);

   // Finally merge all the results.
   merge(sortMe, leftHalf, rightHalf, mid, size - mid);

}

public static void merge(int[] sortMe, int[] leftHalf, int[] rightHalf, int leftSize, int rightSize) {
    int leftIterator = 0;
    int rightIterator = 0;
    int sortMeIterator = 0;

    // Here we are merging by placing the smallest items first.
    while (leftIterator < leftSize && rightIterator < rightSize) {
        if (leftHalf[leftIterator] <= rightHalf[rightIterator]) {
            sortMe[sortMeIterator] = leftHalf[leftIterator];
            sortMeIterator++;
            leftIterator++;
        } else {
            sortMe[sortMeIterator] = rightHalf[rightIterator];
            sortMeIterator++;
            rightIterator++;
        }
    }

    // Place the remaining items in the array for both sides. Though 
    // in reality only one of these loops will every iterate for each
    // run of merge since the other item would have triggered the previous
    // loop to end.
    while (leftIterator < leftSize) {
        sortMe[sortMeIterator] = leftHalf[leftIterator];
        sortMeIterator++;
        leftIterator++;
    }

    while (rightIterator < rightSize) {
        sortMe[sortMeIterator] = rightHalf[rightIterator];
        sortMeIterator++;
        rightIterator++;
    }

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
* Worst-case: `O(n^2)` comparisons, `O(n)` swaps
* Best-case: `O(n^2)` comparisons, `O(1)` swap
* Average: `O(n^2)` comparisons, `O(n)` swaps
* Worse-case space complexity: `O(1)` auxiliary

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
* Worst-case: `O(n log(n))`
* Best-case: `O(n log(n))` with distinct keys or `O(n)` with equal keys.
* Average performance: `O(n log(n))`
* Worst-case space complexity: `O(n)` total `O(1)` auxiliary

### Insertion Sort
This sorting algorithm builds the final sorted list one item at a time by
comparisons. It is much less efficient on large lists than quicksort, heapsort
or mergesort. It is however very simple to implement.

Insertion sort iterates through the list consuming one input element each
repetition and grows a sorted output list. At each iteration insertion sort
removes one element fromt he input data, finds the location it belongs and
inserts it into the sorted list. IT repeats until no elements remain. 

#### Implementation
```java
**
 * Insertion Sort:
 * Iterate through the list one at at a time. 
 * Compare the elements, if the current element you are looking at is 
 * less than the largest item in the sorted list, move the items in the sorted 
 * list forward and compare it to the next until you have gotten to the start
 * of the sorted list or the next item in the list is less than it. 
 * Repeat these steps until you get to the end of the list.
 * 
 **/
public static int[] insertionSort(int[] array) {
    // Iterate through the array to build the sorted list in place
    for (int unSortedStart = 1; unSortedStart < array.length; unSortedStart++){
        // Get our current item
        int currentItem = array[unSortedStart];
        // Create a aroted Iterator which we will use to traverse the sorted
        // part of the list. This will be the end of the sorted list
        int sortedIterator = unSortedStart - 1;
        // While the iterator for the sorted part of the list is >= 0 and the
        // item we are looking at in the list is less than the item we are 
        // at in the sorted array.
        while (sortedIterator >= 0 &&  currentItem < array[sortedIterator]) {
            // Move the sorted item we are lookint at forward one spot in the array
            array[sortedIterator + 1] = array[sortedIterator];
            sortedIterator--;
        }
        // Move the current item to the last potition we were at in the sorted
        // Array. this will either be the start of the entire list or the place
        // in the sorted list where the item on the left is less than the current
        // item.
        array[sortedIterator + 1] = currentItem;
    }
    return array;
}
```

#### Performance
* Worse Case: `O(n^2)`
* Best Case: `O(n)` comparison `O(1)` swaps
* Average: `O(n^2)`
* Worse Case Space: `O(n)` total, `O(1)` auxiliary

### Bubble Sort
Bubble sort is perhaps one of the easiest sorts to implement and understand but
also one of the most inefficient types of sorting algorithms. Primarily it is
used as an educational tool to get started with sorting. However if you are
able to use parallel processing bubble sort sorts in `O(n)`time, making it
considerably faster than insertion or selection sort which do not parrallelize
as effectively. 

Bubble sort works by iterating through the input list element by element
swapping elements that are larger/smaller than each other. Larger items will
"bubble up" to the end of the list as it is sorted in this way hence the name. 

#### Implementation
```java
/** 
 * Bubble Sort
 * Iterate through the list, As you iterate compare the current item with the 
 * next item. If it is smaller the other item keep it where it is at. If it is
 * larger than the other item swap the position of the two items. Do this until
 * you reach the end of the list. 
 */
public static int [] bubbleSort(int [] array) {
    for (int i = 0; i < array.length; i ++) {
        for (int j = i + 1; j < array.length; j++) {
            if (array[i] > array[j]) {
                swap(i, j, array);
            }
        }
    }
    return array;
}

private static void swap(int previous, int next, int [] array) {
    int temp = array[previous];
    array[previous] = array[next];
    array[next] = temp;
}
   
}
```

#### Performance
* Worst Case: `O(n^2)` comparisons `O(n^2)` swaps
* Best Case: `O(n)` comparisons and `O(1)` swaps
* Average: `O(n^2)` comparisons `O(n^2)` swaps
* Worst Case Space: `O(n)` total `O(1)` auxilliary. 
