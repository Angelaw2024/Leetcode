class Solution {
    public int calculate(String s) {
        int res = 0;
        int num = 0;
        int cur = 0;
        char prevOp = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                if (prevOp == '+') {
                    cur += num;
                } else if (prevOp == '-') {
                    cur -= num;
                } else if (prevOp == '*') {
                    cur *= num;
                } else if (prevOp == '/') {
                    cur /= num;
                }

                if (c == '+' || c == '-' || i == s.length() - 1) {
                    res += cur;
                    cur = 0;
                }
                prevOp = c;
                num = 0;
            }
        }
        return res;
    }
}