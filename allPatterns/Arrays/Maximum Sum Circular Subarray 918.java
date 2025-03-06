import java.util.*;

class Solution {
    // Approach-1 (Using Kadane's Algo) - O(N)
    // Time Complexity: O(N), since we traverse the array twice (once for max and once for min subarray sum)
    // Space Complexity: O(1), as we use only a few extra variables
    private int kadanesMax(int[] nums, int n) {
        int sum = nums[0];
        int maxSum = nums[0];
        
        for (int i = 1; i < n; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, sum);
        }
        
        return maxSum;
    }
    
    private int kadanesMin(int[] nums, int n) {
        int sum = nums[0];
        int minSum = nums[0];
        
        for (int i = 1; i < n; i++) {
            sum = Math.min(sum + nums[i], nums[i]);
            minSum = Math.min(minSum, sum);
        }
        
        return minSum;
    }
    
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int SUM = Arrays.stream(nums).sum();
        
        int minSum = kadanesMin(nums, n);
        int maxSum = kadanesMax(nums, n);
        
        int circSum = SUM - minSum;
        
        if (maxSum > 0) {
            return Math.max(maxSum, circSum);
        }
        
        return maxSum;
    }
    
    // Approach-2 (Writing everything in one loop) - O(N)
    // Time Complexity: O(N), as we traverse the array only once
    // Space Complexity: O(1), since only a few extra variables are used
    public int maxSubarraySumCircularOptimized(int[] nums) {
        int n = nums.length;
        int SUM = Arrays.stream(nums).sum();
        
        int k_sum_min = nums[0];
        int min_sum = nums[0];
        
        int k_sum_max = nums[0];
        int max_sum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            min_sum = Math.min(nums[i] + min_sum, nums[i]);
            k_sum_min = Math.min(k_sum_min, min_sum);
            
            max_sum = Math.max(nums[i] + max_sum, nums[i]);
            k_sum_max = Math.max(k_sum_max, max_sum);
        }
        
        int circular_sum = SUM - k_sum_min;
        
        if (k_sum_max > 0) {
            return Math.max(k_sum_max, circular_sum);
        }
        
        return k_sum_max;
    }
}
