/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // 获取山脉数组的长度
        int length = mountainArr.length();

        // 1. 找到峰值元素的索引
        int low = 1;
        int high = length - 2;
        while (low != high) {
            int testIndex = (low + high) / 2;
            // 通过比较相邻元素找到峰值
            if (mountainArr.get(testIndex) < mountainArr.get(testIndex + 1)) {
                low = testIndex + 1;
            } else {
                high = testIndex;
            }
        }
        int peakIndex = low;

        // 2. 在严格递增的部分中查找目标值
        low = 0;
        high = peakIndex;
        while (low != high) {
            int testIndex = (low + high) / 2;
            if (mountainArr.get(testIndex) < target) {
                low = testIndex + 1;
            } else {
                high = testIndex;
            }
        }
        // 检查目标值是否在递增部分
        if (mountainArr.get(low) == target) {
            return low;
        }

        // 3. 否则，在严格递减的部分中查找
        low = peakIndex + 1;
        high = length - 1;
        while (low != high) {
            int testIndex = (low + high) / 2;
            if (mountainArr.get(testIndex) > target) {
                low = testIndex + 1;
            } else {
                high = testIndex;
            }
        }
        // 检查目标值是否在递减部分
        if (mountainArr.get(low) == target) {
            return low;
        }

        // 目标值不在山脉数组中
        return -1;
    }
}