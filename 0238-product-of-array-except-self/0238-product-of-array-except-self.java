class Solution {
    /*
    [1, 2, 6, 24]
    [24, 24, 12, 4]
    [24, 12, 8, 6]
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums.length < 2) return nums;
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int r = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * r;
            r *= nums[i];
         }
        return res;
    }
}