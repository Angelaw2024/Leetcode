class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr.length() > word.length()) return false;
        int num = 0;
        int j = 0;
        for (int i = 0; i < abbr.length(); i++) {
            char c = abbr.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
                if (num == 0) return false;
            } else {
                j += num;
                if (j >= word.length() || word.charAt(j) != c) {
                    return false;
                }
                j++;
                num = 0;
            }
        }
        j += num;
        return j == word.length();
    }
}