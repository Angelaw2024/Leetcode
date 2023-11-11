class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int count = 0;

        Comparator<Integer> comparator = (a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]);
        TreeSet<Integer> left = new TreeSet<>(comparator);
        TreeSet<Integer> right = new TreeSet<>(comparator);

        for (int i = 0; i < nums.length; i++) {
            right.add(i);
            balance(left, right);
            
            if (i >= k - 1) {
                if (i >= k) {
                    if (!left.remove(i - k)) {
                        right.remove(i - k);
                    }
                    balance(left, right);
                }
                if (k % 2 == 0) {
                    res[count++] = ((double) nums[left.last()] + nums[right.first()]) / 2;
                } else {
                    res[count++] = (double) nums[left.last()];
                }
            }
        }
        return res;
    }
// Method to balance left and right TreeSets
        public void balance(TreeSet<Integer> left, TreeSet<Integer> right ) {
            while (left.size() <= right.size()) {
                left.add(right.pollFirst());
            }
            while (left.size() > right.size() + 1) {
                right.add(left.pollLast());
            }
        }
}