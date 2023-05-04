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

