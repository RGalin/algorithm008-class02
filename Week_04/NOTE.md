DFS&BFS：
    在树（图/状态集）中寻找特定节点。
    
    每个节点都要访问一次
    每个节点仅仅访问一次
    对于节点的访问顺序不限
        - 深度优先搜索（Depth first search）
        - 广度优先搜索（Breadth first search）
        
贪心算法：
    在每一步选择中都采取在当前状态下最好的或最优的选择，从而希望导致结果是全局最优或最好的的算法。
    
    贪心算法对每个子问题的解决方案都做出选择，不能回退。
    动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。
    贪心：当下做局部最优判断
    回溯：能够回退
    动态规划：最优判断 + 回溯
    
    1、一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法
    2、由于贪心法的高效性以及其所求得的答案比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题
    
    最优子结构：
    问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。
    能证明能得到全局最优解。
    从前往后进行贪心，某个点切入，或者从后往前进行贪心。
    
二分查找：
    1. 目标函数具有单调性（单调递增/单调递减）
    2. 存在上下界 （bound）
    3. 能通过索引访问 (index accessible)
    
    left, right = 0, len(array) - 1
    while left <= right:
        mid = (left + right) / 2
        if array[mid] == target:
           #Already find the target
            break or return result
        elif array[mid] < target:
           #target in the right side
           left = mid + 1
        else： 
            #target in the left  side 
            right = mid - 1
    
    部分有序数组中查找
    1. if (array[mid] == target) return mid;
    2. 找到有序区间，target是否在有序区间中，如果不在就去无序区间找，通过改变左右边界。
    
    有序二维矩阵中查找：
    1. 元素个数为m * n -1个
    2. 下界为0， 上届为 m * n - 1
    3. 第mid个元素对应的原数组下标位置，matrix[mid / n][mid % n]。n为列数。