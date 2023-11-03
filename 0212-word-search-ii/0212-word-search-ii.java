class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String>  result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, trie.root, result);
            }
        }
        return result;
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '?'
           || node.children[board[i][j] - 'a'] == null) {
            return;
        }
        TrieNode curNode = node.children[board[i][j] - 'a'];
        if (curNode.word != null) {
            result.add(curNode.word);
            curNode.word = null;
        }
        char c = board[i][j];
        board[i][j] = '?';
        dfs(board, i + 1, j, curNode, result);
        dfs(board, i - 1, j, curNode, result);
        dfs(board, i, j - 1, curNode, result);
        dfs(board, i, j + 1, curNode, result);
        board[i][j] = c;
    }
    
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
        TrieNode(){};
    }
    
    class Trie {

        TrieNode root;
        
        Trie(){
            this.root = new TrieNode();
        }
        public void insert(String s) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                     cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
            }
            cur.word = s;
        }
    }
    
    
}