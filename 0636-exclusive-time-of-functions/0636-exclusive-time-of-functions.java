class Solution {
    // 函数计算每个函数的独占时间
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n]; // 结果数组，存放每个函数的执行时间
        Deque<Integer> stack = new ArrayDeque<>(); // 使用栈来存储函数的ID
        int prev = 0; // 存储上一次的时间戳

        for (String log : logs) {
            String[] parts = log.split(":"); // 将日志信息分割成部分
            int id = Integer.parseInt(parts[0]); // 获取函数ID
            int curr = Integer.parseInt(parts[2]); // 获取当前的时间戳
            if (parts[1].equals("start")) {
                if (!stack.isEmpty()) {
                    // 如果栈不为空，则当前栈顶元素的独占时间增加
                    res[stack.peek()] += curr - prev;
                }
                // 函数开始，将其ID压入栈中
                stack.push(id);
                prev = curr; // 更新之前的时间戳为当前时间戳
            } else {
                // 函数结束，将其ID从栈中弹出，并更新该函数的独占时间
                res[stack.pop()] += curr - prev + 1;
                prev = curr + 1; // 结束时间是包含当前时间戳的，所以需要+1
            }
        }
        return res; // 返回结果
    }
}
