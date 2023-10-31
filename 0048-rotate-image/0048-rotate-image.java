public class Solution {
    public void rotate(int[][] matrix) {
        // 转置矩阵
        transpose(matrix);
        // 反射矩阵
        reflect(matrix);
    }

    // 转置矩阵
    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    // 反射矩阵
    public void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 交换 matrix[i][j] 和 matrix[i][n-j-1]
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
}
