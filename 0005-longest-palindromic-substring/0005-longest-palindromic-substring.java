public class Solution {
    public String longestPalindrome(String s) {
        // 用于存储最长回文子串的起始和结束索引
        int[] ans = new int[]{0, 0};
        
        // 遍历字符串中的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 以 i 为中心点，查找长度为奇数的回文子串
            int oddLength = expand(i, i, s);
            // 更新最长回文子串的信息
            if (oddLength > ans[1] - ans[0] + 1) {
                int dist = oddLength / 2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }
            
            // 以 i 和 i + 1 为中心点，查找长度为偶数的回文子串
            int evenLength = expand(i, i + 1, s);
            // 更新最长回文子串的信息
            if (evenLength > ans[1] - ans[0] + 1) {
                int dist = (evenLength / 2) - 1;
                ans[0] = i - dist;
                ans[1] = i + 1 + dist;
            }
        }

        int i = ans[0];
        int j = ans[1];
        // 返回最长回文子串
        return s.substring(i, j + 1);
    }
    
    // 扩展函数，用于查找以 i 和 j 为中心的回文子串的长度
    private int expand(int i, int j, String s) {
        int left = i;
        int right = j;
        
        // 扩展边界，直到找到不满足回文条件的位置
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // 返回回文子串的长度
        return right - left - 1;
    }
}
