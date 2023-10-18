class Solution {
    int[] nums;
    Random rand;
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int res = -1;
        int targetIdxs = 0;
        for (int i = 0; i < this.nums.length; i++) {
            if (this.nums[i] != target) {
                continue;
            }
            int pick = rand.nextInt(1 + targetIdxs++);
            if (pick == 0) {
                res = i;
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */