import java.util.HashMap;

/*
 * Problem: Check Permutations: Given two Strings, write a method to decide if one is a permutation of the
 * other.
 *
 * Clarification: A permutation is an ordering of elements. So one string needs to be repeated in the other.
 */
public class StringPermutationDetector {

    // Book Solution

    // My Implementation: 1) Need to determine if one String is bigger than the other. 
    //                    2) Start with smallest string or first if they are the same length.
    //                    3) Starting at the start of the Largest string find the first occurence of the first char in the Smaller String. 
    //                    4) When you find it start comparing the following strings in the Largest Strings with the remaining strings in the 
    //                       smaller String. If you find the last character in the smallest string in the larger string return true.
    //                    5) Otherwise continue to the end of the Larger string and return false.
    //
    // Assumptions: (Would ask as a question in interview). If either string is null will return false. If both strings empty return false. 
    // 
    // Mistakes:
    //
    static public boolean stringPermutationDetector(String s1, String s2) {
	return false;
    }

    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("Using Solution This string, string, is a permutation of this string, corgistring, : " + stringPermutationDetector("string", "corgistring"));


    }
}
