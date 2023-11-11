class Solution {
    public String minWindow(String s, String t) {
       // 如果 t 的长度大于 s 的长度，返回空字符串
        if (t.length() > s.length()) return "";

        // `dict` 用于存储 t 中每个字符的频率
        // `total` 用于存储 t 的长度
        int[] dict = new int[256];
        int total = 0;
        for (char c : t.toCharArray()) {
            dict[c]++;
            total++;
        }

        // `sDict` 用于存储当前 s 窗口中每个字符的频率
        // `count` 用于存储当前窗口中也在 t 中的字符数量
        int[] sDict = new int[256];
        int count = 0;
        int start = 0; // 当前窗口的起始索引
        int minStart = -1; // 到目前为止找到的最小窗口的起始索引
        int minEnd = -1; // 到目前为止找到的最小窗口的结束索引
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            // 如果字符存在于 t 中，更新 `sDict` 和 `count`
            if (dict[c] > 0) {
                sDict[c]++;
                if (sDict[c] <= dict[c]) {
                    count++;
                }
            }

            // 如果窗口包含 t 中的所有字符
            if (count == total) {
                // 从左侧缩小窗口
                while (dict[s.charAt(start)] == 0 || sDict[s.charAt(start)] > dict[s.charAt(start)]) {
                    if (sDict[s.charAt(start)] > dict[s.charAt(start)]) {
                        sDict[s.charAt(start)]--;
                    }
                    start++;
                }
                // 如果需要，更新最小窗口
                if (minStart == -1 || end - start < minEnd - minStart) {
                    minStart = start;
                    minEnd = end;
                }
            }
        }

        return minStart == -1 ? "" : s.substring(minStart, minEnd + 1);
    }
}