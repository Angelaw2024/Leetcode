class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int length = heights.length;

        // Initialize the stack with a sentinel value
        stack.push(-1);

        // Process each bar
        for (int i = 0; i < length; ++i) {
            maxArea = updateMaxArea(stack, heights, maxArea, i);
            stack.push(i);
        }

        // Process remaining bars in the stack
        while (stack.peek() != -1) {
            maxArea = updateMaxArea(stack, heights, maxArea, length);
        }

        return maxArea;
    }

    private int updateMaxArea(Deque<Integer> stack, int[] heights, int currentMax, int rightBoundary) {
        while (stack.peek() != -1 && (rightBoundary == heights.length || heights[stack.peek()] >= heights[rightBoundary])) {
            int height = heights[stack.pop()];
            int width = rightBoundary - stack.peek() - 1;
            currentMax = Math.max(currentMax, height * width);
        }
        return currentMax;
    }
}