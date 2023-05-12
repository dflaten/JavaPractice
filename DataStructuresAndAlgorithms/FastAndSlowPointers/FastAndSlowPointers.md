# Fast And Slow Pointers

## Description
The fast and slow pointer pattern of problems involve the use of two pointers
like the two pointer problem. However this approach has one pointer leading or
going faster than another pointer as it iterates through a linked list or an
array. 

Usually one pointer moves at the pace of one element per iteration while the
other moves at a factor of two but the speed can be adjusted depending on the
problem. 

The fast and slow pointer approach is used to determine traits about the data
structure rather than traits about the data as the two pointer approach does.
For example the fast and slow pointer approach is used to identify if a data
structure has a cycle or loop within it. This is done by checking to see when
the fast pointer overtakes the clower one after completing a lap. 

## Small Example
Problem: Delete the n'th node from a linked list. 

Data Structure: 7 -> 8 -> 9 -> 10 -> 11 -> 12 -> 1 -> 7
Input: 3, should delete 10. 

Algorithm: Move your fast pointer n forward in the list (in this case to 10).
Then move each forward by one until the fast pointer is pointed at null (on 7).
Now you now your slow pointer will be on 12, the 3rd item from the end which
can be deleted. 

## How to Know when you can use this method to solve a problem
1. The problem requires identifying the first x% of the elements in a linked
   list.
1. The element at the k-way point in a linked list (the middle, kth last
   element in a linked list). 
1. The problem requires identifying a cycle in a linked list or sequence of
   symbols. 
1. Do not use if the input data can't be traversed in a linear fashion. 
1. Use the two pointer approach if you can solve the problem by the pointers
   going at the same pace. 

## Real World Uses

### Symlink Verification
Symlinks are used by operating systems to shortcut to another file. These links
can easily create loops or cycles. By using the two pointer approach these
cycles can be easily identified and errors thrown to avoid having these
worthless symlinks in the system. 

### Compiling an Object Oriented Program
There can be cyclical dependencies in an Object Oriented program which can be
identified using this method in the same way the symlink verification is done. 

## Examples

### Happer Number
Write a program that determines if a number, n is happy. 

A happy number is: 
* Starting with any positive integer, replace the number by the sum of the
  squares of its digits. 
* Repeat the process until the number equals 1 (where it will stay), or it
  loops endlessly in a cycle which does not include 1. 
* If there is a cycle it is not happy, if it ends in one it is happy. 

#### Approach Logic
I first though to compute the sums of each square and store them in a set to be
reference later to check for a duplicate. If a duplicate is ever found we know
we have a cycle. If our sum ever gets to 1 we know we have a Happy Number. 

The problem is this approach doesn't scale well as we are going to have to
store some very large numbers. 

My Approach: I didn't like having a loop that looks like an infinite loop with
the `while (1 == 1)` statement, but due to the way the math works this will
work. 

```java
public static boolean isHappyNumber(int n) {
    int slow = n;
    int fast = sumOfSquaresOfDigits(n);
    while ( 1 == 1) {
        if (fast == 1) {
            return true;
        } else if (fast == slow) {
            return false;
        } else {
            slow =  sumOfSquaresOfDigits(slow);
            fast = sumOfSquaresOfDigits(sumOfSquaresOfDigits(fast));
        }
    }
}

public static int sumOfSquaresOfDigits(int n) {
    int temp = 0;
    int result = 0;
    int myNumber = n;

    while (myNumber > 0) {
        temp = myNumber % 10;
        result += (temp * temp);
        myNumber /= 10;
    }
    return result;
}
```

Alternatively I could have used:
```
while (fast != 1 && slow != fast) {
      slow = sumOfSquaredDigits(slow);
      fast =  sumOfSquaredDigits(sumOfSquaredDigits(fast));
  }
return fast == 1;
```

#### Time Complexity
This solution is `O(log n)` because we are calculating the sum of sqaures,
technically this is `O(log n)` + `O(log n)`  since we are doing it twice but it
ultimately breaks down to `O(log n)`.

### Linked List Cycle
Write a program that when provided the head of a LinkedList returns true if
there is a cycle in the list or false if it does not. 

#### Approach Logic
I will use a fast/slow pointer in this solution. The fast pointer will move at
2X speed and the slow at 1X Speed iterating through the list. If the fast
pointer or the fast.next pointer is ever null we know there is no cycle. If the
fast and slow pointer ever end up on the same node we know there is a cycle. 

My Solution: `O(n)` for time, `O(1)` for space
```java
public static boolean detectCycle(LinkedListNode head) {
LinkedListNode slow = head;
LinkedListNode fast = head;

//Don't need this if we check for fast.next being null in the while loop
if (head == null || head.next == null) {
  return false;
}

while(fast != null) {
//Also don't need this if we check for fast.next being null in the while loop
  if (fast.next == null) {
    return false;
  }
  slow = slow.next;
  fast = fast.next.next;
  if (slow == fast) {
    return true;
  }

}
return false;
}
```

