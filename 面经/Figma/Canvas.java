import java.util.Stack;

public class Canvas {
    private static class Action {
        int x, y;
        String prevColor;
        String newColor;

        public Action(int x, int y, String prevColor, String newColor) {
            this.x = x;
            this.y = y;
            this.prevColor = prevColor;
            this.newColor = newColor;
        }
    }

    private String[][] canvas;
    private Stack<Action> undoStack;
    private Stack<Action> redoStack;

    public Canvas(int width, int height) {
        canvas = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = "WHITE"; // default color
            }
        }
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void paint(int x, int y, String color) {
        if (x < 0 || x >= canvas[0].length || y < 0 || y >= canvas.length) {
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
        String prevColor = canvas[y][x];
        canvas[y][x] = color;
        undoStack.push(new Action(x, y, prevColor, color));
        redoStack.clear(); // clear the redo stack whenever a new action is performed
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        Action action = undoStack.pop();
        canvas[action.y][action.x] = action.prevColor;
        redoStack.push(action);
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        Action action = redoStack.pop();
        canvas[action.y][action.x] = action.newColor;
        undoStack.push(action);
    }

    public String read(int x, int y) {
        if (x < 0 || x >= canvas[0].length || y < 0 || y >= canvas.length) {
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
        return canvas[y][x];
    }

    public static void main(String[] args) {
        Canvas c = new Canvas(5, 5);
        c.paint(2, 2, "RED");
        System.out.println(c.read(2, 2)); // RED
        c.undo();
        System.out.println(c.read(2, 2)); // WHITE
        c.redo();
        System.out.println(c.read(2, 2)); // RED
    }
}