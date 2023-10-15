class Solution {
    /**
    This helped me visualize the ans. In the example:
[23,2,6,4,7] k =12

(23)%12 = 11
(23+2)%12=1
(23+2+6)%12 = 7
(23+2+6+4)%12=11 -> seen before, so return true because:

(23+2+6+4) - (23) = 12 -> 12 % 12 = 0
 */
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum % k)) {
                map.put(sum % k, i + 1);
            } else if (map.get(sum % k) < i) {
                return true;
            }
        }
        return false;
    }
}