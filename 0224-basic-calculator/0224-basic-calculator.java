class Solution {
    int idx = 0;
    public int calculate(String s) {
        int sign = 1;
        int num = 0;
        int result = 0;
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                idx++;
                num = calculate(s);
            } else if (c == ')') {
                return result + sign * num;
            } else if (c == '+' || c == '-') {
                result += sign * num;
                num = 0;
                sign = c == '+' ? 1 : -1;
            }
            idx++;
        }
        return result + sign * num;
    }
}