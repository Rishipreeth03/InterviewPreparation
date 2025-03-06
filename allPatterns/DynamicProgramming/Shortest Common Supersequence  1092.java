/*
    Company Tags                : Microsoft
    GfG Link                    : https://www.geeksforgeeks.org/problems/shortest-common-supersequence0322/1
    Leetcode Link               : https://leetcode.com/problems/shortest-common-supersequence/description/
*/
// Approach-1 (Recursion + Memoization)
// T.C : O(m*n)
// S.C : O(m*n)
class Solution {
    int[][] t;

    public int solve(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return m + n;
        }

        if (t[m][n] != -1) {
            return t[m][n];
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return t[m][n] = 1 + solve(s1, s2, m - 1, n - 1);
        } else {
            return t[m][n] = 1 + Math.min(solve(s1, s2, m - 1, n), solve(s1, s2, m, n - 1));
        }
    }

    // Function to find length of shortest common supersequence of two strings.
    public int shortestCommonSupersequence(String s1, String s2, int m, int n) {
        t = new int[m + 1][n + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return solve(s1, s2, m, n);
    }
}

// Approach-2 (Bottom Up)
// T.C : O(m*n)
// S.C : O(m*n)
class Solution2 {
    // Function to find length of shortest common supersequence of two strings.
    public int shortestCommonSupersequence(String s1, String s2, int m, int n) {
        int[][] t = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = i + j;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
                    t[i][j] = 1 + Math.min(t[i - 1][j], t[i][j - 1]);
                }
            }
        }

        return t[m][n];
    }
}

// Approach-3 (Using LCS Code)
// You need to write the common letters only once and then write remaining letters of s1 and s2
// T.C : O(m*n)
// S.C : O(m*n)
class Solution3 {
    // Function to find length of shortest common supersequence of two strings.
    public int shortestCommonSupersequence(String s1, String s2, int m, int n) {
        int[][] t = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0)
                    t[i][j] = 0;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
            }
        }

        int LCS = t[m][n];

        int letters_from_s1 = m - LCS;
        int letters_from_s2 = n - LCS;

        return LCS + letters_from_s1 + letters_from_s2;
    }
}

// Approach -4 constructing string 
/*
     * Time Complexity: O(n1 * n2) 
     * Space Complexity: O(n1 * n2)
     * 
     * Approach: 
     * We use dynamic programming to find the length of the shortest common 
     * supersequence (SCS). The dp table is filled using a variation of the 
     * Longest Common Subsequence (LCS) approach. Then, we backtrack through 
     * the dp table to construct the SCS string.
     */
class Solution4 {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return constructSCS(str1, str2, dp);
    }

    public String constructSCS(String str1, String str2, int[][] dp) {
        StringBuilder scs = new StringBuilder();
        int i = str1.length(), j = str2.length();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                scs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] < dp[i][j - 1]) {
                scs.append(str1.charAt(i - 1));
                i--;
            } else {
                scs.append(str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            scs.append(str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            scs.append(str2.charAt(j - 1));
            j--;
        }

        return scs.reverse().toString();
    }
}
