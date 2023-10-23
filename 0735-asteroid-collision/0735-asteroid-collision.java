class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return asteroids;
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            if (ast > 0) {
                stack.push(ast);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -ast) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(ast);
                } else if (stack.peek() == -ast) {
                    stack.pop();
                }
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}