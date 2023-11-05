class Solution {
    public List<String> generatePalindromes(String s) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }

        // Check if the string can form a 
        int oddCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (dict[i] % 2 == 1) {
                oddCount++;
                sb.append((char)(i + 'a'));
                dict[i]--;
            }
        }

        // More than one char with odd count cannot form a palindrome
        if (oddCount > 1) return new ArrayList<String>();

        List<String> res = new ArrayList<>();
        backtrack(dict, res, sb, s.length());
        return res;
    }

    public void backtrack(int[] dict, List<String> res, StringBuilder sb, int length) {
        if (sb.length() == length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (dict[i] > 0) {
                dict[i] -= 2;
                sb.append((char)(i + 'a'));
                sb.insert(0, (char)(i + 'a'));
                backtrack(dict, res, sb, length);
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(0);
                dict[i] +=2;
            } 
        }
    }
}