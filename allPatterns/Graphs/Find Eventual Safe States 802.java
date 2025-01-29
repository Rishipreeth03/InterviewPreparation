/*
    YT refernce  : https://www.youtube.com/watch?v=k8LBJqGLLQE
    Company Tags                : MICROSOFT
    Leetcode Link               : https://leetcode.com/problems/find-eventual-safe-states/
*/

/*
    Key Points Before Each Approach

    Approach-1: Using DFS to Check for Cycles
    1. Cycle Detection: Uses DFS to identify nodes involved in cycles in a directed graph.
    2. Visited and Recursion Tracking: Maintains arrays to track visited nodes and nodes currently in the recursion stack.
    3. Time Complexity: O(V+E) due to DFS traversal.
    4. Space Complexity: O(V+E) for adjacency list and recursion stack.

    Approach-2: Using BFS (Topological Sort)
    1. Reverse Graph: Constructs a reverse graph and calculates the indegree of each node.
    2. BFS: Uses a queue to process nodes with indegree 0 and identifies safe nodes.
    3. Time Complexity: O(V+E) due to graph traversal.
    4. Space Complexity: O(V+E) for adjacency list and queue.
*/

// Approach-1 (Using Same code of DFS Cycle Check in directed Graph)
// T.C : O(V+E)
// S.C : O(V+E)
import java.util.*;

class Solution {
    private boolean isCycleDFS(List<List<Integer>> adj, int u, boolean[] visited, boolean[] inRecursion) {
        visited[u] = true;
        inRecursion[u] = true;

        for (int v : adj.get(u)) {
            // if not visited, then we check for cycle in DFS
            if (!visited[v] && isCycleDFS(adj, v, visited, inRecursion)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }

        inRecursion[u] = false;
        return false;
    }

    public List<Integer> eventualSafeNodesDFS(int[][] graph) {
        int V = graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            for (int v : graph[i]) {
                adj.get(i).add(v);
            }
        }

        boolean[] visited = new boolean[V];
        boolean[] inRecursion = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                isCycleDFS(adj, i, visited, inRecursion);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (!inRecursion[i]) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    // Approach-2 (Using BFS) (Topological Sort)
    // T.C : O(V+E)
    // S.C : O(V+E)
    public List<Integer> eventualSafeNodesBFS(int[][] graph) {
        int V = graph.length;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[V];
        int count = 0;

        // 1. Create reverse graph and calculate indegree
        for (int u = 0; u < V; u++) {
            for (int v : graph[u]) {
                adj.get(v).add(u);
                indegree[u]++;
            }
        }

        // 2. Fill queue with nodes having indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        // 3. Simple BFS
        boolean[] safe = new boolean[V];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            safe[u] = true;

            for (int v : adj.get(u)) {
                indegree[v]--;

                if (indegree[v] == 0) {
                    queue.add(v);
                    count++;
                }
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (safe[i]) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }
}
