class Solution1 {
        // 哈希映射用于存储已经计算过的结果
        HashMap<String, Integer> memo = new HashMap<>();
    public int change(int amount, int[] coins) {
        return backtrack(amount, coins, 0);
    }

    // 回溯函数
    private int backtrack(int amount, int[] coins, int start) {
        // 生成唯一的键，用于记忆化
        String key = amount + "-" + start;
        // 如果已经计算过该结果，则直接返回
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // base case
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        int res = 0;
        for (int i = start; i < coins.length; i++) {
            // 尝试每一个硬币，然后进入下一层递归
            res += backtrack(amount - coins[i], coins, i);
        }
        // 将计算结果存入哈希映射
        memo.put(key, res);
        return res;
    }
}

class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }
}