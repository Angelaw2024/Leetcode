import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// valid input type
public class Layers1 {
    private Map<Integer, Map<String, String>> map = new HashMap<>();
    private Stack<Action> undoStack = new Stack<>();
    
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
        boolean isAdd = !map.containsKey(id);
        Map<String, String> currentProps = map.getOrDefault(id, new HashMap<>());
        Map<String, String> prevProps = new HashMap<>(currentProps);

        for (String[] pair : props) {
            currentProps.put(pair[0], pair[1]);
        }

        map.put(id, currentProps);
        undoStack.push(new Action(id, isAdd, currentProps, prevProps));
    }

    public void undo() {
        if (undoStack.isEmpty()){
            return;
        }
        Action action = undoStack.pop();
        if (action.isAdd) {
            map.remove(action.id);
        } else {
            map.put(action.id, action.prev);
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
        Layers1 layers = new Layers1();

        // First batch of changes
        layers.apply(1, new String[][] {{"color", "green"}});
        layers.apply(1, new String[][] {{"color", "white"}});
        layers.printHelper(); 
        System.out.println("----------------------");

        layers.undo();
        layers.printHelper(); 
    }
}
