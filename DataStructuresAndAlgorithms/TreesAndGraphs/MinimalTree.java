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
        return String.valueOf(data);
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
        int [] array = new int[9];
        array[0] = 0;
        array[1] = 1;
        array[2] = 4;
        array[3] = 7;
        array[4] = 9;
        array[5] = 10;
        array[6] = 32;
        array[7] = 37;
        array[8] = 45;
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
     * Assumptions: 
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
     *           
     * Big O: 
     *         
     */
    static Node buildShortestBinaryTree(int [] arrayOfIntegers) {
        int middle = arrayOfIntegers.length / 2;
        Node root = new Node(middle);
        Node current = root;
        // Offset needed because event numbers will have one less node 
        // on the right than the left. 
        boolean evenArrayLength = false; 
        if (arrayOfintegers.length % 2 == 0 ) {
            evenArrayLength = true;
        } else {
            evenArrayLength = false;
        }
        // Iterate through the array starting in the middle
        // How do I make sure I get all of the things in the array without
        // trying to get something that is not in the size of the array? 
        boolean isLeft = true;
        for (int i = 1; i <= middle; i++) {
               current.left = new Node(arrayOfIntegers[middle - i]); 
               current.right = new Node([middle + i]); 

               current = current.left;
        }

        return new Node(0);
    }


    public static void main(String args[]) {
        System.out.println("Starting Program!");
        System.out.println("--------");
        System.out.println("The shortest Binary Tree is: ");
        buildShortestBinaryTree(createSortedArray());
        System.out.println("--------");
    }
}
