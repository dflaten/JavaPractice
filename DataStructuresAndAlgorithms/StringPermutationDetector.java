import java.util.HashMap;
import java.util.Arrays;

/*
 * Problem: Check Permutations: Given two Strings, write a method to decide if one is a permutation of the
 * other.
 *
 * Clarification: My Understanding: A permutation is an ordering of elements. So one string needs to be repeated in the other.
 * Clarification: Book Understanding: One String contains all the same characters as another String but in a different order.
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
    // Mistakes: I didn't think about asking for examples.
    //           I tried to iterate through both strings in one loop using if statements and breaks to determine if the substring existed. 
    //           This didn't work because the Strings need to move together if you find one that equals. To fix I added a check to see if
    //           we are on the last character of the subsring when comparing and if we were and they were equal then we found the substring!
    // Big O: O(a * b) where a and b are the length of String 1, and String 2.
    static public boolean stringPermutationDetector(String s1, String s2) {
	//Edge Cases
	if (s1 == null || s2 == null) {
	    return false;
	}
	if (s1 == "" || s2 == "") {
	    return false;
	}

	String largerString;
	String smallerString;

	if (s1.length() > s2.length()) {
	    largerString = s1;
	    smallerString = s2;
	}

	else {
	    largerString = s2;
	    smallerString = s1;
	}
	boolean foundSubStringPermutation = false;	
	for (char largeChar : largerString.toCharArray()) {
	    //Check each character.
	    for (int i = 0; i < smallerString.length(); i++){
		if (largeChar == smallerString.charAt(i)) {
		    //if the character matches and it is the last character we found the substring! 
		    if (i == smallerString.length() - 1) {
			foundSubStringPermutation = true;
		    }
		}
	    }
	}
	return foundSubStringPermutation;

    }
    // Solution after confirming the assumptions by looking at the answer in the book. (I misunderstood permutation in solution 1). h
    // My Implementation: 1) Make Sure strings are both the same length 
    //                    2) Sort Each String. 
    //                    3) See if they are Equal. 
    //
    // Assumptions: (Would ask as a question in interview). If either string is null will return false. If both strings empty return false. 
    // 
    // Mistakes: At first I thought two HashMaps would provide a good solution but realized it wouldn't because each string could have
    //           repeat characters.
    //           Didn't add a sort method outside of the function to help re-use code for sorting.
    // Big O: O(n + j + n log(n) + j log(j) + n * j) or O(nj) simplified.
    static public boolean stringPermutationDetectorCorrectAssumptions(String s1, String s2) {
	//Edge Cases
	if (s1 == null || s2 == null) {
	    return false;
	}
	if (s1 == "" || s2 == "") {
	    return false;
	}
	if (s1.length() != s2.length()){
	    return false;
	}
	char s1Array [] = s1.toCharArray();
	char s2Array [] = s2.toCharArray();

	Arrays.sort(s1Array);
	Arrays.sort(s2Array);
	
	String sortedS1 = String.valueOf(s1Array);
	String sortedS2 = String.valueOf(s2Array);

	return sortedS1.equals(sortedS2);
	
    }


    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("Using the stringPermutationDetector solution this string, string, is a permutation of this string, corgistring, : " + stringPermutationDetector("string", "corgistring"));
	System.out.println("Using the stringPermutationDetector solution this string, dog, is a permutation of this string, corgidoggood, : " + stringPermutationDetector("corgidoggood", "dog"));
	System.out.println("Using the stringPermutationDetector solution this string, cat, is not a permutation of this string, corgidoggood, : " + stringPermutationDetector("corgidoggood", "cat"));
	System.out.println("Using the stringPermutationDetectorCorrectAssumptions solution this string, cat, is not a permutation of this string, bat, : " + stringPermutationDetectorCorrectAssumptions("bat", "cat"));
	System.out.println("Using the stringPermutationDetectorCorrectAssumptions solution this string, cat, is a permutation of this string, tac, : " + stringPermutationDetectorCorrectAssumptions("tac", "cat"));
	System.out.println("Using the stringPermutationDetectorCorrectAssumptions solution this string, catcat, is a permutation of this string, tactac, : " + stringPermutationDetectorCorrectAssumptions("tactac", "catcat"));


    }
}
