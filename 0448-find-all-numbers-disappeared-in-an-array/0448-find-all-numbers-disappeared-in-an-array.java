class Solution {
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