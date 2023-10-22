class Solution {
    public void setZeroes(int[][] matrix) {
        boolean isCol = false;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Mark the rows and columns to be zeroed
        for (int i = 0; i < rows; ++i) {
            if (matrix[i][0] == 0) isCol = true;
            for (int j = 1; j < cols; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero out the marked rows and columns
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out the first row if needed
        if (matrix[0][0] == 0) {
            Arrays.fill(matrix[0], 0);
        }

        // Zero out the first column if needed
        if (isCol) {
            for (int i = 0; i < rows; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}