// class Solution {
//     public int minTotalDistance(int[][] grid) {
//         List<Integer> rows = new ArrayList<>();
//         List<Integer> cols = new ArrayList<>();
//         for (int i = 0; i < grid.length; i++) {
//             for (int j = 0; j < grid[0].length; j++) {
//                 if (grid[i][j] == 1) {
//                     rows.add(i);
//                     cols.add(j);
//                 }
//             }
//         }
//         Collections.sort(cols);
//         int row = rows.get(rows.size() / 2);
//         int col = cols.get(cols.size() / 2);
//         int result = Distance1D(rows, row) + Distance1D(cols, col);
//         return result;
//     }
//     public int Distance1D(List<Integer> positions, int target) {
//         int sum = 0;
//         for (Integer position : positions ) {
//             sum += Math.abs(target - position);
//         }
//         return sum;
//     }
// }

class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                }
            }
        }
        int result = minDistance1D(rows) + minDistance1D(cols);
        return result;
    }
    // 依次取两边最远的距离
    private int minDistance1D(List<Integer> points) {
        int distance = 0;
        int i = 0;
        int j = points.size() - 1;
        while (i < j) {
            distance += points.get(j) - points.get(i);
            i++;
            j--;
        }
        return distance;
    }
}

class Solution2 {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] r = new int[m], c = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                r[i] += grid[i][j];
                c[j] += grid[i][j];
            }
        }
        
        return getMinDis(r) + getMinDis(c);
    }

    private int getMinDis(int[] arr) {
        int i = -1, j = arr.length;
        int dis = 0, left = 0, right = 0;

        while (i < j) {
            if (left < right) {
                dis += left;
                left += arr[++i];
            } else {
                dis += right;
                right += arr[--j];
            }
        }

        return dis;
    }
}