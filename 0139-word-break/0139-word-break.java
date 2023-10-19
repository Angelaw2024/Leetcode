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

    private String s;
    private List<String> wordDict;
    private int[] memo;
    
    private boolean dp(int i) {
        if (i < 0) return true;
        
        if (memo[i] != -1) {
            return memo[i] == 1;
        }
        
        for (String word: wordDict) {
            // Handle out of bounds case
            if (i - word.length() + 1 < 0) {
                continue;
            }
            
            if (s.substring(i - word.length() + 1, i + 1).equals(word) && dp(i - word.length())) {
                memo[i] = 1;
                return true;
            }
        }
        
        memo[i] = 0;
        return false;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = wordDict;
        this.memo = new int[s.length()];
        Arrays.fill(this.memo, -1);
        return dp(s.length() - 1);
    }
}