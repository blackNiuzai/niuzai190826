package DpTest;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 这里隐含最小值是1
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1
 *
 * 你可以认为每种硬币的数量是无限的
 *
 *
 * 1、确定base case: amount=0时， 结果返回0
 * 2、确定状态: 唯一的状态是 “amount”
 * 3、确定选择， 也就是导致状态发生变化的行为
 * 4、定义dp dp(n)，对于目标金额n, 返回凑出目标金额n的最少硬币数
 *
 */
public class ChangeCoin {

    int[] memo;


    // 自底向上
    public int coinChange(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        // 这里初始amount + 1 替代正无穷，因为最多只能是amount(全用1)
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
        // 自底向上计算
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                // 这里表示不选当前这个硬币的最小值 加上选这个硬币后
                dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1: dp[amount];
    }


    public int solution(int[] coins, int amount){
        if (coins.length==0) return -1;
        memo = new int[amount];
        return changeCoinV2(coins, amount);
    }


    // 自顶向下： 递归+备忘录
    public int changeCoinV2(int[] coins, int amount){
        if(amount< 0) return -1;
        if(amount==0) return 0;

        // 如果备忘录中有值直接返回
        if(memo[amount-1] !=0){
            return memo[amount-1];
        }
        // 求最小值 所以初始化最大int
        int min =Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = changeCoinV2(coins, amount-coin);
            // 如果有结果 那么更新min 即下层计算结果加上当前结果
            if (res >= 0){
                min = Math.min(min, res + 1);
            }
        }
        memo[amount -1] = min == Integer.MAX_VALUE? -1: min;
        return memo[amount-1];
    }






}
