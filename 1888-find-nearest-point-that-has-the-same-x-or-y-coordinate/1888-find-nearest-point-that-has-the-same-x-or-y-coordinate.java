class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int res = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int curX = points[i][0];
            int curY = points[i][1];
            // valid
            if (curX == x || curY == y) {
                int curDist = dist(curX, curY, x, y);
                if (curDist < minDist) {
                    res = i;
                    minDist = curDist;
                }
            }
        }
        return res;
        
    }
    public int dist(int px, int py, int x, int y) {
        return Math.abs(px - x) + Math.abs(py - y);
    }
}