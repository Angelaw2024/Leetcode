class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(cols);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        int result = Distance1D(rows, row) + Distance1D(cols, col);
        return result;
    }
    public int Distance1D(List<Integer> positions, int target) {
        int sum = 0;
        for (Integer position : positions ) {
            sum += Math.abs(target - position);
        }
        return sum;
    }
}