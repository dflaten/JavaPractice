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


