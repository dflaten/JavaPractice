
/*
 * Problem: Remove Dupes: Write a function to remove dupes from an unsorted Linked List. 
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

public class RemoveDupes {

    // My Implementation: 1) 
    //                    2)
    //                    3)
    //
    // Assumptions: Singly Linked List is used. 
    // 
    // Mistakes:   
    // Big O: 
    static public Node removeDupes(Node head) {

	return head;
    }


    public static Node createLinkedList() {
	Node head = new Node(3);
	Node nodeTwo = new Node(2);
	head.next = nodeTwo;
	Node nodeThree  = new Node(2);
	nodeTwo.next = nodeThree;
	Node nodeFour = new Node(34);
	nodeThree.next = nodeFour;
	return head;
    }
    public static void printLinkedList(Node head) {
	Node current = head;
	int nodeNumber = 1;

	while(current != null) {
	    System.out.println("Node: " + nodeNumber + "Value: " + current.data);
	    current = current.next;
	}
    }

    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	Node head = createLinkedList();
	System.out.println("The Linked List before removing dupes is: ");
	printLinkedList(head);
	removeDupes(head);
	System.out.println("The Linked List after removing dupes is: ");
	printLinkedList(head);
    }
}
