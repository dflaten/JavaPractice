import java.util.*;

class RecursiveSwitch {
 
    // This function will swap characters for every permutation
    public static char [] swapChar(String word , int i, int j) { 
       char[] swapIndex = word.toCharArray();
       char temp = swapIndex[i];
       swapIndex[i] = swapIndex[j];
       swapIndex[j] = temp;

       return swapIndex;
    }

    public static void permuteStringRec(String word, int currentIndex, ArrayList<String> results) {
        // Why are we adding the word here at the current index pointing at the 
        // last character? Because there are no more characters to switch.
        if(currentIndex == word.length() - 1) {
            System.out.println("\t\t Adding word " + word + " to results.");
            results.add(word); 
            return;
        }
        /*
         * Here there is a loop that is making recursive calls to the function
         * which contains the loop. We start at the "currentindex" which will
         * be 0 but then switch it with every index in the String until we get
         * to the last character (base case above). Then we add the word to the 
         * results list.
         *
         * Assuming input is 'abcd'
         * currentIndex = 0 is called 1 time for each character in the string
         * or 4 times in the input `abcd`.
         *
         * The loop kicks of a recursive call to switch each current with the
         * next (currentIndex + 1) until we get to the last character at which
         * point we know we are done "generating" that String. We get to the
         * end twice for each letter. Once when we are switching that character
         * with itself and once when we are switching it with the previous
         * character.
         *
         * Permutations are being created with this recursive call, no "fixing"
         * is done there is just one permutation where you switch with its
         * self due to 'index = currentIndex` in the for loop.
        */
        for (int index = currentIndex; index < word.length(); index++) {
            // swaps character for each permutation
            char [] swappedStr = swapChar(word, currentIndex, index);
            System.out.println("\t After swapping the indices in the string: " 
                    + String.valueOf(swappedStr) + ", index: " + index 
                    + " currentIndex: " + currentIndex);
            // recursively calls itself to find each permutation
            permuteStringRec(String.valueOf(swappedStr), currentIndex + 1, results);
        }
    }
    // Driver code
    public static void main( String args[] ) {
        String[] inputWord = {"ab", "bad", "abcd"};
        ArrayList<String> results = new ArrayList<String>();

        for (int index = 0; index < inputWord.length; index++) {
            System.out.println(index + 1 + ".\t Input string: '" + inputWord[index] + "'");
            permuteStringRec(inputWord[index], 0, results);
            System.out.println("------------------------");
        }
    }
}
