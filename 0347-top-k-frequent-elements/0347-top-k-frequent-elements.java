class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return quickSelect(map, new ArrayList<>(map.keySet()), nums, k);
    }

    private int[] quickSelect(Map<Integer, Integer> map, List<Integer> keys, int[] nums, int k) {
        int left = 0, right = keys.size() - 1;
        int[] res = new int[k];
        while (left < right) {
            int pivot = partition(map, keys, left, right);
            if (pivot == k) {
                break;
            } else if (pivot > k) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        int idx = 0;
        for (int i = 0; i < k; i++) {
            res[idx++] = keys.get(i);
        }

        return res;
    }

    private int partition(Map<Integer, Integer> map, List<Integer> keys, int l, int r) {
        int pivot = map.get(keys.get(l));
        int i = l;
        int j = r + 1;
        while (i < j) {
            while (map.get(keys.get(++i)) > pivot) {
                if (i == r) break;
            }
            while (map.get(keys.get(--j)) < pivot) {
                if (j == l) break;
            }
            if (i >= j) break;
            Collections.swap(keys, i, j);
        }
        Collections.swap(keys, l, j);
        return j;
    }
}