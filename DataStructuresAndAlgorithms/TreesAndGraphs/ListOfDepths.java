import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

/*
 * Problem: Given a Binary Tree, design an algorithim which creates a Linked 
 *          List of all the nodes at each depth. (eg. if you have a tree with 
 *          a depth of d, you have d linked lists.
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


public class ListOfDepths {
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

    /* My Implementation: 1) Do a Depth First Search of the Tree, accessing
     *                       each element and adding each to a LinkedList, one 
     *                       for each layer. (How do you know if you are at a
     *                       new layer?) A: you keep track and pass it in.
     *                    2) 
     *                       
     *                    3) 
     *                       
     * 
     * Assumptions: 
     * 
     * Mistakes:   I need to watch the little details of the program to make
     *             sure I add/substract where possible and use the simplest 
     *             forms of comparison. For example I was looking at adding a 
     *             new list when the size did not equal the layer - 1 but 
     *             could have just left the comparison at size() and layer. 
     *
     *             I needed to look up depthFirstSearch implementation for this
     *             problem and I should just have depthFirstSeearch and
     *             breadthFirstSearch memorized.
     *
     * Big O: O(n) where n is the number of nodes in the tree 
     *         
     */
    static ArrayList<LinkedList<Node>> createLinkedListOfEachDepth(Node root) {
        // Go deep into a tree creating a new linked list for every new layer
        // adding it to the arrayList (layer 1 goes in arr[0], layer 2 in
        // arr[1]...
        ArrayList<LinkedList<Node>> listOfLinkedLists = new ArrayList();

        depthFirstSearch(root, 0, listOfLinkedLists);
        
        return listOfLinkedLists;


    }

    static void depthFirstSearch(Node node, int layer, ArrayList<LinkedList<Node>> listOfLinkedLists) {
        if (node == null) {
            return;
        }

        if (listOfLinkedLists.size() ==  layer) {
            
            LinkedList<Node> newLinkedList = new LinkedList<>();
            newLinkedList.add(node);
            listOfLinkedLists.add(newLinkedList);
        } else {
            listOfLinkedLists.get(layer).add(node);
        }

        depthFirstSearch(node.left, layer + 1, listOfLinkedLists);
        depthFirstSearch(node.right, layer + 1, listOfLinkedLists);
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


    public static void main(String args[]) {
        System.out.println("Starting Program!");
        System.out.println("--------");
        Node root = buildShortestBinaryTree(createSortedArray());
        ArrayList<LinkedList<Node>> result = createLinkedListOfEachDepth(root);
        System.out.println("The Root node for the Binary Tree is: ");
        System.out.println(root);
        System.out.println("--------");
        System.out.println("The Number of linked lists is: ");
        System.out.println(result.size());
    }
}
