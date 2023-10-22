class Solution {
  public String removeDuplicates(String S) {
    StringBuilder sb = new StringBuilder();  // 用于构建最终的字符串
    int sbLength = 0;  // StringBuilder的当前长度
    // 遍历输入字符串的每一个字符
    for(char character : S.toCharArray()) {
      // 如果StringBuilder的长度不为0，且当前字符与StringBuilder最后一个字符相同
      if (sbLength != 0 && character == sb.charAt(sbLength - 1)) {
        sb.deleteCharAt(sbLength-- - 1);  // 删除StringBuilder的最后一个字符
      } else {
        // 如果当前字符与StringBuilder的最后一个字符不相同，或StringBuilder长度为0
        sb.append(character);  // 将当前字符追加到StringBuilder
        sbLength++;  // 更新StringBuilder的长度
      }
    }
    return sb.toString();  // 返回最终结果
  }
}
