class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        _permuteUnique(used, nums, new LinkedList<Integer>(), res);
        return res;
    }

    private void _permuteUnique(boolean[] used, int[] nums, LinkedList<Integer> curr,  List<List<Integer>> res) {

        //terminator
        if (curr.size() == nums.length) {
            res.add(new LinkedList<>(curr));
            return;
        }

        //process current logic
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i-1] == nums[i] && !used[i-1]) continue;
            curr.add(nums[i]);
            used[i] = true;
            _permuteUnique(used, nums, curr, res);
            used[i] = false;
            curr.removeLast();
        }

        //drill down

        //reverse status
    }
}