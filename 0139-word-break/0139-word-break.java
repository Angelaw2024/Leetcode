class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] valid = new boolean[s.length() + 1];
        valid[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (valid[j] && set.contains(s.substring(j, i))) {
                    valid[i] = true;
                }
            }
        }
        return valid[s.length()];
    }
}