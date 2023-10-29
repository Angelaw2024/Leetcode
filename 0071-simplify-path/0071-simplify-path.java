class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] componenets = path.split("/");
        for (String comp : componenets) {
            if (comp.equals(".") || comp.isEmpty()) {
                continue;
            } else if (comp.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.add(comp);
            }
        }
    
        if(stack.isEmpty()) return "/";

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }
        return sb.toString();
    }
}