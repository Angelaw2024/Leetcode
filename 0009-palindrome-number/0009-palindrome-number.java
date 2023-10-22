class Solution {
    public boolean isPalindrome(int x) {
         if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = reverse(x);
        return x == revertedNumber;
    }

    public int reverse(int x) {
        long result = 0;
        while( x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)result;
    }
}