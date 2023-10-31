class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        
        int[] map = new int[256];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int found = 0;
        int minStart = 0;
        int begin = 0, end = 0;
        int d = Integer.MAX_VALUE;
        while (end < s.length()) {
            if(map[s.charAt(end)] >0) {
                found++;
            }
            map[s.charAt(end)]--; 
            end++;
            while (found == t.length()) { // counter condition
                if (end - begin < d) {
                    minStart = begin;
                    d = end - begin;
                }
                if (map[s.charAt(begin)] == 0) {
                    found--;
                }
                map[s.charAt(begin)]++;
                begin++;
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + d);
    }
}