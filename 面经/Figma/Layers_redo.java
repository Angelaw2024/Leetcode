import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// valid input type
public class Layers_redo {
    private Map<Integer, Layer> map = new HashMap<>();
    // private Stack<Action> undoStack = new Stack<>();
    // Add stack for batch
    private Stack<Object> undoStack = new Stack<>();    
    private Stack<Action> batchStack = new Stack<>();
    
    // layer
    public static class Layer {
        int id;
        Map<String, String> props = new HashMap<>();

        public Layer(int id, String[][] pairs) {
            this.id = id;
            
            for (String[] s: pairs) {
                props.putIfAbsent(s[0], s[1]);
            }
        }
    }

    public static class Action {
        int id;
        boolean isAdd;
        Map<String, String> prev;
        Map<String, String> cur;

        public Action(int id, boolean isAdd, Map<String, String>  curMap, Map<String, String>  prevMap) {
            this.id = id;
            this.isAdd = isAdd;
            if (!isAdd) {
                this.prev = prevMap;
            } 
            this.cur = curMap;

        }
    }

    public void apply(int id, String[][] props) {
        Layer layer = new Layer(id, props);
        if (!map.containsKey(id)) {
            map.put(id, layer);
            // undoStack.push(new Action(id, false, map.get(id).props, null));
            batchStack.push(new Action(id, true, map.get(id).props, null));
        } else {
            Map<String, String> prevMap = new HashMap<>(map.get(id).props);
            for (String[] pair: props) {
                map.get(id).props.put(pair[0], pair[1]);
            }
            // undoStack.push(new Action(id, true, map.get(id).props, prevMap));
            batchStack.push(new Action(id, false, map.get(id).props, prevMap));

        }
    }

    public void undo() {
        if (undoStack.isEmpty()){
            return;
        }
        // Action action = undoStack.pop();
        // if (!action.isExist) {
        //     map.remove(action.id);
        // } else {
        //     map.get(action.id).props = action.prev;
        // }
        Object top = undoStack.pop();
        if (top instanceof Action) {
            Action action = (Action) top;
            if (action.isAdd) {
                map.remove(action.id);
            } else {
                map.get(action.id).props = action.prev;
            }
        } else if (top instanceof List) {
            List<Action> actions = (List<Action>) top;
            for (int i = actions.size() - 1; i >= 0; i--) {
                Action action = actions.get(i);
                if (action.isAdd) {
                    map.remove(action.id);
                } else {
                    map.get(action.id).props = action.prev;
                }
            }
        }
    }

    public void commit_batch() {
        if (!batchStack.isEmpty()) {
            List<Action> actionsBatch = new ArrayList<>(batchStack);
            undoStack.push(actionsBatch);
            batchStack.clear();
        }
    }
    
    public void printHelper() {
        for (Map.Entry<Integer, Layer> entry: map.entrySet()) {
            System.out.print("Layer Id:" + entry.getKey());
            System.out.print("; props: " + entry.getValue().props);
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        Layers_redo layers = new Layers_redo();

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
    }
}
