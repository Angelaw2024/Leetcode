class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0];
        }

        int n = mat.length;
        int m = mat[0].length;
        int row = 0, column = 0;
        int direction = 1;
        int[] result = new int[n * m];
        int r = 0;

        while (row < n && column < m) {
            result[r++] = mat[row][column];
            int new_row = row + (direction == 1 ? -1 : 1);
            int new_column = column + (direction == 1 ? 1 : -1);
            if (new_row < 0 || new_row == n || new_column < 0 || new_column == m) {    
                if (direction == 1) {
                    row += (column == m - 1 ? 1 : 0) ;
                    column += (column < m - 1 ? 1 : 0);  
                } else {
                    column += (row == n - 1 ? 1 : 0);
                    row += (row < n - 1 ? 1 : 0);
                }
                direction = 1 - direction;         
            } else {
                
                row = new_row;
                column = new_column;
            }
        }
        return result;      
    }
}