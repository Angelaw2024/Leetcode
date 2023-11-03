class Solution {
    // 1-9 的英文单词
    public String one(int num) {
        String[] words = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return words[num];
    }

    // 10-19 的英文单词
    public String twoLessThan20(int num) {
        String[] words = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        return words[num - 10];
    }

    // 十位数的英文单词
    public String ten(int num) {
        String[] words = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        return words[num];
    }

    // 处理 1-99 的数字
    public String two(int num) {
        if (num < 10)
            return one(num);
        else if (num < 20)
            return twoLessThan20(num);
        else {
            int tenner = num / 10;
            int rest = num % 10;
            return (rest != 0) ? (ten(tenner) + " " + one(rest)) : ten(tenner);
        }
    }

    // 处理 1-999 的数字
    public String three(int num) {
        int hundred = num / 100;
        int rest = num % 100;
        if (hundred != 0 && rest != 0)
            return one(hundred) + " Hundred " + two(rest);
        else if (hundred != 0)
            return one(hundred) + " Hundred";
        else
            return two(rest);
    }

    // 主函数，处理任意数字
    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int billion = num / 1000000000;
        int million = (num % 1000000000) / 1000000;
        int thousand = (num % 1000000) / 1000;
        int rest = num % 1000;

        String result = "";
        if (billion != 0)
            result = three(billion) + " Billion";
        if (million != 0)
            result = result.isEmpty() ? three(million) + " Million" : result + " " + three(million) + " Million";
        if (thousand != 0)
            result = result.isEmpty() ? three(thousand) + " Thousand" : result + " " + three(thousand) + " Thousand";
        if (rest != 0)
            result = result.isEmpty() ? three(rest) : result + " " + three(rest);
        return result;
    }
}
