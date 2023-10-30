class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] dict = new int[26];
        for (char c: magazine.toCharArray()) {
            dict[c - 'a']++;
        }
        for (char c: ransomNote.toCharArray()) {
            dict[c - 'a']--;
            if (dict[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}