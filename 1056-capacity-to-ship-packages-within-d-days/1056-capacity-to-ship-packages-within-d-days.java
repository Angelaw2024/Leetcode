class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxLoad = 0, totalLoad = 0;
        for (int weight : weights) {
            totalLoad += weight;
            maxLoad = Math.max(maxLoad, weight);
        }
        int l = maxLoad, r = totalLoad;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int shipDays = getShipDays(weights, mid);
            if (shipDays <= days) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    public int getShipDays(int[] weights, int limit) {
        int sum = 0;
        int days = 1;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > limit) {
                days++;
                sum = weights[i];
            }
        }
        return days;
    }
}