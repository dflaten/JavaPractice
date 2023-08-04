# Triple step

## Description
Given a staircase of n steps, write a program that will provide the number of
ways a child could get up the steps given they could go 1, 2, or 3 steps at a
time. 

## Solution
If you look at a few examples you will see that the number of ways you can get
up a staircase of n steps is the number of ways you can get up steps of size
(n-1) + (n-2) + (n-3). 

So you just need to calculate (recursively) how many steps it takes to get to n
by calculating how many steps it takes to get to each step before n and add one
number to those steps (to make the last hop). 

```java
    public int climbStairs(int n) {
        return climbStairsRec(n, new int[n + 1]);
    }
    private int climbStairsRec(int n, int[] waysUpBySteps) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }
        if (waysUpBySteps[n] == 0) {
            // Determine how many steps to get to the three previous steps
            // Since the base case covers how to get to the final step from
            // there.
            waysUpBySteps[n] = climbStairsRec(n-1, waysUpBySteps) +
                               climbStairsRec(n-2, waysUpBySteps) +
                               climbStairsRec(n-3, waysUpBySteps) ;

        }
        return waysUpBySteps[n];
    
```

## Picture
![Triple Step](TripleStep.png "Triple Step")
