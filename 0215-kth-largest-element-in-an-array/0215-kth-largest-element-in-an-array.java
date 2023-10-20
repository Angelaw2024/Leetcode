class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int target = nums.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int pivot = partition(nums, left, right);
            if (pivot == target) {
                return nums[target];
            } else if (pivot < target) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return nums[left];
    }
    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        int pivotNum = nums[lo];
        while (i < j) {
            while (nums[++i] < pivotNum) {
                if (i >= hi) break;
            }
            while (nums[--j] > pivotNum) {
                if (j <= lo) break;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, j, lo);
        return j;
    }

    private int partition2(int[] nums, int lo, int hi) {
        int i = lo + 1, j = hi;
        int pivotNum = nums[lo];
        while (true) {
            while (nums[i] < pivotNum) {
                if (i >= hi) break;
                i++;
            }
            while (nums[j] > pivotNum) {
                if (j <= lo) break;
                j--;
            }
            if (i >= j) break;
            swap(nums, i, j);
            i++;
            j--;
        }
        swap(nums, j, lo);
        return j;
    }
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}