package 买它;
import java.util.*;

class ValidPalindrom {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean test = solution.isPalindrome("Racecar"); // Should return true

    }
 }

 class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int strLen = s.length();
        int l = 0, r = strLen - 1;

        while (l < r) {
            // 获取左边的字符的Unicode码点
            int codePointL = s.codePointAt(l);
            // 如果不是字母或数字，向右移动
            while (l < r && !Character.isLetterOrDigit(codePointL)) {
                l += Character.charCount(codePointL);
                if (l < strLen) codePointL = s.codePointAt(l);
            }

            // 获取右边的字符的Unicode码点
            int codePointR = s.codePointBefore(r + 1);
            // 如果不是字母或数字，向左移动
            while (l < r && !Character.isLetterOrDigit(codePointR)) {
                r -= Character.charCount(codePointR);
                if (r > 0) codePointR = s.codePointBefore(r + 1);
            }

            // 比较两个码点
            if (l < r && codePointL != codePointR) {
                return false;
            }

            // 向中心移动
            l += Character.charCount(codePointL);
            r -= Character.charCount(codePointR);
        }

        return true;
    }
}
