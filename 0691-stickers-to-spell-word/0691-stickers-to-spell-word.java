class Solution {
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