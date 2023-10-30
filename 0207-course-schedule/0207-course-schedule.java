class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] visited = new int[numCourses];
        for(int[] pre: prerequisites) {
            if (!map.containsKey(pre[0])) {
                map.put(pre[0], new ArrayList<>());
            }
            map.get(pre[0]).add(pre[1]);
        }
        for (int cur: map.keySet()) {
            if (visited[cur] == 0) {
                if (!dfs(visited, map, cur)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean dfs(int[] visited, HashMap<Integer, List<Integer>> map, int cur) {
        if (map.get(cur) == null) {
            visited[cur] = 1;
            return true;
        }
        visited[cur] = 2;
        for (int i: map.get(cur)) {
            if (visited[i] == 0) {
                if (!dfs(visited, map, i)) return false;
            } else if (visited[i] == 2) {
                return false;
            }
        }
        visited[cur] = 1;
        return true;
    }
}