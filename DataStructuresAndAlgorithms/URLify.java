import java.util.HashMap;
import java.util.Arrays;

/*
 * Problem: URLify: Replace all spaces in a string with `%20`. Assume the string has space at the end to hold the additional characters
 *                  and the length of the string is also provided. 
 *
 *                  Example: Input: "Mr John Smith      ", 13
 *                           Output: "Mr%20John@20Smith"
 *
 */
public class URLify {

    // Book Implementation: 1) Create a StringBuilder 
    //                    1) Iterate through the provided String. 
    //                    2) If you find a space, add a `%20` to the StringBuilder
    //                    3) Else add the char that is there.
    // Assumptions: The book wanted a method that actually mutated the String. So to this end
    //              they made the input a character array and the return type void.
    static public void urlifyBook(char [] string, int finalLength) {
	//Impmentation in book.
    }

    // My Implementation: 1) Create a StringBuilder 
    //                    1) Iterate through the provided String. 
    //                    2) If you find a space, add a `%20` to the StringBuilder
    //                    3) Else add the char that is there.
    //                    4) Continue Iterating until you get to the end of the String.
    //
    // Assumptions: No Null values provided for either parameter. 
    //              Empty Strings will be returned as is.
    //              The provided length will always be the length of the String.
    //              We are looking at URLs so the max value of the String will probably be less than 2,000 characters
    // 
    // Mistakes:   Forgot the final edge case before running program(no spaces);
    //             I made the mistake of overcomplicating this at first and trying to append the rest of the
    //             string instead of simply building it one character at a time.
    // Big O: O(n) 
    static public String urlify(String string, int finalLength) {
	//Edge Cases
	if (string == "") {
	    return string;
	}

	StringBuilder newString = new StringBuilder();
	int charAfterLastSpaceFound = 0;

	for(int i = 0; i < finalLength; i++) {
	    if(string.charAt(i) == ' ') {
		newString.append("%20");
	    } else {
		newString.append(string.charAt(i));
	    }

	}
	return newString.toString();

    }


    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	System.out.println("Using the urlify solution this string, //, produced : " + urlify("", 0));
	System.out.println("Using the urlify solution this string, /Mr John Smith/, produced : " + urlify("Mr John Smith      ", 13));
	System.out.println("Using the urlify solution this string, /MrJohnSmith/, produced : " + urlify("MrJohnSmith", 11));
	System.out.println("Using the urlify solution this string, / MrJohnSmith/, produced : " + urlify(" MrJohnSmith", 12));
	System.out.println("Using the urlify solution this string, /   MrJohnSmith/, produced : " + urlify("   MrJohnSmith", 14));


    }
}
