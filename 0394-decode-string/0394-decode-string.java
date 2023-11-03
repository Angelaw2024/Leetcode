class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            } else if (c == '[') {
                countStack.push(k); 
                stringStack.push(currentString); 
                currentString = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                StringBuilder decodedString = stringStack.pop();
                int currentCount = countStack.pop();
                while (currentCount-- > 0) {
                    decodedString.append(currentString);
                }
                currentString = decodedString; 
            } else {
                currentString.append(c); 
            }
        }
        return currentString.toString();
    }
}