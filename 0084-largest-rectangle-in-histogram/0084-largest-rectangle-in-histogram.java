public class Solution {
    // 主函数
    public int largestRectangleArea(int[] heights) {
        // 初始化单调栈和最大面积
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int length = heights.length;

        // 哨兵值，用于方便处理
        stack.push(-1);

        // 处理每一根柱子
        for (int i = 0; i < length; ++i) {
            // 更新最大面积
            maxArea = updateMaxArea(stack, heights, maxArea, i);
            // 将当前柱子的索引入栈
            stack.push(i);
        }

        // 处理栈中剩下的柱子
        while (stack.peek() != -1) {
            // 更新最大面积
            maxArea = updateMaxArea(stack, heights, maxArea, length);
        }

        return maxArea;
    }

    // 辅助函数：用于更新最大面积
    private int updateMaxArea(Deque<Integer> stack, int[] heights, int currentMax, int rightBoundary) {
        // 当栈顶的柱子高度大于或等于当前柱子的高度时，弹出栈顶柱子并更新面积
        while (stack.peek() != -1 && (rightBoundary == heights.length || heights[stack.peek()] >= heights[rightBoundary])) {
            int height = heights[stack.pop()];
            int width = rightBoundary - stack.peek() - 1;
            // 更新最大面积
            currentMax = Math.max(currentMax, height * width);
        }
        return currentMax;
    }
}
