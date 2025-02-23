/*
    Company Tags                : Microsoft, Meta, Epic Systems, Amazon, Flipkart
    Leetcode Link               : https://leetcode.com/problems/letter-combinations-of-a-phone-number/
*/

// Backtracking - template
/*
    Each digit can be presented at most by 4 letters. On each iteration, we go over all 4 of them. And we do this N (the number of digits) times. 
    The complexity would be at most O(4^N).
    But, you still have to traverse the entire string and do the same amount of operations, so it's O(n • 4^N).
*/

import java.util.*;

class Solution {
    List<String> result = new ArrayList<>();

    public void solve(int idx, String digits, StringBuilder temp, Map<Character, String> mp) {
        if (idx >= digits.length()) {
            result.add(temp.toString());
            return;
        }

        char ch = digits.charAt(idx);
        String str = mp.get(ch);

        for (int i = 0; i < str.length(); i++) {
            // Do
            temp.append(str.charAt(i));
            solve(idx + 1, digits, temp, mp);
            // Undo (Backtrack)
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return new ArrayList<>();

        Map<Character, String> mp = new HashMap<>();
        mp.put('2', "abc");
        mp.put('3', "def");
        mp.put('4', "ghi");
        mp.put('5', "jkl");
        mp.put('6', "mno");
        mp.put('7', "pqrs");
        mp.put('8', "tuv");
        mp.put('9', "wxyz");

        StringBuilder temp = new StringBuilder();
        solve(0, digits, temp, mp);

        return result;
    }
}
