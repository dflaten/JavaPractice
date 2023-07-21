# Two Pointers
This type of problem uses two pointers to get to a solution. Usually these
problems involve an Array of some kind in which the pointers will "point" to a
location and be updated as the problem is solved. 

## Determining if a Problem matches this pattern

1. Can the input be traversed in a linear way? (Do we have an Array[], Linked
   List, or String to operate upon?)

1. Is the linear data is arranged in a way that is relevant to the problem?

1. Are we only considering two elements rather than the whole set of elements
   when preforming our operation?

1. Does this problem require an exhaustive search/comparison of elements, as in
   you need to look at/compare/operate on every item in a list? If so this
   problem does NOT match the pattern.

## Simple Example: isPalindrome 
For a simple example think of the palindrome problem where you want to 
determine if a String is a Palindrome or not. The Brute force method `O(n^2)` is 
to iterrate over the string twice and compare each charavter to the other. With
two pointers we use a start and end pointer to comapare characters from one 
end of the string to the other. This gets the problem done in `O(n)`

``` java
private boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        } else {
            left++;
            right--;
        }
    }
    return true;
```

## Another Example: Sum Of Three Values
Given an array find any three values that add up to a given sum. If you can
find a triplet, return true otherwise return false. 

So Given: [1,2,3] and 6 Return: true
And Given: [2,3,4,5] and 16 Return: false

Solution: We will iterate through a sorted list and then use two pointers to
check the values in the list to see if we get the sum. When determining how we
will move the pointers we will use the value of the current sum. If the value
needs to be greater we move the left forward up in the sorted array. If lesser
we move the right pointer down. 

Time Complexity: O(n^2) in the worse case. 

```java
public boolean findSumOfThree(int [nums], int target) {
    //Sort the Array because of rule 2.
    Arrays.sort(nums);
    // We need nums.length - 2 because we can't use the same element twice
    // and normally would due to our "right" pointer here. 
    for (int i = 0; i < nums.length - 2; i++) {
        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if (sum == target) {
                return true;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
    return false;
    }
}
```

## Another Example: Reverse the Words in a Sentence
Given a String which is a series of words and spaces. Reverse the words in the
String and remove any extra spaces so there is exactly one space between each
word. 

Example: Given: "A water bottle" Result: "bottle water a"
Example: Given: "today   is a sunny day" Resuls: "day sunny a is today"

Solution: Iterate through the String starting at the back of the string
checking for a character that is not " " with pointer 'a'. As soon as you find 
a character that is not " ". Set your 2nd pointer, 'b' at that character and 
keep iterating until you get to another " ". When you get to another space or
the end of the String then transfer your "word" which is now `a` to `b` to 
your new String. Adding  `a` instead of `a-1` makes sure we get the space at 
the end.

My First attempt:
```java
public static String reverseWords(String sentence) {
    StringBuilder reversedString = new StringBuilder();
    int b = sentence.length() - 1;
    boolean wordInProgress = false;
    for(int a = sentence.length() - 1; a >= 0; a--) {
        if (sentence.charAt(a) == ' ') {
            if (b < a) {
                //Create new word with space at the end
                reversedString.append(sentence.substring(a, b));
                reversedString.append(' ');
                b = a;
                wordInProgress = false;
            }
        } else {
            if (!wordInProgress) {
                 b = a;
                 wordInProgress = true;
            }
        }
    }
    //Remove extra space added by latst word.
    reversedString.deleteCharAt(reversedString.length()-1);
    return reversedString.toString();
}
```

Final Code: 
```java
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
```

## Another Example: Palindrome II

Given a String can you determine if it is a Palindrome or can be turned into a
Palindrome by removing one character from it? 

Implementation: At first I attempted to just move through the Strings with two
pointers but that produced inconsistent results because essentially you need to
just *remove* a character from the string and check to see if it is a
Palindrome. You can also solve this problem with recursion.

```java
public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while(left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            if (isPalindrome(s, left+1, right)) {
                return true;
            } else if (isPalindrome(s, left, right-1)) {
                return true;
            } else {
                return false;
            }
        }
        left++;
        right--;
    }
    return true;
}

static public boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
                return false;
        }
        left++;
        right--;
    }
    return true;
}
```

## Another Examnple: Palindrome III
Determine if a String is a valid palindrome after converting all uppercase
characters to lower case and removing non alphanumeric characeters it reads the
same backwards and forwards. 

Solution: 
```java
class Solution {
    /** 
     * 1. Create two pointers, one pointed at the end of the string and one at the
     * end of the String. 
     * 2. First check the Character each string is pointing at and make sure it is an
     * Alpha numeric character. If it is not move the pointer forward(or backward).
     * And check the next. 
     * 3. Compare the characters, if they are equal then move the pointers one place 
     * and repeat.
     * 4. Do this until you have both pointers pointing at an alphanumeric character 
     * or both pointers are pointing at the same character.
     * 5. If they are, return true. If one pointer ever overtakes the other return   
     * false.
     */ 
    public boolean isPalindrome(String s) {
      int front = 0;
      int back = s.length()-1;

      while (front < back) {
          
          while (isNotAlphanumeric(s.charAt(front)) && front < back) {
              front++;
          }
          while (isNotAlphanumeric(s.charAt(back)) && front < back) {
              back--;
          }
          if (front < back) {
              if ((areNotSameCharacter(s.charAt(front) ,s.charAt(back)))) {
                  return false;
              } else {
                front++;
                back--;
              }
          }
      }  
        return true;
    }

    private boolean isNotAlphanumeric(char character) {
        return !Character.isLetterOrDigit(character );
    }
    private boolean areNotSameCharacter(char first, char second) {
        return !(Character.toLowerCase(first) == Character.toLowerCase(second));
    }

```
