class Solution {
  private int[] dx = {1, 0, 0, -1};
  private int[] dy = {0, 1, -1, 0};

  public List<String> findWords(char[][] board, String[] words) {
    if (words == null || words.length == 0 || board == null ||board.length == 0 || board[0].length == 0)
      return new ArrayList<>();

    int m = board.length;
    int n = board[0].length;

    Trie trie = new Trie();
    for (String word : words) {
      trie.insert(word);
    }

    HashSet<String> res = new HashSet<>();
    boolean[][] processed = new boolean[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char ch = board[i][j];
        if (trie.character.containsKey(ch)) {
          dfs(trie.character.get(ch), board, i, j, processed, Character.toString(ch),res);
        }
      }
    }

    return new ArrayList<>(res);
  }

  private void dfs(Trie trie, char[][] board, int i, int j, boolean[][] processed, String path, HashSet<String> res) {
    //terminator
    if (trie.isLeaf) {
      res.add(path);
    }

    //process current logic
    processed[i][j] = true;

    for (Map.Entry<Character, Trie> entry : trie.character.entrySet()) {

      for (int k = 0; k < dx.length; k++) {

        if (isSafe(i + dx[k], j + dy[k], processed, board, entry.getKey())) {
          //drill down
          dfs(entry.getValue(), board, i + dx[k], j + dy[k], processed, path + entry.getKey(), res);
        }
      }
    }

    //reverse status
    processed[i][j] = false;
  }

  private boolean isSafe(int x, int y, boolean[][] processed, char[][] board, char key) {
    return (x >= 0 && y >= 0) && (x < board.length && y < board[0].length) && !processed[x][y] && board[x][y] == key;
  }


  private static class Trie {
    private boolean isLeaf;
    private Map<Character, Trie> character;

    public Trie() {
      isLeaf = false;
      character = new HashMap<>();
    }

    public void insert(String key) {
      Trie curr = this;

      for (char c : key.toCharArray()) {
        curr.character.putIfAbsent(c, new Trie());
        curr = curr.character.get(c);
      }

      curr.isLeaf = true;
    }

    public boolean search(String key) {
      Trie curr = this;

      for (char c : key.toCharArray()) {
        if (!curr.character.containsKey(c)) return false;
        curr = curr.character.get(c);
      }

      return curr.isLeaf;
    }
  }
}