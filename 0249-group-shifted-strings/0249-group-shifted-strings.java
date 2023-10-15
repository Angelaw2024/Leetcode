class Solution {
    /**
    那就是字符串的每个字母和首字符的相对距离都是相等的，比如 abc 和 efg 互为偏移，对于 abc 来说，b和a的距离是1，c和a的距离是2，对于 efg 来说，f和e的距离是1，g和e的距离是2。再来看一个例子，az 和 yx，z和a的距离是 25，x和y的距离也是 25 (直接相减是 -1，这就是要加 26 然后取余的原因)，那么这样的话，所有互为偏移的字符串都有个 unique 的距离差，根据这个来建立映射就可以很好的进行单词分组了
     */
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            StringBuilder key = new StringBuilder();
            char initial = s.charAt(0);
            for (char c : s.toCharArray()) {
                // Shift difference computed and appended to the key
                key.append((c - initial + 26) % 26).append(",");
            }
            // ComputeIfAbsent to avoid checking containsKey separately
            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }
}