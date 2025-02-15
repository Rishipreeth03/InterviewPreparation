
/*
    Company Tags                : Will update soon
    Leetcode Link               : https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls
*/

    // Approach-1 (Using array and hashmap)
    // T.C : O(n)
    // S.C : O(limit) - This will cause MLE
    class Solution {
        public int[] queryResults(int limit, int[][] queries) {
            int n = queries.length;
            int[] result = new int[n];
            
            Map<Integer, Integer> colormp = new HashMap<>(); // color -> count
            int[] ballArr = new int[limit + 1]; // ball[i] = x; ith ball has x color
            Arrays.fill(ballArr, -1);
            
            for (int i = 0; i < n; i++) {
                int ball = queries[i][0];
                int color = queries[i][1];
                
                if (ballArr[ball] != -1) { // already colored ball
                    int prevColor = ballArr[ball];
                    colormp.put(prevColor, colormp.get(prevColor) - 1);
                    
                    if (colormp.get(prevColor) == 0) {
                        colormp.remove(prevColor);
                    }
                }
                
                ballArr[ball] = color;
                colormp.put(color, colormp.getOrDefault(color, 0) + 1);
                
                result[i] = colormp.size();
            }
            
            return result;
        }
    }
    
    
    // Approach-2 (Using hashmaps)
    // T.C : O(n)
    // S.C : O(n)
    class Solution {
        public int[] queryResults(int limit, int[][] queries) {
            int n = queries.length;
            int[] result = new int[n];
            
            Map<Integer, Integer> colormp = new HashMap<>(); // color -> count
            Map<Integer, Integer> ballmp = new HashMap<>(); // ball -> color
            
            for (int i = 0; i < n; i++) {
                int ball = queries[i][0];
                int color = queries[i][1];
                
                if (ballmp.containsKey(ball)) { // already colored ball
                    int prevColor = ballmp.get(ball);
                    colormp.put(prevColor, colormp.get(prevColor) - 1);
                    
                    if (colormp.get(prevColor) == 0) {
                        colormp.remove(prevColor);
                    }
                }
                
                ballmp.put(ball, color);
                colormp.put(color, colormp.getOrDefault(color, 0) + 1);
                
                result[i] = colormp.size();
            }
            
            return result;
        }
    }