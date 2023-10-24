class Solution {
    public int nextGreaterElement(int n) {
        StringBuilder numStr = new StringBuilder(Integer.toString(n));
        int len = numStr.length();
        
        // 1. Find the first decreasing digit from the right
        int i = len - 2;
        while (i >= 0 && numStr.charAt(i) >= numStr.charAt(i + 1)) {
            i--;
        }
        
        // If no such digit is found, return -1
        if (i == -1) return -1;

        // 2. Swap it with the smallest digit found to its right that is greater than it
        int j = len - 1;
        while (numStr.charAt(j) <= numStr.charAt(i)) {
            j--;
        }
        char tmp = numStr.charAt(i);
        numStr.setCharAt(i, numStr.charAt(j));
        numStr.setCharAt(j, tmp);

        // 3. Reverse the substring to the right of the swapped digit
        numStr.replace(i + 1, len, new StringBuilder(numStr.substring(i + 1)).reverse().toString());

        long result = Long.parseLong(numStr.toString());
        return (result > n && result <= Integer.MAX_VALUE) ? (int) result : -1;
    }
}