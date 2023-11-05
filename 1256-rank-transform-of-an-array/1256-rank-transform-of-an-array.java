class Solution {
    public int[] arrayRankTransform(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : arr) {
            set.add(i);
        }
        int rank = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : set) {
           map.put(i, rank++);
        }

        int[] ranks = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ranks[i] = map.get(arr[i]);
        }
        return ranks;
    }
}