class Solution1 {
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        int[] dists = new int[n];
        for (int i = 0; i < n; i++) {
            dists[i] = dist(points[i]);
        }
        Arrays.sort(dists);
        int limit = dists[k - 1];
        int[][] ans = new int[k][2];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (dist(points[i]) <= limit) {
                ans[j++] = points[i];
            }
        }
        return ans;
    }
    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

// 小小clean up
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        int[][] dists = new int[n][3];
        for (int i = 0; i < n; i++) {
            dists[i] = new int[]{dist(points[i]), points[i][0], points[i][1]};
        }
        Arrays.sort(dists, (a, b) -> (a[0] - b[0]));
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i][0] = dists[i][1];
            ans[i][1] = dists[i][2];
        }
        return ans;
    }
    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}