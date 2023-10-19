class Solution {
    List<String> res;

    public List<String> wordBreak(String s, List<String> wordDictList) {
        res = new ArrayList<>();
        Set<String> wordDict = new HashSet(wordDictList);
        List<String>[] dp = new ArrayList[s.length() + 1];
        dp[0] = new ArrayList<>();
        int maxLen = getMaxLen(wordDict);

        // 预处理 加快 backtracking
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= i - maxLen && j >= 0; j--) {
                if (dp[j] == null) continue; // only search from the index of last letter of word
                String word = s.substring(j, i);
                if (wordDict.contains(word)) {
                    if (dp[i] == null) {
                        dp[i] = new ArrayList<>();
                    }
                    dp[i].add(word);
                }
            }
        }

        if (dp[s.length()] == null) {
            return res;
        }

        dfs(dp, s.length(), new ArrayList<>());
        return res;
    }

    private void dfs(List<String>[] dp, int end, List<String> temp) {
        if (end <= 0) {
            StringBuilder path = new StringBuilder();
            for (int i = temp.size() - 1; i >= 0; i--) {
                path.append(" ").append(temp.get(i));
            }
            res.add(path.toString().trim());
            return;
        }
        for (String word : dp[end]) {
            temp.add(word);
            dfs(dp, end - word.length(), temp);
            temp.remove(temp.size() - 1);
        }
    }

    private int getMaxLen(Set<String> wordDict) {
        int res = 0;
        for (String w : wordDict) {
            res = Math.max(res, w.length());
        }
        return res;
    }
    
}