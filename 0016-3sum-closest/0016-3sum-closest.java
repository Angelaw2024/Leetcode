class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return Integer.MAX_VALUE;
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            int sum = Integer.MAX_VALUE;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }
                if (sum == target){
                    return sum;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}