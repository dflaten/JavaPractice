# Sliding Window
A sliding window is a technique that can be used when solving various
algorithmic problems. What you do is create a window and make your
comparison. A "comparison" could be: [The sum of those numbers, do they add up
to a value?], [The list of those characters, are they a palindrome?]. If you
need to you can update your window after you make a comparison, like
increasing/decreasing the window size and then moving your  window to the next
possible set of values. 


## Example: Find the Largest Sum of 5 continous integers in an Array. 

```
[1,7,9,2,3,1,9,4,3,7,3,4]
```

Our first window here looks at: 

```
[1,7,9,2,3] - Sum: 22
```
Next: 
```
[7,9,2,3,1] - Sum: 22 
```
Next:
```
[9,2,3,1,9] - Sum: 26
```

We continue that sliding of the window, saving the highest sum. 

## Example: Longest Substring with at Most Two Distinct Chars

```java
class Solution {
    /* Potential Solutions
    * 1) Sliding Window: Use a left and right pointer to make a window and a 
    *    Hashset to keep track of the characters you've seen before.
    */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        for(int right = 0; right < s.length(); right++) {
            // If the char isn't in the set add it and recalculate MaxLength
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            // If the char is in the set.
            } else {
                /* First move the left pointer to where the right pointer
                *  is pointing at the same char. Removing chars from the set
                *  as you go to keep it up to date.
                */ 
                while(s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                /* Finally move the left pointer forward one more so that the 
                *  same char is not counted twice. 
                *
                *  Little Example: `a b c d e a b c`
                *                   ^         ^
                *                  left      right
                * Here you would move the pointer from 0 to 1 in the String.
                */  
                left++;
            }

        }

        return maxLength;
    }

}
```
