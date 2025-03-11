// Approach -1 (Brute Force)
import java.util.*;

public class solution{
    public static int salution(int N, int K, int[] money) {
        // Count how many people already have money equal to K
        int totalKCount = 0;
        for (int m : money) {
            if (m == K) {
                totalKCount++;
            }
        }
        
        // If we don't perform any operation
        int maxFollowers = totalKCount;

        // For each possible subarray
        for (int L = 0; L < N; L++) {
            // Map to track the frequency of each potential X value
            Map<Integer, Integer> xValueCount = new HashMap<>();
            
            // Count of K values within current subarray expansion
            int kCountInSubarray = 0;

            for (int R = L; R < N; R++) {
                // Update K count in current subarray
                if (money[R] == K) {
                    kCountInSubarray++;
                }

                // Calculate X needed to make money[R] equal to K
                int x = K - money[R];
                xValueCount.put(x, xValueCount.getOrDefault(x, 0) + 1);

                // Find the X value that would make the most elements in the subarray equal to K
                int maxXValueCount = 0;
                for (int count : xValueCount.values()) {
                    maxXValueCount = Math.max(maxXValueCount, count);
                }

                // Calculate followers outside this subarray (elements that are already K)
                int kCountOutsideSubarray = totalKCount - kCountInSubarray;

                // Calculate total followers with the best X value
                int totalFollowers = maxXValueCount + kCountOutsideSubarray;
                maxFollowers = Math.max(maxFollowers, totalFollowers);
            }
        }
        return maxFollowers;
    }
}

//Approach -2
import java.util.*;

class MaximizeFollowers {
    public static int solution2(int N, int K, int[] money) {
        // Count initial followers and build a prefix sum array
        int[] kPrefixSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            kPrefixSum[i + 1] = kPrefixSum[i] + (money[i] == K ? 1 : 0);
        }
        int totalKCount = kPrefixSum[N];
        
        // If we don't perform any operation
        int maxFollowers = totalKCount;
        
        // For each possible subarray
        for (int L = 0; L < N; L++) {
            Map<Integer, Integer> xValueCount = new HashMap<>();
            
            for (int R = L; R < N; R++) {
                // Calculate X needed to make money[R] equal to K
                int x = K - money[R];
                xValueCount.put(x, xValueCount.getOrDefault(x, 0) + 1);
                
                // Find the X value that would make the most elements in the subarray equal to K
                int maxXValueCount = 0;
                for (int count : xValueCount.values()) {
                    maxXValueCount = Math.max(maxXValueCount, count);
                }
                
                // Calculate count of K values in the current subarray using prefix sum
                int kCountInSubarray = kPrefixSum[R + 1] - kPrefixSum[L];
                
                // Calculate followers outside this subarray (elements that are already K)
                int kCountOutsideSubarray = totalKCount - kCountInSubarray;
                
                // Calculate total followers with the best X value
                int totalFollowers = maxXValueCount + kCountOutsideSubarray;
                maxFollowers = Math.max(maxFollowers, totalFollowers);
            }
        }
        
        return maxFollowers;
    }
}