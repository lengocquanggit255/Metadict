import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Layer {
    private List<Shape> shapes = new ArrayList<Shape>();

    /**
     * Layer.
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    /**
     * Layer.
     */
    public void removeCircles() {
        Shape[] target = new Shape[shapes.size()];
        int n = 0;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Circle) {
                target[n++] = shapes.get(i);
            }
        }

        for (int i = 0; i < n; i++) {
            shapes.remove(target[i]);
        }
    }

    /**
     * Layer.
     */
    public String getInfo() {

        String res = "Layer of crazy shapes:\n";
        for (Shape shape : shapes) {
            res += shape.toString() + "\n";
        }

        return res;
    }

    /**
     * Layer.
     */
    public void removeDuplicates() {
        Map<Integer, Shape> unorderedMap = new LinkedHashMap<>();
        for (int i = 0; i < shapes.size(); i++) {
            if (!unorderedMap.containsKey(shapes.get(i).hashCode())) {
                unorderedMap.put(shapes.get(i).hashCode(), shapes.get(i));
            }
        }
        shapes.removeAll(shapes);
        for (Map.Entry<Integer, Shape> pair : unorderedMap.entrySet()) {
            shapes.add(pair.getValue());
        }
    }
}
