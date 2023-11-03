import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// valid input type
public class Layers_past {
    Map<String, Layer> map;
    Stack<List<Action>> undoStack;
    List<Action> actions;

    public static class Layer {
        public String id;
        public Map<String, String> properties;
        public Layer(String id, Map<String, String> properties) {
          this.id = id;
          this.properties = properties;
        }
        
        public String toString() {
          return "id: " + this.id + ", properties: " + this.properties.toString();
        }
      }

      public static class Action {
        int id;
        boolean isAdd;
        Map<String, String> prev;
        Map<String, String> cur;

        public Action(String id2, boolean isAdd, Map<String, String>  curMap, Map<String, String>  prevMap) {
            this.id = id;
            this.isAdd = isAdd;
            this.prev = prevMap;
            this.cur = curMap;
        }
    }

    public Layer layerById(String id) {
      // valid 
      if (!map.containsKey(id)) {
        return null;
      }
      return map.get(id);
    }
    
    public void apply(String id, String property, String value) {
      boolean isAdd = !map.containsKey(id);
      Layer layer = map.getOrDefault(id, new Layer(id, new HashMap<>()));
      Map<String, String> props = layer.properties;
      Map<String, String> prev = new HashMap<>(props);
      props.put(property, value);

      actions.add(new Action(id, isAdd, prev, props));
    }
    
    public void undo() {
      if (undoStack.isEmpty()) {
        return;
      }
      List<Action> curActions = undoStack.pop();
        System.out.println(curActions);


      for (int i = curActions.size() - 1; i >= 0; i--) {
        Action action = curActions.get(i);
        if (action.isAdd) {
          map.remove(action.id);
        } else {
          Layer layer = map.get(action.id);
          layer.properties = action.prev;
        }
      }
    }
    // Part 2: add a method called `commitBatch()` that allows multiple changes to
    // be batched together. The document is still changed during each "apply()", but 
    // each "undo()" call reverts the last batch of changes.
    // 
    // You can assume that `commitBatch()` will _always_ be called before `undo()`.
    public void commitBatch() {
      undoStack.push(actions);
      actions.clear();
    }

    public static void main(String[] args) {
        Layers_past layers = new Layers_past();

        // First batch of changes
        layers.apply("a", "color", "green");
        layers.apply("a", "color", "blue");
        layers.commitBatch();
   System.out.println(layers.layerById("a"));

        System.out.println("----------------------");

        // // Second batch of changes
        // layers.apply(1, new String[][] {{"color", "blue"}});
        // layers.apply(1, new String[][] {{"color", "white"}});
        // layers.commit_batch();
        // layers.printHelper(); 

        // System.out.println("----------------------");

        // layers.undo();
        // layers.printHelper(); 
    }
}
