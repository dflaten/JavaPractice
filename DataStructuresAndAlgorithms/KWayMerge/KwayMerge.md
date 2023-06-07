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

1.*Merge tweets in twitter feed:* Sometimes we need to implement a module that 
adds a user’s Tweets into an already populated Twitter feed in chronological order.

2. *Used in external sorting procedures:* When an algorithm is processing huge 
amounts of data, it needs to repeatedly fetch it from external storage because 
RAM capacity is fixed. To overcome the speed limitation of external storage, 
k-way merges are used in external sorting. Let’s consider a case where we need 
to perform six merges. A binary merge requires three merge passes while a 
6-way merge only requires one pass. K-way merge reduces the number of accesses 
to external storage, which in turn greatly improves performance when dealing 
with large amounts of data.

## Examples

