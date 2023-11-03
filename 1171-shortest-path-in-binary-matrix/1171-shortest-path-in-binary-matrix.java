class Solution {
    int[][] dir = new int[][]{{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1;
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int x = cur[0];
            int y = cur[1];
            int dist = grid[x][y];
            if (x == n - 1 && y == n - 1) return dist;
            for (int i = 0; i < 8; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    grid[nx][ny] = dist + 1;
                }
            }
        }
        return -1;
    }
}

// class Solution {
//     int[][] dir = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1};
//     public int shortestPathBinaryMatrix(int[][] grid) {
//         List<List<String>> res = new ArrayList<>();
        
//         int n = grid.length;
//         if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

//         Queue<int[]> queue = new ArrayDeque<>();
//         grid[0][0] = 1;
//         queue.add(new int[]{0, 0});

//         while (!queue.isEmpty()) {
//             int[] cur = queue.remove();
//             int x = cur[0];
//             int y = cur[1];
//             int dist = grid[x][y];
//             if (x == n - 1 && y == n - 1) return dist;
//             for (int i = 0; i < 8; i++) {
//                 int nx = x + dir[i][0];
//                 int ny = y + dir[i][1];
//                 if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
//                     queue.add(new int[]{nx, ny});
//                     grid[nx][ny] = dist + 1;
//                 }
//             }
//         }
//         return -1;
//     }
// }

// // 打印路径
// class Solution{
//     public  int shortestPathBinaryMatrix(int[][] grid) {
//         List<Integer[]> res = new ArrayList<>();
//         List<Integer[]> cur = new ArrayList<>();
//         backtrack(grid, 0, 0, cur, res);
//         for (Integer[] i : res) {
//             System.out.println(i[0] + " " + i[1]);
//         }
//         // return res;
//         return 0;
//     }

//     Integer[][] dir = new Integer[][]{ {1, 0}, {-1, 0}, {0, -1}, {0, 1}};

//     // Integer[][] dir = new Integer[][]{ {1, 0},{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 1}};
//     public boolean backtrack(int[][] grid, int x, int y, List<Integer[]> cur, List<Integer[]> res) {
//         if (x == grid.length - 1 && y == grid[0].length - 1) {
//             for (Integer[] i : cur) {
//                 res.add(i);
//             }
//             res.add(new Integer[]{x, y});
//             return true;
//         }
//         if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 0) {
//             return false;
//         }
//         grid[x][y] = 2;
//         cur.add(new Integer[]{x, y});
//         for (int i = 0; i < 4; i++) {
//             Integer nx = x + dir[i][0];
//             Integer ny = y + dir[i][1];
//             if (backtrack(grid, nx, ny, cur, res)) return true;
//         }
//         grid[x][y] = 0;
//         cur.remove(cur.size() - 1);
//         return false;
//     }
// }