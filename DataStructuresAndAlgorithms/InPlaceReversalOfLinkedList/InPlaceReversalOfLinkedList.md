# In-place Reversal of Linked List

This pattern allows us to reverse a linked list without any additional memory,
using only the given nodes.

To do this we iterate a linked list and keep track of the current node, the
next node, and the previous node simultaneously. Keeping track of the nodes
allows us to change the links between them and make them point to a different
node than before. 

The naive approach of solving these types of problems using nested loops takes
`O(n^2)` time. Using the in-place reversal pattern the time complexity comes to
`O(n)` since we use a single pass through of the list to do so. 

## When to use this Pattern
* When you need to reverse a given linked list as a part of the solution to the
  problem. 
* The modifications to the linked list must be made in place (that is no using
  more than `O(1)` memory. 
* The input data is in the form of a linked list. 
* We do NOT need to use additional memory. 
* We must be able to modify the input linked list. 

## Real World Problems

* *Stocks*: A total of n stock transactions need to be carried out by K
  brokers. We need to assign transactions to each broker that need to be
  carried out in the same order in which they were arrived. 
* *E-commerce*: A list of products is arranged such that the first half is in
  ascending order based on prices and the second half is in ascending order
  based on popularity. A list of products needs to be displayed on a landing
  page in pairs of price and popularity such that the first product is cheaper
  and the second is the most popular. 

## Problem Examples

### Reverse Linked List
Given the head of a singly linked list, reverse the linked list and return its
updated head. 

#### Constraints
* 1<= n <= 500
* -5000 <= Node.value <= 5000

#### Solution
Iterate through the linked list, track the previous node current node and next
node. Updating them as you move through the list to reverse it. 

```java
public class ReverseLinkedList{
    public static LinkedListNode reverse(LinkedListNode head) {
    // Start previous as null (because that is where the new list will end)
    LinkedListNode previous = null;
    // Start Current at head (because that is where we will start)
    LinkedListNode current = head;
    // Start next at head.next because that is where we will be going.
    LinkedListNode next = head.next;
    
    // Stop when you get to null or the end of the list
    while(current != null) {
        // Update next first so we know where we are going.
        next = current.next;
        // Do your reverse operation, move the current.next value to the
        // previous.
        current.next = previous;
        // Now do your updates for the next iteration, track your previous will
        // now be the current item and current will be the item you marked as 
        // next at the start of the loop.
        previous = current;
        current = next;
    }
    // We can return previous because that is where the new head will be at
    // when we finish iterating through the list.
    return previous;
  }
}
```

### Reverse Nodes in k-Group
Given a linked list, reverse the nodes of the linked list k at a time and
return the modified list. Here k is a positive integer and less than or equal
to the length of the linked list. If the number of nodes is not a multiple of
k, the nodes left in the end will remain in their original order. 

##### Constraints
Where `n` is the number of nodes in the linked list.

* Use only `O(1)` extra memory space. 
* 1 <= `k` <= `n` <= 500
* 0 <= `Node.value` <= 1000

#### Solution

1. Count and check if there are `k` number of nodes in a linked list.
1. Reverse the set of `k` nodes.
1. Reconnect the reversed set of `k` nodes with the rest of the linked list. 
1. Repeat the process till less than `k` or no nodes are left in the linked
   list. 

My first attempt. This solution does not work and gets stuck in an infinite
loop.
```java
    public static LinkedListNode reverseLinkedList(LinkedListNode head, int k) {
    int sizeOfLinkedList = 0;
    // Get Size of Linked List
    sizeOfLinkedList = determineLinkedListSize(head);
    int leftovers = sizeOfLinkedList % k;
    
    LinkedListNode temp = head;
    
    int countToStopReversing = sizeOfLinkedList - leftovers;
    
    // KSet Variables used for reversing a kSet
    int countToK = 0;
    LinkedListNode startOfCurrentKSet = null;
    LinkedListNode currentKSetNode = temp;
    LinkedListNode previousKSetNode = null;
    LinkedListNode nextKSetNode = temp.next;
    // Need this to connect the K Set to the previous
    LinkedListNode endOfPreviousKSetNode = null;
      while (temp != null) {

          if (countToStopReversing == 0) {
            temp = temp.next;
          } else {
            // See if we are at the end of a set to reverse
            if (countToK == k) {
              countToK = 0;

              // Reverse the previous k items
              int reverseCount = k;
              startOfCurrentKSet = temp;
              // Need to update some currentKSetNodes here or earlier before starting reverse
              currentKSetNode = temp;
              previousKSetNode = temp;
              nextKSetNode = temp.next;
              while (reverseCount != 1) {
                nextKSetNode = currentKSetNode.next;
                currentKSetNode.next = previousKSetNode;
                previousKSetNode = currentKSetNode;
                currentKSetNode = nextKSetNode;
                reverseCount--;
              }

            } else {
              // Keep iterating
              countToK++;
            }
            temp = temp.next;
            countToStopReversing--;
          }

      }
      return head;
  }

  public static int determineLinkedListSize(LinkedListNode head){
    LinkedListNode temp = head;
    int linkedListSize = 0;
    while(temp != null) {
      linkedListSize++;
      temp = temp.next;
    }
    return linkedListSize;
  }
```


Working Solution: 
```java
   public static LinkedListNode reverseLinkedList(LinkedListNode head, int k)
    {
        if(k <= 1 || head == null)
            return head;
        int i = 0;
        int count = 0;
        LinkedListNode current = head;
        LinkedListNode previous = null;
        LinkedListNode lastNodeofPreviousPart = null;
        LinkedListNode lastNodeofCurrentPart =null;
        int totalLength = findLength(head);
        while(true)
        {
            i += 1;
            lastNodeofPreviousPart = previous;
            lastNodeofCurrentPart = current;
            LinkedListNode next = null;
            List<LinkedListNode> res = reverse(lastNodeofCurrentPart, k);
            previous = res.get(0);
            current = res.get(1);
            next = res.get(2);
            count += k;
            //Stitching together the previous and current part
            if(lastNodeofPreviousPart != null)
                lastNodeofPreviousPart.next = previous;
            else 
                head = previous;
            lastNodeofCurrentPart.next = current;
            if(current == null || (totalLength - count) < k)
                break;
            previous = lastNodeofCurrentPart;
        }
        return head;
    }

  public static List<LinkedListNode>  reverse(LinkedListNode head, int k)
  {
    LinkedListNode previous = null;
        LinkedListNode current = head;
        LinkedListNode next = null;
        int index = 0;
        while(current != null && index < k)
        {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            index += 1;
        }
        List<LinkedListNode> resultList = Arrays.asList(previous, current, next);
        return resultList;
  }
    public static int findLength(LinkedListNode start)
    {
        LinkedListNode current = start;
        int count = 0;
        while(current != null)
        {
            current = current.next;
            count += 1;
        }
        return count;
    }
```

### Reverse Linked List II

You're given the head of a singly linked list with `n` nodes and two positive
integers, `left` and `right`. Our task is to reverse the list's nodes from
position `left` to posisiton `right` and returned the reversed list.


