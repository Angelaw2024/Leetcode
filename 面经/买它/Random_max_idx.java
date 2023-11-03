package 买它;

/*
 * Given an array of integers arr, randomly return an index of the maximum value seen by far.

Example:
Input: [11, 30, 2, 30, 30, 30, 6, 2, 62, 62]

Having iterated up to the at element index 5 (where the last 30 is), randomly give an index among [1, 3, 4, 5] which are indices of 30 - the max value by far. Each index should have a ¼ chance to get picked.

Having iterated through the entire array, randomly give an index between 8 and 9 which are indices of the max value 62.
 */
import java.util.*;

class Random_max_idx {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {11, 30, 2, 30, 30, 30, 6, 2, 62, 62};
        for (int i = 0; i < arr.length; i++) {
            System.out.println("test1: " + solution.pick(arr, i));
        }
        System.out.println("--------");
        Solution1 solution1 = new Solution1();
        solution1.pick(arr);

        System.out.println("--------");
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.pick(arr));
    }
 }

 class Solution {
    public int pick(int[] nums, int idx) {
        int max = Integer.MIN_VALUE;
        var maxIdx = new ArrayList<Integer>();

        Random rand = new Random();

        for (int i = 0; i <= idx; i++) {
            if (nums[i] > max) {
                maxIdx.clear();
                max = nums[i];
                maxIdx.add(i);
            } else if (nums[i] == max) {
                maxIdx.add(i);
            }
        }
        return maxIdx.get(rand.nextInt(maxIdx.size()));
    }
}

class Solution1 {
    public int pick(int[] nums) {
        int max = Integer.MIN_VALUE;
        var maxIdx = new ArrayList<Integer>();

        Random rand = new Random();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                maxIdx.clear();
                max = nums[i];
                maxIdx.add(i);
            } else if (nums[i] == max) {
                maxIdx.add(i);
            }
            int curPick = maxIdx.get(rand.nextInt(maxIdx.size()));
            System.out.println("index: " + i + ", pick: " + curPick);

        }
        return maxIdx.get(rand.nextInt(maxIdx.size()));
    }
}

class Solution2 {
    public int pick(int[] nums) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        Random rand = new Random();
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                count = 1;
            } else if (nums[i] == max) {
                count++;
            } else if (nums[i] < max) {
                continue;
            }
            int curPick = rand.nextInt(count);
            if (curPick == 0) {
                res = i;
            }
            

        }
        return res;
    }
}