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

class Solution1 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();
        // initial union find
        int[] parents = new int[10000];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        int id = 0;
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, id);
                    id++;
                }
                int root1 = findParent(parents, emailToId.get(account.get(1)));
                int root2 = findParent(parents, emailToId.get(email));
                parents[root1] = root2;
            }
        }

        Map<Integer, List<String>> users = new HashMap<>();
        for (String email : emailToName.keySet()) {
            int index = findParent(parents, emailToId.get(email));
            users.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> emails : users.values()) {
            Collections.sort(emails); // Sort emails alphabetically
            String name = emailToName.get(emails.get(0));
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