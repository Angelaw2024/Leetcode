public class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0]; // 初始化最大乘积结果
        int positive = 1;  // 当前最大的正数乘积
        int negative = 1;  // 当前最小的负数乘积

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            // 当前数是非负数
            if (x >= 0) {
                positive = Math.max(positive * x, x); // 更新最大正数乘积
                negative = negative * x;              // 更新最小负数乘积
            } else {
                // 当前数是负数，交换 positive 和 negative
                int tmp = negative;
                negative = Math.min(positive * x, x); // 更新最小负数乘积
                positive = tmp * x;                   // 更新最大正数乘积
            }

            // 更新最大乘积结果
            res = Math.max(res, positive);
            res = Math.max(res, negative);
        }
        return res; // 返回最大乘积结果
    }
}