### Middle of the Linked List
Write a program that when provided a Linked List will return the middle node of
the Linked List. If the number of nodes are even return the 2nd middle node. 

**Example**
Given: 1 -> 2 -> 3 -> 4 -> 5 -> null
Return: 3

#### Approach Logic
Use a fast and slow pointer. Set both pointers to the head. Move the slow
forward by one and the fast forward by two. When the fast pointer is null or
fast.next equals null then return the slow pointer because we will be pointing
at the middle of the list. 

#### Time/Space Complexity
Time - `O(n)` where n is the number of nodes in the list. 
Space - `O(1)`

```java
public static LinkedListNode(LinkedListNode head) {
    LinkedListNode slow = head;
    LinkedListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    return slow;
}
```

### Circular Array Loop
An array is given that has non zero integers stored in it where the value
represents the number of places to skip forward (positive) or backward
(negative). When skipping forward or backward, wrap arround if you reach either
end of the array. If you do so you have a circular array. 

* The same set of indices must be repeated when the sequence is traversed
  according to the rules above. 
* The length of the sequence is >= 2. 
* The loop must be in a single direction forward or backward. 

**Example**
Input: `1, 3, -2, -4, 1`
Output: `true`

You move 1 forward, then three forward, then 1 forward back to the start where
the sequence continues. 

```java

  public static boolean circularArrayLoop(int[] nums) {
  int size = nums.length;
  for (int i = 0; i < size; i++) {
    // Set slow and fast pointer at current index value.
    int slow = i, 
    int fast = i;
    boolean forward = nums[i] > 0;

    while (true) {
      slow = nextStep(slow, nums[slow], size);

      if (isNotCycle(nums, forward, slow))
        break;

      fast = nextStep(fast, nums[fast], size);
      if (isNotCycle(nums, forward, fast))
        break;

      fast = nextStep(fast, nums[fast], size);
      if (isNotCycle(nums, forward, fast))
        break;
      
      // Loop has been found
      if (slow == fast)
        return true;
    }
  }

  return false;
}

public static int nextStep(int arrayIndex, int steps, int size) {
  // To get how many steps we should move take the provided position, add the 
  // steps provided and then take the modulus of that sum against the size.
  // We take the modulus because we don't actually need to move through the
  // array if the size is super large, we just need to know where we will end 
  // up.
  int result = (arrayIndex + steps) % size;
  // If the result is negative we should add the size so that we can just add
  // the number to the current value to move the pointer forward correctly. (We
  // can do this because we used modulus in the previous line. 
  if (result < 0) {
      result = result + size;
  }
  return result;
}

// A function to detect a cycle doesn't exist
public static boolean isNotCycle(int[] nums, boolean prevDirection, int pointer) {
  // Set current direction to true if current element is positive, 
  // set false otherwise.
  boolean currDirection = nums[pointer] >= 0;
  
  // If current direction and previous direction are different or moving a 
  // pointer takes back to the same value, then the cycle is not possible, 
  // we return true, otherwise return false.
  if (prevDirection != currDirection || Math.abs(nums[pointer] % nums.length) == 0) {
    return true;
  }
  return false;
}

```


### Find The Duplicate Number
Given an array of integers, find a duplicate number such that the array
contains n + 1 elements and each inter is in the rage [1, n] inclusive. 

There is only one repeated number in nums. Find and return that number. 

Constraints:
* You must use constant space
* 1 <= n <= 10^5
* `nums.length` = n + 1
* 1 <= n[i] <= n + 1
* All integers in the array are unique except for one integer that will appear
  more than once. 

#### Approach Logic
Use a fast and slow pointer. Both points move using the values in the array.
Due to the constrains this is possible. You first find the intersection point
which is where fast and slow point. The intersection point in general is not
equal to the cycle entrance. Then you do another loop to find the entrance.
Start the slow pointer at the start of the sequence and leave the fast pointer
on the intersection point.

#### Solution
```java
   public static int findDuplicate(int[] nums) {
      int slow = nums[0];
      int fast = nums[0];
      // Find the Intersection Point
      while(true) {
         slow = nums[slow];
         fast = nums[nums[fast]];

         if (slow == fast) {
            break;
         }
      }

     // Find the Cycle entrance. We start slow at the beggning of the array and
     // the fast pointer at the intersection point. (Left it there from the
     // previous loop.)

      slow = nums[0];
      while (slow != fast) {
         slow = nums[slow];
         fast = nums[fast];
      }

      return fast;
   }
```
