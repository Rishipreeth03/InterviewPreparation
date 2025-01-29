import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// TC:O(n) Sc:O(n)
class Solution {
    List<TreeNode> list=new ArrayList<>();
    public void flatten(TreeNode root) {
        if(root==null )return ;
        //does preOrder Traversal
        recur(root);
        TreeNode current=root;
        //Flattens binaryTree
        for(int i=1;i<list.size();i++){
            TreeNode nn=(list.get(i));
            current.right=nn;
             current.left=null;
            current=current.right;
        }
    }
    public void recur(TreeNode root){
        if(root==null) return;
        list.add(root);
        recur(root.left);
        recur(root.right); 
    }
}

//Approach-2
/*
 * Time Complexity: O(n)
Space Complexity: O(h), where h is the height of the tree.
 */
class Solution {
    // Public method to flatten a binary tree into a linked list
    public void flatten(TreeNode root) {
        build(root); // Start the recursive flattening process
    }

    // Recursive helper method to flatten the tree
    public void build(TreeNode root) {
        // Base case: if the current node is null, return
        if (root == null) return;

        // Store the left and right subtrees
        TreeNode left = root.left;
        TreeNode right = root.right;

        // Move the left subtree to the right
        root.right = left;
        root.left = null; // Set the left pointer to null to flatten

        // Recursively flatten the left subtree
        build(left);

        // Move to the end of the newly added right subtree
        while (root.right != null) {
            root = root.right;
        }

        // Attach the original right subtree at the end of the current right subtree
        root.right = right;

        // Recursively flatten the original right subtree
        build(right);
    }
}
