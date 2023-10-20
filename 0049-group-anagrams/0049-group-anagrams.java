class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String s: strs) {
            Arrays.fill(count, 0);
            String key = getKey(count, s);
            
            if (!map.containsKey(key)) {
                
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }

    public String getKey(int[] count, String s) {
        for (char c: s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append(count[i]);
            sb.append(",");
        }
        return sb.toString();
    }
}