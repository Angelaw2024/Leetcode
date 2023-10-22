class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // 初始化两个候选者和它们的计数器
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        // 第一遍遍历，找出两个出现次数最多的元素（Boyer-Moore Voting Algorithm）
        for (int n : nums) {
            if (candidate1 != null && n == candidate1) {
                count1++;
            } else if (candidate2 != null && n == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = n;
                count1++;
            } else if (count2 == 0) {
                candidate2 = n;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        // 第二遍遍历，计算两个候选者的确切出现次数
        count1 = 0;
        count2 = 0;
        for (int n : nums) {
            if (candidate1 != null && n == candidate1) count1++;
            if (candidate2 != null && n == candidate2) count2++;
        }

        // 检查是否有候选者的出现次数超过 n/3
        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) result.add(candidate1);
        if (count2 > nums.length / 3) result.add(candidate2);

        return result;
    }
}