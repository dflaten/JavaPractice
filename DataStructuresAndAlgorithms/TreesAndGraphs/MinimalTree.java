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
     *                    2) Iterate through the Array taking the next lowest
     *                       number in the array and placing it on the left of
     *                       the root and the next highest number on the right
     *                       of the root.
     *                    3) Then go the right and left nodes for the root and
     *                       do the same, continuing until we get to the end
     *                       of the array.
     *                       
     * 
     * Assumptions: 
     * 
     * Mistakes: 
     *           
     *           
     * Big O: 
     *         
     */
    static Node buildShortestBinaryTree(int [] arrayOfIntegers) {
        return new Node(0);
    }


    public static void main(String args[]) {
        System.out.println("Starting Program!");
        System.out.println("--------");
        System.out.println("The shortest Binary Tree is: ");
        System.out.println("TO BE WRITTEN");
        System.out.println("--------");
    }
}
