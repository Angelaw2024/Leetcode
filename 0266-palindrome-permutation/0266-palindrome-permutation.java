class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (dict[i] != 0) {
                int cur = dict[i] % 2;
                if (cur == 1 && count == 1) {
                    return false;
                }
                count += cur;
            }
        }
        return true;
    }
}