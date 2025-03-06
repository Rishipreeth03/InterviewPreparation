//Approach-1 (Recursion + Memoization (i, j, k))
//Time Complexity: O(m * n * N)
//Space Complexity: O(m * n * N)
class Solution {
    int m, n, N;
    int[][][] t = new int[101][101][201];
    
    boolean solve(int i, int j, int k, String s1, String s2, String s3) {
        if (i == m && j == n && k == N) {
            return true;
        }
        
        if (k >= N) //other string didn't get consumed at all
            return false;
        
        if (t[i][j][k] != -1)
            return t[i][j][k] == 1;
        
        boolean result = false;
        
        if (i < m && s1.charAt(i) == s3.charAt(k)) {
            result = solve(i + 1, j, k + 1, s1, s2, s3);
        }
        
        if (result)
            return (t[i][j][k] = result ? 1 : 0) == 1;
        
        if (j < n && s2.charAt(j) == s3.charAt(k)) {
            result = solve(i, j + 1, k + 1, s1, s2, s3);
        }
        return (t[i][j][k] = result ? 1 : 0) == 1;
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        N = s3.length();
        for (int[][] layer : t) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }
        return solve(0, 0, 0, s1, s2, s3);
    }
}

//Approach-2 (Recursion + Memoization (i, j))
//Time Complexity: O(m * n)
//Space Complexity: O(m * n)
class Solution {
    int m, n, N;
    int[][] t = new int[101][101];
    
    boolean solve(int i, int j, String s1, String s2, String s3) {
        if (i == m && j == n && i + j == N) {
            return true;
        }
        
        if (i + j >= N)
            return false;
        
        if (t[i][j] != -1)
            return t[i][j] == 1;
        
        boolean result = false;
        
        if (i < m && s1.charAt(i) == s3.charAt(i + j)) {
            result = solve(i + 1, j, s1, s2, s3);
        }
        
        if (result)
            return (t[i][j] = result ? 1 : 0) == 1;
        
        if (j < n && s2.charAt(j) == s3.charAt(i + j)) {
            result = solve(i, j + 1, s1, s2, s3);
        }
        return (t[i][j] = result ? 1 : 0) == 1;
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        N = s3.length();
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return solve(0, 0, s1, s2, s3);
    }
}

//Approach - 3 (Bottom Up : O(m*n))
//Time Complexity: O(m * n)
//Space Complexity: O(m * n)
class Solution {
    boolean check(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int N = s3.length();
        if (m + n != N)
            return false;
        
        boolean[][] t = new boolean[m + 1][n + 1];
        t[0][0] = true;
        
        for (int j = 1; j <= n; j++) {
            t[0][j] = t[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        for (int i = 1; i <= m; i++) {
            t[i][0] = t[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int k = i + j - 1;
                t[i][j] = (t[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k)) ||
                           (t[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k));
            }
        }
        return t[m][n];
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        return check(s1, s2, s3);
    }
}

//Approach - 4 (Bottom Up : Space O(n) : Converting Approach-3 to Space O(n) approach)
//Time Complexity: O(m * n)
//Space Complexity: O(n)
class Solution {
    boolean check(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int N = s3.length();
        if (m + n != N)
            return false;
        
        boolean[] t = new boolean[n + 1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    t[j] = true;
                } else if (i == 0) {
                    t[j] = t[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    t[j] = t[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    t[j] = (t[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) ||
                           (t[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return t[n];
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        return check(s1, s2, s3);
    }
}
