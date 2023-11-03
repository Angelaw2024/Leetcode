public class Solution {
    public int trap(int[] height) {
        // 如果数组长度小于或等于2，不能接雨水
        if (height.length <= 2) return 0;

        // 初始化左右两边的最大值
        int lmax = height[0];
        int rmax = height[height.length - 1];

        // 初始化结果
        int ans = 0;

        // 初始化左右两个指针
        int l = 0, r = height.length - 1;

        // 当左指针小于右指针时，进行循环
        while (l < r) {
            // 更新左侧最大值
            lmax = Math.max(lmax, height[l]);
            // 更新右侧最大值
            rmax = Math.max(rmax, height[r]);

            // 如果左侧最大值小于右侧最大值
            if (lmax < rmax) {
                // 计算能接的雨水并累加到结果
                ans += lmax - height[l];
                // 移动左指针
                l++;
            } else {
                // 计算能接的雨水并累加到结果
                ans += rmax - height[r];
                // 移动右指针
                r--;
            }
        }
        
        return ans;
    }
}
