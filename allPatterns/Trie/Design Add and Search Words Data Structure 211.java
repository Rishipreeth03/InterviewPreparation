/*
 * Leetcode Link               : https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */
/*
 * Time Complexity:
 * - addWord: O(L) where L is the length of the word being added.
 * - search: O(L * 26^D) where L is the word length and D is the number of wildcards ('.') in the word.
 *   - Worst-case occurs when each '.' requires checking 26 possible paths.
 * 
 * Space Complexity:
 * - O(N * L) where N is the number of words and L is the average word length.
 *   - Each word creates a unique path in the trie, contributing to space usage.
 */
class WordDictionary {
    class TrieNode{
        TrieNode c[];
        boolean eow;
        TrieNode(){
            c=new TrieNode[26];
            eow=false;
        }
    }
    private TrieNode root;
    public WordDictionary() {
        root=new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode curr=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(curr.c[index]==null){
                curr.c[index]=new TrieNode();
            }
            curr=curr.c[index];
        }
        curr.eow=true;
    }
    
    public boolean search(String word) {
          return searchUtil(root,word);
    }
    
    public boolean searchUtil(TrieNode root,String word){
        TrieNode curr=root;
        for(int j=0;j<word.length();j++){
            if(word.charAt(j)=='.'){
                for(int i=0;i<26;i++){
                    if(curr.c[i]!=null && searchUtil(curr.c[i],word.substring(j+1)) ){
                        return true;
                    }
                }
                return false;
            }else{
                int index=word.charAt(j)-'a';
                if(curr.c[index]==null)return false;
                curr=curr.c[index];
            }
        }
        return curr.eow && curr!=null;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */