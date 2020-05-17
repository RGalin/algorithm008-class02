class Solution {
    // public int findMin(int[] nums) {
    //     if (nums == null || nums.length == 0) return -1;
    //     int left = 0, right = nums.length -1;
    //     int min = Integer.MAX_VALUE;

    //     while (left <= right) {
    //         int mid = left + ((right - left) >> 1);

    //         if (nums[left] <= nums[mid]) {
    //             min = Math.min(min, nums[left]);
    //             left = mid + 1;
    //         } else {
    //             min = Math.min(min, nums[mid]);
    //             right = mid - 1;
    //         }
    //     }
    //     return min;
    // }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (nums[mid] >= nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[right];
    }
}