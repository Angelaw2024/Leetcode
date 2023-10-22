class Solution {
 public int count(int n) {
        return (n * (n + 1)) / 2;
    }

    public int candy(int[] ratings) {
        // 处理边界情况
        if (ratings.length <= 1) {
            return ratings.length;
        }

        int candies = 0;  // 总糖果数
        int up = 0;       // 上坡长度
        int down = 0;     // 下坡长度
        int oldSlope = 0; // 旧坡度

        // 遍历孩子的评分
        for (int i = 1; i < ratings.length; i++) {
            // 计算新的坡度
            int newSlope = (ratings[i] > ratings[i - 1]) ? 1 
                          : (ratings[i] < ratings[i - 1] ? -1 : 0);

            // 坡度变化点：从上坡到平地，或从下坡到上坡/平地
            if ((oldSlope > 0 && newSlope == 0) || (oldSlope < 0 && newSlope >= 0)) {
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }

            // 更新上坡和下坡长度
            if (newSlope > 0) {
                up++;
            } else if (newSlope < 0) {
                down++;
            } else {
                candies++;
            }

            oldSlope = newSlope;
        }

        // 添加最后一段的糖果数
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }
}