class Solution {
    // DFS
    Set<String> res;
    public List<String> removeInvalidParentheses(String s) {
        res = new HashSet<>();
        // calculate 'remove left/right' 记录的是需要移除的括号
        int rml = 0, rmr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rml++;
            } else if (s.charAt(i) == ')') {
                if (rml != 0) {
                    rml--;
                } else {
                    rmr++;
                }
            }
        }
        dfs(s, 0,  new StringBuilder(), rml, rmr, 0);
        return new ArrayList<>(res);
    }

    // backtracking
    // @balance means if need back parentheses
    // NOW: s (a)), curIdx 0, sb "", rml 0, rmr 1, balance 0
    private void dfs(String s, int curIdx, StringBuilder sb, int rml, int rmr, int balance) {
        if (rml < 0 || rmr < 0 || balance < 0) {
            return;
        }
        if (curIdx == s.length()) {
            if (rml == 0 && rmr == 0 && balance == 0) {
                res.add(sb.toString());
            }
            return;
        }
        char ch = s.charAt(curIdx); // (
        int oriLen = sb.length(); // 0
        if (ch == '(') {
            // delete this parentheses '('
            dfs(s, curIdx + 1, sb, rml - 1, rmr, balance);
            // use this parentheses
            dfs(s, curIdx + 1, sb.append(ch), rml, rmr, balance + 1);
        } else if (ch == ')') {
            dfs(s, curIdx + 1, sb, rml, rmr - 1, balance);
            // if (balance > 0)  only add right pare when balance > 0, but it is included in base condition
            dfs(s, curIdx + 1, sb.append(ch), rml, rmr, balance - 1);
        } else { // letters
            dfs(s, curIdx + 1, sb.append(ch), rml, rmr, balance);
        }
        sb.setLength(oriLen);
    }
}

// class Solution1 {
//      // BFS 对于遇到的左右括号的字符，去掉括号字符生成一个新的字符串，如果这个字符串之前没有遇到过，将其排入队中，
//      //  O(2^n) 因为每个字符你都有两个选择：要么保留，要么删除。
//     public List<String> removeInvalidParentheses(String s) {
//         List<String> res = new ArrayList<>();
//         if (s == null) {
//             return res;
//         }
//         Set<String> visited = new HashSet<>();
//         Queue<String> queue = new LinkedList<>();

//         visited.add(s);
//         queue.add(s);
//         boolean found = false;

//         while (!queue.isEmpty()) {
//                 String curr = queue.poll();
//                 if (isValid(curr)) {
//                     res.add(curr);
//                     found = true;
//                 }
//                 if (found) continue;
//                 for (int i = 0; i < curr.length(); i++) {
//                     if (curr.charAt(i) != '(' && curr.charAt(i) != ')') {
//                         continue;
//                     }
//                     String temp = curr.substring(0, i) + curr.substring(i + 1);
//                     if (!visited.contains(temp)) {
//                         visited.add(temp);
//                         queue.add(temp);
//                     }
//                 }
            
            
//         }
//         return res;
//     }

//     private boolean isValid(String s) {
//             int ct = 0;
//             for (char c : s.toCharArray()) {
//                 if (c == '(') {
//                     ct++;
//                 } else if (c == ')' && --ct < 0){
//                     return false;
//                 }
//             }
//             return ct == 0;
//         }
// }