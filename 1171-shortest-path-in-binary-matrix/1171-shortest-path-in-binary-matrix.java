class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        if (grid[0][0] == 1 || grid[m - 1][m - 1] == 1) {
            return -1;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        int[][] dir = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        queue.offer(0);
        int row, col;
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Integer cur = queue.poll();
                int x = cur / m;
                int y = cur % m;
                for (int i = 0; i < dir.length; i++) {
                    row = x + dir[i][0];
                    col = y + dir[i][1];
                    if (row >=0 && row < m && col >= 0 && col < m && grid[row][col] == 0) {
                        if (row == m - 1 && col == m - 1) {
                            return step + 1;
                        }
                        queue.offer(row * m + col);
                        grid[row][col] = 1;
                    }
                }
            }
           
            step++;
        }
        return -1;
    }
}