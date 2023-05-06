import java.lang.StringBuilder;

/*
 * Problem: See `TwoPointers.md` for a description of the problem and the
 * Solution below.
 */
public class ReverseWords{

    // Due to the way substrings work in Java this was a little unintuitive of
    // an implementation. 
    //
    // Big O: O(n)
    public static String reverseWords(String sentence) {
        StringBuilder reversedWordString = new StringBuilder();
        int b = sentence.length() - 1;
        boolean wordInProgress = false;
        //Iterate through the string starting at the back.
        for (int a = sentence.length() - 1; a >= 0; a--) {
            //If we get to the end of a word or the String
            if (sentence.charAt(a) == ' ' || a == 0) {
                //And the word is in progress
                if (wordInProgress) {
                    //Add the word, the final word is an edge case
                    if (a == 0) {
                        reversedWordString.append(sentence.substring(a, b+1));
                    } else {
                        reversedWordString.append(sentence.substring(a+1, b+1));
                        reversedWordString.append(' ');
                        b = a;
                        wordInProgress = false;
                    }
                }
            } else if (!wordInProgress) {
                    b = a;
                    wordInProgress = true;
            }
        }
        return reversedWordString.toString();
   }

   public static void main(String args[]) {
       System.out.println("Starting Program!");
       System.out.println("--------");
       System.out.println("Reverse the string, /Hello  World I am here/, produced : " 
               + "/"+reverseWords("Hello  World") +"/");
   }
}
