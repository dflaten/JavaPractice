# Stacks and Queues

## Problems
### Valid Parenthesis
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

An input string is valid if:

* Open brackets must be closed by the same type of brackets.
* Open brackets must be closed in the correct order.
* Every close bracket has a corresponding open bracket of the same type.

#### Solution
First pass I misunderstood the problem due to the examples given and thought
each parenthesis had to be open and closed right next to each other thanks to
the second bullet. Here is the solution for that: 

```java
import java.util.*;
class Solution {
    //Every open parentheses must have a closing parenthesis. So the String must be of length 2 at least and have an even number of characters in it. Every 2 characters must be either a pair of (), {}, or []
    public boolean isValid(String s) {
        //1. First make sure the String length is even. If not return false.
        if (!isEven(s.length())) {
            return false;
        }
        //2. Iterate through the String and take characters two at a time and make sure they are (), {}, or []. If at any point they are not return false. If you get to the end return true. 
        for (int i = 0; i < s.length() - 1; i = i + 2) {
            if(isNotValidSubString(s.substring(i, i+2))) {
                return false;
            }
        }
        return true;
    }
    boolean isEven(int number) {
        if ((number & 1) == 0) {
            return true;
        }
        return false;
    }
    boolean isNotValidSubString(String pairOfChars) {
        Set<String> validPairs = Set.of("{}", "()", "[]");
        if (validPairs.contains(pairOfChars)) {
            return false;
        } else {
            return true;
        }
    }
}
```

However after attempting to submit I realized that it meant you must close a
parenthesis before you start another one. You can have pairs of other
parenthesis inside it. So here is my solution to that problem: 

```java
/**
 * Every open parentheses must have a closing parenthesis. So the String must 
 * be of length 2 at least and have an even number of characters in it. The open 
 * and closing parenthesis must be in the correct order so when you get one ( 
 * before you get another ( you must get a ). 
 * Solution: 
 * We create three stacks. One for each pair type. Whenever we get a ( we 
 * push a ( on the stack and whenever we get a ) we pop it off. Before we push 
 * we check to see if there is already a ( on the 
 * stack, if there is we return false. At the end we make sure all stacks are 
 * empty then return true. 
*/
```

*This solution is also incorrect because I didn't understand that the correct
order also meant that the String shouldn't have parenthesis mixed. One needed
to be closed before the other started.*

Correct Solution below: 
```java

/**
 * Every open parentheses must have a closing parenthesis. So the String must 
 * be of length 2 at least and have an even number of characters in it. The open 
 * and closing parenthesis must be in the correct order so when you get one ( 
 * before you get another ( you must get a ) or a { or a [.
 * Solution: 
 * We create one stack. If the stack is empty you must have a (, {, or [ as
 * your first character. You push that character onto the stack. If you get a
 * (, {, or [ you push it on the stack. If you get a [,},or) you pop the last
 * one off the stack and make sure it is a match for your current String.
*/

import java.util.*;
class Solution {
 
    public boolean isValid(String s) {
        //1. First make sure the String length is even. If not return false.
        if (!isEven(s.length())) {
            return false;
        }
        Stack<Character> trackStack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            Character currentChar = s.charAt(i);
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                trackStack.push(currentChar);
            } else if (trackStack.isEmpty()){
                return false;
            } else {
                Character lastCharacter = trackStack.pop();
                if (currentChar == ')') {
                    if (lastCharacter != '(') {
                        return false;
                    } 
                }
                if (currentChar == '}') {
                    if (lastCharacter != '{') {
                        return false;
                    } 
                }
                if (currentChar == ']') {
                    if (lastCharacter != '[') {
                        return false;
                    } 
                }
            }
        }
        if (trackStack.isEmpty()) {
            return true;

        } else {
            return false;
        }
    }
    boolean isEven(int number) {
        if ((number & 1) == 0) {
            return true;
        }
        return false;
    }
```

### Implement Queue using Stacks
Implmement a FIFO queue using only two stacks. The implemented queue should
support all the standard queue functions (`push`, `pop`, `peek`, and `empty`).

Must use only the standard operations of a stack, which means only `push to
top`, `peak/pop from top`, `size` and `isEmpty` operations are valid. 

#### Solution
```java
/** 
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
/**
 * Function to implement Queue Using Stacks
 * Here I chose to make the push operation a bit more time intensive but if we
 * wanted to make the pop operation more time intensive we could store the */  
class MyQueue {
    private Stack<Integer> dataStack;
    private Stack<Integer> tempStack; 

    public MyQueue() {
        dataStack = new Stack<Integer>();
        tempStack = new Stack<Integer>();
        
    }
    
    public void push(int x) {
       // Since we want a FIFO Queue we must push all items in the current
       // stack to our placeholder stack.
       while (!dataStack.isEmpty() ) {
           tempStack.push(dataStack.pop());
       }
       dataStack.push(x);
       while(!tempStack.isEmpty()) {
           dataStack.push(tempStack.pop());
       }
    }
    // Alternatively we could do the time intensive process of re-organizing
    // the stack during the pop operation if needed.
    public int pop() {
        if (!dataStack.isEmpty()) {
            return dataStack.pop();
        }
        return -1;
    }
    
    public int peek() {
        if (!dataStack.isEmpty()){
            return dataStack.peek();
        }
        return -1;
        
    }
    
    public boolean empty() {
       return dataStack.isEmpty(); 
    };
}
```
#### Performance
Overall we will have a amortized `O(1)` amoritized time complexity for the
functions except for the `push()` operation.
