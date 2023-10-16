class Solution {
     // BFS 对于遇到的左右括号的字符，去掉括号字符生成一个新的字符串，如果这个字符串之前没有遇到过，将其排入队中，
     //  O(2^n) 因为每个字符你都有两个选择：要么保留，要么删除。
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(s);
        queue.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
                String curr = queue.poll();
                if (isValid(curr)) {
                    res.add(curr);
                    found = true;
                }
                if (found) continue;
                for (int i = 0; i < curr.length(); i++) {
                    if (curr.charAt(i) != '(' && curr.charAt(i) != ')') {
                        continue;
                    }
                    String temp = curr.substring(0, i) + curr.substring(i + 1);
                    if (!visited.contains(temp)) {
                        visited.add(temp);
                        queue.add(temp);
                    }
                }
            
            
        }
        return res;
    }

    private boolean isValid(String s) {
            int ct = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    ct++;
                } else if (c == ')') {
                    if (ct == 0) return false;
                    ct--;
                }
            }
            return ct == 0;
        }
}