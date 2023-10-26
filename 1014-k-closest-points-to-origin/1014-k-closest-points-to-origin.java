class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int pivotIdx = partition(points, l, r);
            if (pivotIdx == k - 1) {
                break;
            }
            if (pivotIdx < k - 1) {
                l = pivotIdx + 1;
            } else {
                r = pivotIdx - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }
    
    public int partition(int[][] points, int lo, int hi) {
        int[] pivot = points[lo];
        int pivotDist = dist(pivot);
        int l = lo + 1, r = hi;
        while (true) {
            while (l < hi && dist(points[l]) < pivotDist) l++;
            while (r > lo && dist(points[r]) >= pivotDist) r--;
            if (l >= r) break;
            swap(points, l, r);
        }
        swap(points, lo, r);
        return r;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
    public void swap(int[][] points, int idx1, int idx2) {
        int[] tmp = points[idx1];
        points[idx1] = points[idx2];
        points[idx2] = tmp;
    }
}