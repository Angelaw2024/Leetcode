// class Solution {
//     public int[] searchRange(int[] nums, int target) {
//         int[] res = helper(nums, target, 0, nums.length - 1);
//         return res;
//     }
//     public int[] helper(int[] nums, int target, int l, int r) {
//         if (l > r) return new int[]{-1, -1};
//         if (l == r) {
//             if (nums[l] == target) return new int[]{l, l};
//             return new int[]{-1, -1};
//         }
//         int mid = l + (l - r) / 2;
//         int[] left = helper(nums, target, l, mid);
//         int[] right = helper(nums, target, mid + 1, r);
//         int[] cur = new int[2];
//         cur[0] = Math.min(left[0], right[0]);
//         cur[1] = Math.max(left[1], right[1]);
//         return cur;

//     }
// }

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};

        int first = findFirst(nums, target);
        int last = findFirst(nums, target + 1);
        int[] res = new int[]{-1, -1};
        if (nums[first] != target) return res;
        res[0] = first;
        res[1] = nums[last] == target ? last : last - 1;
        return res;
    }

    private int findFirst(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}