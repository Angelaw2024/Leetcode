class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>(); // 用于存储结果
       
        // 如果数组长度等于k，则直接返回整个数组
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }
            return result;
        }
        
        // 通过二分查找找到最接近x的元素
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        // 初始化滑动窗口的左右边界
        left -= 1;
        right = left + 1;
        
        // 扩展窗口直到其大小为k
        while (right - left - 1 < k) {
            if (left == -1) {
                right += 1;
                continue;
            }
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        } 
        
        // 构建和返回窗口
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
}
