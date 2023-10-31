// ave O(n) worst O(n^2)  space O(1)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;

        while (left < right) {
            int pivotIdx = partition(nums, left, right);
            if (pivotIdx == target) return nums[target];
            if (pivotIdx < target) { 
                left = pivotIdx + 1;
            } else {
                right = pivotIdx - 1;
            }
        }
        return nums[left];
    }

    public int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        int pivot = nums[lo];

        while (true) {
            while (nums[++i] < pivot) {
                if (i == hi) break;
            }
            while (nums[--j] > pivot) {
                if (j == lo) break;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, j, lo);
        return j;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
