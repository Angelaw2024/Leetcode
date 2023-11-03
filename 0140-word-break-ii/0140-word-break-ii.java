class Solution {
    List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        Set<String> dict = new HashSet(wordDict);
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(0, new ArrayList<>());

        int maxLength = 0;
        for (String word: wordDict) {
            maxLength = Math.max(word.length(), maxLength);
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= i - maxLength && j >= 0; j--) {
                if(!map.containsKey(j)) continue;
                String word = s.substring(j, i);
                if (dict.contains(word)) {
                    map.computeIfAbsent(i, k -> new ArrayList<>()).add(word);
                }
            }
        }
        if (!map.containsKey(s.length()))  return res;

        dfs(map, s.length(), new ArrayList());
        return res;
    }

    public void dfs(Map<Integer, List<String>> map, int end, List<String> cur) {
        if (end <= 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = cur.size() - 1; i >= 0; i--) {
                sb.append(" ").append(cur.get(i));
            }
            res.add(sb.toString().trim());
        }
        for (String word: map.get(end)) {
            cur.add(word);
            dfs(map, end - word.length(), cur);
            cur.remove(cur.size() - 1);
        }
    }
}