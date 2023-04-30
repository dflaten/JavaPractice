import java.lang.Math;
import java.util.Arrays;

/*
 * Problem: Triple Step: Given a staircase of n steps, write a program that
 *                       will provide the number of possible ways a child could
 *                       get up the steps given they could go 1,2, or 3 steps
 *                       at a time.
 *
 * Example: Input: 3 
 *          Output: [1,1,1],[1,2],[2,1],[3] - 4 
 *
 *          Input: 4
 *          Output: [1,1,1,1], [1,1,2], [1,3], [2,1,1], [2,2], [3, 1] - 6
 *         
 *          
 */
public class TripleStep {

   /* My Implementation:
    *                    1) What is the last hop the child needs to make? 
    *                       A: When the number of hops they've made so far is 
    *                          n - sumOfHopsSoFar = 1, 2, or 3
    *                    2) If we knew the number of paths to each step before
    *                       the number 100, could we figure out the number of
    *                       steps to 100?
    *                    3)  
    *                       
    * Assumptions: Assuming that different combinations of the same numbers
    *              arranged differently are unique. So [2,1] and [1,2] make 2
    *              ways to go up the steps. 
    *              If 0 enterred it will always return 1. 
    *              Negative values always return 0.
    *              `n` must be a valid Integer.
    *              
    * Mistakes: I had a very hard time noticing that if you added the number
    *           of ways up for n-1, n-2, n-3 you could get the number of 
    *           steps to n.
    *
    * Big O: O(n^3) because we are making 3 recursive calls.
    */  
    static public int determineNumberOfWaysUpSteps(int numberOfSteps) {
        return determineStepUp(numberOfSteps, new int[numberOfSteps + 1]);

    }

    static public int determineStepUp(int n, int[] waysUpBySteps) {
        //Base Case
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        } else {
            if (waysUpBySteps[n] == 0) {
                waysUpBySteps[n] = determineStepUp(n-1, waysUpBySteps) + 
                                   determineStepUp(n-2, waysUpBySteps) + 
                                   determineStepUp(n-3, waysUpBySteps);
            }
        }
        return waysUpBySteps[n];
    }

    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("The number of ways up 3 steps is: " + 
            determineNumberOfWaysUpSteps(3));
	System.out.println("The number of ways up 4 steps is: " + 
            determineNumberOfWaysUpSteps(4));
	System.out.println("The number of ways up 5 steps is: " + 
            determineNumberOfWaysUpSteps(5));
	System.out.println("The number of ways up 6 steps is: " + 
            determineNumberOfWaysUpSteps(6));
	System.out.println("The number of ways up 15 steps is: " + 
            determineNumberOfWaysUpSteps(15));
    }
}
