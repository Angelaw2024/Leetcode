class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] > end) {
                res.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            } else if (interval[1] > end) {
                end = interval[1];
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[res.size()][]);
    }
}
/**
Space complexity : O(log⁡N)O(\log N)O(logN) (or O(n)O(n)O(n))

If we can sort intervals in place, we do not need more than constant additional space, although the sorting itself takes O(log⁡n)O(\log n)O(logn) space. Otherwise, we must allocate linear space to store a copy of intervals and sort that.
 */