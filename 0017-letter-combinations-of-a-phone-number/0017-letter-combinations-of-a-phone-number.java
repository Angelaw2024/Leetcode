class Solution {
    String[] letters = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result; 
        dfs(digits, 0, result, new StringBuilder());
        return result;
    }
    public void dfs(String digits, int idx, List<String> result, StringBuilder sb) {
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }
        String curLetters = letters[digits.charAt(idx) - '2'];
        for (char c: curLetters.toCharArray()) {
            sb.append(c);
            dfs(digits, idx + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}