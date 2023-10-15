// class Solution {
//     /**
//     This helped me visualize the ans. In the example:
// [23,2,6,4,7] k =12

// (23)%12 = 11
// (23+2)%12=1
// (23+2+6)%12 = 7
// (23+2+6+4)%12=11 -> seen before, so return true because:

// (23+2+6+4) - (23) = 12 -> 12 % 12 = 0
//  */
//     public boolean checkSubarraySum(int[] nums, int k) {
//         if (nums.length <= 1) return false;
//         Map<Integer, Integer> map = new HashMap<>();
//         map.put(0, 0);
//         int sum = 0;
//         for (int i = 0; i < nums.length; i++) {
//             sum += nums[i];
//             if (!map.containsKey(sum % k)) {
//                 map.put(sum % k, i + 1);
//             } else if (map.get(sum % k) < i) {
//                 return true;
//             }
//         }
//         return false;
//     }
// }

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) return false;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] %= k;
            sum += nums[i];
            if (i > 0 && (nums[i] + nums[i - 1]) % k  == 0) {
                return true;
            }
        }
        if (sum < k) return false;
        for (int i = 0; i < nums.length; i++) {
            int total = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                total += nums[j];
                if (total % k == 0) return true;
            }
        }
        return false;
    }
}