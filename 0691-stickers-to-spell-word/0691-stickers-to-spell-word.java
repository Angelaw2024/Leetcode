class Solution {
    Map<String, Integer> map; // string, # of stickers
    int[][] pool; 
    int[] targetMap;

    public int minStickers(String[] stickers, String target) {
        
        map = new HashMap<>();
        map.put("", 0);
        
        pool = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            pool[i] = toMap(stickers[i]);
        }

        targetMap = toMap(target);
        return dfs(target);
    }

    private int[] toMap(String word) {
        int[] dict = new int[26];
        for (char ch : word.toCharArray()) {
            dict[ch - 'a']++;
        }
        return dict;
    }

    public int dfs(String target) {
        if (map.containsKey(target)) return map.get(target);
        
        int min = Integer.MAX_VALUE;
        for (int[] word: pool) {
            if (word[target.charAt(0) - 'a'] <= 0) continue;
            int[] curMap = toMap(target);
            StringBuilder newTarget = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (curMap[i] > 0) {
                    for (int time = 0; time < Math.max(curMap[i] - word[i], 0); time++) {
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