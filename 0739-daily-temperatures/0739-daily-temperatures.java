public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 创建一个单调栈来保存温度的索引
        Stack<Integer> stack = new Stack<>();
        // 获取温度数组的长度
        int n = temperatures.length;
        // 创建一个结果数组来保存答案
        int[] result = new int[n];

        // 遍历每一天的温度
        for (int i = 0; i < n; i++) {
            // 获取当前天的温度
            int cur = temperatures[i];

            // 当栈不为空，并且栈顶的温度小于当前温度
            while (!stack.isEmpty() && temperatures[stack.peek()] < cur) {
                // 弹出栈顶的温度，因为我们已经找到了更暖和的一天
                int prev = stack.pop();
                // 更新结果数组
                result[prev] = i - prev;
            }

            // 将当前天的索引压入栈
            stack.push(i);
        }
        return result;
    }
}
