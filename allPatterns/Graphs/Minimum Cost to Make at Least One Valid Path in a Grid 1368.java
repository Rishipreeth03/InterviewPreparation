import java.util.*;
/*
 * leetcode link : https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/
 * 
 */
class Solution {
    
    // Approach 1: Backtracking DFS (TLE)
    // Time Complexity: O(4^(m*n)) - Worst case scenario where we explore all possible paths
    // Space Complexity: O(m*n) - Space used by the recursion stack and visited array
    public int dfs(int i, int j, int[][] grid, boolean[][] visited, int cost) {
        if (i == m - 1 && j == n - 1) { // Reached destination
            return cost;
        }
        
        visited[i][j] = true;
        int minCost = Integer.MAX_VALUE;
        
        for (int dir_i = 0; dir_i < 4; dir_i++) {
            int i_ = i + dir[dir_i][0];
            int j_ = j + dir[dir_i][1];
            
            if (i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && !visited[i_][j_]) {
                int nextCost = cost + ((grid[i][j] - 1 != dir_i) ? 1 : 0);
                minCost = Math.min(minCost, dfs(i_, j_, grid, visited, nextCost));
            }
        }
        
        visited[i][j] = false;
        return minCost;
    }

    // Approach 2: Dijkstra's Algorithm (Accepted)
    // Time Complexity: O((m*n) * log(m*n)) - Dijkstra's algorithm with a priority queue
    // Space Complexity: O(m*n) - Space used by the result array and the priority queue
    private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int minCost(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        // Approach 1: Backtracking DFS
        if (useDFS) {  // Assuming 'useDFS' is a flag that lets us choose the approach (set to true for DFS)
            boolean[][] visited = new boolean[m][n];
            return dfs(0, 0, grid, visited, 0); // Explore all paths by backtracking
        }
        
        // Approach 2: Dijkstra's Algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // Min-heap of int
        int[][] result = new int[m][n];
        
        for (int[] row : result) Arrays.fill(row, Integer.MAX_VALUE); // Initialize result array with max values
        pq.offer(new int[]{0, 0, 0}); // Push starting point into priority queue
        result[0][0] = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCost = curr[0], i = curr[1], j = curr[2];
            
            if (result[i][j] < currCost) continue; // Skip if already found a cheaper path
            
            for (int dir_i = 0; dir_i < 4; dir_i++) {
                int i_ = i + dir[dir_i][0], j_ = j + dir[dir_i][1];
                
                if (i_ >= 0 && j_ >= 0 && i_ < m && j_ < n) {
                    int gridDir = grid[i][j];
                    int dirCost = (gridDir - 1 != dir_i) ? 1 : 0; // Calculate the cost to go in the current direction
                    int newCost = currCost + dirCost;
                    
                    if (newCost < result[i_][j_]) {
                        result[i_][j_] = newCost;
                        pq.offer(new int[]{newCost, i_, j_}); // Push the new path with updated cost
                    }
                }
            }
        }
        
        return result[m - 1][n - 1]; // Return the minimum cost to reach the bottom-right corner
    }
    
    // Variables for grid dimensions
    private int m, n;
    // Direction array
    private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    // Flag to choose which approach to use
    private boolean useDFS = false; // Set this flag to true to use DFS approach, false for Dijkstra's
    
}