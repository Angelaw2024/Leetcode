class Solution {
    public int[] findBuildings(int[] heights) {
        int curMax = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > curMax) {
                curMax = heights[i];
                list.add(i);
            }
        }
        int[] ans = new int[list.size()];
        int j = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            ans[j++] = list.get(i);
        }
        return ans;
    }
}
// 4 2 3 1
// 4
// List<> 3 ,2 ,0
