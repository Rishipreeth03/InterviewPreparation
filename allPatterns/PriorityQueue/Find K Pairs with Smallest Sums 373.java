import java.util.*;

class Solution {

    // Approach 1: Complete Brute Force Using Max-Heap (TLE)
    /*
        Time Complexity  : O(m * n * log(k)) - TLE
        Space Complexity : O(k)
    */
    public List<List<Integer>> kSmallestPairsApproach1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // Max-Heap

        int m = nums1.length, n = nums2.length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];

                if (maxHeap.size() < k) {
                    maxHeap.offer(new int[] {sum, i, j});
                } else if (maxHeap.peek()[0] > sum) {
                    maxHeap.poll();
                    maxHeap.offer(new int[] {sum, i, j});
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int i = top[1], j = top[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
        }

        return result;
    }

    // Approach 2: Improved Brute Force Using Max-Heap (Accepted)
    /*
        Time Complexity  : O(m * n * log(k)) - Slightly optimized but still expensive
        Space Complexity : O(k)
    */
    public List<List<Integer>> kSmallestPairsApproach2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // Max-Heap

        int m = nums1.length, n = nums2.length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];

                if (maxHeap.size() < k) {
                    maxHeap.offer(new int[] {sum, i, j});
                } else if (maxHeap.peek()[0] > sum) {
                    maxHeap.poll();
                    maxHeap.offer(new int[] {sum, i, j});
                } else {
                    break; // Slight optimization: No need to continue for larger sums
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int i = top[1], j = top[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
        }

        return result;
    }

    // Approach 3: Optimized Approach Using Min-Heap and Visited Set (O(k log k))
    /*
        Time Complexity  : O(k log k) - Best approach using MinHeap
        Space Complexity : O(k)
    */
    public List<List<Integer>> kSmallestPairsApproach3(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // Min-Heap
        Set<String> visited = new HashSet<>();

        int m = nums1.length, n = nums2.length;
        if (m == 0 || n == 0) return new ArrayList<>();

        // Push the first pair (0, 0)
        minHeap.offer(new int[] {nums1[0] + nums2[0], 0, 0});
        visited.add("0,0");

        List<List<Integer>> result = new ArrayList<>();

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int i = top[1], j = top[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Push (i, j+1) if not visited
            if (j + 1 < n && !visited.contains(i + "," + (j + 1))) {
                minHeap.offer(new int[] {nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(i + "," + (j + 1));
            }

            // Push (i+1, j) if not visited
            if (i + 1 < m && !visited.contains((i + 1) + "," + j)) {
                minHeap.offer(new int[] {nums1[i + 1] + nums2[j], i + 1, j});
                visited.add((i + 1) + "," + j);
            }
        }

        return result;
    }

    // Main method to test all approaches
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        // Test Approach 1
        System.out.println("Approach 1: " + solution.kSmallestPairsApproach1(nums1, nums2, k));

        // Test Approach 2
        System.out.println("Approach 2: " + solution.kSmallestPairsApproach2(nums1, nums2, k));

        // Test Approach 3
        System.out.println("Approach 3: " + solution.kSmallestPairsApproach3(nums1, nums2, k));
    }
}
