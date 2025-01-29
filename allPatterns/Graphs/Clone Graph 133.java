//Approach-1 (DFS) Using ArrayList as a map
/*
Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
Space Complexity: O(V), for the visited list and recursion stack.
*/
import java.util.*;
class Solution1 {
    public void DFS(Node node, Node cloneNode, List<Node> visited) {
        visited.set(node.val, cloneNode);
        
        for (Node neighbor : node.neighbors) {
            if (visited.get(neighbor.val) == null) {
                Node clone = new Node(neighbor.val);
                cloneNode.neighbors.add(clone);
                DFS(neighbor, clone, visited);
            } else {
                cloneNode.neighbors.add(visited.get(neighbor.val));
            }
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Node cloneNode = new Node(node.val);
        List<Node> visited = new ArrayList<>(Collections.nCopies(101, null));
        visited.set(node.val, cloneNode);
        
        DFS(node, cloneNode, visited);
        
        return cloneNode;
    }
}

//Approach-2 (DFS) Using HashMap
/*
Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
Space Complexity: O(V), for the HashMap and recursion stack.
*/

class Solution2 {
    private Map<Node, Node> map = new HashMap<>();

    public void DFS(Node node, Node cloneNode) {
        for (Node neighbor : node.neighbors) {
            if (!map.containsKey(neighbor)) {
                Node clone = new Node(neighbor.val);
                map.put(neighbor, clone);
                cloneNode.neighbors.add(clone);
                DFS(neighbor, clone);
            } else {
                cloneNode.neighbors.add(map.get(neighbor));
            }
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        map.clear();
        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);
        DFS(node, cloneNode);
        
        return cloneNode;
    }
}

//Approach-3 (BFS) Using ArrayList as a map
/*
Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
Space Complexity: O(V), for the visited list and queue.
*/
import java.util.*;

class Solution3 {
    public void BFS(Queue<Node> queue, List<Node> visited) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node neighbor : node.neighbors) {
                if (visited.get(neighbor.val) == null) {
                    Node clone = new Node(neighbor.val);
                    visited.get(node.val).neighbors.add(clone);
                    visited.set(neighbor.val, clone);
                    queue.add(neighbor);
                } else {
                    visited.get(node.val).neighbors.add(visited.get(neighbor.val));
                }
            }
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Node cloneNode = new Node(node.val);
        List<Node> visited = new ArrayList<>(Collections.nCopies(101, null));
        visited.set(node.val, cloneNode);
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        BFS(queue, visited);
        
        return cloneNode;
    }
}

//Approach-4 (BFS) Using HashMap
/*
Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
Space Complexity: O(V), for the HashMap and queue.
*/
import java.util.*;

class Solution4 {
    private Map<Node, Node> map = new HashMap<>();

    public void BFS(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Node cloneNode = map.get(node);
            
            for (Node neighbor : node.neighbors) {
                if (!map.containsKey(neighbor)) {
                    Node clone = new Node(neighbor.val);
                    map.put(neighbor, clone);
                    cloneNode.neighbors.add(clone);
                    queue.add(neighbor);
                } else {
                    cloneNode.neighbors.add(map.get(neighbor));
                }
            }
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        map.clear();
        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        BFS(queue);
        
        return cloneNode;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
