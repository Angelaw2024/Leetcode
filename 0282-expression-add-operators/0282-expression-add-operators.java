class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) return result;
        backtracking(result, new StringBuilder(), num, 0, target, 0, 0);
        return result;
    }

    private void backtracking(List<String> result, StringBuilder sb, String num, int idx, int target, long prevSum, long preMulti) {
        if (idx == num.length())  {
            if (prevSum == target) {
                result.add(sb.toString());
            }
            return;
        }
        for (int i = idx; i < num.length(); i++) {
            if (i != idx && num.charAt(idx) == '0') break;
            long curNum = Long.parseLong(num.substring(idx, i + 1));
            int len = sb.length();

            if (idx == 0) {
                backtracking(result, sb.append(curNum), num, i + 1, target, curNum, curNum);
                sb.setLength(len); 
            } else {
                // "+"
                backtracking(result, sb.append("+").append(curNum), num, i + 1, target, prevSum + curNum, curNum);
                sb.setLength(len); 
                // "-"
                backtracking(result, sb.append("-").append(curNum), num, i + 1, target, prevSum - curNum, -curNum);
                sb.setLength(len); 
                // "*"
                backtracking(result, sb.append("*").append(curNum), num, i + 1, target, prevSum - preMulti + preMulti * curNum, preMulti * curNum);
                sb.setLength(len); 
            }
        }
    }
}