class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int left = 0, right = points.length - 1;
        int target = k;

        while (left < right) {
            int pivotIdx = partition(points, left, right);
            if (pivotIdx == target) break;
            if (pivotIdx < target) { 
                left = pivotIdx + 1;
            } else {
                right = pivotIdx - 1;
            }
        }
        return Arrays.copyOf(points, k);
    }
    public int partition(int[][] points, int lo, int hi) {
        int i = lo, j = hi + 1;
        int[] pivot = points[lo];
        int pivotDist = dist(pivot);
        while (true) {
            while (dist(points[++i]) < pivotDist) {
                if (i == hi) break;
            }
            while (dist(points[--j]) > pivotDist) {
                if (j == lo) break;
            }
            if (i >= j) break;
            swap(points, i, j);
        }
        swap(points, j, lo);
        return j;
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
