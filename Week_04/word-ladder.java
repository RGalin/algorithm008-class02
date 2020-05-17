class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Deque<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        queue.offer(beginWord);
        visited.add(beginWord);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) return level + 1;
                for (int j = 0; j < curr.length(); j++) {
                    char[] currArray = curr.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        currArray[j] = ch;
                        String next = new String(currArray);
                        if (!visited.contains(next) && wordSet.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            level++;
        }

        return 0;
    }
}