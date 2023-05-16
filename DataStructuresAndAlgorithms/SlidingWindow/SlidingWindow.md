# Sliding Window

## Description
This is a computational method which reduces the number of nested loops you
need to solve a problem. It is a varation of the two pointer problem where you
use the two pointers as two ends of the "window" you are currently looking at.

This method is only more efficient if we do not iterate over every item in the
window because that would just give us `O(kn)` efficiency if it did. For
example if we need to find the largest sum made from three consecutive numbers
in an array we could make a window of size three and then move the window up by
one, subtract the value we just moved out of the window and add the value that
we just added into the window. That gives us `O(n` efficiency. 

## How to Know when you can use this method
1. The problem requires repeated computations on a contiguous set of data
elements. (An array or string) such as that the window moves across the
input from one end to the other. Size of the window could be variable or
fixed, repeated steps could be part of the solution or build up to final
solution. 
1. The computations preformed every time the window moves take `O(1)` or are a
slow-growing function such as log of small variable k, where `k <<n`. 
1. The input must support random access. 
1. You don't have to process the entire data. 

## Real World Problems

### Telecommunications
Find the maximum number of users connected to a cellular network's base station
in every k-millisecond sliding window. 

### E-commerce
        Given a dataset of product IDs in the order they were viewed by the user and a
list of products that are likely to be similar, find how many times these
products occur in the dataset. 

### Video Streaming
Given a list of numbers which are the number of buffering events in a given
user session, calculate the median number of buffering events in each
one-minute interval. 

## Example Problems

### Repeated DNA Sequences
Given a String that represents a DNA sequence and a number, k, return all the
contiguous sequences of length k that occur more than once in the String. The
order of the returned subsequences does not matter. If no repeated subsequence
is found, the function should return an empty set. 

**Also Note**
`1 <= s.length <= 10^4`
`s[i]` == `A` or `C` or `G` or `T`

#### Solution

We use a `HashSet<String>` and harness the built in Java functionality to
compute a Hash value for each substring we are evaluating and then iterate
through the string making sure we end our window searching at 
`i <= s.length() - k` so we don't get any out of bounds errors but also look at
every possible substring.

I should probably add some checks at the beggining of the code to check for k
being greater than or equal to the length of the Stringand produce an error if
it does.

``` java
public static List<String> findRepeatedSequences(String s, int k) {
    HashSet<String> repeatedSequenceChecker = new HashSet<String>();
    ArrayList<String> results = new ArrayList<String>();
    for (int i = 0; i <= s.length() - k; i++) {
        String currentSubString = s.substring(i, i + k);
        if(repeatedSequenceChecker.contains(currentSubString) && !results.contains(currentSubString)) {
            results.add(currentSubString);
        } else {
            repeatedSequenceChecker.add(currentSubString);
        }
    }
    return results;
}
```


