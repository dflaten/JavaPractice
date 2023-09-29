# Find Sub Strings in another String
Have the function ArrayChallenge(strArr) read the array of strings stored in 
strArr, which will contain 2 elements: the first element will be a sequence of 
characters, and the second element will be a long string of comma-separated 
words, in alphabetical order, that represents a dictionary of some arbitrary length. 

For example: strArr can be: ["hellocat", "apple,bat,cat,goodbye,hello,yellow,why"]. 
Your goal is to determine if the first element in the input can be split into 
two words, where both words exist in the dictionary that is provided in the 
second input. In this example, the first element can be split into two words: 
hello and cat because both of those words are in the dictionary.

Your program should return the two words that exist in the dictionary 
separated by a comma. So for the example above, your program should return 
hello,cat. There will only be one correct way to split the first element of 
characters into two words. If there is no way to split string into two words 
that exist in the dictionary, return the string not possible. The first element 
itself will never exist in the dictionary as a real word.

Once your function is working, take the final output string and remove any 
characters (case-insensitive) from it that appear in your ChallengeToken. 
If the new final string is empty, return the string EMPTY.

Your ChallengeToken: `q7kh8of0pbc1`
Examples
Input: `new String[] {"baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"}`
Output: `base,ball`
Final Output: `ase,all`
Input: `new String[] {"abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"}`
Output: `abcg,efd`
Final Output: `ag,ed`

## Solution
```java
import java.util.*; 
import java.io.*;

class Main {

  public static String ArrayChallenge(String[] strArr) {
    String searchSequence = strArr[0];

    String dictionaryString = strArr[1]; 
    ArrayList<String> dictionaryList = new ArrayList<>(Arrays.asList(dictionaryString.split(",")));
    // Check every split of the Search Sequence for matches in list
    for (int i = 0; i <= searchSequence.length() - 2; i ++) {
      String firstWord = searchSequence.substring(0, i + 1);
      if (dictionaryList.contains(firstWord)) {
        String secondWord = searchSequence.substring(i + 1, searchSequence.length());
        if (dictionaryList.contains(secondWord)) {
          String result = firstWord + ',' + secondWord;
          return removeChallengeCharacters(result);
        }
      }
    }
    return removeChallengeCharacters("not possible");
  }

  public static String removeChallengeCharacters(String output) {
    // Define Challenge Characters
    String CHALLENGE_CHARACTERS = "q7kh8of0pbc1";
    // Create a Set of the characters for easy access
    Set<Character> challengeCharactersSet = new HashSet<>();
    for (int i = 0; i < CHALLENGE_CHARACTERS.length(); i++) {
      challengeCharactersSet.add(CHALLENGE_CHARACTERS.charAt(i));
    }

    StringBuilder builder = new StringBuilder();
    for (int j = 0; j < output.length(); j++) {
      if (!challengeCharactersSet.contains(Character.toLowerCase(output.charAt(j)))) {
        builder.append(output.charAt(j));
      }
    } 
    if (builder.toString().isEmpty()) {
      return "EMPTY";
    }
    return builder.toString();
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(ArrayChallenge(s.nextLine())); 
  }
```

