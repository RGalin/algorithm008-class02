class Trie {
  private boolean isLeaf;
  private Map<Character, Trie> children;

  /** Initialize your data structure here. */
  public Trie() {
    isLeaf = false;
    children = new HashMap<>();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    Trie curr = this;

    for (char c : word.toCharArray()) {
      curr.children.putIfAbsent(c, new Trie());
      curr = curr.children.get(c);
    }

    curr.isLeaf = true;
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    Trie curr = this;

    for (char c : word.toCharArray()) {
      if (!curr.children.containsKey(c)) return false;
      curr = curr.children.get(c);
    }

    return curr.isLeaf;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    Trie curr = this;

    for (char c : prefix.toCharArray()) {
      if (!curr.children.containsKey(c)) return false;
      curr = curr.children.get(c);
    }

    return true;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */