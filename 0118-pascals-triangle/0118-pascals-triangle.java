class Solution {
    public List<List<Integer>> generate(int numRows) {
        // 初始化一个空的三角形列表
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // 基础情况：第一行总是 [1]
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        // 从第二行开始，生成每一行
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            // 初始化这一行为一个空列表
            List<Integer> row = new ArrayList<>();
            
            // 获取前一行的列表，以便于生成当前行
            List<Integer> prevRow = triangle.get(rowNum-1);

            // 第一个元素总是 1
            row.add(1);

            // 每个三角形元素（除了每行的第一个和最后一个元素）
            // 等于上面一行的左边和右边的元素之和
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // 最后一个元素总是 1
            row.add(1);

            // 将这一行添加到三角形列表中
            triangle.add(row);
        }

        return triangle;
    }
}