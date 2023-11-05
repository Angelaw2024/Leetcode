class Solution {
    public List<String> generatePalindromes(String s) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }

        // Check if the string can form a 
        int oddCount = 0;
        String mid = "";
        for (int i = 0; i < 26; i++) {
            if (dict[i] % 2 == 1) {
                oddCount++;
                mid += (char)(i + 'a');
            }
            dict[i] /= 2; // Only need half of each char for forming half palindrome
        }

        // More than one char with odd count cannot form a palindrome
        if (oddCount > 1) return new ArrayList<String>();

        List<String> res = new ArrayList<>();
        backtrack(dict, res, new StringBuilder(), s.length() / 2, mid);
        return res;
    }

    public void backtrack(int[] dict, List<String> res, StringBuilder half, int length, String mid) {
        if (half.length() == length) {
            res.add(half.toString() + mid + half.reverse().toString());
            half.reverse(); // Need to reverse back to keep the original order for backtracking
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (dict[i] > 0) {
                dict[i]--;
                half.append((char)(i + 'a'));
                backtrack(dict, res, half, length, mid);
                half.deleteCharAt(half.length() - 1);
                dict[i]++;
            } 
        }
    }
}