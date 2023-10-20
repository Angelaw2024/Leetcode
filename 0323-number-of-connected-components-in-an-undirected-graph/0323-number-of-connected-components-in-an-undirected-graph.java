class Solution {
    public int countComponents(int n, int[][] edges) {
        // initial union find 
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge: edges) {
            int p1 = findParent(parents, edge[0]);
            int p2 = findParent(parents, edge[1]);
            if (p1 != p2) {
                parents[p1] = p2;
                n--;
            }
        }
        return n;
    }
    public int findParent(int[] parents, int idx) {
        while (parents[idx] != idx) {
            parents[idx] = parents[parents[idx]];
            idx = parents[idx];
        }
        return idx;
    }
}