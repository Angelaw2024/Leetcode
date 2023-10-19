class MedianFinder {
    PriorityQueue<Integer> small; // smaller part
    PriorityQueue<Integer> large; // larger part
    public MedianFinder() {
        small = new PriorityQueue<Integer>(Collections.reverseOrder());
        large = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        small.add(num);
        large.add(small.poll());
        if (small.size() < large.size()) {
            small.add(large.poll());
        }
    }
    
    public double findMedian() {
        if (small.size() == 0 && large.size() == 0) {
            return 0;
        }
        if (small.size() == large.size()) {
            return (double)(small.peek() + large.peek()) / 2.0;
        } else {
            return small.peek();
        }

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */