class Solution {
    /**下面这种解法是带记忆数组memo的递归解法，可以看作是DP解法的递归形式，核心思想都一样。只不过dp数组换成了哈希Map，建立子集合跟最小使用的sticker的个数之间的映射，初始化空集为0，我们首先统计每个sticker的各个字母出现的频率，放到对应的二维数组freq中，然后就是调用递归函数。在递归函数中，首先判断，如果target已经在memo中，直接返回其值。否则我们开始计算，首先统计出此时的target字符串的各个字母出现次数，然后我们遍历统计所有sticker中各个字母出现次数的数组freq，如果target字符串的第一个字母不在当前sticker中，我们直接跳过，注意递归函数中的target字符串不是原始的字符串，我们心间一个临时字符串t，然后我们遍历target字符串中存在的字符，如果target中的某字符存在的个数多于sticker中对应的字符，那么将多余的部分存在字符串t中，表示当前sticker无法拼出的字符，交给下一个递归函数来处理，我们看再次调用递归函数的结果ans，如果不为-1，说明可以拼出剩余的那些字符，那么此时我们的res更新为ans+1，循环退出后，此时我们的res就应该是组成当前递归函数中的target串的最少贴片数，更新dp[target]值，如果res是INT_MAX，说明无法拼出，更新为-1，否则更新为res，
     */
    private Map<String, Integer> map;
    int[][] pool;
    int[] targetMap;
    public int minStickers(String[] stickers, String target) {
        map = new HashMap<>();
        map.put("", 0);

        // add stickers to pool;
        pool = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                pool[i][c - 'a']++;
            }
        }

        targetMap = new int[26];
        for (char c : target.toCharArray()) {
            targetMap[c - 'a']++;
        }
        return dfs(target);
    }

    private int[] toMap(String word) {
        int[] dict = new int[26];
        for (char ch : word.toCharArray()) {
            dict[ch - 'a']++;
        }
        return dict;
    }

    private int dfs(String target) {
        if (map.containsKey(target)) return map.get(target);
        int min = Integer.MAX_VALUE;

        for (int[] w : pool) {
            // Skip words that don't cover the first character of target. we limit the choice of paths by
                // selecting only the words that contains the first character in the target word, much faster
            if (w[target.charAt(0) - 'a'] <= 0) {
                continue;
            }

            int[] curMap = toMap(target);
            StringBuilder newTarget = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (curMap[i] > 0) {
                    for ( int time = 0; time < Math.max(curMap[i] - w[i], 0); time++) {
                        newTarget.append((char) ('a' + i));
                    }
                }
            }
            int rest = dfs(newTarget.toString());
            if (rest != -1) {
                min = Math.min(1 + rest, min);
            }
        }

        int result = (min == Integer.MAX_VALUE) ? -1 : min;
        map.put(target, result);
        return result;
    }
}