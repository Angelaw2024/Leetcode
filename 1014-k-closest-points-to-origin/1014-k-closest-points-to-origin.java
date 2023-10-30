class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int l = 0, r = points.length - 1;
        while (l < r) {
            int pivotIdx = partition(points, l, r);
            if (pivotIdx == k) break;
            if (pivotIdx < k) {
                l = pivotIdx + 1;
            } else {
                r = pivotIdx - 1;
            }
        }
        return Arrays.copyOf(points, k);
    }

    public int partition(int[][] points, int start, int end) {
        int[] pivot = points[start];
        int pivotDist = dist(pivot);
        int l = start, r = end + 1;
        while (true) {
            while (dist(points[++l]) < pivotDist) {
                if (l == end) break;
            }
            while (dist(points[--r]) > pivotDist) {
                if (r == start) break;
            }
            if (l >= r) break;
            swap(points, l, r);
        }
        swap(points, start, r);
        return r;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public void swap(int[][] points, int idx1, int idx2) {
        int[] point = points[idx1];
        points[idx1] = points[idx2];
        points[idx2] = point;
    }
}