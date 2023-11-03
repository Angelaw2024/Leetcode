import java.io.*;
import java.util.*;


class Solution {

  class Layer {
    public String id;
    public HashMap<String, String> properties;
    public Layer(String id, HashMap<String, String> properties) {
      this.id = id;
      this.properties = properties;
    }
    
    public String toString() {
      return "id: " + this.id + ", properties: " + this.properties.toString();
    }
  }

  class Action {
    String id;
    boolean isAdd;
    HashMap<String, String> prev;
    HashMap<String, String> cur;

    public Action(String id, boolean isAdd, HashMap<String, String> prev, HashMap<String, String> cur) {
      this.id = id;
      this.isAdd = isAdd;
      this.prev = prev;
      this.cur = cur;
    }
  }

  class Document {
    HashMap<String, Layer> map;
    Stack<List<Action>> undoStack;
    List<Action> actions;

    public Document(Layer[] layers) {
      // Initial
      map = new HashMap<>();
      undoStack = new Stack<>();
      actions = new ArrayList<>();

      for (Layer layer: layers) {
        map.putIfAbsent(layer.id, layer);
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
      HashMap<String, String> props = layer.properties;
      HashMap<String, String> prev = new HashMap<>(props);
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

  }
  public static void main(String[] args) {
    // Solution.testApplyAndUndo();
    // System.out.println("All tests passed");
    Solution.testCommitBatch();
  }
  
//   public static void testApplyAndUndo() {
//     Document document = new Document(new Layer[]{
//       new Layer("a", new HashMap<String, String>(Map.of("color", "red"))),
//       new Layer("b", new HashMap<String, String>(Map.of("shape", "triangle"))),
//     });

//     document.apply("a", "color", "green");
//     document.apply("b", "shape", "square");
//     document.apply("a", "color", "blue");
//     document.undo();
//     // System.out.println(document.layerById("a"));
    
//     assertEquals("green", document.layerById("a").properties.get("color"));
//     assertEquals("square", document.layerById("b").properties.get("shape"));

//     document.apply("a", "color", "purple");
//     assertEquals("purple", document.layerById("a").properties.get("color"));
//     assertEquals("square", document.layerById("b").properties.get("shape"));

//     document.undo();
//     assertEquals("green", document.layerById("a").properties.get("color"));
//     assertEquals("square", document.layerById("b").properties.get("shape"));

//     document.undo();
//     assertEquals("green", document.layerById("a").properties.get("color"));
//     assertEquals("triangle", document.layerById("b").properties.get("shape"));

//     document.undo();
//     assertEquals("red", document.layerById("a").properties.get("color"));
//     assertEquals("triangle", document.layerById("b").properties.get("shape"));

    
//   }

  public static void testCommitBatch() {
    Document document = new Document(new Layer[]{
      new Layer("a", new HashMap<String, String>(Map.of("color", "red"))),
      new Layer("b", new HashMap<String, String>(Map.of("shape", "triangle"))),
    });

    document.apply("a", "color", "green");
    document.apply("a", "color", "blue");
    document.apply("b", "shape", "square");
    document.commitBatch();

   System.out.println(document.layerById("a"));
       document.undo();

  //   document.apply("a", "color", "purple");
  //   document.apply("a", "color", "orange");
  //   document.commitBatch();

  //  System.out.println(document.layerById("a"));

  //   document.undo();
  //   System.out.println(document.layerById("a"));

    // assertEquals("blue", document.layerById("a").properties.get("color"));
    // assertEquals("square", document.layerById("b").properties.get("shape"));
    
    // document.undo();
    // assertEquals("red", document.layerById("a").properties.get("color"));
    // assertEquals("triangle", document.layerById("b").properties.get("shape"));
  }
}