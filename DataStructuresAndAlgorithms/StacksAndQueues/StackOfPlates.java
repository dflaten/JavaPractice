import java.util.ArrayList;

/*
 * Problem: SetOfStacks: Create a data structure that has a Stack that operates 
 * like a Stack of plates. At some point when stacking plates you do not want 
 * to stack higher than x plates or there is risk of them toppling over.
 *
 * This Set of Stacks will work in the same way. It will store data in a Stack
 * until it reaches a certain limit, then it will add another Stack in the set
 * to store data in. The Set of Stacks will continue to grow in this way. 
 *
 * However the pop/push methods should still work the exact same way as a
 * normal Stack.
 *
 *
 * Assumptions: Each Stack shouldn't be greater than 3.
 *
 *              The problem actually means a List of Stacks not a Set, the same
 *              stack could be entered twice if the same elements were added
 *              to it in the same order twice in a row.
 *
 * Implementation: 
 *
 * Mistakes: 
 */
//Simple Node Class used to create Data Structure
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
/* This is a simple normal stack. 
*/
class Stack {
    Node top = null;
    final int maxStackHeight = 3;
    int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public void push(int value) {
        Node newTop = new Node(value);
        if (top == null) {
            top = newTop;
        } else {
            newTop.next = top;
            top = newTop;
        }
        size++;
    }

    public int pop() {
        if (top == null) {
            System.out.println("Stack Empty, would normally throw exception.");
            return 0;
        }
        int value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public int peak() {
        if (top == null) {
            System.out.println("Stack Empty, would normally throw exception.");
            return 0;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public boolean isFull() {
        if (maxStackHeight == size){
            return true;
        } else {
            return false;
        }

    }

}
/* This is a data structure that has a Stack that operates like a Stack of
 * plates. At some point when stacking plates you do not want to stack higher
 * than x plates or there is risk of them toppling over.
 *
 * This Set of Stacks will work in the same way. It will store data in a Stack
 * until it reaches a certain limit, then it will add another Stack in the set
 * to store data in. The Set of Stacks will continue to grow in this way. 
 *
 * However the pop/push methods should still work the exact same way as a
 * normal Stack.
 */
class SetOfStacks {
    ArrayList<Stack> stacks;

    public UpgradedStack(){
        this.stacks = new ArrayList();
    }

    public void push(int value) {
    // We start out, there are no stacks, we create a new stack and add it to
    // the list.
        if (this.stacks.isEmpty()) {
            stacks.add(new Stack());
        }
        // Check if any existing stacks have room. Start at end of list
        // because that is where we add new stacks.
        for (stackNumber = stacks.size() - 1; stackNumber >= 0; stackNumber--) {
            if(!stacks.get(stackNumber).isFull()) {
                stacks.get(stackNumber).push(value);
                return;
            }         
        }
        //Handle case where all stacks are full
        stacks.add(new Stack());
        stacks.get(stacks.size()-1).push(value);

    }

    public int pop() {
        // Edge case where stack has no values added
        if (this.stacks.isEmpty()) {
            System.out.println("Stack Empty, would normally throw exception.");
            return Integer.MAX_VALUE;
        }
        // Pop an item off the stack last added that still has an item.
        for (stackNumber = stacks.size() - 1; stackNumber >= 0; stackNumber--) {
            if(!stacks.get(stackNumber).isEmpty()) {
                return stacks.get(stackNumber).pop;
            }         
        }
        System.out.println("All Stacks in list Empty, would normally throw exception.");
        return Integer.MAX_VALUE;
    }

    public int peak() {
        return stacks.get(stacks.size()-1).peak();
    }

    //If last stack is empty they must all be empty.
    public boolean isEmpty() {
        return stacks.get(0).isEmpty();
    }

}

public class StackMin {

    // My Implementation: 1) Create a Simple stack to track Mins
    //                    2) Create Expanded Stack which implements a min()
    //                       method to return the current min. Will use a 
    //                       stack of mins to track the current min.
    //
    // Assumptions: 
    //
    // Mistakes: When implementing a simple stack I didn't have the else 
    //           statement. causing the if block to execute and then the
    //           others creating an infinite loop.
    //
    //           Didn't actually need a min number to track here I could have
    //           used the Stack and just peaked at the top number at any time.
    //
    // Big O: O(1) for all operations, O(2n) for space due to min stack 


    public static UpgradedStack createUpgradedStack() {
        UpgradedStack myStack = new UpgradedStack();
        myStack.push(8);
        myStack.push(3);
        myStack.push(1);
        myStack.push(3);
        myStack.push(8);
        return myStack;
    }


    public static Stack createStack() {
        Stack myStack = new Stack();
        myStack.push(1);
        myStack.push(3);
        myStack.push(7);
        myStack.push(8);
        return myStack;
    }

    public static void printStack(Stack stack) {
        int itemNumber = 0;
        while(!stack.isEmpty()) {
            System.out.println("Stack: " + itemNumber + 
                    " Value: " + stack.pop());
            itemNumber++;
        }
    }

    public static void printUpgradedStack(UpgradedStack stack) {
        int itemNumber = 0;
        while(!stack.isEmpty()) {
            System.out.println("Item: " + itemNumber + " " + 
                "Current Min: "+ stack.min()  +" Value popped: " + stack.pop());
            itemNumber++;
        }
    }

    public static void main(String args[]) {
        System.out.println("Starting Program!");
        System.out.println("--------");

        Stack stack = createStack();
        System.out.println("The Simple Stack created was : ");
        printStack(stack);
        Stack stack2 = createStack();
        System.out.println("Peaking at the Simple Stack first item: ");
        System.out.println(stack2.peak());
        System.out.println("--------");
        UpgradedStack upgradedStack = createUpgradedStack();
        System.out.println("The Upgraded Stack created was : ");
        printUpgradedStack(upgradedStack);
    }
}
