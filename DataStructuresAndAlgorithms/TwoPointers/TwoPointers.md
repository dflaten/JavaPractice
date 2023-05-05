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
