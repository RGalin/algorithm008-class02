class Solution {
    int[][] index = {
            {1, 1},
            {1, 0},
            {1, -1},
            {0, 1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1}
    };
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0] == null) return board;

        int m = board.length;
        int n = board[0].length;

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            dfs(click[0], click[1], m, n, board);
        }
        return board;
    }

    private void dfs(int i, int j, int m, int n, char[][] board) {
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'E') return;

        int count  = neighborMines(i, j, board);

        if (count == 0) {
            board[i][j] = 'B';
            for (int k = 0; k < index.length; k++) {
                dfs(i + index[k][0], j + index[k][1], m, n, board);
            }
        } else {
            board[i][j] = (char)(count + '0');
        }
    }

    private int neighborMines(int i, int j, char[][] board) {
        int count = 0;
        for (int k = 0; k < index.length; k++) {
            if (i + index[k][0] < 0 || j + index[k][1] < 0 ||
                    i + index[k][0] >= board.length || j + index[k][1] >= board[0].length) continue;
            if (board[i + index[k][0]][j + index[k][1]] == 'M' || board[i + index[k][0]][j + index[k][1]] == 'X') count++;
        }
        return count;
    }
}