class Solution {
    int count = 0; // 记录满足条件的数组的个数

    public int numSquarefulPerms(int[] nums) {
        Map<Integer, Integer> numCount = new HashMap<>(); // 记录每个数字出现的次数
        for (int num : nums) {
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
        }
        backtrack(nums, new int[nums.length], 0, numCount);
        return count;
    }

    // 检查两个数的和是否为完全平方数
    private boolean isSquareful(int x, int y) {
        double sum = x + y;
        double root = Math.sqrt(sum);
        return root == Math.floor(root);
    }

    // 回溯函数
    private void backtrack(int[] nums, int[] path, int pos, Map<Integer, Integer> numCount) {
        if (pos == nums.length) {
            count++; // 找到一个满足条件的数组，计数加一
            return;
        }
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            int num = entry.getKey();
            int cnt = entry.getValue();
            if (cnt == 0) continue; // 该数字已经用完，跳过
            if (pos == 0 || isSquareful(path[pos - 1], num)) { // 第一个位置或者和前一个数字的和是完全平方数
                path[pos] = num; // 将数字添加到当前路径
                numCount.put(num, cnt - 1); // 更新该数字的剩余数量
                backtrack(nums, path, pos + 1, numCount); // 进行下一步递归
                numCount.put(num, cnt); // 回溯，恢复该数字的剩余数量
            }
        }
    }

}