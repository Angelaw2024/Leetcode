class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Union find
        int[] parents = new int[accounts.size()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i; // assign the initial parent to themselves
        }

        Map<String, Integer> map = new HashMap<>(); // <email, parent>
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (map.containsKey(email)) {
                    int currentParent = map.get(email); // parent stored
                    int parent1 = findParent(parents, i); // current' parent
                    int parent2 = findParent(parents, currentParent); // stored parent's parent
                    if (parent1 != parent2){
                        parents[parent1] = parent2;
                    }
                } else {
                    map.put(email, i);
                }
            }
        }
       
        // <id, emails>
        Map<Integer, Set<String>> users = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int parent = findParent(parents, i);
            List<String> emails = accounts.get(i);
            users.computeIfAbsent(parent, k -> new HashSet<>()).addAll(emails.subList(1, emails.size()));
        }

        
        List<List<String>> res = new ArrayList<>();
        for (Integer i : users.keySet()) {
            String name = accounts.get(i).get(0);
            List<String> emails = new ArrayList<>(users.get(i));
            Collections.sort(emails);
            emails.add(0, name);
            res.add(emails);
        }
        return res;
    }
    private int findParent(int[] parents, int child) {
        while (child != parents[child]) {
            parents[child] = parents[parents[child]];
            child = parents[child];
        }
        return child;
    }
}