class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (i >= 0 || j >= 0) {
            int first = i >= 0 ? num1.charAt(i) - '0' : 0;
            int second = j >= 0 ? num2.charAt(j) - '0' : 0;
            num += first + second;
            sb.append(num % 10);
            num /= 10;
            i--;
            j--;
        }
        if (num > 0) {
            sb.append(num);
        }
        return sb.reverse().toString();
    }
}