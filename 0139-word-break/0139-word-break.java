class Solution {
    // public boolean wordBreak(String s, List<String> wordDict) {
    //     Set<String> set = new HashSet<>(wordDict);
    //     boolean[] valid = new boolean[s.length() + 1];
    //     valid[0] = true;
    //     for (int i = 1; i <= s.length(); i++) {
    //         for (int j = 0; j < i; j++) {
    //             if (valid[j] && set.contains(s.substring(j, i))) {
    //                 valid[i] = true;
    //                 break;
    //             }
    //         }
    //     }
    //     return valid[s.length()];
    // }

    // private String s;
    // private List<String> wordDict;
    // private int[] memo;
    
    // private boolean dp(int i) {
    //     if (i < 0) return true;
        
    //     if (memo[i] != -1) {
    //         return memo[i] == 1;
    //     }
        
    //     for (String word: wordDict) {
    //         // Handle out of bounds case
    //         if (i - word.length() + 1 < 0) {
    //             continue;
    //         }
            
    //         if (s.substring(i - word.length() + 1, i + 1).equals(word) && dp(i - word.length())) {
    //             memo[i] = 1;
    //             return true;
    //         }
    //     }
        
    //     memo[i] = 0;
    //     return false;
    // }
    
    // public boolean wordBreak(String s, List<String> wordDict) {
    //     this.s = s;
    //     this.wordDict = wordDict;
    //     this.memo = new int[s.length()];
    //     Arrays.fill(this.memo, -1);
    //     return dp(s.length() - 1);
    // }

    class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;
        TrieNode() {
            this.children = new HashMap<>();
        }
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word: wordDict) {
            TrieNode curr = root;
            for (char c: word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }

                curr = curr.children.get(c);
            }
            
            curr.isWord = true;
        }
        
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || dp[i - 1]) {
                TrieNode curr = root;
                for (int j = i; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (!curr.children.containsKey(c)) {
                        // No words exist
                        break;
                    }
                    
                    curr = curr.children.get(c);
                    if (curr.isWord) {
                        dp[j] = true;
                    }
                }
            }
        }
        
        return dp[s.length() - 1];
    }
}