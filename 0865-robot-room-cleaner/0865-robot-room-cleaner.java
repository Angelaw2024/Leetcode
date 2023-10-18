/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Set<String> visited = new HashSet<>();
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0);
    }

    private void dfs(Robot robot, int x, int y, int curDir) {
        visited.add(x + " " + y);
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int nextDir = (curDir + i) % 4;
            int nx = x + dirs[nextDir][0], ny = y + dirs[nextDir][1];
            if (!visited.contains(nx + " " + ny) && robot.move()) {
                dfs(robot, nx, ny, nextDir);
                // backtracking
                // go back to previous dir status
                robot.turnRight();
                robot.turnRight();
                // after turn right and turn right (180degree reversed)
                // move back
                robot.move();
                // turn direction back, ready for next direction from dirs array
                robot.turnLeft();
                robot.turnLeft();
            }
            // for next clockwise direction, as dirs array, turn right to ensure
            // the robot faces same direction as next direction (x, y) from dirs array
            robot.turnRight();
        }
    }
}
