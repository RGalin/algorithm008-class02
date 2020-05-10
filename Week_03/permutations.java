class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        _permute(nums, new LinkedList<>(), res);
        return res;
    }

    private void _permute(int[] nums, LinkedList<Integer> curr, List<List<Integer>> res) {

        //terminator
        if (curr.size() == nums.length) {
            res.add(new LinkedList<>(curr));
            System.out.println(res);
            return;
        }

        //process current logic
        for (int value : nums) {
            if (curr.contains(value)) continue;
            curr.add(value);
            //drill down
            _permute(nums, curr, res);
            curr.removeLast();
        }

        //restore status
    }
}