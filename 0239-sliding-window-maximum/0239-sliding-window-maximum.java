class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        // Initialize the first window and TreeMap
        for (int i = 0; i < k; i++) {
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
        }

        // Record the maximum element for the first window
        result[0] = treeMap.lastKey();

        // Slide the window
        for (int i = k; i < n; i++) {
            // Add the new element
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);

            // Remove the left-most element of the previous window
            if (treeMap.get(nums[i - k]) == 1) {
                treeMap.remove(nums[i - k]);
            } else {
                treeMap.put(nums[i - k], treeMap.get(nums[i - k]) - 1);
            }

            // Record the maximum element for the current window
            result[i - k + 1] = treeMap.lastKey();
        }

        return result;
    }
}