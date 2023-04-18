import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Problem: Route Between Nodes: Write a function that finds a route between
 *          two Nodes in a directed Graph. 
 */

// Simple Node class used to create Graph 
class Node extends Object {
    int data;
    public List<Node> children;

    public Node(int data) {
	this.data = data;
    children = new ArrayList();
    }
}

// Graph Class
class Graph extends Object{
    public List<Node> nodes;
    public Graph(){
        nodes = new ArrayList(); 
    }
}

public class RouteBetweenNodes {

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
        node0.children.add(node1);
        node0.children.add(node2);
	    return graph;
    }

    /* My Implementation: 1) 
     *                    2) 
     *                    3)
     * 
     * Assumptions: 
     * 
     * Mistakes: 
     *           
     * Big O: 
     */
    static public void findPathBetweenNodes(Node firstNode, Node secondNode) {
        return;
    }


    public static void main(String args[]) {
	System.out.println("Starting Program!");
	System.out.println("--------");
	Graph graph = createGraph();
	System.out.println("The Graph is: ");
    //Print Graph
	System.out.println("--------");
	System.out.println("The Path between Node 1: " + graph.nodes.get(4) + 
            " and Node 2: " + graph.nodes.get(8) + " is:");
    findPathBetweenNodes(graph.nodes.get(4), graph.nodes.get(8));
    }
}
