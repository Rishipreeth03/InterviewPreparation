/*
      Company Tags                : META, AMAZON, GOOGLE
      Leetcode Link               : https://leetcode.com/problems/unique-length-3-palindromic-subsequences
*/
import java.util.*;
//Approach-1
//T.C : O(n)
//S.C : O(1) - ONly 26 sized always
class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        Set<Character> uniqueLetters = new HashSet<>();

        for (char ch : s.toCharArray()) {
            uniqueLetters.add(ch);
        }

        int result = 0;

        for (char letter : uniqueLetters) {

            int firstIdx = -1;
            int lastIdx = -1;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == letter) {

                    if (firstIdx == -1) {
                        firstIdx = i;
                    }

                    lastIdx = i;

                }
            }

            Set<Character> set = new HashSet<>();
            for (int middle = firstIdx + 1; middle <= lastIdx - 1; middle++) {
                set.add(s.charAt(middle));
            }

            result += set.size();

        }

        return result;
    }
}


//Approach-2 (Storing first and last indices before hand
//T.C : O(n)
//S.C : O(1) - Only 26 sized always
class Solution {
    public int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (first[curr] == - 1) {
                first[curr] = i;
            }
            
            last[curr] = i;
        }
        
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }
            
            Set<Character> st = new HashSet();
            for (int middle = first[i] + 1; middle < last[i]; middle++) {
                st.add(s.charAt(middle));
            }
            
            ans += st.size();
        }
        
        return ans;
    }
}