class Solution {
    // public boolean searchMatrix(int[][] matrix, int target) {
    //     if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
    //     int left = 0, right = matrix.length - 1, max = matrix[0].length - 1;

    //     while (left <= right) {
    //         int mid = left + ((right - left) >> 1);
    //         if (matrix[mid][0] <= target && matrix[mid][max] >= target) {
    //             //在这个区间中查找
    //             return searchInSubArray(matrix[mid], target);
    //         } else if (matrix[mid][max] <  target) {
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }

    //     return false;
    // }

    // private boolean searchInSubArray(int[] array, int target) {
    //     int left = 0, right = array.length - 1;

    //     while (left <= right) {
    //         int mid = left + ((right - left) >> 1);

    //         if (array[mid] == target) {
    //             return true;
    //         } else if (array[mid] < target) {
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }

    //     return false;
    // }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length, n = matrix[0].length, left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (matrix[mid / n][mid % n] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}