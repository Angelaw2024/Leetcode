class Solution {
    /**
    下面来看一种递归解法，这种解法首先统计了多余的半括号的数量，用 cnt1 表示多余的左括号，cnt2 表示多余的右括号，因为给定字符串左右括号要么一样多，要么左括号多，要么右括号多，也可能左右括号都多，比如 “)(“。所以 cnt1 和 cnt2 要么都为0，要么都大于0，要么一个为0，另一个大于0。好，下面进入递归函数，首先判断，如果当 cnt1 和 cnt2 都为0时，说明此时左右括号个数相等了，调用 isValid 子函数来判断是否正确，正确的话加入结果 res 中并返回即可。否则从 start 开始遍历，这里的变量 start 表示当前递归开始的位置，不需要每次都从头开始，会有大量重复计算。而且对于多个相同的半括号在一起，只删除第一个，比如 “())”，这里有两个右括号，不管删第一个还是删第二个右括号都会得到 “()”，没有区别，所以只用算一次就行了，通过和上一个字符比较，如果不相同，说明是第一个右括号，如果相同则直接跳过。此时来看如果 cnt1 大于0，说明此时左括号多，而如果当前字符正好是左括号的时候，可以删掉当前左括号，继续调用递归，此时 cnt1 的值就应该减1，因为已经删掉了一个左括号。同理，如果 cnt2 大于0，说明此时右括号多，而如果当前字符正好是右括号的时候，可以删掉当前右括号，继续调用递归，此时 cnt2 的值就应该减1，因为已经删掉了一个右括号，
     */
    public List<String> removeInvalidParentheses(String s) {
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l > 0) {
                    l--;
                } else {
                    r++;
                }
            }
        }
        List<String> res = new ArrayList<>();
        helper(s, res, l, r, 0);
        return res;
    }
    private void helper(String s, List<String> res, int l, int r, int cur) {
        if (l == 0 && r == 0 && valid(s)) {
            res.add(new String(s));
            return;
        }
        for (int i = cur; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i > cur && c == s.charAt(i - 1)) continue;
            if (c == '(' && l > 0) {
                helper(s.substring(0, i) + s.substring(i + 1), res, l - 1, r, i);
            }
            if (c == ')' && r > 0) {
                helper(s.substring(0, i) + s.substring(i + 1), res, l, r - 1, i);
            }
        }
    }
    private boolean valid(String s) {
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l > 0) {
                    l--;
                } else {
                    r++;
                }
            }
        }
        return l == 0 && r == 0;
    }
}