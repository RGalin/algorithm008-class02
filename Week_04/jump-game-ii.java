class Solution {
    public int jump(int[] nums) {

        int end = 0, maxPostion = 0, steps = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            maxPostion = Math.max(maxPostion, i + nums[i]);
            if (i == end) {
                end = maxPostion;
                steps++;
            }
        }

        return steps;
    }
}