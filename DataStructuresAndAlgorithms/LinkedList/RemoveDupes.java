
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

    // My Implementation: 1) Track Nodes seen before. 
    //                    2) Compare each node against the nodes seen before to 
    //                       see if we have a match.
    //                    3) If we do remove the node. 
    //
    // Assumptions: Singly Linked List is used. 
    // 
    // Mistakes: Watch your order when updating nodes in a Linked List.
    //           
    // Big O: O(n) with O(n) for memory 
    static public Node removeDupes(Node head) {
	Node current = head.next;
	Node previous = head;
	Node headOfDupeList = new Node(head.data);
	while(current != null) {
	    if (isDupe(current, headOfDupeList)) {
		previous.next = current.next; 
	    }
	    previous = current;
	    current = current.next;
	}
	return head;
    }

    // My Implementation: 1)   
    //                    2) 
    //                    3) 
    //
    // Assumptions: Singly Linked List is used. 
    // 
    // Mistakes: Don't get caught up on the Runner method think about what 
    //           the code will actually be doing. Here it is just comparing
    //           every node against each other.
    //           
    // Big O: O(n^2)
    static public Node removeDupesNoBuffer(Node head) {
	Node current = head;
	//Essentially we are just doing two loops through the list.
	while(current != null) {
	    Node runner = current;
	    while(runner.next != null){
		if(runner.next.data == current.data) {
		    runner.next = runner.next.next;
		} else {
		    runner = runner.next;
		}
	    }
	    current = current.next;
	}
	return head;
    }

    /* Function takes a node to be checked for being a dupe and 
     * the head of the list tracking potential dupes. If a dupe
     * is found returns true, if not it addes a new node on the
     * dupe list with the value and returns False
     *
     */
    public static boolean isDupe(Node nodeBeingChecked, Node headOfDupeList) {
	System.out.println("Current Dupe List is: ");
	printLinkedList(headOfDupeList);
	Node current = headOfDupeList;
	Node previous = null; 
	while(current != null) {
	    if(nodeBeingChecked.data == current.data) {
		return true;
	    }
	    previous = current;
	    current = current.next;	
	}
	previous.next = new Node(nodeBeingChecked.data);
	return false;
    }


    public static Node createLinkedList() {
	Node head = new Node(3);
	Node nodeTwo = new Node(2);
	head.next = nodeTwo;
	Node nodeThree  = new Node(2);
	nodeTwo.next = nodeThree;
	Node nodeFour = new Node(34);
	nodeThree.next = nodeFour;
	Node nodeFive = new Node(3);
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
	System.out.println("The Linked List before removing dupes with removeDupes is: ");
	printLinkedList(head);
	System.out.println("--------");
	removeDupes(head);
	System.out.println("The Linked List after removing dupes with removeDupes is: ");
	printLinkedList(head);
	System.out.println("The Linked List before removing dupes with removeDupesNoBuffer is: ");
	Node head2 = createLinkedList();
	printLinkedList(head2);
	System.out.println("The Linked List after removing dupes with removeDupesNoBuffer is: ");
	removeDupesNoBuffer(head2);
	printLinkedList(head2);
    }
}
