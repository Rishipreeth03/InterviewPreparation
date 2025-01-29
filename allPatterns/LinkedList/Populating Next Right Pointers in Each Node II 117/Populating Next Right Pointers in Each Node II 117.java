//Approach-1
/**
 * Time Complexity: O(n)
 * - Each node in the tree is visited exactly once as we traverse each level of the tree.
 * - The operations performed per node (checking children and updating pointers) take O(1) time.
 * - Therefore, the total time complexity is O(n), where n is the number of nodes in the tree.
 *
 * Space Complexity: O(1)
 * - The algorithm uses constant space as it does not rely on any auxiliary data structures (like queues or stacks).
 * - The dummy node and pointer variables (e.g., `prev`, `current`, `temp`) are reused for each level.
 * - Since the traversal is done in-place using the `next` pointers, the space complexity is O(1).
 */
class Solution {
    public Node connect(Node root) {
        // If the tree is empty, return null
        if (root == null) return null;

        Node current = root; // Start processing from the root node
        
        // Outer loop: Traverse level by level
        while (current != null) {
            Node dummy = new Node(0); // Dummy node to act as the head of the next level
            Node prev = dummy;       // Pointer to connect nodes in the next level
            
            // Inner loop: Process all nodes in the current level
            Node temp = current;
            while (temp != null) {
                // If the current node has a left child, connect it to the 'prev' pointer
                if (temp.left != null) {
                    prev.next = temp.left; // Link the previous node to the current node's left child
                    prev = prev.next;      // Move the 'prev' pointer to the newly added node
                }

                // If the current node has a right child, connect it to the 'prev' pointer
                if (temp.right != null) {
                    prev.next = temp.right; // Link the previous node to the current node's right child
                    prev = prev.next;       // Move the 'prev' pointer to the newly added node
                }

                // Move to the next node in the current level using the 'next' pointer
                temp = temp.next;
            }

            // After processing the current level, move to the next level
            // The dummy node's 'next' points to the first node in the next level
            current = dummy.next;
        }

        // Return the root node with all 'next' pointers correctly connected
        return root;
    }
}

//Approach-2(using queue)
/*
 * Time Complexity (O(n)):
Every node in the tree is processed exactly once, so the time complexity is proportional to the number of nodes.
Space Complexity (O(n)):
The queue stores nodes at each level of the tree, and in the worst case (when the tree is a perfect binary tree), 
the queue can store up to n/2 nodes at the last level, leading to a space complexity of O(n).
 */
class Solution {
    public Node connect(Node root) {
        // If the tree is empty, return null
        if (root == null) return null;

        // Initialize a queue to perform level-order traversal
        Queue<Node> queue = new LinkedList<>();
        // Start by adding the root node to the queue
        queue.offer(root);

        // Traverse each level of the tree
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int levelSize = queue.size();
            // A pointer to keep track of the previous node at the same level
            Node prev = null;

            // Process each node at the current level
            for (int i = 0; i < levelSize; i++) {
                // Dequeue the next node to process
                Node currentNode = queue.poll();

                // If there is a previous node, link it to the current node's next pointer
                if (prev != null) {
                    prev.next = currentNode;
                }

                // Move the 'prev' pointer forward to the current node
                prev = currentNode;

                // Add the left child of the current node to the queue, if it exists
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // Add the right child of the current node to the queue, if it exists
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        // Return the root of the tree with updated 'next' pointers
        return root;
    }
}

