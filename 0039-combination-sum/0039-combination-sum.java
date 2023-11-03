class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }
    private void helper(int[] c, int t, List<List<Integer>> res, List<Integer> cur, int s) {
        if (t == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = s; i < c.length; i++) {
            if (c[i] > t) break;
            cur.add(c[i]);
            helper(c, t - c[i], res, cur, i);
            cur.remove(cur.size() - 1);
        }
    }
}