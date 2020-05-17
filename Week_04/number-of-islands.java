class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, m, n, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, int m, int n, char[][] grid) {

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != '1') return;

        grid[i][j] = '0';

        dfs(i + 1, j, m, n, grid);
        dfs(i , j + 1, m, n, grid);
        dfs(i - 1, j, m, n, grid);
        dfs(i, j - 1, m, n, grid);
    }
}