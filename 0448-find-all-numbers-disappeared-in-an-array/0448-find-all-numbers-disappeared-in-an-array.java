class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}

class Solution1 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        boolean[] appear = new boolean[nums.length + 1];
        Arrays.fill(appear, false);
        for (int num: nums) {
            if (num <= n) {
                appear[num] = true;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!appear[i]) {
                result.add(i);
            }
        }
        return result;
    }
}