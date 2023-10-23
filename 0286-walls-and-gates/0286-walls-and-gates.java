/* BFS time: O(mn) space: O(mn)*/
class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0){
                    queue.offer(new Pair(i, j));
                }
            }
        }
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<Integer, Integer> p = queue.poll();
                int x = p.getKey();
                int y = p.getValue();
                for (int i = 0; i < 4; i++) {
                    int nx = x + dir[i][0];
                    int ny = y + dir[i][1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && rooms[nx][ny] == Integer.MAX_VALUE) {
                        rooms[nx][ny] = count;
                        queue.offer(new Pair(nx, ny));
                    }
                }
            }
            count++;
        }
    }
}

/* DFS time: O(m^2 n ^2) space: O(mn)*/
class Solution1 {
    int inf = Integer.MAX_VALUE;
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) { //O(mn) 
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                } 
            }
        }
    }
    // 时间：O(mn) 空间：O(mn)
    public void dfs(int[][] rooms, int x, int y, int dist) { 
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] < dist) {
            return;
        }
        rooms[x][y] = dist++;
        dfs(rooms, x + 1, y, dist);
        dfs(rooms, x - 1, y, dist);
        dfs(rooms, x, y + 1, dist);
        dfs(rooms, x, y - 1, dist);
    }
}