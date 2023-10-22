class Solution {
    public int romanToInt(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int sum = 0;
        int prevValue = romanMap.get(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            int curValue = romanMap.get(s.charAt(i));
            if (curValue > prevValue) {
                sum += (curValue - 2 * prevValue); // Subtract twice because prevValue was added before
            } else {
                sum += curValue;
            }
            prevValue = curValue;
        }

        return sum + romanMap.get(s.charAt(0)); 
    }
}