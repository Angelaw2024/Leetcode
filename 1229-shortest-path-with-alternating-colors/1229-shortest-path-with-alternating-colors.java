class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // adj 存储每个节点的邻居信息。颜色用 0 和 1 表示：0 代表红色，1 代表蓝色。
        Map<Integer, List<List<Integer>>> adj = new HashMap<>();
        
        // 初始化邻接表：红色边
        for (int[] redEdge : redEdges) {
            adj.computeIfAbsent(redEdge[0], k -> new ArrayList<>()).add(Arrays.asList(redEdge[1], 0));
        }
        
        // 初始化邻接表：蓝色边
        for (int[] blueEdge : blueEdges) {
            adj.computeIfAbsent(blueEdge[0], k -> new ArrayList<>()).add(Arrays.asList(blueEdge[1], 1));
        }

        // answer 存储从节点 0 到每个节点的最短交替路径长度
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        // visit 记录每个节点的每种颜色是否被访问过
        boolean[][] visit = new boolean[n][2];

        // 使用队列进行 BFS
        Queue<int[]> q = new LinkedList<>();

        // 从节点 0 开始，距离为 0，上一条边的颜色未定义（-1）
        q.offer(new int[] { 0, 0, -1 });
        answer[0] = 0;
        visit[0][0] = visit[0][1] = true;

        while (!q.isEmpty()) {
            int[] element = q.poll();
            int node = element[0], steps = element[1], prevColor = element[2];

            if (!adj.containsKey(node)) {
                continue;
            }

            for (List<Integer> nei : adj.get(node)) {
                int neighbor = nei.get(0);
                int color = nei.get(1);

                // 只有当颜色不同且未访问过的节点才加入队列
                if (!visit[neighbor][color] && color != prevColor) {
                    if (answer[neighbor] == -1) {
                        answer[neighbor] = steps + 1;
                    }
                    visit[neighbor][color] = true;
                    q.offer(new int[] { neighbor, steps + 1, color });
                }
            }
        }

        return answer;
    }
}
