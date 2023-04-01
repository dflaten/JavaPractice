// String Builder Class implemented using an ArrayList
// Mistakes: I assumed I could use ArrayList (maybe I could, maybe not but I should check.)
//           I didn't add documentation to describe the function. 
public class DavidsStringBuilder {
	private ArrayList<String> strings;

	public DavidsStringBuilder() {
         strings =  new ArrayList();
	}	

	public void append(String s) {
		if (isNull(s)) {
			throw InvalidArgumentException("Attempted to append string with value of null to StringBuilder.");
		}
		strings.add(s);
	}

	public String toString(){
		String newString = "";
		for (String s : strings){
			newString = newString + s;
		}
		return newString;
	}
}

// Mistakes I made here: 1) I need to clearly define what kinds of methods they would like me to include in my implementation of the ArrayList.

/* Custom ArrayList data structure. Provides a way of storing a list of non specified size of Objects.
   This should scale up or down depending on the number of elements stored in the array. 

   Methods include: 

   add(Object o) - add an object O to the list.
   remove(Object o) - remove an object o from the list.

   Assuming we do not want to allow storage of null values but the object can be of any type. 

*/
public class DavidsArrayList {
	private DEFAULT_SIZE = 2;
	private ARRAY_SCALING_UP_FACTOR = 2;
	private ARRAY_SCALING_DOWN_FACTOR = .5;

	numberOfStoredObjects;
	Object [] objectArray;


	public DavidsArrayList() {
		objectArray = new Object[DEFAULT_SIZE];
		numberOfStoredObjects = 0;
	}

	public void add(Object o){
		if (isNull(o)) {
			throw InvalidArgumentException("Attempted to add null object to DavidsAarrayList, null values are not allowed");
		}
		//Check to see if we have a full array.
	    if (numberOfStoredObjects < objectArray.size())	{
	       objectArray[objectArray.size() + 1] = o;
	       numberOfStoredObjects++;
	    }
	    else {
	       //Create a new array which is the size of the current array multiplied by a factor.
	       Object [] newObjectArray = new Object[numberOfStoredObjects * ARRAY_SCALING_FACTOR]; 

	       //Copy the objects from the old Array over to the new Array
	       for (int i =0; i < objectArray.size(); i++){
	    		newObjectArray[i] = objectArray[i];
	       }
	       //Assign new Array to stored Array and store new Object
	       objectArray = newObjectArray;
	       objectArray[numberOfStoredObjects + 1] = o
	       numberOfStoredObjects++;
	    }
	}

	public boolean remove(Object o){
		//Iterate through the list and remove the object if it exists and check if the array is less than the scaling down factor. If it is scale the array down and copy the array over to the new one.  
		//Return true if something removed, false otherwise. 
	}
}
// PROBLEM 1
// Implement an algorith that determines if a given string has all unique characters. What if you cannot use additional data structures. 
// Eddge Cases - What do you want to do with null and empty strings?

// If I can use data structures I would use a HashMap to accomplish this task. Without additional structures I would iterate through the string and adding unique characters to another list to compare the other strings against. 
// If I ever found the same striing return false, otherwise return true. 

// Implementation using Hashmap
// Has a Big O(n) where n is the length of the string
public boolean stringContainsAllUniqueChars(String s) {
	//Handle null/empty strings
	if (isNull(s) || s = "") {
		throw InvalidArgumentException("Invalid Argument submitted, String must not be null or empty string.")
	}

	HashMap<String, String> uniqueChars = new HashMap();

	for (Char c: s) {
		if (isNull(uniqueChars.get(c))){
			uniqueChars.add(c,c);
		}
		else {
			return false;
		}
	}
	return true;

}
// Implementation using Array
// Has a Big O(n^2) where n is the length of the string
public boolean stringContainsAllUniqueChars(String s) {
	//Handle null/empty strings
	if (isNull(s) || s = "") {
		throw InvalidArgumentException("Invalid Argument submitted, String must not be null or empty string.")
	}

	Char [] uniqueChars = new Char[s.size()]
	for (int j = 0; j< s.size(), j++) {
		//Check to see if the value is in the uniqueChars List 
		for (int i = 0; i < uniqueChars.size(); i++) {
			if (c == uniqueChars[i]) {
				return false;
			}
		}
		//Add the value to the uniqueChars List
		uniqueChars[j] = s[j];

	}
	return true;

}

// Book Solution
// They first ask if this is a ASCII or Unicode String.
// Since they know it is an ASCII String you can assume the length of the String can't be more than 128 characters. 
// The Big O for this is O(n) since we only need to go through the String one time and can take advantage of the fact that this is an ASCII string.
// Mistakes:
// I did not catch that for arrays you add the .length attribute because it is not a method but a value you are accessing on the array. 
// For strings like 'String str' you use the method like: str.length() 
public boolean isUniqueChars(String str) {
	if (str.length > 128) {
		return false
	}

	boolean[] char_set = new boolean[128];
	for (int i = 0; i < str.length(); i++) {
		inv val = str.charAt(i);
		if (char_set[val]) {
			return false;
		}
		char_set[val] = true;
	}
	return true;
}
// PROBLEM 2
// Check Permutations: Given two Strings, write a method to decide if one is a permutation of the other.

