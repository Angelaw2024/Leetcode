class Solution {
    public int divide1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int neg = 0;
        if (dividend < 0) {
            neg++;
            dividend = -dividend;
        }
        if (divisor < 0) {
            neg++;
            divisor = -divisor;
        }

        int quotient = 0;
        while (dividend - divisor >= 0) {
            quotient++;
            dividend -= divisor;
        }
        if (neg == 1) quotient = -quotient;
        return quotient;
    }
    private static int HALF_INT_MIN = -1073741824;

public int divide(int dividend, int divisor) {

    // Special cases: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }
    if (dividend == Integer.MIN_VALUE && divisor == 1) {
        return Integer.MIN_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    /* We want to find the largest doubling of the divisor in the negative 32-bit
     * integer range that could fit into the dividend.
     * Note if it would cause an overflow by being less than HALF_INT_MIN,
     * then we just stop as we know double it would not fit into INT_MIN anyway.
     divisor = -3，maxBit = 0。
-3 + -3 = -6，它仍然大于 -10，所以 maxBit = 1，divisor = -6。
-6 + -6 = -12，它不再大于 -10，所以停止循环。
      */
    int maxBit = 0;
    
    while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
        maxBit += 1;
        divisor += divisor;
    }

    int quotient = 0;
    /* We start from the biggest bit and shift our divisor to the right
     * until we can't shift it any further
     初始时，quotient = 0。
当 bit = 1 时，divisor = -6。因为 -6 >= -10，我们有 quotient = 0 - (1 << 1) = -2，dividend = -10 - (-6) = -4。
当 bit = 0 时，divisor = -3。因为 -3 >= -4，我们有 quotient = -2 - (1 << 0) = -3，dividend = -4 - (-3) = -1。
更多的 bit 不会帮助我们接近 0，所以停止。
*/
    for (int bit = maxBit; bit >= 0; bit--) {
        /* If the divisor fits into the dividend, then we should set the current
         * bit to 1. We can do this by subtracting a 1 shifted by the appropriate
         * number of bits. */
        if (divisor >= dividend) {
            quotient -= (1 << bit);
            /* Remove the current divisor from the dividend, as we've now
             * considered this part. */
            dividend -= divisor;
        }
        /* Shift the divisor to the right so that it's in the right place
         * for the next positon we're checking at. */
        divisor = (divisor + 1) >> 1;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        quotient = -quotient;
    }
    return quotient;
}
}
