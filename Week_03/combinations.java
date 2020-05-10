class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        _recur(1, k, n, new LinkedList<Integer>(), res);
        return res;
    }

    private void _recur(int first, int k, int n, LinkedList<Integer> curr, List<List<Integer>> res) {

        //terminator
        if (curr.size() == k) {
            //process result
            res.add(new LinkedList<>(curr));
            return;
        }

        //process current logic
        for (int i = first; i <= n; i++) {
            curr.add(i);
            //drill down
            _recur(i + 1, k, n, curr, res);
            curr.removeLast();
        }

        //reverse status

    }
}