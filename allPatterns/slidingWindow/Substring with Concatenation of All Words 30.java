/*
 * problem-link:https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 */
import java.util.*;
//Approach-1
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // Step 1: Store frequency of each word in 'words' array
        HashMap<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> indexes = new ArrayList<>(); // To store starting indices of valid substrings
        int n = s.length(), num = words.length, len = words[0].length(); // Total string length, number of words, word length
        
        // Step 2: Iterate through the string to check for valid substrings
        for (int i = 0; i < n - num * len + 1; i++) {
            Map<String, Integer> seen = new HashMap<>(); // Track words seen in the current window
            int j = 0;
            
            // Step 3: Extract words from 's' in chunks of 'len' size and compare with 'words'
            while (j < num) {
                String word = s.substring(i + j * len, i + (j + 1) * len); // Extract a word from 's'
                
                if (counts.containsKey(word)) { // If word exists in 'words' array
                    seen.put(word, seen.getOrDefault(word, 0) + 1); // Increment count in 'seen'
                    
                    // If the word appears more times than it should, break out
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break; // If an invalid word appears, break early
                }
                j++;
            }
            
            // Step 4: If all words are matched correctly, store the starting index
            if (j == num) {
                indexes.add(i);
            }
        }
        
        return indexes;
    }
    
    // Time Complexity: O(N * M * L), Space Complexity: O(M)
}

/*
 * String s = "barfoothefoobarman";
String[] words = {"foo", "bar"};
Each word has len = 3, and we need to check for substrings that are of total length num * len = 2 * 3 = 6.

If i = 0, then:

j = 0: We extract s.substring(0 + 0 * 3, 0 + (0 + 1) * 3) → s.substring(0, 3) = "bar"
j = 1: We extract s.substring(0 + 1 * 3, 0 + (1 + 1) * 3) → s.substring(3, 6) = "foo"
Thus, "barfoo" is checked against the words in words.

 if i = 1, we extract:
j = 0: s.substring(1, 4) = "arf" (not valid)
j = 1: s.substring(4, 7) = "oot" (not valid)

*************************************************************************************************

Why n - num * len + 1 in the loop condition?
java
Copy
Edit
for (int i = 0; i < n - num * len + 1; i++)
This ensures that we do not go out of bounds.

n is the total length of s.
num * len is the total length of a valid substring that includes all words.
n - num * len gives us the last possible starting index i where a full substring of length num * len can fit.
We add +1 because Java’s substring(a, b) extracts up to b - 1, so we need to allow for the last valid i.
 */


 //Approach-2
 // Time Complexity: O(N * L), Space Complexity: O(M)  (Sliding Window + HashMap)
class Solution1 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indexes = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return indexes;

        int n = s.length(), num = words.length, len = words[0].length();
        int totalLen = num * len; // Total length of concatenated substring

        // Step 1: Store word counts
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Step 2: Try sliding window for each offset (0 to len - 1)
        for (int start = 0; start < len; start++) {
            int left = start, right = start;
            Map<String, Integer> seen = new HashMap<>();
            int count = 0; // Number of valid words in the window

            while (right + len <= n) {
                // Extract the next word
                String word = s.substring(right, right + len);
                right += len;

                if (wordCount.containsKey(word)) {
                    // Add the word to seen
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    // If a word appears more than expected, shrink the window
                    while (seen.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + len);
                        left += len;
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                    }

                    // If all words matched exactly, store the start index
                    if (count == num) {
                        indexes.add(left);
                    }
                } else {
                    // Reset window if an invalid word is found
                    seen.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return indexes;
    }
}
