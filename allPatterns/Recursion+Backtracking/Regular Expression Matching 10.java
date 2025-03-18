/*
    Company Tags                : GOOGLE
    Leetcode Link               : https://leetcode.com/problems/regular-expression-matching/
*/

//Approach-1 (Using Recursion)
//T.C : In worst case, the function may recursively call itself twice for each character in the pattern (due to *), leading to an exponential number of recursive calls.
        //Hence, time complexity will be approaximately O(2^(max(n, m))). where n , m are lengths of s and p respectively.
//S.C : O(m) - Recursion stack space : where m = length of pattern (maximum depth of recursion tree)
        //NOTE : We are passing substr to isMatch every time and hence they are being copied to x and p so, we will be creating O(2^(max(n, m))) number of substrings.
public class Solution {
    public boolean isMatch(String text, String pattern) {
        if (pattern.length() == 0) {
            return text.length() == 0;
        }

        boolean firstCharMatched = false;
        if (text.length() > 0 && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.')) {
            firstCharMatched = true;
        }

        // Best example to understand: s = "aaab", p = "a*b"
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (firstCharMatched && isMatch(text.substring(1), pattern)));
        } else {
            return firstCharMatched && isMatch(text.substring(1), pattern.substring(1));
        }
    }
}


//Approach-2 (Recursion + Memoization)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    int[][] t;

    public boolean solve(int i, int j, String text, String pattern) {
        if (j == pattern.length()) {
            return i == text.length();
        }

        if (t[i][j] != -1) {
            return t[i][j] == 1;
        }

        boolean ans = false;

        boolean first_match = (i < text.length() &&
                (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
            ans = (solve(i, j + 2, text, pattern) ||
                    (first_match && solve(i + 1, j, text, pattern)));
        } else {
            ans = first_match && solve(i + 1, j + 1, text, pattern);
        }

        return (t[i][j] = ans ? 1 : 0) == 1;
    }

    public boolean isMatch(String text, String pattern) {
        t = new int[21][21];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return solve(0, 0, text, pattern);
    }
}


class TUF {
//Approach-3 Memoization Approach
// Time Complexity: O(N*M)
// Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.
// Space Complexity: O(N*M) + O(N+M)


    static int wildcardMatchingUtil(String S1, String S2, int i, int j, int[][] dp) {
        // Base Cases
        if (i < 0 && j < 0)
          return 1; // Both strings are empty, and the pattern matches.
        if (i < 0 && j >= 0)
          return 0; // S1 is empty, but there are characters left in S2.
        if (j < 0 && i >= 0)
          return isAllStars(S1, i) ? 1 : 0; // S2 is empty, check if remaining characters in S1 are all '*'.
    
        // If the result is already computed, return it.
        if (dp[i][j] != -1) return dp[i][j];
    
        // If the characters match or S1 has a '?', continue matching the rest of the strings.
        if (S1.charAt(i) == S2.charAt(j) || S1.charAt(i) == '?')
          return dp[i][j] = wildcardMatchingUtil(S1, S2, i - 1, j - 1, dp);
    
        else {
          if (S1.charAt(i) == '*') {
            // Two possibilities when encountering '*':
            // 1. '*' matches one or more characters in S2.
            // 2. '*' matches zero characters in S2.
            return dp[i][j] = (wildcardMatchingUtil(S1, S2, i - 1, j, dp) == 1 || wildcardMatchingUtil(S1, S2, i, j - 1, dp) == 1) ? 1 : 0;
          } else {
            // Characters don't match, and S1[i] is not '*'.
            return 0;
          }
        }
      }
      // Helper function to check if all characters from index 1 to i in S1 are '*'
  static boolean isAllStars(String S1, int i) {
    for (int j = 1; j <= i; j++) {
      if (S1.charAt(j - 1) != '*')
        return false;
    }
    return true;
  }

      
// Approach-4 (Tabulation)
// Time Complexity: O(N*M)
// Space Complexity: O(N*M)

  // Function to perform wildcard pattern matching
  static boolean wildcardMatching(String S1, String S2) {
    int n = S1.length();
    int m = S2.length();

    // Create a 2D array to store the matching results
    boolean dp[][] = new boolean[n + 1][m + 1];
    dp[0][0] = true;

    // Initialize the first row and column based on wildcard '*' in S1
    for (int j = 1; j <= m; j++) {
      dp[0][j] = false;
    }
    for (int i = 1; i <= n; i++) {
      dp[i][0] = isAllStars(S1, i);
    }

    // Fill the dp array using a bottom-up approach
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
          dp[i][j] = dp[i - 1][j - 1]; // Characters match or '?' is encountered.
        } else {
          if (S1.charAt(i - 1) == '*') {
            dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // '*' matches one or more characters.
          } else {
            dp[i][j] = false; // Characters don't match, and S1[i-1] is not '*'.
          }
        }
      }
    }

    return dp[n][m]; // The final result indicates whether S1 matches S2.
  }


  //Approach -5
  //space optimised 
  //Time Complexity: O(N*M)
// Reason: There are two nested loops.
// Space Complexity: O(M)
// Reason: We are using an external array of size ‘M+1’ to store two rows.

  static boolean wildcardMatching(String S1, String S2) {
    int n = S1.length();
    int m = S2.length();

    // Create two boolean arrays to store the matching results for the current and previous rows
    boolean[] prev = new boolean[m + 1];
    boolean[] cur = new boolean[m + 1];

    // Initialize the first element of prev as true
    prev[0] = true;

    // Iterate through S1 and S2 to fill the cur array
    for (int i = 1; i <= n; i++) {
      // Initialize the first element of cur based on whether S1 contains '*'
      cur[0] = isAllStars(S1, i);
      for (int j = 1; j <= m; j++) {
        if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
          cur[j] = prev[j - 1]; // Characters match or '?' is encountered.
        } else {
          if (S1.charAt(i - 1) == '*') {
            cur[j] = prev[j] || cur[j - 1]; // '*' matches one or more characters.
          } else {
            cur[j] = false; // Characters don't match, and S1[i-1] is not '*'.
          }
        }
      }
      // Update prev array to store the current values
      prev = cur.clone();
    }

    return prev[m]; // The final result indicates whether S1 matches S2.
  }


  public static void main(String args[]) {
    String S1 = "ab*cd";
    String S2 = "abdefcd";

    if (wildcardMatching(S1, S2)) {
      System.out.println("String S1 and S2 do match");
    } else {
      System.out.println("String S1 and S2 do not match");
    }
  }
}

