# Overview
This document outlines a methodology of identifying what type of technique you
should apply to a given Programming Problem. This for now focuses on the
current list of most common Leetcode problems and the flowchart on [this
site](https://sebinsua.com/algorithmic-bathwater) was used as the base. 

## Technique Decision Tree 
First we must identify what type of general problem are we trying to solve. 

1. [Optimization Problem?](#optimization-problems) Are we trying to find the 
   best possible solution from a list of possible solutions?
2. [Create/Recover a partial ordering of
   elements?](#create/recover-a-partial-ordering-of-elements)
3. [Does the problem involve Interconnected Points?](#interconnected-points)
4. [Efficient Lookups of data?](#efficient-lookups-of-data)
5. [Is the problem related to a linear or sequential data structure?](#linear-or-sequential-data-structure)

### Optimization Problems

1. Do the local optimmum decisions lead to a global optimum? If so use a [Greedy
   Algorithm](GreedyTechniques/Greedy.md). 
2. Do solutions to smaller problems help us solve the bigger one (optimal
   subsctructure of problem)? Do some of these smaller problems have overlap
   with each other or do we find ourselves solving the same problem over and
   over? If so use [Dynamic
   Programming](RecursionAndDynamicProgramming/RecursionDynamicProgramming.md)
   to solve the problem.

### Create/Recover A Partial Ordering of Elements
If you are able to infer enough information about how elements are connected
such as their order or if they overlap or touch you can use this information
to build a Directed Graph. Once you have a directed graph you can use
[Topological Sort](TopologicalSort/TopologicalSort.md) to solve the problem.
This is a good technique to recover orderings of a sequence.

Examples include determining possible class orderings while making sure
pre-requisite requirements are met. 

### Interconnected Points
Do you have data points that are interconnected in some way, needing to
generate and test paths or sequences, or wishing to traverse elements in a
matrix? This could be data in an array for example.

1. **Do you need to find a combination or permutation of elements that match a
   constraint?** If you have a particular target or constraint that needs to be
   met it means you will need to find and test each path. This suggests you use
   a DFS using a [Dynamic Programmimng
   Approach](RecursionAndDynamicProgramming/RecursionDynamicProgramming.md) 
   approach, specifically top-down memoisation. However this will be very 
   inefficient for most meaningful datasets  in terms of memory and time. 
   Pruning will be needed so you will need to use 
   [Backtracking Approach](Backtracking/Backtracking.md) to invalidate any 
   paths later shown to be impossible and rollback modifications to shared 
   state made during the sarch. 

   * **Now should you implement DFS using recursion or iteration?** Recursion
      has limits as far as the depth is concerned depening on the programming
      language but recursion provides a natual mechanism for backtracking since
      you can just `return` to undo the last step. However iteration will
      require you to manually implemented backtracking by either copying the
      data structures into each stack item or by using shared state and
      carefully rolling back changes to this. Generally recursion is more
      intuitive mathematically but iteration provides better debuging and
      tracing of state.

2. **Do you need to fill or quantify contiguous regions in a matrix?**
   You can do using a [Flood-Fill](Backtracking/FloodFill.md) algorithm. You
   can do this using Breadth-First Search(BFS) or Depth-First Search (DFS). You
   can avoid accidentally revisting cells by placing them into a *Set* or
   *HasmMap* as you visity them, or to save memory mutate the matrix in place
   by marking visited cells with a different 'color' (reverting them once you
   have finished if you wish to do so).

3. **Is searching for the shortest path between some nodes or doing a
   'level-order' traversal your main concern?**
   Use Breadth-First Search(BFS). BFS does use more memory since it needs to
   keep all of the nodes at the current level in memory, DFS only needs to
   store the nodes along a single path in the tree/graph so if memory is a
   concern DFS could be better. BFS is also not suitable for weighted graphs
   which need an algorithm like Dijkstra's or A*.
4. **Are you being asked to determine whether nodes are connected, to count the
   number of components in a graph, or to detect cycles?** 
    * *In an undirected graph?* - Use [Union-Find](UnionFind/UnionFind.md)
      Reminder: Union-Find is not suitable for directed graphs because it
      inherently deals with undirected equivalence relations; it merges sets
      and loses the sense of direction between nodes. 
    * *In a directed graph?* - Use DFS

### Efficient Lookups of Data
1. **Do we need to quickly store or lookup strings or provide auto-complete
   functionality?**
   Use a [Trie](Trie/Trie.md) to store the strings in a way that allows for
   fast `O(k)` retrieval and updates of strings that share a common prefix.

2. **Are we being asked to query the smallest/largest/median values?**
    * Use a [Heap](TwoHeaps/TwoHeaps.md) to store the data and retrieve the
      largest/smallest in `O(1)` time.
    * Use a combination of both min and max to compute the median.
    * There are time/space costs to maintaining heaps (creating costs `O(n)`
      but updates are `O(log n)` due to `heapify` cost. The amortized cost of
      a heap is important to keep in mind as an algorithm like
      [Quicksort](Sorting/Sorting.md) may be more efficient for one-off or
      occasional operations.
    * Heaps do not maintain order of their elements, and do not provide `O(1)`
      access to the k'th smallest/largest element. If you need this you should
      use a [Modified Binary
      Search](ModifiedBinarySearch/ModifiedBinarySearch.md) to solve your
      problem.

### Linear or Sequential Data Sructure

Does the problem have to do with an array, linked list, string, or search
space?

1. **Are we being asked to find pairs, triplets or sub-arrays that match a
   constraint?**
   * To solve this optimally, `O(n)` we need to be able to compare elements in
     the sequence without using nested loops. We have two options:
     1. Ensure the sequence is sorted and then apply the [Two
        Pointers](FastAndSlowPointers/FastAndSlowPointers.md).  
        technique. This is particularly useful when a deterministic order
        matters or when looking for unique paris/triplets.

     2. Use a [HashMap](HashMaps/HashMaps.md) - This allows us to store seen 
        elements and then look up the completment of the current element in 
        the hash table. This is a better option when the list is unsorted and 
        sorting it would break problem constraints or when we need to remember 
        past elements for comparison but don't care about their order.

2. **Are we being asked to find the longest/shortest substring or subsequence
   that matches a constraint or to compute aggregate statistics for a
   particular length subsequence?**
   * To solve optimally, we want to be able to re-use computation from previous
     windows so that we can update our answer for each window in `O(1)`
     (constant) time and the whole sequence can be completed in `O(n)` 
     (linear) time. To do this we can use the [Sliding
     Window](SlidingWindow/SlidingWindow.md) technique.

3. **Do we need to find a target value or min/max value in the sequence?**
    * If the sequence is unsorted, there is no way to do better than a linear
      scan of the sequence `O(n)`. 
    * If the sequence is sorted we can use [Binary
      Search](ModifiedBinarySearch/ModifiedBinarySearch.md). 

4. **Do we need to detect cycles in a linked list or find the middle element?**
   * Use [Fast and Slow Pointer](FastAndSlowPointers/FastAndSlowPointers.md) to
     solve the problem.

5. **Do we need to find duplicates in a sequence?**
   * If sorted then duplicates are next to each other and we can find them by
     comparing adjacent items in the list.
   * If the sequence is unsorted we can use a [HashMap](HashMaps/HashMaps.md)
     or **Set**.
6. **Does the problem require frequent and ordered access to recently processed
   elements?**
   * If you need access to elements in a dynamic order, you canot just use a
     `for-loop` to iterate through some elements and likely will need to use a
     [Stack or Queue](StacksAndQueues/StacksAndQueues.md).
   * If we need access to the most recently processed elements, use a
     **Stack**. 
   * If we need access to the least recently processed element (oldest) we can 
   use a **Queue**.

7. Are we being asked to merge sorted Lists?
   * Use a [Min-Heap](TwoHeaps/TwoHeaps.md). 

