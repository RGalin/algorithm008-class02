class Solution {
  public int findCircleNum(int[][] M) {
    int m = M.length;
    UnionFind uf = new UnionFind(m);

    for (int i = 0; i < m - 1; i++) {
      for (int j = i + 1; j < m; j++) {
        if (M[i][j] == 1) uf.union(i, j);
      }
    }

    return uf.getCount();
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