class Solution {
    // public boolean wordBreak(String s, List<String> wordDict) {
    //     Set<String> set = new HashSet<>(wordDict);
    //     boolean[] valid = new boolean[s.length() + 1];
    //     valid[0] = true;
    //     for (int i = 1; i <= s.length(); i++) {
    //         for (int j = i - 1; j >= 0; j--) {
    //             if (valid[j] && set.contains(s.substring(j, i))) {
    //                 valid[i] = true;
    //             }
    //         }
    //     }
    //     return valid[s.length()];
    // }
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] dp = new Boolean[s.length()+1];
        return wordBreak(s, dp, wordDict, 0);
    }

    private boolean wordBreak(String s, Boolean[] dp, List<String> wordDict, int k){
        if(k==s.length()){
            return true;
        }
        if(dp[k]!=null){
            return dp[k];
        }
        for(int i=0;i<wordDict.size();i++){
            if(s.startsWith(wordDict.get(i), k)){
                if(wordBreak(s, dp, wordDict, k+wordDict.get(i).length())){
                    dp[k] = true;
                    return true;
                }
            }
        }
        dp[k] = false;
        return false;
    }
}