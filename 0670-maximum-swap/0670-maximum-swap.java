class Solution {
    /**
    实际上只关注两个需要交换的位置即可，即高位上的小数字和低位上的大数字，分别用 pos1 和 pos2 指向其位置，均初始化为 -1，然后用一个指针 mx 指向低位最大数字的位置，初始化为 n-1，然后从倒数第二个数字开始往前遍历，假如 str[i] 小于 str[mx]，说明此时高位上的数字小于低位上的数字，是一对儿潜在可以交换的对象（但并不保证上最优解），此时将 pos1 和 pos2 分别赋值为 i 和 mx；若 str[i] 大于 str[mx]，说明此时 str[mx] 不是低位最大数，将 mx 更新为 i。循环结束后，若 pos1 不为 -1，说明此时找到了可以交换的对象，而且找到的一定是最优解，直接交换即可
     */
    public int maximumSwap(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        int n = chars.length;
        int pos1 = -1;
        int pos2 = -2;
        int mx = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (chars[i] < chars[mx]) {
                pos1 = i;
                pos2 = mx;
            } else if (chars[i] > chars[mx]) {
                mx = i;
            }
        }
        if (pos1 != -1) {
            char c = chars[pos1];
            chars[pos1] = chars[pos2];
            chars[pos2] = c;
        }
        return Integer.valueOf(new String(chars));
    }
}