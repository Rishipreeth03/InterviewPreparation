/*
    Leetcode Link               : https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
*/


/********************************************************************** JAVA **********************************************************************/
// Approach-1 (Using DSU)
// T.C : O(m*n * alpha(m*n))
// S.C : O(m*n)
class DSU {
    private int[] parent;
    private int[] size;

    public DSU(int n) { // size of parent and size array
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 0; // fish count is 0 initially for each
        }
    }

    // find the parent of x
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]); // Path Compression
    }

    public void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        if (xParent == yParent) { // both already in same group
            return;
        }

        if (size[xParent] > size[yParent]) {
            parent[yParent] = xParent;
            size[xParent] += size[yParent];
        } else {
            parent[xParent] = yParent;
            size[yParent] += size[xParent];
        }
    }

    public void setSize(int x, int fishCount) {
        size[x] = fishCount;
    }

    public int getMaxFishCount() {
        int maxFish = 0;
        for (int fish : size) {
            maxFish = Math.max(maxFish, fish);
        }
        return maxFish;
    }
}

class Solution {
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int totalCells = m * n;
        DSU dsu = new DSU(totalCells);

        // Initialize size array with initial fish count of each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    int idx = i * n + j;
                    dsu.setSize(idx, grid[i][j]);
                }
            }
        }

        // Perform union operations
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    int idx = i * n + j; // parent index
                    for (int[] dir : directions) {
                        int i_ = i + dir[0];
                        int j_ = j + dir[1];
                        if (i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && grid[i_][j_] > 0) {
                            int idx_ = i_ * n + j_;
                            dsu.union(idx, idx_);
                        }
                    }
                }
            }
        }

        return dsu.getMaxFishCount();
    }
}

// Approach-2 (Using BFS)
// T.C : O(m*n)
// S.C : O(m*n)
class Solution {
    private int m, n;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int bfs(int i, int j, int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        int fishCount = grid[i][j];
        grid[i][j] = 0;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            i = cell[0];
            j = cell[1];

            for (int[] dir : directions) {
                int i_ = i + dir[0];
                int j_ = j + dir[1];

                if (i_ >= 0 && j_ >= 0 && i_ < m && j_ < n && grid[i_][j_] > 0) {
                    queue.offer(new int[]{i_, j_});
                    fishCount += grid[i_][j_];
                    grid[i_][j_] = 0;
                }
            }
        }

        return fishCount;
    }

    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int maxFish = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) { // water cell found
                    maxFish = Math.max(maxFish, bfs(i, j, grid));
                }
            }
        }

        return maxFish;
    }
}

// Approach-3 (Using DFS)
// T.C : O(m*n)
// S.C : O(1)
class Solution {
    private int m, n;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }

        int fishCount = grid[i][j];
        grid[i][j] = 0; // Mark this cell as visited (fish collected)

        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];
            fishCount += dfs(i_, j_, grid);
        }

        return fishCount;
    }

    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int maxFish = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) { // Fish cell found
                    maxFish = Math.max(maxFish, dfs(i, j, grid));
                }
            }
        }

        return maxFish;
    }
}