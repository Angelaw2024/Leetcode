/**
时间复杂度
第一个DFS用于标记每块陆地并计算面积，时间复杂度为O(N^2)，N是网格的边长。
第二个循环用于找出通过一次变换能得到的最大陆地面积，时间复杂度也是O(N^2)。
所以总时间复杂度为O(N^2)。

空间复杂度
网格自身占用O(N^2)空间。
DFS递归深度最坏情况下是O(N^2)。
HashSet和ArrayList存储的元素数量不会超过周围格子的数量，是O(1)。
所以总空间复杂度为O(N^2)。
 */
class Solution {
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};
    int[][] grid;
    int N;

    // 主函数
    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;

        int index = 2; // 用于标记不同的岛屿
        int[] area = new int[N*N + 2]; // 存储每个岛屿的面积
        // 第一次遍历，标记所有岛屿并计算面积
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 1)
                    area[index] = dfs(r, c, index++);

        int ans = 0; // 存储最大面积
        for (int x: area) ans = Math.max(ans, x);

        // 第二次遍历，尝试将0变为1并计算面积
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 0) {
                    Set<Integer> seen = new HashSet();
                    for (Integer move: neighbors(r, c))
                        if (grid[move / N][move % N] > 1)
                            seen.add(grid[move / N][move % N]);

                    int bns = 1;
                    for (int i: seen) bns += area[i];
                    ans = Math.max(ans, bns);
                }

        return ans;
    }

    // DFS函数，用于标记岛屿并返回面积
    public int dfs(int r, int c, int index) {
        int ans = 1;
        grid[r][c] = index;
        for (Integer move: neighbors(r, c)) {
            if (grid[move / N][move % N] == 1) {
                grid[move / N][move % N] = index;
                ans += dfs(move / N, move % N, index);
            }
        }

        return ans;
    }

    // 获取一个点的邻居
    public List<Integer> neighbors(int r, int c) {
        List<Integer> ans = new ArrayList();
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < N && 0 <= nc && nc < N)
                ans.add(nr * N + nc);
        }

        return ans;
    }
}
