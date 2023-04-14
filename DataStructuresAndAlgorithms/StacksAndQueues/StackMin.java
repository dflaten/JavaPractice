
/*
 * Problem: Stack Min: Implement a stack that in addition to the methods push()
 *                     pop(), peak(), and isEmpty() also has min() which 
 *                     returns the minimum element. 
 *
 * Assumptions: If the value being entered is equal to the minimum value in the
 *              list the min() function will return the late 
 *
 * Implementation: 
 *
 * Mistakes: I missed a very important edge case until I started creating the
 *           code for the problem. What if the value being pushed on the stack
 *           is equal to the minimum alread in the Stack?
 *           
 *           The book used a LinkedList instead of an Array for the base of
 *           the stack, this way we can add additional information to each 
 *           node in the stack, the minimum for every node below that node.
 *
 *           Alternatively and perhaps more cleanly we can just use another 
 *           Stack to track to the mins!
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

    public Stack(){
        this.top = null;
    }

    public void push(int value) {
        Node newTop = new Node(value);
        if (top == null) {
            top = newTop;
        } else {
            newTop.next = top;
            top = newTop;
        }
    }

    public int pop() {
        if (top == null) {
            System.out.println("Stack Empty, would normally throw exception.");
            return 0;
        }
        int value = top.data;
        top = top.next;
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

}
/* This is an upgraded version of a stack that keeps track of the minimum
 * value stored in the stack and can return it at any time. 
 */
class UpgradedStack {
    Node top = null;
    int min;
    Stack minStack;

    public UpgradedStack(){
        this.min = Integer.MAX_VALUE;
        this.minStack = new Stack();
        this.top = null;

    }

    public void push(int value) {
        if (min >= value) {
            min = value;
            minStack.push(value);
        }
        Node newTop = new Node(value);
        if (top == null) {
            top = newTop;
        } else {
            newTop.next = top;
            top = newTop;
        }
    }

    public int pop() {
        if (top == null) {
            System.out.println("Stack Empty, would normally throw exception.");
            return 0;
        }
        if (top.data == min) {
            minStack.pop();
            if (minStack.isEmpty()) {
            System.out.println("Stack Empty, would normally throw exception.");
                min = Integer.MAX_VALUE;
            } else {
                min = minStack.peak();
                System.out.println("New Min Value: " + min);
            }
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    public int peak(int stackNumber) {
        if (top == null) {
            System.out.println("Stack Empty, would normally throw exception.");
            return 0;
        } else{
            return top.data;
        }
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public int min() {
        return minStack.peak();
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
