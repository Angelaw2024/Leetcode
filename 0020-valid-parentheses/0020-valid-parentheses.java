class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        int i = 0;
        while (i < c.length) {
            if (c[i] == '(' || c[i] == '{' || c[i] == '[') {
                stack.push(c[i]);
            } else if (c[i] == ')') {
                if (stack.size() == 0) return false;
                char pre = stack.pop();
                if (pre != '(') return false;
            } else if (c[i] == '}') {
                if (stack.size() == 0) return false;
                char pre = stack.pop();
                if (pre != '{') return false;
            } else if (c[i] == ']') {
                if (stack.size() == 0) return false;
                char pre = stack.pop();
                if (pre != '[') return false;
            }
            i++;
        }
        return stack.size() == 0;
    }
}