import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Layers_batch {
    private Map<Integer, Map<String, String>> map = new HashMap<>();
    private Stack<Object> undoStack = new Stack<>();
    private Stack<Action> batchStack = new Stack<>();
    private Map<Integer, Map<String, String>> batchMap = new HashMap<>();

    public static class Action {
        int id;
        boolean isAdd;
        Map<String, String> prev;
        Map<String, String> cur;

        public Action(int id, boolean isAdd, Map<String, String>  curMap, Map<String, String>  prevMap) {
            this.id = id;
            this.isAdd = isAdd;
            this.prev = prevMap;
            this.cur = curMap;
        }
    }

    public void apply(int id, String[][] props) {
        Map<String, String> currentProps = map.getOrDefault(id, new HashMap<>());
        Map<String, String> prevProps = batchMap.getOrDefault(id, new HashMap<>(currentProps));  // Use batchMap for previous values
        
        for (String[] pair : props) {
            currentProps.put(pair[0], pair[1]);
        }

        map.put(id, currentProps);
        batchMap.put(id, prevProps);  // Store the original value in batchMap
    }

    public void undo() {
        if (undoStack.isEmpty()){
            return;
        }
        Object top = undoStack.pop();
        if (top instanceof Action) {
            Action action = (Action) top;
            if (action.isAdd) {
                map.remove(action.id);
            } else {
                map.put(action.id, action.prev);
            }
        } else if (top instanceof List) {
            List<Action> actions = (List<Action>) top;
            for (int i = actions.size() - 1; i >= 0; i--) {
                Action action = actions.get(i);
                if (action.isAdd) {
                    map.remove(action.id);
                } else {
                    map.put(action.id, action.prev);
                }
            }
        }
    }

    public void commit_batch() {
        for (Map.Entry<Integer, Map<String, String>> entry : batchMap.entrySet()) {
            Map<String, String> currentProps = map.getOrDefault(entry.getKey(), new HashMap<>());
            boolean isAdd = !map.containsKey(entry.getKey());
            batchStack.push(new Action(entry.getKey(), isAdd, currentProps, entry.getValue()));
        }

        if (!batchStack.isEmpty()) {
            List<Action> actionsBatch = new ArrayList<>(batchStack);
            undoStack.push(actionsBatch);
            batchStack.clear();
            batchMap.clear();
        }
    }

    public void printHelper() {
        for (Map.Entry<Integer, Map<String, String>> entry: map.entrySet()) {
            System.out.print("Layer Id:" + entry.getKey());
            System.out.print("; props: " + entry.getValue());
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Layers_batch layers = new Layers_batch();

        // First batch of changes
        layers.apply(1, new String[][] {{"color", "green"}});
        layers.apply(2, new String[][] {{"shape", "triangle"}, {"color", "blue"}});
        layers.apply(1, new String[][] {{"color", "pink"}});
        layers.commit_batch();
        layers.printHelper(); // Print the layers after the first batch

        System.out.println("----------------------");

        // Second batch of changes
        layers.apply(1, new String[][] {{"color", "blue"}});
        layers.apply(1, new String[][] {{"color", "white"}});
        layers.commit_batch();
        layers.printHelper();

        System.out.println("----------------------");

        layers.undo();
        layers.printHelper();
        System.out.println("----------------------");

        layers.undo();
        layers.printHelper();
    }
}
