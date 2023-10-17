class Solution {
    public double myPow(double x, int n) {
        long m = (long) n;
        if (m == 0) return 1;

        if (m < 0) {
            m = -1 * m;
            x = 1.0 / x;
        }

        double result = 1;
        while (m != 0) {
            if (m % 2 == 1) {
                result *= x;
                m -= 1;
            }
            x = x * x;
            m = m / 2;
        }
        return result;
    }
}