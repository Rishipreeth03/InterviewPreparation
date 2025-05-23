//Approach-1 (Using cumulative sum)
//T.C : O(n + m)
//S.C : O(n)
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        int[] cumSum = new int[n];
        // cumSum[i] = total count of violating indices till index i
        cumSum[0] = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) { // violating index
                cumSum[i] = cumSum[i - 1] + 1;
            } else {
                cumSum[i] = cumSum[i - 1];
            }
        }

        boolean[] result = new boolean[m];
        for (int i = 0; i < m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            if (cumSum[end] - cumSum[start] == 0) {
                result[i] = true;
            } else {
                result[i] = false;
            }
        }

        return result;
    }
}


//Approach-2 (Using 2 pointers)
//T.C : O(n + m)
//S.C : O(n)
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        int[] validRightMostIdx = new int[n];
        // validRightMostIdx[i] = j; the rightmost index starting from i which is a special subarray [i..j]

        int i = 0;
        int j = 0;

        while (i < n) {
            if (j < i) {
                j = i;
            }

            while (j + 1 < n && nums[j] % 2 != nums[j + 1] % 2) {
                j++;
            }

            validRightMostIdx[i] = j;
            i++;
        }

        boolean[] result = new boolean[m];

        for (int k = 0; k < m; k++) {
            int start = queries[k][0];
            int end = queries[k][1];

            if (end <= validRightMostIdx[start]) {
                result[k] = true;
            } else {
                result[k] = false;
            }
        }

        return result;
    }
}


//Approach-3 (Using binary search)
//T.C : O(n + m*logn)
//S.C : O(n)
import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean bSearch(List<Integer> vi, int sp, int ep) {
        int l = 0;
        int r = vi.size() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (vi.get(mid) < sp) {
                l = mid + 1;
            } else if (vi.get(mid) > ep) {
                r = mid - 1;
            } else {
                // Found a violating index
                return true;
            }
        }

        return false;
    }

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        List<Integer> vi = new ArrayList<>(); // List of violating indices

        for (int i = 1; i < n; i++) { // O(n)
            if (nums[i] % 2 == nums[i - 1] % 2) {
                vi.add(i); // Always in sorted order
            }
        }

        boolean[] result = new boolean[m];

        for (int i = 0; i < m; i++) { // O(m)
            int start = queries[i][0];
            int end = queries[i][1];

            boolean hasViolatingIndexInRange = bSearch(vi, start + 1, end); // O(log(n))

            if (!hasViolatingIndexInRange) { // No violating index found
                result[i] = true;
            } else {
                result[i] = false;
            }
        }

        return result;
    }
}