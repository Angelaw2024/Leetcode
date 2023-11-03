import java.util.Stack;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

/*
 * On a 2d canvas, implement three methods 
 * paint(x, y, color), 
 * undo(), 
 * redo() and 
 * read(x,y), read(x, y) ‍‍‌‍‌‍‍‌‍‌‌‍‌‍‌‍‌will return the color of [x, y]
 * 'r' for Red
 * 'y' for yellow
 * 'b' for blue
 */
public class Canvas_test {

    private String[][] canvas;
    private Stack<Action> undoActions;
    private Stack<Action> redoActions;

    public static class Action {
        int row, col;
        String prevColor;
        String newColor;

        public Action(int row, int col, String prevColor, String newColor) {
            this.row = row;
            this.col = col;
            this.prevColor = prevColor;
            this.newColor = newColor;
        }
    }

    public Canvas_test(int width, int height) {
        // Initial and set default color
        canvas = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = "white";
            }
        }
        undoActions = new Stack<>();
        redoActions = new Stack<>();
    }

    public String read(int x, int y) {
        // Valid input
        if (!validateInput(x, y)) {
            return "Input out of boundry";
        }
        return canvas[x][y];
    }

    // true for success, false to fail
    public boolean paint(int x, int y, String color) {
        // Valid input
        if (!validateInput(x, y)) {
            return false;
        }

        String curColor = canvas[x][y];
        undoActions.push(new Action(x, y, curColor, color));
        redoActions.clear();
        canvas[x][y] = color;
        return true;
    }

    public boolean undo() {
        if (undoActions.isEmpty()) {
            System.out.println("nothing to undo");
            return false;
        }
        Action prevAction = undoActions.pop();
        canvas[prevAction.row][prevAction.col] = prevAction.prevColor;
        redoActions.push(prevAction);
        return true;
    }

    public boolean redo() {
        if (redoActions.isEmpty()) {
            System.out.println("nothing to redo");
            return false;
        }
        Action prevAction = redoActions.pop();
        canvas[prevAction.row][prevAction.col] = prevAction.newColor;
        undoActions.push(prevAction);
        return true;
    }

    private boolean validateInput(int x, int y) {
        if (x < 0 || x >= canvas.length || y < 0 || y >= canvas[0].length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Canvas_test canvas = new Canvas_test(2, 3);
        canvas.paint(0, 0, "red");
        canvas.undo();
        System.out.println(canvas.read(2, 2)); // WHITE
        canvas.paint(3, 3, "red");
        canvas.paint(0, 0, "red");
        canvas.paint(0, 0, "blue");
        System.out.println(canvas.read(0, 0)); // WHITE

        canvas.undo();
        System.out.println(canvas.read(0, 0)); // WHITE

        canvas.redo();
        System.out.println(canvas.read(0, 0)); // WHITE





    }


}
