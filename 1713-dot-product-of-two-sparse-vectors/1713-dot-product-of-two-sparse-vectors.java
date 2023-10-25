class SparseVector {
    List<int[]> pairs;
    SparseVector(int[] nums) {
        pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        int p1 = 0, p2 = 0;
        while (p1 < pairs.size() && p2 < vec.pairs.size()) {
            if (pairs.get(p1)[0] == vec.pairs.get(p2)[0] ) {
                result += pairs.get(p1)[1]  * vec.pairs.get(p2)[1];
                p1++;
                p2++;
            } else if (pairs.get(p1)[0]  > vec.pairs.get(p2)[0] ) {
                p2++;
            } else {
                p1++;
            }
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);