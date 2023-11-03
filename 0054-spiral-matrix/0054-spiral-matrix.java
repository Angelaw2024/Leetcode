class Solution {
   	public List<Integer> spiralOrder(int[][] matrix) {
	List<Integer> result = new ArrayList<>();
	//corner case
	if (matrix.length == 0 || matrix[0].length == 0) {
		return result;
	}

	int n = matrix.length - 1;
	int m = matrix[0].length - 1;
	
	int x = 0;
	int y = 0;
	
	while(n > 0 && m > 0) {
		for (int i = y; i < y + m; i++) {
			result.add(matrix[x][i]);
		}
		for (int i = x; i < x + n; i++) {
			result.add(matrix[i][y + m]);
		}
		for (int i = y + m; i > y; i--) {
			result.add(matrix[x + n][i]);
		}
		for (int i = x + n; i > x; i--) {
			result.add(matrix[i][y]);
		}
		x++;
		y++;
		n = n -2;
		m = m - 2;
	}
	if (n == 0) {
		for (int i = y; i <= y + m; i++) {
			result.add(matrix[x][i]);
		}
	} else if (m == 0) {
		for (int i = x; i <= x + n; i++) {
			result.add(matrix[i][y]);
		}
	}
	return result;
    }
}