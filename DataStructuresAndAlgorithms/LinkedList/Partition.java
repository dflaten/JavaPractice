
/*
 * Problem: Partition: Write code to partition a linked list arround a value x
 *                     such that all nodes less than x are to the left of x and
 *                     all nodes greater than x are to the right of x. x can
 *                     appear anywhere in the right partition. 
 *
 * Example: Input: 3->5->8->5->10->2->1, and x = 5 
 *          Output: 3->1->2     ->     10->5->5->8
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

public class Partition {

    // My Implementation: 1) Make a left list and a right list.
    //                    2) Iterate through linked list.  
    //                    3) Add nodes less than x to left, and nodes greater
    //                       than or equal to x to right.
    //                    4) Return list after iterating through entire list. 
    //
    // Assumptions: You want to return a Linked list, paritioned by x.
    //              Do not need to consider null inputs for either value.
    //              Do not need to consider case where only one value is in
    //              the list or when there aren't enough nodes to partion 
    //              a left and  a right.
    //
    // 
    // Mistakes: Event if there wasn't enough we should have still assumed
    //           the case of handling a 1 node list on each was handled.
    //
    //           I am thinking about the nodes as objects instead of pointers.
    //           The End can be the same as head. Both are just a reference
    //           not the actual object.
    //
    //           
    //           
    //        
    // Big O: O(n) 
    static public Node partition(Node head, int partitionValue) {
        Node leftListHead = null;
        Node leftListEnd = null;
        Node rightListHead = null;
        Node rightListEnd = null;

        Node current = head;

        while(current != null) {
            Node next = current.next;
            if(current.data < partitionValue) {
                if (leftListHead == null) {
                    leftListHead = current;
                    leftListEnd = leftListHead;
                }
                 else {
                    leftListEnd.next = current;
                    leftListEnd = current;
                }
            }
            else {
                if (rightListHead == null) {
                    rightListHead = current;
                    rightListEnd = rightListHead;
                }
                else{
                    rightListEnd.next = current;
                    rightListEnd = current;
                }
            }
            current = next;
        }
        if (leftListEnd != null ) {
            leftListEnd.next = rightListHead;
            rightListEnd.next = null;
            return leftListHead;
        } else {
            leftListHead.next = rightListHead;
            rightListEnd.next = null;
            return leftListHead;
        }

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
        System.out.println("The Linked List before partitioning with 4 was: ");
        printLinkedList(head);
        System.out.println("--------");
        System.out.println("The Linked List after partitioning with 4 is: ");
        Node partitionedHead = partition(head, 4);
        printLinkedList(partitionedHead);

    }
}
