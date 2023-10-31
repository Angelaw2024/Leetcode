class Solution {
    public int longestConsecutive(int[] nums) {
         // 创建一个HashSet，用于快速查找
        Set<Integer> num_set = new HashSet<Integer>();
        
        // 将所有数字添加到HashSet中
        for (int num : nums) {
            num_set.add(num);
        }

        // 用于存储最长连续序列的长度
        int longestStreak = 0;

        // 遍历HashSet
        for (int num : num_set) {
            // 如果num是序列的起点
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 查找以num为起点的最长连续序列
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // 更新最长连续序列的长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
/**
O(n) O(n)
第一次迭代，num = 100

99 不在集合中，所以进入内部的 while 循环。
while 循环查找 101，但是找不到。所以这个数字的最长序列是它自己，长度为 1。
第二次迭代，num = 4

3 在集合中，所以不执行 while 循环。
第三次迭代，num = 200

199 不在集合中，所以进入内部的 while 循环。
while 循环查找 201，但是找不到。所以这个数字的最长序列是它自己，长度为 1。
第四次迭代，num = 1

0 不在集合中，所以进入内部的 while 循环。
while 循环查找 2，3，4，然后停止。所以这个数字的最长序列是 [1, 2, 3, 4]，长度为 4。
第五次迭代，num = 3

2 在集合中，所以不执行 while 循环。
第六次迭代，num = 2

1 在集合中，所以不执行 while 循环。
 */