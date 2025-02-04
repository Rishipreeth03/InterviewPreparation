/*
    Company Tags                : Google, Uber
    Leetcode Link               : https://leetcode.com/problems/making-a-large-island/
*/
//Approach - 1 - Brute Force DFS (you can do BFS as well)
//T.C : O(n^4)
//S.C : O(n^2)
class Solution {
    private int n;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        int maxArea = 0;

        // Try changing each '0' to '1' and compute the largest island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1; // Change 0 to 1

                    boolean[][] visited = new boolean[n][n];
                    int largest = 0;

                    // Compute the largest island from scratch
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (grid[x][y] == 1 && !visited[x][y]) {
                                largest = Math.max(largest, dfs(grid, x, y, visited));
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, largest);
                    grid[i][j] = 0; // Backtrack
                }
            }
        }

        return (maxArea == 0) ? n * n : maxArea; // If grid was full of 1s
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        int count = 1;

        for (int[] dir : directions) {
            count += dfs(grid, i + dir[0], j + dir[1], visited);
        }

        return count;
    }
}

//Approach - 2 - Better Brute Force DFS (you can do BFS as well)
//T.C : O(n^4)
//S.C : O(n^2)
class Solution {
    private int n;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        int maxArea = 0;

        // Try changing each '0' to '1' and compute the largest island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1; // Change 0 to 1

                    boolean[][] visited = new boolean[n][n];
                    int largest = 0;

                    // Compute the largest island from scratch
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (grid[x][y] == 1 && !visited[x][y]) {
                                largest = Math.max(largest, dfs(grid, x, y, visited));
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, largest);
                    grid[i][j] = 0; // Backtrack
                }
            }
        }

        return (maxArea == 0) ? n * n : maxArea; // If grid was full of 1s
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        int count = 1;

        for (int[] dir : directions) {
            count += dfs(grid, i + dir[0], j + dir[1], visited);
        }

        return count;
    }
}


//Approach - 3  - Optimal DFS (You can use BFS as well)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    private int m, n;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private int DFS(int[][] grid, int i, int j, int id) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return 0;
        }
        
        grid[i][j] = id;
        int count = 1;
        
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            count += DFS(grid, x, y, id);
        }
        
        return count;
    }
    
    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int maxArea = 0;
        Map<Integer, Integer> islandSizes = new HashMap<>();
        int islandId = 2;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = DFS(grid, i, j, islandId);
                    maxArea = Math.max(maxArea, size);
                    islandSizes.put(islandId, size);
                    islandId++;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> uniqueIslands = new HashSet<>();
                    for (int[] dir : directions) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 0) {
                            uniqueIslands.add(grid[x][y]);
                        }
                    }
                    
                    int sum = 1; // converting current 0 to 1
                    for (int id : uniqueIslands) {
                        sum += islandSizes.get(id);
                    }
                    maxArea = Math.max(maxArea, sum);
                }
            }
        }
        
        return maxArea;
    }
}



//Approach-4 (Using DSU)
//T.C : O(m*n * alpha(m*n))
//S.C : O(m*n)
class DSU {
    private int[] parent;
    private int[] size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // Path Compression
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        if (size[rootX] > size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }

    public int getSize(int x) {
        return size[find(x)];
    }
}

class Solution {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int totalCells = n * n;
        DSU dsu = new DSU(totalCells);
        int maxArea = 0;

        // Step 1: Connect all existing 1s using DSU
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int idx = i * n + j;
                    for (int[] dir : directions) {
                        int i_ = i + dir[0], j_ = j + dir[1];
                        int idx_ = i_ * n + j_;
                        if (i_ >= 0 && i_ < n && j_ >= 0 && j_ < n && grid[i_][j_] == 1) {
                            dsu.union(idx, idx_);
                        }
                    }
                }
            }
        }

        // Step 2: Find the largest island without modification
        for (int idx = 0; idx < totalCells; idx++) {
            if (grid[idx / n][idx % n] == 1) {
                maxArea = Math.max(maxArea, dsu.getSize(idx));
            }
        }

        // Step 3: Try flipping each 0 to 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> uniqueParents = new HashSet<>();
                    for (int[] dir : directions) {
                        int i_ = i + dir[0], j_ = j + dir[1];
                        int idx_ = i_ * n + j_;
                        if (i_ >= 0 && i_ < n && j_ >= 0 && j_ < n && grid[i_][j_] == 1) {
                            uniqueParents.add(dsu.find(idx_));
                        }
                    }

                    int newSize = 1; // Flip this 0 to 1
                    for (int parent : uniqueParents) {
                        newSize += dsu.getSize(parent);
                    }
                    maxArea = Math.max(maxArea, newSize);
                }
            }
        }

        return maxArea;
    }
}