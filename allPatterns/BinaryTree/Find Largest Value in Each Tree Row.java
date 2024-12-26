import java.util.*;

//Approach-1 (Using BFS)
//T.C : O(n) we visit each node once.
//S.C : O(n) taken by quque
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxEl = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                maxEl = Math.max(maxEl, node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(maxEl);
        }

        return result;
    }
}



//Approach-2 (Using DFS)
//T.C : O(n) We visit each node once
//S.C : O(n) Recursion stack space
public class Solution {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (depth == result.size()) {
            result.add(root.val);
        } else {
            result.set(depth, Math.max(result.get(depth), root.val));
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}