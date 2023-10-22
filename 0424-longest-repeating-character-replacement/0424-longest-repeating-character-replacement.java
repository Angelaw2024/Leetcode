class Solution {
    public int characterReplacement(String s, int k) {
        int start = 0; // 窗口的开始位置
        int[] frequencyMap = new int[26]; // 字符频率映射
        int maxFrequency = 0; // 窗口中最高频率的字符的频率
        int longestSubstringLength = 0; // 最长有效子字符串的长度

        // 遍历整个字符串
        for (int end = 0; end < s.length(); end++) {
            // 计算当前字符相对于'A'的偏移量
            int currentChar = s.charAt(end) - 'A';

            // 更新当前字符的频率
            frequencyMap[currentChar]++;

            // 更新窗口中最高频率的字符的频率
            maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

            // 检查当前窗口是否有效
            boolean isValid = (end + 1 - start - maxFrequency <= k);
            if (!isValid) {
                // 计算移出窗口的字符相对于'A'的偏移量
                int outgoingChar = s.charAt(start) - 'A';

                // 减少移出窗口字符的频率
                frequencyMap[outgoingChar]--;

                // 移动窗口的开始位置
                start++;
            }

            // 更新最长有效子字符串的长度
            longestSubstringLength = end + 1 - start;
        }

        return longestSubstringLength;
    }
}
