
/*
 * Problem: Three Stacks One Array: Implement 3 stacks using one Array. 
 *
 */
//
class threeStacksOneArrayDS {
    int firstSpotStack One = 0;
    int finalSpotStackOne = 2;

    int firstSpotStackTwo = 3;
    int finalSpotStackTwo = 5;

    int firstSpotStackThree = 6;
    int finalSpotStackThree = 8;

    int [] data = int[9];

    public void push(int stackNumber, int value) {
        for (int i = firstSpotStackOne; i <= finalSpotStackOne; i++){
            if(data[i] = null) {
                data[i] = value;
            }
        }
        if (data[finalSpotStackOne]){
            expandArray(1);
        }
    }

    public int pop(int stackNumber) {
    }

    public int peak(int stackNumber) {
    }

    public boolean isEmpty(int stackNumber) {
    }
    private void expandArray(int stackBeingExpanded) {
        int newArraySize = determineNewStackSize(stackBeingExpanded); 
        int newDataArray [] = int[newArraySize];

        if (stackBeingExpanded == 1){
            int newFinalSpotStackOne;
            int newFinalSpotStackTwo;
            int newFinalSpotStackThree;
            for (int a = firstSpotStackOne; a <= finalSpotStackOne; a++){
                newDataArray[a] = dataArray[a];
            }
        }
    }

    private int determineNewStackSize(int stackBeingExpanded) {
        int currentSizeOfStackOne = finalSpotStackOne - firstSpotStackOne + 1; 
        int currentSizeOfStackTwo = finalSpotStackTwo - firstSpotStackTwo + 1; 
        int currentSizeOfStackThree = finalSpotStackThree - firstSpotStackThree + 1; 
        int newArraySize = 0;
        if (stackBeingExpanded == 1) {
        newArraySize = (currentSizeOfStackOne * 2) + currentSizeOfStackTwo + currentSizeOfStackThree;
        } else if (stackBeingExpanded == 2) {
        newArraySize = currentSizeOfStackOne  + (currentSizeOfStackTwo *2) + currentSizeOfStackThree;
        } else if (stackBeingExpanded == 3) {
        newArraySize = currentSizeOfStackOne + currentSizeOfStackTwo + (currentSizeOfStackThree * 2);
        }
        return newArraySize;
    }
}

public class ThreeStacksOneArray {

    // My Implementation: 1) Split the Array into 3 equal chunks. I'm starting
    //                       with a size of 9 but will create a new array and
    //                       replace if one chunk gets full, allowing for
    //                       double the space for the new stack and the same
    //                       for the existing.
    //                    2) Create Push, Pop, Peak, isEmpty methods. Each of 
    //                       these methods will have an extra parameter, an int
    //                       which will denote which stack is being operated
    //                       upon.
    //                    3) Push will have a check at the end to determine if
    //                       we just filled up the stack. If we did we will
    //                       create a new array and copy over existing elements
    //                       to make room.
    //
    // Assumptions: We do not care that the array may end up with extra space
    //              allocated to it if one of the sub stacks fills up and is
    //              then emptied.
    //              Do not need to consider null inputs for either value.
    //
    // 
    // Mistakes: At first I focused on making this very Java like and though
    //           about using a Class and super class to create the data
    //           structure but then realized that would be overkill for this
    //           problem. 
    // Disclaimer: This is left unfinished because it is much to complex for
    //             an interview and would require a few hours to implement. 
    //             I'm leaving it here as it is a good base and I may come back
    //             to do it some day for practice.
    //        
    // Big O: 
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
