# Bitwise Manipulation
You can take advantage of the fact that numbers are stored in binary to improve
your code. Here will be listed some pratical applications. 

## How to Tell if a Whole Number is Even or Odd

You can use bit manipulation to do this. 

In this code below:
```java
int lowestOneBit = Integer.lowestOneBit(number);
```

`lowestOneBit` will always be 1 if it is an odd number. Anything else is an 
even number.  This is due to the why numbers are stored in binary. 

You can also get at the number with something like: 

```java
   private static void determineIfNumberIsEventOrOdd(int number) {
		// Here the & operator does the bit-wise AND of the provided number
        // and 1. If the number was 0 it will be an even number. If it is 
        // one it will be odd.

        if ( (number & 1) == 0 ) {   
            System.out.println(“Number is even”);
        } 
        else { 
            System.out.println(“Number is odd”);
        }
    }
``` 

A detailed example: 

Let us assume we have a call like `determineIfNumberIsEvenOrOdd(7)`. When we
get to `if ( (number & 1) == 0)` we are comparing the bitwise representation of
7 and one like so: 

```
7 - 111
1 - 001
```

We will get: 
```
001
```

Because the one at the last digit in the binary number is the only common
number between them. If it had been 6 instead of seven we would have: 


```
6 - 110
1 - 001
```

We will get: 
```
000
```


