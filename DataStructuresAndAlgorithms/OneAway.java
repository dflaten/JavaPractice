import java.util.HashMap;
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
 */
public class OneAway {

    // My Implementation:
    //                    1) 
    //                    2) 
    //                    3) 
    // Assumptions: No Nulls are passed in. These are ASCII encoded strings. Strings
    //              that are already equal will return true.
    //
    // Mistakes:
    static public boolean areOneChangeAwayFromEquality(String string1, String string2) {
	return true;
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
