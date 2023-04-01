import java.util.HashMap;

public class StringContainsAllUniqueCharsProblem {

    //Book Solution
    static public boolean isUniqueChars(String str) {
	if (str.length() > 128) {
	    return false;
	}

	boolean[] char_set = new boolean[128];
	for (int i = 0; i < str.length(); i++) {
	    int val = str.charAt(i);
	    if (char_set[val]) {
		return false;
	    }
	    char_set[val] = true;
	}
	return true;
    }

    // My Implementation using Array
    // Has a Big O(n^2) where n is the length of the string
    // Minor Changes: Took out execeptions and simplified to remove need to do imports.
    // Mistakes: Comparison missed and `=` sign., Program at first always returned false because
    //           I was doing the comparison against the `uniqueChars` array itself instead of the 
    //           string.
    static public boolean stringContainsAllUniqueChars(String s) {
	//Handle null/empty strings
	if (s == null || s == "") {
	    System.out.println("Invalid Argument submitted, String must not be null or empty string.");
	    return false;
	}

	char [] uniqueChars = new char[s.length()];
	for (int j = 0; j < s.length(); j++) {
	    //Check to see if the value is in the uniqueChars List 
	    for (int i = 0; i < uniqueChars.length; i++) {
		if (s.charAt(j) == uniqueChars[i]) {
		    return false;
		}
	    }
	    //Add the value to the uniqueChars List
	    uniqueChars[j] = s.charAt(j);
	}
	return true;

    }
    // Implementation using Hashmap
    // Has a Big O(n) where n is the length of the string
    // Mistakes: I Used a String, String map instead of a Char, Char map, can't use 
    //           chars as a key in HashMap have to use char instead, used add 
    //           instead of put when placing in map. Also needed to make sure I 
    //           make the chars I am passing into the hashmap a String as well or 
    //           the keys don't work. 
    // Notes
    static public boolean stringContainsAllUniqueCharsHM(String s) {
	//Handle null/empty strings
	if (s==null || s == "") {
	    System.out.println("Invalid Argument submitted, String must not be null or empty string.");
	    return false;
	}

	HashMap<String, String> uniqueChars = new HashMap();

	for (char c: s.toCharArray()) {

	    if (uniqueChars.get(String.valueOf(c)) == null){
		uniqueChars.put(String.valueOf(c), String.valueOf(c));
	    }
	    else {
		return false;
	    }
	}
	return true;

    }


    public static void main(String args[]) {
	System.out.println("Starting Practice!");
	System.out.println("--------");
	System.out.println("Using Book Solution This string, sstring, is unique: " + isUniqueChars("sstring"));
	System.out.println("--------");
	System.out.println("Using Array Solution this string, sstring, is unique: " + stringContainsAllUniqueChars("sstring"));
	System.out.println("Using Array Solution this string, string, is unique: " + stringContainsAllUniqueChars("string"));
	System.out.println("--------");
	System.out.println("Using HashMap Solution this string, string, is unique: " + stringContainsAllUniqueCharsHM("string"));
	System.out.println("Using HashMap Solution this string, strinrg, is unique: " + stringContainsAllUniqueCharsHM("strinrg"));


    }
}
