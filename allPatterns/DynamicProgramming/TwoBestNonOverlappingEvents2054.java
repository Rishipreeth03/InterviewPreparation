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

// Other Approaches
class Solution {
    // ------------------------- Using Priority Queue ------------------------- //
    //Time Complexity: O(nlogn)
    //Space Complexity: O(n)
    public int maxTwoEventsWithPriorityQueue(int[][] events) {
        // Create a PriorityQueue to store pairs of [endTime, value].
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    
        // Sort the array in ascending order of start times.
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
    
        int maxVal = 0, maxSum = 0;
    
        for (int[] event : events) {
            // Poll all valid events from the queue and update the maximum value.
            while (!pq.isEmpty() && pq.peek()[0] < event[0]) {
                maxVal = Math.max(maxVal, pq.poll()[1]);
            }
    
            // Calculate the maximum sum of two non-overlapping events.
            maxSum = Math.max(maxSum, maxVal + event[2]);
    
            // Add the current event's [endTime, value] to the queue.
            pq.add(new int[] { event[1], event[2] });
        }
    
        return maxSum;
    }

    // ------------------------- Using Greedy Algorithm ------------------------- //
    //Time Complexity: O(nlogn)
    //Space Complexity: O(n)
    public int maxTwoEventsWithGreedy(int[][] events) {
        List<int[]> times = new ArrayList<>();

        // Convert events into start and end times with their values
        for (int[] event : events) {
            // 1 denotes start time
            times.add(new int[] { event[0], 1, event[2] });
            // 0 denotes end time
            times.add(new int[] { event[1] + 1, 0, event[2] });
        }

        // Sort the times: first by time, then by type (end before start for same time)
        times.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]) );

        int ans = 0, maxValue = 0;

        // Process the sorted times
        for (int[] timeValue : times) {
            if (timeValue[1] == 1) {
                // Start time: Calculate the maximum sum of two events.
                ans = Math.max(ans, timeValue[2] + maxValue);
            } else {
                // End time: Update the maximum value seen so far.
                maxValue = Math.max(maxValue, timeValue[2]);
            }
        }

        return ans;
    }
}