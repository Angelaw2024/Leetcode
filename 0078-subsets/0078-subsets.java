class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result, new ArrayList<>());
        return result;
    }
    public void backtrack(int[] nums, int idx, List<List<Integer>> result, List<Integer> cur) {
        result.add(new ArrayList<>(cur));
        for (int i = idx; i < nums.length; i++) {
            cur.add(nums[i]);
            backtrack(nums, i + 1, result, cur);
            cur.remove(cur.size() - 1);
        }
    } 
}