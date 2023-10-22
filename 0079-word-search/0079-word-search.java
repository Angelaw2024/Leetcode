class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfSearch(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfSearch(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = '0';

        boolean exist =
                dfSearch(board, word, i + 1, j, index + 1) ||
                        dfSearch(board, word, i - 1, j, index + 1) ||
                        dfSearch(board, word, i, j + 1, index + 1) ||
                        dfSearch(board, word, i, j - 1, index + 1);
        board[i][j] = tmp;
        return exist;
    }
}