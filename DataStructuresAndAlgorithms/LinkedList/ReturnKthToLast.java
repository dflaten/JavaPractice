
/*
 * Problem: Return Kth To Last: Implement an Algorithm to find the Kth to last element in a
 *                              singly linked list.
 *
 * 
 */
//Simple Node class used to create LinkedList
class Node {
    int data;
    Node next;

    public Node(int data) {
	this.data = data;
	this.next = null;
    }
}

public class ReturnKthToLast {

    // My Implementation: 1) Iterate through the Linked List counting how many are in the list. 
    //                    2) Iterate through the Linked List returning the Kth to last. 
    //
    // Assumptions: LinkedList will be always at least one node.
    //              Will return null if the k provided is greater than the length of the list.
    //              2nd to last is the item directly before the last item. There is no 
    //              "first to last".
    //
    // 
    // Mistakes: Missed assumption regarding the 1rst to last in my first pass at creating solution.
    //           Book solution actually didn't cover the solution I provided. They were expecting
    //           a recursive solution in which they used pointers or a wrapper class to pass back
    //           the node. Or they used two "pointers" where one pointer moved k nodes into the 
    //           list and then had another pointer at the start. They moved them forward until
    //           the 1rst pointer got to the end of the list and then returned the 2nd pointer.
    //           
    // Big O: O(n), we iterate through the list twice but still n in the end.
    static public Node returnKthToLast(Node head, int k) {
	Node current = head;
        int sizeOfList = findLengthOfLinkedList(head);	
	if (k > sizeOfList || k < 1) {
	    return null;
	}
        //Adding 1 since there is no 1rst to last.
        int nodeToReturnCount = sizeOfList - k + 1;
	System.out.println("Node number to return: " + nodeToReturnCount);
	int currentNodeCount = 1;
	while(current != null) {
	    if (currentNodeCount == nodeToReturnCount) {
		return current;
	    }
	    current = current.next;
	    currentNodeCount++;
	}
	//Should throw exception here 
	System.out.println("Unable to return valid result due to an invalid state.");
	return null;
    }

    /* Function takes the head of a linked list and returns the length of it. 
     */
    public static int findLengthOfLinkedList(Node head) {
	Node current = head;
	int sizeOfList = 0;
	while(current != null) {
	    current = current.next;	
	    sizeOfList++;
	}
	return sizeOfList;
    }


    public static Node createLinkedList() {
	Node head = new Node(3);
	Node nodeTwo = new Node(2);
	head.next = nodeTwo;
	Node nodeThree  = new Node(2);
	nodeTwo.next = nodeThree;
	Node nodeFour = new Node(34);
	nodeThree.next = nodeFour;
	Node nodeFive = new Node(9);
	nodeFour.next = nodeFive;
	return head;
    }

    public static void printLinkedList(Node head) {
	Node current = head;
	int nodeNumber = 1;

	while(current != null) {
	    System.out.println("Node: " + nodeNumber + " Value: " + current.data);
	    current = current.next;
	    nodeNumber++;
	}
    }

    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	Node head = createLinkedList();
	System.out.println("The Linked is: ");
	printLinkedList(head);
	Node kthToLast = returnKthToLast(head, 1);
	System.out.println("The 2 to last node value is: " + kthToLast.data);
	System.out.println("--------");
    }
}
