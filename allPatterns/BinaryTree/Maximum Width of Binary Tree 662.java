/*
    Company Tags                : VMWare, Flipkart, Amazon
    Leetcode Link               : https://leetcode.com/problems/maximum-width-of-binary-tree/
*/

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// Approach-1 (Using BFS)
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        //since value may overflow if we go on multiply by 2 so taking long
        Queue<Pair<TreeNode, Long>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0L));
        long maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            long first = queue.peek().getValue();
            long last = 0;
            
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Long> current = queue.poll();
                TreeNode node = current.getKey();
                long index = current.getValue();
                
                if (i == size - 1)
                    last = index;
                
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * index + 1));
                }
                
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * index + 2));
                }
            }
            maxWidth = Math.max(maxWidth, last - first + 1);
        }
        return (int) maxWidth;
    }
}

// Approach-2 : Using DFS
class SolutionDFS {
    private long maxWidth = 1;
    
    private void DFS(TreeNode root, long index, int level, List<Long> list) {
        if (root == null)
            return;
        
        if (level == list.size()) {
            list.add(index);
        } else {
            maxWidth = Math.max(maxWidth, index - list.get(level) + 1);
        }
        
        DFS(root.left, 2 * index + 1, level + 1, list);
        DFS(root.right, 2 * index + 2, level + 1, list);
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        
        List<Long> list = new ArrayList<>();
        DFS(root, 0, 0, list);
        return (int) maxWidth;
    }
}


// Approach-3 (Iterative BFS with correction)
class SolutionBFS {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        int ans = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            int mmin = q.peek().num; // Normalize the index
            int first = 0, last = 0;
            
            for (int i = 0; i < size; i++) {
                Pair current = q.poll();
                TreeNode node = current.node;
                int cur_id = current.num - mmin;
                
                if (i == 0) first = cur_id;
                if (i == size - 1) last = cur_id;
                
                if (node.left != null)
                    q.offer(new Pair(node.left, cur_id * 2 + 1));
                
                if (node.right != null)
                    q.offer(new Pair(node.right, cur_id * 2 + 2));
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }
}
