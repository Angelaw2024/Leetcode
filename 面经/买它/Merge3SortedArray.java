package 买它;

import java.util.ArrayList;
import java.util.List;

public class Merge3SortedArray {
    public static List<Integer> mergeThreeSortedArrays(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        Integer prev = null;  // 用于跟踪上一个添加到结果中的元素，以便去重

        while (i < arr1.length || j < arr2.length || k < arr3.length) {
            int val1 = i < arr1.length ? arr1[i] : Integer.MAX_VALUE;
            int val2 = j < arr2.length ? arr2[j] : Integer.MAX_VALUE;
            int val3 = k < arr3.length ? arr3[k] : Integer.MAX_VALUE;

            int minVal = Math.min(Math.min(val1, val2), val3);

            // 如果minVal与上一个添加到结果中的元素不同，则将其添加到结果中
            if (prev == null || minVal != prev) {
                result.add(minVal);
                prev = minVal;
            }

            // 移动指向最小元素的指针
            if (minVal == val1) {
                i++;
            } else if (minVal == val2) {
                j++;
            } else {
                k++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 2, 3, 3, 4, 4, 5, 5};
        int[] arr2 = {1, 1, 2, 2, 4, 4};
        int[] arr3 = {1, 2, 4};

        List<Integer> result = mergeThreeSortedArrays(arr1, arr2, arr3);
        System.out.println(result);  // Output should be [1, 2, 3, 4, 5]
    }
}
