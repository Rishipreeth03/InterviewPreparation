/*
 * problem-link:https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */

 //Approach-1
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
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    // Time Complexity: O(N) | Space Complexity: O(H)
    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE}; // Use array to store reference
        maxpathdown(root, max);
        return max[0]; // Return the updated max value
    }

    public int maxpathdown(TreeNode root, int[] max) {
        if (root == null) return 0;
        
        int left = Math.max(0, maxpathdown(root.left, max));
        int right = Math.max(0, maxpathdown(root.right, max));

        max[0] = Math.max(max[0], root.val + left + right);

        return Math.max(left, right) + root.val;
    }
}

class SolutionAtomic {
    // Time Complexity: O(N) | Space Complexity: O(H)
    public int maxPathSum(TreeNode root) {
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE); // Use AtomicInteger for reference
        maxpathdown(root, max);
        return max.get(); // Return the updated max value
    }

    public int maxpathdown(TreeNode root, AtomicInteger max) {
        if (root == null) return 0;
        
        int left = Math.max(0, maxpathdown(root.left, max));
        int right = Math.max(0, maxpathdown(root.right, max));

        max.set(Math.max(max.get(), root.val + left + right));

        return Math.max(left, right) + root.val;
    }
}

/*
Reason why primitive int max does not work:
- Java is pass-by-value for primitive types, meaning changes inside the function do not reflect outside.
- When we update `max` inside `maxpathdown`, it modifies a local copy instead of the actual `max` variable.
- Using an array (`int[] max`) or `AtomicInteger` ensures `max` is updated globally.
*/
