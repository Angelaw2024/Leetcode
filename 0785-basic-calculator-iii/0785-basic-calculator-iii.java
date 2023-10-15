class Solution {
    int idx = 0;
    public int calculate(String s) {
        int num = 0;
        int cur = 0;
        int result = 0;
        char prevOp = '+';

        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                idx++; 
                num = calculate(s); 
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || idx == s.length() - 1 || c == ')') {
                if (prevOp == '+'){
                    cur += num;
               } else if (prevOp == '-') {
                   cur -= num;
               } else if (prevOp == '*') {
                   cur *= num;
               } else if (prevOp == '/') {
                   cur /= num;
               }
               if (c == '+' || c == '-' || c == ')' || idx == s.length() - 1) {
                   result += cur;
                   cur = 0;
               }
               prevOp = c;
               num = 0;
            }

            if (c == ')') {
                break; 
            }

            idx++;
        }

        return result;
    }
}