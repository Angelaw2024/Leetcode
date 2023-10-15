class Solution {
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