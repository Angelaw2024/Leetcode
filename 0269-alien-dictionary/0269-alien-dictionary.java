class Solution {
    Map<Character, List<Character>> children = new HashMap<>();
    Map<Character, List<Character>> parents = new HashMap<>();

    public String alienOrder(String[] words) {
        for (String word: words) {
            for (char c: word.toCharArray()) {
                children.putIfAbsent(c, new ArrayList<>());
                parents.putIfAbsent(c, new ArrayList<>());   
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }

            int j = 0;
            while (j < word1.length() && j < word2.length()) {
                char parent = word1.charAt(j);
                char child = word2.charAt(j);
                j++;
                if (parent != child) {
                    children.get(parent).add(child);
                    parents.get(child).add(parent);
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (var entry: parents.entrySet()) {
            if (entry.getValue().size() == 0) {
                queue.add(entry.getKey());
            }
        }

        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            res.append(c);
            List<Character> child = children.get(c);
            for (Character cur : child) {
                parents.get(cur).remove(c);
                if (parents.get(cur).size() == 0) {
                    queue.add(cur);
                }
            }
        }
        if (res.length() != children.size()) return "";
        return res.toString();
    }
}