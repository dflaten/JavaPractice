import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

/*
 * Problem: Given a sorted (increasing order) array with unique Integer
 *          elements, write an algorithm to create a binary search tree with 
 *          minimal height.
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


public class MinimalTree {
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
        array[17] = 99;
        array[18] = 113;
        array[19] = 119;
        return array;
    }

    public static void printBinaryTree(Node node) {
        System.out.println("TO BE IMPLEMENTED");
    }

    /* My Implementation: 1) Find the mid point and use that as the root for my
     *                       tree.
     *                    2) We start building the tree first from the left.
     *                       We take not the next lowest but the one after that
     *                       and make it the left node of the root. Its right 
     *                       node will be the number between the root and the
     *                       root.left number. Repeat this pattern until we 
     *                       get to the start of the array. 
     *                       
     *                    3) Do the same thing for the right side (except items
     *                       are  greater. until we get to the end of the
     *                       Array. Done!
     *                       
     * 
     * Assumptions: There will be at least one item in the list. 
     * 
     * Mistakes: At first I started by finding the midpoint and iterrating
     *          through the array putting the lowest highest as children on 
     *          the left and then right. This obviously wouldn't result in 
     *          a balanced Binary Tree though because there would be elements
     *          to the left of the root greater than the right.
     *
     *          In the future I need to create a picture of my end state and 
     *          work through my algorithm there before starting on
     *          implementation.
     *
     *          The second attempt I tried to build the array by thinking of
     *          the values from the middle, starting from the left and then
     *          building the left side first then building the right. However
     *          that naturally got me a try that was weighed heavily to the
     *          left on the left and heavily to the right on the right. 
     *           
     *           
     * Big O: Log(n) operation 
     *         
     */
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


    public static void main(String args[]) {
        System.out.println("Starting Program!");
        System.out.println("--------");
        System.out.println("The Root node for the Binary Tree is: ");
        Node root = buildShortestBinaryTree(createSortedArray());
        Node left = root.left; 
        Node leftleft = root.left.left; 
        Node leftright = root.left.right; 
        Node print = root.left.left.right;
        System.out.println(root);
        System.out.println("--------");
        System.out.println("Left: ");
        System.out.println(left);
        System.out.println("Left, left: ");
        System.out.println(leftleft);
        System.out.println("Left, right: ");
        System.out.println(leftright);
        System.out.println(print);
    }
}
