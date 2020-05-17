class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, di = 0, ans = 0;

        Set<String> obstacleSet = new HashSet<>();

        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "_" + obstacle[1]);
        }

        for (int cmd : commands) {
            if (cmd == -2) {
                di = (di + 3) % 4;
            } else if (cmd == -1) {
                di = (di + 1) % 4;
            } else {
                for (int i = 0; i < cmd; i++) {
                    if (!obstacleSet.contains((x + direct[di][0]) + "_" + (y + direct[di][1]))) {
                        x += direct[di][0];
                        y += direct[di][1];
                        ans = Math.max(ans, x*x + y*y);
                    }
                }
            }
        }
        return ans;
    }
}