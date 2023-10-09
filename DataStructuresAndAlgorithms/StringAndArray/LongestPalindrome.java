/**
 * Problem: Given a string s which consists of lowercase or uppercase letters, 
 * return the length of the longest palindrome that can be built 
 * with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a 
 * palindrome here.
 */
// Solution below was a failure due to the incorrect assumption that you could
// only use one odd numbered character when infact your can use all but one
// odd character in your solution.
 class IncorrectSolution {
    public int longestPalindrome(String s) {
        int [] letterCounter = new int[128];

        //Count our characters
        for(int i = 0; i < s.length(); i++) {
            letterCounter[s.charAt(i)] = letterCounter[s.charAt(i)] + 1;
        }

        int longestPalindrome = 0;
        int numberOfOddCharacters = 0;
        PriorityQueue<Integer> oddMaxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int j = 0; j < letterCounter.length; j++) {
            if(isEven(letterCounter[j])) {
                longestPalindrome = longestPalindrome + letterCounter[j];
            } else {
                numberOfOddCharacters++;
                oddMaxHeap.add(letterCounter[j]);
            }
        }
        if (!oddMaxHeap.isEmpty()) {
            longestPalindrome = longestPalindrome + oddMaxHeap.peek();
        }
        return longestPalindrome;
        
    }

    private boolean isEven(int i) {
        if (i % 2 == 0) {
            return true;
        }
        return false;
    }
}
//This Solution works correctly.
class Solution {
    //Use array to keep track of character counts.
    //Iterate through string to count our characters.
    //Iterate through the character counter to find out how many odd number
    //characters exist.
    //If we have more than one subtract the number of odd characters we have
    //from the total length of the string since we can't take those as part of
    //our Palindrome.
    //Otherwise return the length of the String.
    public int longestPalindrome(String s) {
        int [] letterCounter = new int[128];

        //Count our characters
        for(int i = 0; i < s.length(); i++) {
            letterCounter[s.charAt(i)] = letterCounter[s.charAt(i)] + 1;
        }

        // One Character may have odd values in the middle. 
        int oddCount = -1;
        for (int j = 0; j < letterCounter.length; j++) {
            if(isOdd(letterCounter[j])) {
                oddCount++;
            } 
        }
        if (oddCount > 0) {
            return s.length() - oddCount;
        } else {
            return s.length();
        }
    }

    private boolean isOdd(int i) {
        if (i % 2 == 0) {
            return false;
        }
        return true;
    }
}
