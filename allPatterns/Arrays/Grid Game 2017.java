/*
    YT ref : https://www.youtube.com/watch?v=Y8VC_OnkazE
    Leetcode Link   : https://leetcode.com/problems/grid-game
*/

    //Approach - (Using cumulative sum + game strategy)
    //T.C : O(col), col = total columns in the grid
    //S.C : O(1)
    class Solution {
        public long gridGame(int[][] grid) {
            long firstRowRemainSum = 0;
            for (int num : grid[0]) {
                firstRowRemainSum += num;  // O(col)
            }
    
            long secondRowRemainSum = 0;
            long minimizedRobot2Sum = Long.MAX_VALUE;
    
            for (int Robot1Col = 0; Robot1Col < grid[0].length; Robot1Col++) {  // O(col)
                // Robot1 took this strategy
                firstRowRemainSum -= grid[0][Robot1Col];
    
                // Robot2 will try to do best after Robot1 has taken the above strategy
                long bestOfRobot2 = Math.max(firstRowRemainSum, secondRowRemainSum);
    
                minimizedRobot2Sum = Math.min(minimizedRobot2Sum, bestOfRobot2);
    
                secondRowRemainSum += grid[1][Robot1Col];
            }
    
            return minimizedRobot2Sum;
        }
    }


/*
    Approach - (Using cumulative sum + game strategy)
    T.C : O(col), where col = total columns in the grid
    S.C : O(1)

    Explanation:
    - The problem involves two robots playing a game on a 2D grid. Robot 1 selects a path across the first row, and Robot 2 tries to maximize its path's score in the second row.
    - The goal is to minimize the maximum score Robot 2 can achieve after Robot 1's optimal strategy.
    - We calculate the cumulative sum of the first row (`firstRowRemainSum`) and initialize the second row's sum (`secondRowRemainSum`) to 0.
    - For each column Robot 1 picks:
      1. Update `firstRowRemainSum` by excluding the current column.
      2. Calculate the best score Robot 2 can achieve, which is the maximum of the remaining sums of the two rows.
      3. Update `minimizedRobot2Sum` to keep track of the minimum possible maximum score Robot 2 can achieve.
    - The result is the smallest maximum score Robot 2 can achieve across all possible strategies.

    Example:
    Input:
    grid = [[2, 5, 4],
            [1, 5, 1]]

    Steps:
    - Initially, `firstRowRemainSum = 2 + 5 + 4 = 11`, `secondRowRemainSum = 0`.
    - Robot 1 selects column 0:
        - `firstRowRemainSum = 11 - 2 = 9`
        - Robot 2's best score = max(9, 0) = 9
    - Robot 1 selects column 1:
        - `firstRowRemainSum = 9 - 5 = 4`
        - `secondRowRemainSum = 0 + 5 = 5`
        - Robot 2's best score = max(4, 5) = 5
    - Robot 1 selects column 2:
        - `firstRowRemainSum = 4 - 4 = 0`
        - `secondRowRemainSum = 5 + 1 = 6`
        - Robot 2's best score = max(0, 6) = 6
    - Minimum possible maximum score = 5.

    Output:
    5
*/

