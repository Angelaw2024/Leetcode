class Solution {
    // 主函数
    public int lengthOfLIS(int[] nums) {
        // 用于存储 LIS 的 ArrayList
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]); // 添加第一个元素
        
        // 遍历 nums 数组
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // 如果当前元素比 sub 的最后一个元素大，直接添加到 sub
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                // 否则，使用二分查找找到该元素应该插入的位置并更新
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }
        
        // 返回 LIS 的长度
        return sub.size();
    }
    
    // 二分查找函数
    private int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;
        
        while (left < right) {
            int mid = (left + right) / 2;
            // 找到则直接返回
            if (sub.get(mid) == num) {
                return mid;
            }
            
            // 根据中间值调整搜索范围
            if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}
