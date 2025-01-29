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
/* 
 * 
Time Complexity: O(n)
Space Complexity: O(n), including the hash map and recursion stack.
*/
class Solution {
    // Builds the binary tree from inorder and postorder traversal arrays
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Create a map to store the index of each value in the inorder array for quick access
        Map<Integer, Integer> inordermap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inordermap.put(inorder[i], i); // Map value to its index
        }
        // Call the helper function to construct the tree
        return splitTree(postorder, inordermap, postorder.length - 1, 0, inorder.length - 1);
    }

    /**
     * Helper function to recursively build the binary tree.
     * @param postorder The postorder traversal array.
     * @param inordermap A map of inorder values to their indices.
     * @param rootIndex The index of the current root in the postorder array.
     * @param left The start index of the current subtree in the inorder array.
     * @param right The end index of the current subtree in the inorder array.
     * @return The constructed TreeNode representing the root of the subtree.
     */
    public TreeNode splitTree(int[] postorder, Map<Integer, Integer> inordermap, int rootIndex, int left, int right) {
        // Base case: if the current subtree is invalid, return null
        if (left > right) return null;

        // Create the root node with the value at rootIndex in postorder
        TreeNode root = new TreeNode(postorder[rootIndex]);

        // Find the index of the root value in the inorder array
        int mid = inordermap.get(postorder[rootIndex]);

        // Recursively build the left subtree
        // The left subtree size is (mid - left), adjust rootIndex accordingly
        root.left = splitTree(postorder, inordermap, rootIndex - (right - mid) - 1, left, mid - 1);

        // Recursively build the right subtree
        // The next root in postorder is at rootIndex - 1
        root.right = splitTree(postorder, inordermap, rootIndex - 1, mid + 1, right);

        return root; // Return the constructed subtree
    }
}
