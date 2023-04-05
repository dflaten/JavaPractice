import java.util.HashMap;
import java.util.Arrays;

/*
 * Problem: Palindrome Permutation : Given a string, write a function to check if it is a permutation of a palindrome.
 *                                  A palindrome is a word or phrase that is the same forwards or backwards. A permutation
 *                                  is a rearrangement of letters. The plaindrome does not need to be limited to just
 *                                  dictionary words. You can ignore casing and non-letter characters.
 *
 *                    Example: Input: "Tact Coa" 
 *                             Output: Returns true because: ("taco cat", "atco, cta", etc.)
 */
public class PalindromePermutation {

    // 
    // Thoughts:    A permutation of a palindrome would have all the same characters exatly twice except for when the 
    //              palindrome is odd then it will have one unique character.
    //
    // My Implementation: 1) Iterate through the String and make all characters lower case and remove non-letter characters. 
    //                    2) Iterate through the String and make sure it has an even number of each character and one of 1 if it is
    //                       an odd length.
    //                    3) If it does return true, if not return false;
    //
    // Assumptions: I am understanding that given a string we want to know if we can re-arrange the characters in it to
    //              create a palindrome. Also assuming we just want a true/false back not the palindrome that we found.
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
