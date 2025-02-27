import java.util.*;

// Approach 1: Brute Force (Three Nested Loops)
// Time Complexity: O(N^3)
// Space Complexity: O(1)
public class BruteForce {
    public static int maxSubarraySum(int[] arr, int n) {
        int maxi = Integer.MIN_VALUE; // Maximum sum

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    public static void main(String args[]) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        int maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}


// Approach 2: Optimized Brute Force (Two Nested Loops)
// Time Complexity: O(N^2)
// Space Complexity: O(1)
public class OptimizedBruteForce {
    public static int maxSubarraySum(int[] arr, int n) {
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    public static void main(String args[]) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        int maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}


// Approach 3: Kadane's Algorithm
// Time Complexity: O(N)
// Space Complexity: O(1)
public class Kadane {
    public static long maxSubarraySum(int[] arr, int n) {
        long maxi = Long.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            maxi = Math.max(maxi, sum);
            if (sum < 0) sum = 0; // Reset sum if negative
        }
        return maxi;
    }

    public static void main(String args[]) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        long maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}


// Approach 4: Kadane's Algorithm with Subarray Tracking
// Time Complexity: O(N)
// Space Complexity: O(1)
public class KadaneWithSubarray {
    public static long maxSubarraySum(int[] arr, int n) {
        long maxi = Long.MIN_VALUE;
        long sum = 0;
        int start = 0, ansStart = -1, ansEnd = -1;

        for (int i = 0; i < n; i++) {
            if (sum == 0) start = i;
            sum += arr[i];
            if (sum > maxi) {
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }
            if (sum < 0) sum = 0;
        }

        // Printing the subarray
        System.out.print("The subarray is: [");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");

        return maxi;
    }

    public static void main(String args[]) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        long maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}