class Solution {
    // Find the last acceding element x, swap with the smallest number y, y is after x that and y is greater than x.
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        int partition = nums.length - 2;

        while (partition >= 0 && nums[partition + 1] <= nums[partition]) {
            partition--;
        }
        if (partition >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[partition]) {
                j--;
            }
            swap(nums, partition, j);
        }
        reorder(nums, partition + 1);
    }
    private void reorder(int[] nums, int begin) {
        int end = nums.length - 1;
        while(begin < end) {
            swap(nums, begin++, end--);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}