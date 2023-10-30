class Solution {
    // memo数组用于存储已经解决的子问题
    private int[][] memo;

    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        memo = new int[n][n];
        // 计算将字符串s变为回文串所需删除的最少字符数
        int minDeletions = helper(s, 0, n - 1);
        // 如果需要删除的字符数小于等于k，则返回true
        return minDeletions <= k;
    }

    private int helper(String s, int left, int right) {
        // 基本情况：如果字符串为空或只有一个字符，那它就是一个回文串
        if (left >= right) {
            return 0;
        }
        
        // 检查这个子问题是否已经解决过
        if (memo[left][right] != 0) {
            return memo[left][right];
        }

        // 情况1：两端的字符匹配
        if (s.charAt(left) == s.charAt(right)) {
            memo[left][right] = helper(s, left + 1, right - 1);
        } 
        // 情况2：两端的字符不匹配
        else {
            // 删除左端字符或右端字符，选择需要删除字符数较少的方案
            int removeLeft = 1 + helper(s, left + 1, right);
            int removeRight = 1 + helper(s, left, right - 1);
            memo[left][right] = Math.min(removeLeft, removeRight);
        }

        return memo[left][right];
    }
}