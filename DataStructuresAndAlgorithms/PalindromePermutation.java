import java.util.HashMap;
import java.util.Arrays;

/*
 * Problem: Palindrome Permutation : Given a string, write a function to check if it is a permutation of a plaindrome.
 *                                  A palindrome is a word or phrase that is the same forwards or backwards. A permutation
 *                                  is a rearrangement of letters. The plaindrome does not need to be limited to just
 *                                  dictionary words. You can ignore casing and non-letter characters.
 *
 */
public class PalindromePermutation {

    // My Implementation: 1) 
    //                    2) 
    //
    // Assumptions:  
    // 
    // Mistakes:  
    // Big O: 
    static public boolean permutationPalindrome(String string) {
	
	//Edge Cases
	if (string == "") {
	    return false;
	}

	return true;
    }


    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("Using the permutationPalindrome solution this string, //, produced : " + permutationPalindrome(""));


    }
}
