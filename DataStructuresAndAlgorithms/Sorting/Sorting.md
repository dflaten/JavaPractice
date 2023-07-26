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




