class Solution {
    /*
        1. have 2 pointer to track the start and end
        2. have variable to track the number of zero
        3. count the length 
     */
    public int longestOnes(int[] nums, int k) {
        int start = 0, count = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            if(nums[end] == 0) count++;
            if(count > k){
                while(start < end && nums[start] != 0){
                    start++;
                }
                count--;
                start++;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}