public class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 检查所有的行
        for(int i = 0; i < 9; i++) {
            boolean[] used = new boolean[9];
            for(int j = 0; j < 9; j++) {
                char c = board[i][j];
                if(c != '.') {
                    int idx = c - '1';
                    if(used[idx]) {
                        return false; // 已经使用过这个数字
                    }
                    used[idx] = true; // 标记这个数字为已使用
                }
            }
        }

        // 检查所有的列
        for(int j = 0; j < 9; j++) {
            boolean[] used = new boolean[9];
            for(int i = 0; i < 9; i++) {
                char c = board[i][j];
                if(c != '.') {
                    int idx = c - '1';
                    if(used[idx]) {
                        return false;
                    }
                    used[idx] = true;
                }
            }
        }

        // 检查所有的 3x3 小方格
        for(int k = 0; k < 9; k++) {
            boolean[] used = new boolean[9];
            for(int i = k / 3 * 3; i < k / 3 * 3 + 3; i++) {
                for(int j = k % 3 * 3; j < k % 3 * 3 + 3; j++) {
                    char c = board[i][j];
                    if(c != '.') {
                        int idx = c - '1';
                        if(used[idx]) {
                            return false;
                        }
                        used[idx] = true;
                    }
                }
            }
        }

        return true; // 所有的行、列和 3x3 小方格都是有效的
    }
}
