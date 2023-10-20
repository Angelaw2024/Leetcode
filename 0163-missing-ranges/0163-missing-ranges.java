class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            res.add(Arrays.asList(lower, upper));
            return res;
        }

        int l = lower;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > l) {
                res.add(Arrays.asList(l, nums[i] - 1));
            }
            l = nums[i] + 1;
        }
        if (l <= upper) {
            res.add(Arrays.asList(l, upper));
        }
        return res;
    }
}