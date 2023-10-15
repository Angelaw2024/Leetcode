class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = matrix[0][0];
        int r = matrix[n - 1][m - 1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (countSmaller(matrix, mid) < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    public int countSmaller(int[][] matrix, int target) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > target) break;
                count++;
            }
        }
        return count;
    }
}