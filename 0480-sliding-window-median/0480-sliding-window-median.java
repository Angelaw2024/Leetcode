class Solution {
    // PriorityQueue<Integer> left;
    // PriorityQueue<Integer> right;
    // public double[] medianSlidingWindow1(int[] nums, int k) {
    //     if (nums == null || nums.length == 0 || nums.length < k) {
    //         return new double[]{};
    //     }
    //     int n = nums.length;
    //     double[] res = new double[n - k + 1];

    //     left = new PriorityQueue<>(Collections.reverseOrder());
    //     right = new PriorityQueue<>();

    //     for (int i = 0; i <= n; i++) {
    //         if (i >= k) {
    //             res[i - k] = getMedian();
    //             remove(nums[i - k]);
    //         }

    //         if (i < n) {
    //             add(nums[i]);
    //         }
    //     }
    //     return res;
    // }

    // // maintain left size = right size + 1 if odd k
    // private double getMedian() {
    //     if (left.isEmpty() && right.isEmpty()) {
    //         return 0;
    //     } else if (left.size() == right.size()) {
    //         return ((double) left.peek() + (double) right.peek()) / 2.0;
    //     } else {
    //         return left.peek();
    //     }
    // }

    // private void add(int val) {
    //     double median = getMedian();
    //     if (val <= median) {
    //         left.offer(val);
    //     } else {
    //         right.offer(val);
    //     }
    //     balance();
    // }

    // private void remove(int val) {
    //     double median = getMedian();
    //     if (val <= median) {
    //         left.remove(val);
    //     } else {
    //         right.remove(val);
    //     }
    //     balance();
    // }

    // private void balance() {
    //     if (right.size() > left.size()) {
    //         left.offer(right.poll());
    //     } else if (left.size() > right.size() + 1) {
    //         right.offer(left.poll());
    //     }
    // }

    public double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);
        
        Supplier<Double> median = (k % 2 == 0) ?
            () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
            () -> (double) nums[right.first()];
        
        // balance lefts size and rights size (if not equal then right will be larger by one)
        Runnable balance = () -> { while (left.size() > right.size()) right.add(left.pollFirst()); };
        
        double[] result = new double[nums.length - k + 1];
        
        for (int i = 0; i < k; i++) left.add(i);
        balance.run(); result[0] = median.get();
        
        for (int i = k, r = 1; i < nums.length; i++, r++) {
            // remove tail of window from either left or right
            if(!left.remove(i - k)) right.remove(i - k);

            // add next num, this will always increase left size
            right.add(i); left.add(right.pollFirst());
            
            // rebalance left and right, then get median from them
            balance.run(); result[r] = median.get();
        }
        
        return result;
    }
}