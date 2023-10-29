class Solution {
    int[] nums;
    Random rand;
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int result = -1;
        int targetIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;
            int pick = rand.nextInt(1 + targetIdx++);
            if (pick == 0) {
                result = i;
            }
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */