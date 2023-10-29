class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] toReturn = divide_and_conquer(nums, target, 0, nums.length - 1);
        return toReturn[0] < Integer.MAX_VALUE ? toReturn : new int[] {-1, -1};
    }
    
    public int[] divide_and_conquer(int[] nums, int target, int low, int high) {
        int[] ret = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        
        if(low > high) return ret;
        
        if(low == high && nums[low] == target) {
            ret[0] = low;
            ret[1] = high;
            return ret;
        }
        // might need to go
        else if(low == high) {
            return ret;
        }
        
        else {
            int mid = low + (high - low)/2;
            int[] left = divide_and_conquer(nums, target, low, mid);
            int[] right = divide_and_conquer(nums, target, mid+1, high);
            
            ret[0] = Math.min(left[0], right[0]);
            ret[1] = Math.max(left[1], right[1]);
        }
        return ret;
    }
}

class Solution1 {
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