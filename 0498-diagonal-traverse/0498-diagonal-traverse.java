class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int rowMax = mat.length;
        int colMax = mat[0].length;
        int nItems = rowMax * colMax;
        int[] result = new int[nItems];        
        
        rowMax--; colMax--;
        boolean up = true;
        
        for(int i=0, row=0, col=0; i < nItems; i++) {
            result[i] = mat[row][col];
            if(up){
                if(col == colMax){
                    row++;
                    up=false;
                } else if (row == 0){
                    col++;
                    up=false;
                } else {
                    row--; col++;
                }
            } else {
                if(row == rowMax){
                    col++;
                    up = true;
                } else if(col == 0) {
                    row++;
                    up = true;
                } else {
                    row++; col--;
                }
            }      
        }
        return result;
    }
}