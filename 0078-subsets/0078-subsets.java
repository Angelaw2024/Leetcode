class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                List<Integer> arr = new ArrayList<>(list.get(j));
                arr.add(nums[i]);
                list.add(arr);
            }
        }
        return list;
    }
}