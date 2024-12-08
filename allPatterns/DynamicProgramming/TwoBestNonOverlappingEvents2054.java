//Approach-1 (Brute Force) - TLE
//T.C : O(n^2)
//S.C : O(1)
class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            // Consider single event's value
            result = Math.max(result, events[i][2]);

            int val = events[i][2];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                // Check if events overlap
                if (events[j][0] <= events[i][1] && events[j][1] >= events[i][0]) {
                    continue;
                }

                result = Math.max(result, val + events[j][2]);
            }
        }

        return result;
    }
}



//Approach-2 (Recursion + Memoization and Sorting)
//T.C : O(n * logn, After memoizing, we will visit at max n states and for binarysearch it will take log(n))
//S.C : O(n*3) ~= O(n)
class Solution {
    private int n;
    private int[][] dp = new int[100001][3];
    
    // Binary search for the next event start time greater than the current event's end time
    private int binarySearch(int[][] events, int endTime) {
        int left = 0, right = n - 1, result = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > endTime) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private int solve(int[][] events, int i, int count) {
        if (count == 2 || i >= n) {
            return 0;
        }

        if (dp[i][count] != -1) {
            return dp[i][count];
        }

        int nextValidEventIndex = binarySearch(events, events[i][1]);
        int take = events[i][2] + solve(events, nextValidEventIndex, count + 1);
        int notTake = solve(events, i + 1, count);

        dp[i][count] = Math.max(take, notTake);
        return dp[i][count];
    }

    public int maxTwoEvents(int[][] events) {
        n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0])); // Sort events by start time
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(events, 0, 0);
    }
}