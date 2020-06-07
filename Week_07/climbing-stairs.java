class Solution {
  public int climbStairs(int n) {
    return fib(n, new int[n + 1]);
  }

  private int fib(int n, int[] memo) {

    //terminator
    if (n <= 2) return n;

    if (memo[n] == 0) {
      memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    }

    return memo[n];
  }
}