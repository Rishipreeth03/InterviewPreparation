/*
    Company Tags                : OLA, Goldman Sachs, Google (Google asked with small variation)
    Leetcode Link               : https://leetcode.com/problems/minimum-path-sum/   
*/
// Approach-1 (Recur + Memoization)
// T.C : O(m*n)
// S.C : O(m*n)
class Solution {
    private int MPS(int[][] grid, int i, int j, int m, int n, int[][] t) {
        if (i == m - 1 && j == n - 1)
            return t[i][j] = grid[i][j];
        if (t[i][j] != -1)
            return t[i][j];
        if (i == m - 1) { // we can only go right
            return t[i][j] = grid[i][j] + MPS(grid, i, j + 1, m, n, t);
        } else if (j == n - 1) { // we can only go down
            return t[i][j] = grid[i][j] + MPS(grid, i + 1, j, m, n, t);
        } else {
            return t[i][j] = grid[i][j] + Math.min(MPS(grid, i + 1, j, m, n, t), MPS(grid, i, j + 1, m, n, t));
        }
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] t = new int[m][n];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return MPS(grid, 0, 0, m, n, t);
    }
}

// Approach-2 (Bottom Up DP)
// T.C : O(m*n)
// S.C : O(m*n)
class Solution {
    private int DFS(int[][] grid, int m, int n) {
        int[][] t = new int[m][n];
        t[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            t[i][0] = t[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            t[0][j] = t[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                t[i][j] = grid[i][j] + Math.min(t[i - 1][j], t[i][j - 1]);
            }
        }
        return t[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return DFS(grid, m, n);
    }
}