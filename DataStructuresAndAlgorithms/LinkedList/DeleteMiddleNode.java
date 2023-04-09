
/*
 * Problem: Delete Middle Node: Implement an alogirthm to delete a node in the
 *                              middle. IE any node but the First or Last node
 *                              of a singly linked list having been given only
 *                              that node.
 *
 * Example: c is input from the LL: a->b->c->d->e->f
 *                       
 *          Return: nothing, but the linked list now looks like: 
 *                                  a->b->d->e->f
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

public class DeleteMiddleNode {

    // My Implementation: 1) Do edge case checks. Do I need to make sure this
    //                       isn't the first node? If so how?
    //                    2) Pass the node into a recursive call 
    //                    3) Replace current.next with next.next
    //                    4) Pass next in.
    //
    // Assumptions: 
    // 
    // Mistakes: I over complicated this. Did not need a recursive call to move
    //           all of the elements. Everything was already in order. Just
    //           replace the one you are deleting!
    //        
    // Big O: O(1) 
    static public void deleteMiddleNode(Node nodeToBeRemoved) {
        if (nodeToBeRemoved == null) {
            return;
        }
        moveNodeLeft(nodeToBeRemoved);
    }


    /* Recursive function to move nodes left.
     *
     */
    public static void moveNodeLeft(Node current) {
        System.out.println("Node moving left: " + current.data);
        if (current.next == null) {
            return;
        }
        Node next = current.next;
        current.data = current.next.data;
        current.next = current.next.next;
        return;
    }


    public static Node createLinkedList() {
	Node head = new Node(3);
	Node nodeTwo = new Node(4);
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
	System.out.println("The Linked List before removing 2nd node was: ");
	printLinkedList(head);
	System.out.println("--------");
	deleteMiddleNode(head.next);
	System.out.println("The Linked List after removing 2nd node is: ");
	printLinkedList(head);

    Node head2 = createLinkedList();
	System.out.println("The Linked List before removing 4th node was: ");
	printLinkedList(head2);
	System.out.println("--------");
	deleteMiddleNode(head2.next.next.next);
	System.out.println("The Linked List after removing 4th node is: ");
	printLinkedList(head2);

	Node head3 = createLinkedList();
	System.out.println("The Linked List before removing 3rd node was: ");
	printLinkedList(head3);
	System.out.println("--------");
	deleteMiddleNode(head3.next.next);
	System.out.println("The Linked List after removing 3rd node is: ");
	printLinkedList(head3);
    }
}
