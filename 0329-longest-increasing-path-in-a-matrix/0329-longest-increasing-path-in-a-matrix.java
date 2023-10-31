public class Solution {
    // 定义四个方向
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // 矩阵的行数和列数
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        // 如果矩阵为空，返回0
        if (matrix.length == 0) return 0;
        // 初始化行数和列数
        m = matrix.length; n = matrix[0].length;
        // 缓存，用于记忆化
        int[][] cache = new int[m][n];
        int ans = 0;
        // 遍历每一个点，查找从该点出发的最长递增路径
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }

    // 从(i, j)出发，进行深度优先搜索
    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        // 如果这个值已经计算过，直接返回结果
        if (cache[i][j] != 0) return cache[i][j];
        // 遍历四个方向
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            // 判断新的点是否在矩阵内以及是否大于当前点
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
        }
        // 记录从这个点出发的最长递增路径长度
        return ++cache[i][j];
    }
}
