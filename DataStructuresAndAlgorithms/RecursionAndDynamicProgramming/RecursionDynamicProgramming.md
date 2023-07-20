# Recursion

When talking about recursion in Computer Science you are usually referring to
the process of breaking a problem down to its base case, solving it and then
using the results to arrive at your original solution. 

The factorial function is a classic example: 
```java
public int factorial(int n) {
   if (n > 0) {
       return n * factorial(n-1);
   } else {
       return 1;
   }
}
```

# Dynamic Programming
Dynamic Programming is breaking a problem down into simpler sub-problems in a
recursive way. 

To solve a problem with Dynamic Program you must have: 
1. A optimal substrucuture - That is the sub-problems you broke the problem up
   into can be combined to produce the solution. Usually these problems are
   defined via recursion. 
2. Overlapping Subproblems - These sub problems should be small and should be
   the same problems or a Set of the same problems solved over and over again
   so you can take advantage of this and cache the solution to the problem. 

There are two different approaches in dynamic programming you can use to solve
a problem, the Top Down Approach with Memoization, and the Bottom-up approach
with tabulation.

## Top Down Approach with memoization
Here we store all the results of the solved subproblems in an array or hash
map. That way whenever we encounter an overlapping subproblem during recursion
we can just refer to it instead of re-doing it.


### Fibonacci Example

Will use the Fibonacci Example to show both. Will start with the basic
recursive implementation which is very very slow.

Code for Recursive Implementation:

```java
public int fibRecursive(int in) {
    if (n <= 1) {
        return n;
    } else {
        return fibRecursive(n - 1) + fib(n - 2);
    }

}
```

The runtime of the `fibRecursive` method is not as straightforward as it looks.
It is O(2^n) due to the fact that you are making about that many calculations
with all the recursive calls you are making. 

When you write this problem out in a graph of recursive calls for a number like
10 you will see that there are many common problems being solved:

![Fibonacci](Fibonacci.png "Fibonacci")

If you cache these solutions instead of calculating them you can drastically
reduce the runtime of the function. 

#### Top Down Dynamic Programming Solution

```java
public int fibDynamidProgramming(int n) {
    return fibDynamicProgramming(n, new int[n];
}

public int fibDynamicProgramming(int n, int[] array) {
    if (n <= 1) {
        return n;
    } else {
        if (array[n] == 0) {
            array[n] = fibDynamicProgramming(n - 1, array) +
            fibDynamicProgramming(n - 2, array);
        } 
        return array[n];
    }
}
```

## Bottom-up Approach with tabulation
This approach allows us to use recursion in a different way. We use an
n-dimensional array to store the results of the sub problems. On some problems
this may require computing using two-dimensional arrays or one dimentional
arrays with a twist. It can be tricky and isn't always a good idea for dynamic
programming problems.

### Bottom Up Dynamic Programming Solution

Start calculating the sums from the bottom and move up to n.

```java
public int fibDynamidProgrammingBottomUp(int n) {
    if (n <= 1) {
        return n;
    } else {

    int [] array = new array[n];
    array[0] = 0;
    array[1] = 1;
    for (int i = 2; i < n; i++) {
        array[i] = array[i - 1] + array[i - 2];
    }
    return array[n - 1] + array[n - 2];
}
```

Depending on the problem it may be easier to think of this in one direction or
the other.
