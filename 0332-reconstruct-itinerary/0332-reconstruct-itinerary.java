class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String,PriorityQueue<String>> map = new HashMap<>();
        for (List<String> l : tickets) {
            if (!map.containsKey(l.get(0))) {
                map.put(l.get(0), new PriorityQueue<>());
            }
            map.get(l.get(0)).add(l.get(1));
        }
        
        List<String> res = new ArrayList<>();
        dfs(map, res, "JFK");
        Collections.reverse(res);
        return res;
    }
    
    public void dfs(Map<String, PriorityQueue<String>> map, List<String> res, String cur) {
        PriorityQueue<String> next = map.get(cur);
        while (next != null && next.size() > 0) {
            dfs(map, res, next.poll());
        }
        res.add(cur);
    }
}