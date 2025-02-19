import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while (T-- > 0) {
            String[] input = br.readLine().split(" ", 2);
            String A = input[0];
            String B = input[1];
            sb.append(KMP(B, A)).append("\n"); // Append the result to StringBuilder
        }
        System.out.print(sb); // Print all results at once for efficiency
    }
    
    // KMP algorithm to find occurrences of pattern in text
    public static int KMP(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int[] lps = computeLPS(pattern); // Compute LPS array
        int i = 0, j = 0, count = 0;
        
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) { // Pattern found
                    count++;
                    j = lps[j - 1]; // Move j to the last matched prefix
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1]; // Use LPS array to skip unnecessary checks
                } else {
                    i++;
                }
            }
        }
        return count;
    }
    
    // Compute the Longest Prefix Suffix (LPS) array
    public static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0, i = 1;
        
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Use previously computed LPS value
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
