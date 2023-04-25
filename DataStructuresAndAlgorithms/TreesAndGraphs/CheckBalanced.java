import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.lang.Math;

/*
 * Problem: Given a Binary Tree, design an algorithim which determines if the
 *          Binary tree is balanced. For this problem a balanced tree is one
 *          where the heights of any sub tree never differ by more than 1.
 */

// Simple Node class used to create Tree 
class Node {
    int data;
    boolean marked;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.marked = false;
    }

    @Override
    public String toString(){
        return "|Value: " + String.valueOf(data) + " Right: " + String.valueOf(this.right.data) + " |";
    }

    @Override
    public boolean equals(Object o) {
        if (o==this) {
            return true;
        }

        if (!(o instanceof Node)) {
            return false;
        }
        Node a = (Node) o;
        return Integer.compare(this.data, a.data) == 0;
    }

}


public class CheckBalanced {
    public static int [] createSortedArray() {
        int [] array = new int[20];
        array[0] = 0;
        array[1] = 1;
        array[2] = 4;
        array[3] = 7;

        array[4] = 9;

        array[5] = 10;
        array[6] = 32;
        array[7] = 35;
        array[8] = 39;

        array[9] = 42;

        array[10] = 46;
        array[11] = 47;
        array[12] = 48;
        array[13] = 58;

        array[14] = 59;

        array[15] = 64;
        array[16] = 76;
        array[16] = 77;
        array[16] = 78;
        array[16] = 79;
        array[16] = 80;
        array[17] = 99;
        array[18] = 113;
        array[19] = 119;
        return array;
    }

    public static void printBinaryTree(Node node) {
        System.out.println("TO BE IMPLEMENTED");
    }

    static Node buildShortestBinaryTree(int[] arrayOfIntegers) {
        return createShortestBinaryTree(arrayOfIntegers, 0, 
                arrayOfIntegers.length - 1);
    }

    static Node createShortestBinaryTree(int arrayOfIntegers[] , int start, 
            int end) {
        // Final Case
        if (end < start) {
            return null;
        }
        //create ShortestBinaryTree for each side
        //Create Node for the middle of the tree (or sub tree passed in)
        int mid = (start + end) / 2;
        Node middle = new Node(arrayOfIntegers[mid]);
        //Use the left half of the array to create the left tree
        middle.left = createShortestBinaryTree(arrayOfIntegers, start, mid - 1);
        //Use the right half of the array to create the right tree
        middle.right = createShortestBinaryTree(arrayOfIntegers, mid + 1, end);
        return middle;
    }
    // The Height of any Subtree in the binary tree never differ by more than
    // one.
    //
    /* My Implementation: 1) Start at root, look at paths from left and right
     *                       and Find the longest path from each. If they
     *                       differ by more than one return false. If they do
     *                       not look at the sub trees for each of their
     *                       children, find the longest path from each and if
     *                       they differ by more than one return false. 
     *
     *                    2) Continue until you run out of nodes.
     *                       
     * 
     * Assumptions: Always a tree of at least height 2 passed into method.
     * 
     * Mistakes:        * 
     *
     * Big O: 
     *         
     */
    static boolean checkIfTreeBalanced(Node root) {
        return checkIfChildrenBalanced(root);
    }
    

    static boolean checkIfChildrenBalanced(Node root) {
        //Base Case
        if (root == null) {
            return true;
        }
        
        int heightDiff = determineHeight(root.left) - 
            determineHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return checkIfChildrenBalanced(root.left) && checkIfChildrenBalanced(root.right);
        }
    }


    // This works because every time you are making a recursive call you are 
    // adding one to the result. You are also making sure you get the max
    // number from each side to get the actual largest height.
    static int determineHeight(Node node) {
       if (node == null) {
           return -1;
       }
       return Math.max(determineHeight(node.left), 
               determineHeight(node.right)) + 1;
    }



    public static void main(String args[]) {
        System.out.println("Starting Program!");
        System.out.println("--------");
        Node root = buildShortestBinaryTree(createSortedArray());
        System.out.println("The Root node for the Binary Tree is: ");
        System.out.println(root);
        System.out.println("--------");
        System.out.println("The tree is balanced: ");
        System.out.println(checkIfTreeBalanced(root));
    }
}
