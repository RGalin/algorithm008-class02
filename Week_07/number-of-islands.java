class Solution {
  public int numIslands(char[][] grid) {
    if (grid.length == 0) return 0;
    int m = grid.length, n = grid[0].length, zeros = 0;

    UnionFind uf = new UnionFind(m * n);
    int[][] distance = {{0, 1}, {1, 0}};

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '0') {
          zeros++;
          continue;
        }
        for (int[] d : distance) {
          int x = i+d[0];
          int y = j+d[1];
          if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
            uf.union(i*n+j, x*n+y);
          }
        }
      }
    }

    return uf.getCount() - zeros;
  }

  private static class UnionFind {
    private int count = 0;
    private int[] parent;

    public UnionFind(int n) {
      count = n;
      parent = new int[n];

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    public int find(int p) {
      while (p != parent[p]) {
        parent[p] = parent[parent[p]];
        p = parent[p];
      }
      return p;
    }

    public void union(int p, int q) {
      int rootP = find(p);
      int rootQ = find(q);

      if (rootP == rootQ) return;

      parent[rootP] = rootQ;
      count--;
    }

    public int getCount() {
      return count;
    }
  }
}