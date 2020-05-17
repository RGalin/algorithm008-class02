class Solution {
    public boolean canJump(int[] nums) {

        if (nums == null || nums.length == 0) return false;

        int endReacheable = nums.length - 1;
        for (int i = nums.length - 1; i >= 0 ; i--) {
            if (nums[i] + i >= endReacheable) {
                endReacheable = i;
            }
        }

        return endReacheable == 0;

    }
}