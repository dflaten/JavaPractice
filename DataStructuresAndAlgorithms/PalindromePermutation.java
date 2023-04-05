import java.util.HashMap;
import java.util.Locale;
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
    //              create a palindrome. 
    //              Also assuming we just want a true/false back not the palindrome that we found.
    //              Assuming the letters are all English.
    // 
    // Mistakes: I used the `toLowerCase(), and replaceAll() String methods here which may not be as efficient as adding
    //           a check during the counting of characters to only count values within the expected range (97 - 122) 
    //           assuming ASCII. I put O(n) here but due to using String methods it is possible it is greater.
    // Big O: O(n)
    static boolean isOdd(int i) {
	if(i % 2 == 0) {
	    return false;
	}
	return true;
    }
    static public boolean permutationPalindrome(String string) {
	//Edge Cases
	String ONLY_LOWER_CASE_LETTERS = "[^a-z]";
	if (string == "") {
	    return false;
	}

	String cleanedString = string.toLowerCase(Locale.ENGLISH).replaceAll(ONLY_LOWER_CASE_LETTERS, "");

	int [] letterCounter = new int[173];

	int oddNumberOfChars = 0;
	for (int i = 0; i < cleanedString.length(); i++) {
	    letterCounter[cleanedString.charAt(i)] = letterCounter[cleanedString.charAt(i)] + 1;
	}

   	for (int j = 97; j < 123; j++) {
	    if (isOdd(letterCounter[j])) {
		oddNumberOfChars++;
	    }
	}

	if(isOdd(cleanedString.length())) {
	    if (oddNumberOfChars == 1) {
		return true;
	    }
	    return false;
	}

	if (oddNumberOfChars == 0) {
	    return true;
	}
	return false;
    }


    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("Using the permutationPalindrome solution this string, //, produced : " + permutationPalindrome(""));
	System.out.println("Using the permutationPalindrome solution this string, /Tact Coa/, produced : " + permutationPalindrome("Tact Coa"));
	System.out.println("Using the permutationPalindrome solution this string, /Ract Coa/, produced : " + permutationPalindrome("Ract Coa"));
	System.out.println("Using the permutationPalindrome solution this string, /aa bb/, produced : " + permutationPalindrome("aa bb"));
    }
}
