//我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
//
//
// 示例:
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//
// 说明:
//
//
// 1 是丑数。
// n 不超过1690。
//
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
// Related Topics 数学


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> seen = new HashSet<>();

        PriorityQueue<Long> heap = new PriorityQueue<>();

        long[] prime = {2, 3, 5};

        for (long num : prime) {
            heap.add(num);
            seen.add(num);
        }

        long nthUgly = 1L;
        for (int i = 1; i < n; i++) {
            nthUgly = heap.poll();

            for (long num : prime) {
                if (!seen.contains(num * nthUgly)) {
                    heap.add(num * nthUgly);
                    seen.add(num * nthUgly);
                }
            }
        }

        return (int) nthUgly;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
