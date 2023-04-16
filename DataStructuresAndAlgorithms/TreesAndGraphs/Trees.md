# Trees

A tree is a Data Structure made up of Nodes.

Nodes look somehting like: 

```java
public Node {
    int value; // This could be a String, Integer, or any Object
    Node []; // These are the Nodes that are children of this node
}
```

Binary trees usually have two Nodes instead of the array since they always have
at most two childeren. Usually these are labled `Node left` and `Node right`.

## Binary Search Tress
Binary Search trees are Binary trees that are sorted in a way in which all
values in the left node, including all children of those nodes, are less than
the value in the parent node. And all values in the right node of each node and
its children are greater than the parent node. In this way each node is seen as
a parent node.

Example:

![Binary Search Tree](BinarySearchTree.png "Binary Search Tree")

### Binary Tree Traversal

There are three common ways of traversing a Binary Tree.

1. In-Order Traversal:

```java
void inOrderTraversal(Node node) {
    while(node!= null) {
        inOrderTraversal(node.left);
        access(node); // Note this is the operational step(printing, processing,
                      // etc.
        inOrderTraversal(node.right);
    }
}
```

2. Pre-Order Traversal:

```java
void preOrderTraversal(Node node) {
    while (node!= null) {
        access(node);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }
}
```

3. Post-Order Traversal:

```java
void postOrderTraversal(Node node) {
    while (node!= null) {
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        access(node);
    }
}
```

Because these are ordered Binary search trees we can see that the operational
step will take place in either the "order" or as you walk through the tree in
order. Or after you have already walked all the way through the tree.

## Binary Heaps
Binary Heaps are Binary trees (NOT Binary Search Tress) that are "Complete
Binary Trees". Complete Binary Trees are trees that have two nodes for every
node except for perhaps the last level of the tree. 

Example of Complete Binary Tree: 


![Complete Binary Tree](CompleteBinaryTree.png "Complete Binary Tree")

### Min/Max Binary Heaps
Each Node is Smaller than its children and the top most parent node is the
smallest in the tree. (Change min to Max for Max Heaps).


![Min Binary Heap](MinBinaryHeap.png "Min Binary Heap")

