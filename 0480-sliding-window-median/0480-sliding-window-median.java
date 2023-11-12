class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int count = 0;

        Comparator<Integer> comparator = (a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]);
        TreeSet<Integer> small = new TreeSet<>(comparator);
        TreeSet<Integer> large = new TreeSet<>(comparator);

        for (int i = 0; i < nums.length; i++) {
            large.add(i);
            balance(small, large);
            
            if (i >= k - 1) {
                if (i >= k) {
                    if (!small.remove(i - k)) {
                        large.remove(i - k);
                    }
                    balance(small, large);
                }
                if (k % 2 == 0) {
                    res[count++] = ((double) nums[small.last()] + nums[large.first()]) / 2;
                } else {
                    res[count++] = (double) nums[small.last()];
                }
            }
        }
        return res;
    }
// Method to balance left and right TreeSets
        public void balance(TreeSet<Integer> small, TreeSet<Integer> large ) {
            while (small.size() <= large.size()) {
                small.add(large.pollFirst());
            }
            while (small.size() > large.size() + 1) {
                large.add(small.pollLast());
            }
        }
}