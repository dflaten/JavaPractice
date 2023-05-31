import java.util.*;

/*
 * Problem: Given the head of a signly linked list with n nodes and two
 * positive integers, left and right. Our task is to reverse the list's nodes
 * from position left to position right and return the reversed list.
 *
 * 
 */
//Simple Node class used to create LinkedList
class LinkedListNode {
    public int data;
    public LinkedListNode next;
    // Constructor will be used to make a LinkedListNode type object
    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ReversedLinkedListII {

    // My Implementation: 1) 
    //                    2) 
    //
    // Assumptions: 
    //
    // 
    // Mistakes: 
    //           
    // Big O: 
    public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
        int currentNode = 1;
        LinkedListNode current = head;
        LinkedListNode previous = null;
        LinkedListNode next = null;

        while (current != null) {
            if (currentNode == left) {   
                List<LinkedListNode> headAndTailOfReversedList = reverse(previous, current, right);
                if (previous != null) {
                    previous.next = headAndTailOfReversedList.get(0);
                }
                break;
            }
            previous = current;
            current = current.next;
            next = current.next;
            currentNode++;
        }
        return head;
    }

    /* Function takes the head of a linked list and returns the length of it. 
    */
    public static List<LinkedListNode> reverse(LinkedListNode beforeHead, LinkedListNode head, int right) {
        int counter = 0;
        LinkedListNode current = head;
        LinkedListNode previous = beforeHead;
        LinkedListNode next = head.next;

        while (current != null && counter < right) {
            next = current.next;
            previous = current;
            current = next;
            counter++;
        }
        //Return the head of the reversed list and the current(which is the next item in the list)
        List<LinkedListNode> resultList = Arrays.asList(previous, current);
        return resultList;
    }

    // Template for the linked list
    class LinkedList<T> {
        public LinkedListNode head;
        // constructor will be used to make a LinkedList type object
        public LinkedList() {
            this.head = null;
        }
        // insertNodeAtHead method will insert a LinkedListNode at head
        // of a linked list.
        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }
        // createLinkedList method will create the linked list using the
        // given integer array with the help of InsertAthead method.
        public void createLinkedList(int[] lst) {
            for (int i = lst.length - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst[i]);
                insertNodeAtHead(newNode);
            }
        }


    }
    public static class PrintList {
        public static void printListWithForwardArrow(LinkedListNode head) {
            LinkedListNode temp = head;

            while (temp != null) {
                System.out.print(temp.data); // print node value
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" → ");
                } else{
                    // if this is the last node, print null at the end
                    System.out.print(" → null \n ");
                }

            }

        }
    }

    public static void main(String args[]) {
        int[][] input = {
            {1, 2, 3, 4, 5, 6, 7},
            {6, 9, 3, 10, 7, 4, 6},
            {6, 9, 3, 4},
            {6, 2, 3, 6, 9},
            {6, 2}
        };
        int[] left = {1, 3, 2, 1, 1};
        int[] right = {5, 6, 4, 3, 2};
        for(int i=0; i<input.length; i++){
            System.out.print(i+1);
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.createLinkedList(input[i]);
            System.out.print(".\tOriginal linked list is:  ");
            PrintList.printListWithForwardArrow(list.head);
            System.out.print("\tReversed linked list is:  " );
            PrintList.printListWithForwardArrow(reverseBetween(list.head,left[i],right[i]));
            System.out.println("-------------------------------------");
        }
    }
}
