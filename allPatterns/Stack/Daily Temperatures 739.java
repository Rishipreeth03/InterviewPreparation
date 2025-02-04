/*
    Company Tags                : Google, Meta, Tokopedia
    Leetcode Link               : https://leetcode.com/problems/daily-temperatures/
*/
// Example of Montonic Stack
//T.C : O(n)
//S.C :O(n)
public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> st = new Stack<>();
        
        int[] result = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && temperatures[i] >= temperatures[st.peek()]) {
                st.pop();
            }
            
            if (st.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = st.peek() - i; // days
            }
            
            st.push(i);
        }
        
        return result;
    }
}