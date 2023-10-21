class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair(0, 0));
        List<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<Integer, Integer> p = queue.poll();
                int row = p.getKey();
                int col = p.getValue();
                ans.add(nums.get(row).get(col));
                if (col == 0 && row + 1 < nums.size()) {
                    queue.offer(new Pair(row + 1, col));
                }
                if (col + 1 < nums.get(row).size()) {
                    queue.offer(new Pair(row, col + 1));
                }
            }
        }

        int[] result = new int[ans.size()];
        int i = 0;
        for (int num : ans) {
            result[i] = num;
            i++;
        }
        
        return result;
    }
}

// class Solution1 {
//     public int[] findDiagonalOrder(List<List<Integer>> nums) {
//         Map<Integer, List<Integer>> map = new HashMap<>();
//         int count = 0;
//         for (int i = 0; i < nums.size();  i++) {
//             for (int j = 0; j < nums.get(i).size(); j++) {
//                 count++;
//                 int idx = i + j;
//                 map.computeIfAbsent(idx, k -> new ArrayList<>()).add(nums.get(i).get(j));
//             }
//         }
//         int[] res = new int[count];
//         int n = 0;
//         for (int i = 0; i < map.keySet().size(); i++) {
//             List<Integer> list = map.get(i);
//             for (int j = list.size() - 1; j >= 0; j--) {
//                 res[n++] = list.get(j);
//             }
//         }
//         return res;
//     }
// }