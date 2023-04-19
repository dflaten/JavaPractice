import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

/*
 * Problem: Route Between Nodes: Write a function that finds a route between
 *          two Nodes in a directed Graph. 
 */

// Simple Node class used to create Graph 
class Node {
    int data;
    boolean marked;
    public List<Node> children;

    public Node(int data) {
	this.data = data;
    children = new ArrayList();
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

// Graph Class
class Graph {
    public List<Node> nodes;
    public Graph(){
        nodes = new ArrayList(); 
    }

    @Override
    public String toString(){
        String string = "";
        for (Node node : nodes) {
            string = string + node; 
        }
        return string;
    }
}

public class RouteBetweenNodes {

    /* See RouteBetweenNodes for an image of this graph. 
     */
    static public Graph createGraph() {
        Graph graph = new Graph();
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node0.children.add(node1);
        node0.children.add(node2);

        node1.children.add(node5);
        node1.children.add(node4);

        node2.children.add(node7);

        node4.children.add(node6);

        node5.children.add(node3);
        node5.children.add(node6);

        node6.children.add(node7);

        node7.children.add(node8);
        node8.children.add(node9);
        node9.children.add(node2);

        graph.nodes.add(node0);
        graph.nodes.add(node1);
        graph.nodes.add(node2);
        graph.nodes.add(node3);
        graph.nodes.add(node4);
        graph.nodes.add(node5);
        graph.nodes.add(node6);
        graph.nodes.add(node7);
        graph.nodes.add(node8);
        graph.nodes.add(node9);
	    return graph;
    }

    /* My Implementation: 1) Brute force check every route from firstNode.
     *                    2) If you get to second node there is a route! If
     *                       not there is no route.
     * 
     * Assumptions: We are just returning true or false in the function.
     *              There may not always be a path.
     * 
     * Mistakes: I started coding a recursive version of breadth-first search
     *           before realizing what I was doing. This was incorrect.
     *           
     *           Also I could have used depth first search instead of breadth
     *           and impelemented it recursively. It would have been simpler.
     *           
     * Big O: O(n) which is the longest route in the graph from the node being
     *        checked. 
     */
    static public boolean findPathBetweenNodes(Node firstNode, Node secondNode) {
        // The queue here is the list of nodes that still need to be checked.
        // We add/remove them to the queue as we vist them.
        LinkedList<Node> queue = new LinkedList();
        firstNode.marked = true;
        queue.add(firstNode);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            // If we have reached the second node there is a route.
            if (node.equals(secondNode)) {
                return true;
            }
            for (Node child: node.children) {
                if (child.marked == false) {
                    child.marked = true;
                    queue.add(child);
                }
            }
        }
        // Checked all paths leading from node 1 and there is no route.
        return false;
    }


    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	Graph graph = createGraph();
	System.out.println("The Graph nodes are: ");
    System.out.println(graph);
	System.out.println("--------");
	System.out.println("There is a Path between Node 1: " + graph.nodes.get(3) + 
            " and Node 2: " + graph.nodes.get(8) + " : " + 
            findPathBetweenNodes(graph.nodes.get(3), graph.nodes.get(8)));
	System.out.println("There is a Path between Node 1: " + graph.nodes.get(5) + 
            " and Node 2: " + graph.nodes.get(2) + " : " + 
            findPathBetweenNodes(graph.nodes.get(5), graph.nodes.get(2)));
    }
}
