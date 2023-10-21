class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.size();  i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                count++;
                int idx = i + j;
                map.computeIfAbsent(idx, k -> new ArrayList<>()).add(nums.get(i).get(j));
            }
        }
        int[] res = new int[count];
        int n = 0;
        for (int i = 0; i < map.keySet().size(); i++) {
            List<Integer> list = map.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                res[n++] = list.get(j);
            }
        }
        return res;
    }
}