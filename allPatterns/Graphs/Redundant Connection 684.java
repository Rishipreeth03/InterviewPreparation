/*
    Company Tags                : Google
    Leetcode Link               : https://leetcode.com/problems/redundant-connection
*/

// Approach-1 - Using DFS
// T.C : O(n^2)
// S.C : O(n)
class Solution {
    public boolean dfs(Map<Integer, List<Integer>> adj, int u, int v, boolean[] visited) {
        visited[u] = true;
        if (u == v) {
            return true;
        }
        for (int ngbr : adj.get(u)) {
            if (visited[ngbr]) continue;
            if (dfs(adj, ngbr, v, visited)) {
                return true;
            }
        }
        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            
            if (adj.containsKey(u) && adj.containsKey(v)) {
                boolean[] visited = new boolean[n + 1];
                if (dfs(adj, u, v, visited)) {
                    return edge;
                }
            }
            
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(v);
            adj.get(v).add(u);
            /*or
             * adj.computeIfAbsent(u,k->new ArrayList<>()).add(v);
             * adj.computeIfAbsent(v,k->new ArrayList<>()).add(u);
             */
        }
        return new int[0];
    }
}

// Approach-2 - Using BFS
// T.C : O(n^2)
// S.C : O(n)
class SolutionBFS {
    int n;
    
    public boolean bfs(Map<Integer, List<Integer>> mp, int start, int end) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        
        while (!que.isEmpty()) {
            int curr = que.poll();
            visited[curr] = true;
            if (curr == end) return true;
            
            for (int x : mp.get(curr)) {
                if (!visited[x]) {
                    que.offer(x);
                }
            }
        }
        return false;
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        n = edges.length;
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            
            if (mp.containsKey(u) && mp.containsKey(v) && bfs(mp, u, v)) {
                return edge;
            }
            
            mp.putIfAbsent(u, new ArrayList<>());
            mp.putIfAbsent(v, new ArrayList<>());
            mp.get(u).add(v);
            mp.get(v).add(u);
        }
        return new int[0];
    }
}

// Approach-3 (DSU - Union Find with Path compression)
// T.C : O(n * alpha(n))
// S.C : O(n)
class DSU {
    int[] parent;
    int[] rank;
    
    public DSU(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        int x_parent = find(x);
        int y_parent = find(y);
        
        if (x_parent == y_parent) return;
        
        if (rank[x_parent] > rank[y_parent]) {
            parent[y_parent] = x_parent;
        } else if (rank[y_parent] > rank[x_parent]) {
            parent[x_parent] = y_parent;
        } else {
            parent[y_parent] = x_parent;
            rank[x_parent]++;
        }
    }
}

class SolutionDSU {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        DSU dsu = new DSU(n);
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (dsu.find(u) == dsu.find(v)) {
                return edge;
            }
            dsu.union(u, v);
        }
        return new int[0];
    }
}