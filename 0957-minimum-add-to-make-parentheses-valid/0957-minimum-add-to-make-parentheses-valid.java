// O(n) O(1)
class Solution {
    public int minAddToMakeValid(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0 ; i< s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                };
            }
        }
        return left + right;
    }
}