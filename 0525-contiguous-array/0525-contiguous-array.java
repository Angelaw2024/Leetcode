// class Solution {
//     public int findMaxLength(int[] nums) {
//         int res = 0;
//         int n = nums.length;
//         int sum = 0;
//         Map<Integer, Integer> map = new HashMap<>();
//         map.put(0, -1);
//         for (int i = 0; i < n; i++) {
//             sum += nums[i] == 0 ? -1 : 1;
//             if (map.containsKey(sum)) {
//                 res = Math.max(i - map.get(sum), res);
//             } else {
//                 map.put(sum, i);
//             }
//         }
//         return res;
//     }
// }


public class Solution {

    public int findMaxLength(int[] nums) {
        // count的取值范围是 -n ~ n
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {

                arr[count + nums.length] = i;
            }
        }
        return maxlen;
    }
}
