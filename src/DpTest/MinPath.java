package DpTest;

/**
 * 设dp大小为m * n 矩阵，其中dp[i][j]的值代表走到[i][j]的最小路径和
 * <p>
 * 题目要求只能向右或往下走，因此dp[i][j] = Min(dp[i-1][j],d[i][j-1]) + grid[i][j]
 * 64题
 */


public class MinPath {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        //行数
        int rowCount = grid.length;
        //列数
        int columnCount = grid[0].length;

        int[][] dp = new int[rowCount][columnCount];

        //base case
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rowCount; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < columnCount; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // 自底向上
        for (int i = 1; i < rowCount; i++) {
            for (int j = 1; j < columnCount; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[rowCount-1][columnCount-1];

    }


}
