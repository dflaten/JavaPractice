# Tree Depth-first Search

A tree is a graph that contains the following properties: 
* It is undirected
* It is acyclic (no cycles)
* It is a connected graph where any two vertices are connected by exactly one
  path.

**Tree depth-first search** is a method to reduce the use of nested loops in
our tree related problems. Depth-first search in a tree is usually implented
recursively. You can use a stack to do it iteratively as well.

This type of search takes a path as far as possible along each tree branch 
before exploring the others.  

Just like other depth-first search patterns there are three methods: 

* Preorder
* InOrder
* Postorder

## Preorder Traversal
1. Visit the node.
1. Recursively perform preorder traversal on the left child.
1. Recursively preform preorder traversal on the right side.

## In Order Traversal
1. Recursively perform preorder traversal on the left child.
1. Visit the node.
1. Recursively preform preorder traversal on the right side.

Because you are visiting the smaller nodes first this type of traversal is good
if you are looking for an item that is smaller than the root in a binary search
tree.

## Post Order Traversal
1. Recursively perform preorder traversal on the left child.
1. Recursively preform preorder traversal on the right side.
1. Visit the node.

This type of traversal is good if we are deleting a tree. Because we are going
to each node's children before visting the node to delete it we don't leave any
stranglers or leaves of tree nodes undeleted.

## Does my problem match this pattern?
1. If we have reason to believe the solution is near the leaves of the tree.
2. If componenets of the solution are listed along paths from the root to the
   leaves and finding the optimal solution requires traversal along these
   paths. Example: Finding the height of a given tree.
3. Will not work if the input data is not in a tree or the cost of putting the
   data into a tree will be too high.
4. Will also not work if you need to traverse the tree one level at a time.
   
## Real-world Examples
1. **Find products in a price range**: Convert the prices of all products into
   a binary search tree and perform a variation of preorder traversal on the
   tree. Starting at the root we check if the value lies in the range, if it
   does we add it to the output array. Then we check if the value is >= to the
   lower bound. If it is we call preorder traversal on the right child of the
   node. If the node's value is <= to the upper bound we also call preorder
   traversal on the right side of the node. We continue this until the tree has
   been traversed.
2. **Finding routes**: Used to find a route between a start and destination
   point. 
3. **Solving Mazes**: Used to find the path out of the maze. We select a path
   and follow it until we hit a dead end. If we do we backtrack and take an
   alternative path from the past junction and try the new path. We repeat this
   process until the destination is reached.

## Examples

### Find lowest common ancestor (LCA) of two nodes

Given a binary search tree (BST), find the lowest common ancerstor (LCA) of two
nodes. 

According to wikipedia: "The lowest common ancestor is defined between two
nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as
descendants (where we allow a node to be a descendant of itself."

#### Solution
1. Use DFS to traverse the tree using preorder traversal. 
2. If `p` or `q` is == to the current node then this node is the LCA. 
3. If `p` is < current and `q` > current node (or vice-versa) then current node 
   is LCA. 
4. If both `p` and `q` are less than the current node then move to the left and 
   continue searching. If they are both greater move to the right and continue
   searching.


```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCA(root, p, q);
    }

    public TreeNode findLCA(TreeNode current, TreeNode p, TreeNode q) {
        if (current.val == p.val || current.val == q.val) {
            return current;
        }
        if (current.val < p.val && current.val > q.val) {
            return current;
        }
        if (current.val > p.val && current.val < q.val) {
            return current;
        }
        if (current.val < p.val && current.val < q.val) {
            return findLCA(current.right, p, q);
        }
        if (current.val > p.val && current.val > q.val) {
            return findLCA(current.left, p, q);
        }
        // Never used.
        return current;
    }
}
```

