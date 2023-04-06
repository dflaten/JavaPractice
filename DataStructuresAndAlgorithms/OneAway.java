import java.lang.Math;
import java.util.Arrays;

/*
 * Problem: One Away: There are three types of operations that can be preformed on strings.
 *                    1) Insert a character.
 *                    2) Remove a character.
 *                    3) Replace a character.
 *                    Write a function that takes two Strings has inputs and determines if 
 *                    they are one or 0 operations from being equal.
 *
 * Example: Input: "Pale", "ale" 
 *          Output: True 
 *         
 *          Input: "
 */
public class OneAway {

    // My Implementation:
    //                    1) Check if they are equal, return true.
    //                    2) If one is greater than the other by more than one, return false.    
    //                    3) Check if one string is one greater than the other, if so try to 
    //                       remove the char which is in the larger string and compare it
    //                       against the smaller string.If so return true, if 
    //                       not return false.
    //                    4) If they are equal, check each char to count how many are
    //                       different As soon as there is more than one return false.
    //                       If you get to the end of the strings and have only one
    //                       different return true. 
    //
    // Assumptions: No Nulls are passed in. These are ASCII encoded strings. Strings
    //              that are already equal will return true.
    //
    // Mistakes: Took quite a bit longer than I would have liked to get the solution.
    static public boolean areOneChangeAwayFromEquality(String string1, String string2) {
	if (string1.equals(string2)) {
	    return true;
	}
	if (Math.abs(string1.length() - string1.length()) > 1){
	    return false;
	}
	String largerString;
	String smallerString;
	System.out.println("Length Difference: " + (string1.length() - string2.length()));
	if (string1.length() - string2.length() == 1) {
	    largerString = string1;
	    smallerString = string2;
	} else if(string1.length() - string2.length() == - 1) {
	    largerString = string2;
	    smallerString = string1;
	} else {
	    //Equals case
	    return checkEqualStringsForOneCharDifference(string1, string2);
	}
	// Check for 1 character difference;
	// There is a string 1 character larger than the other. What we need to figure out
	// is can we take one character out of the larger string and have it equal the smaller? 
	for (int a = 0; a < string1.length(); a++) {
	    //Combine Stubstrings of everything before and after the char being removed.
	    // Start of string edge case
	    if (a == 0) {
		if (string1.substring(1,string1.length()).equals(string2)) {
		    return true;
		}
	    }
	    // End of String edge case
	    if (a == string1.length()) { 
	       if (string1.substring(0,string1.length() - 1).equals(string2)) {
		   return true;
	       }
	    }
	    String prefix = string1.substring(0, a);
	    String suffix = string1.substring(a+1, string1.length());
	    String newString1 = prefix + suffix; 
	    if (newString1.equals(string2)) {
		return true;
	    }
		    
	}
	return false;
    }
    private static boolean checkEqualStringsForOneCharDifference(String string1, String string2) {
	int numberOfDifferentCharacters = 0;
	for (int i=0; i < string1.length(); i++) {
	    if(string1.charAt(i) != string2.charAt(i)){
		numberOfDifferentCharacters++;
		if (numberOfDifferentCharacters > 1) {
		    return false;
		}
	    }
	}
	    if (numberOfDifferentCharacters > 1) {
		return false;
	    } else {
		return true;
	    }
    }

    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("Using the areOneChangeAwayFromEquality solution these strings: " +
		"/pale/, /ale/ produced : " + areOneChangeAwayFromEquality("pale", "ale"));
	System.out.println("Using the areOneChangeAwayFromEquality solution these strings: " +
		"/pales/, /pale/ produced : " + areOneChangeAwayFromEquality("pales", "pale"));
	System.out.println("Using the areOneChangeAwayFromEquality solution these strings: " +
		"/pale/, /bale/ produced : " + areOneChangeAwayFromEquality("pale", "bale"));
	System.out.println("Using the areOneChangeAwayFromEquality solution these strings: " +
		"/pale/, /bake/ produced : " + areOneChangeAwayFromEquality("pale", "bake"));


    }
}
