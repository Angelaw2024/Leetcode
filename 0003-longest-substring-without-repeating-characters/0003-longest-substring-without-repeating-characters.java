class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s == "") return 0;
        char[] c = s.toCharArray();
        int ans = 0;
        int l = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            while (set.contains(c[i])) {
                set.remove(c[l]);
                l++;
            }
            ans = Math.max(i - l + 1, ans);
            set.add(c[i]);
        }
        return ans;
    }
}