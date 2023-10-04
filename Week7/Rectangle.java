import java.util.Objects;

public class Rectangle extends Shape {
    protected double width;
    protected double length;
    protected Point topLeft;

    /**
     * Rectangle.
     */
    public Rectangle() {
        super();
    }

    /**
     * Rectangle.
     */
    public Rectangle(double width, double length) {
        super();
        this.width = width;
        this.length = length;
    }

    /**
     * Rectangle.
     */
    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    /**
     * Rectangle.
     */
    public Rectangle(Point topLeft, double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
        this.topLeft = topLeft;
    }

    /**
     * Rectangle.
     */
    public Point getTopLeft() {
        return this.topLeft;
    }

    /**
     * Rectangle.
     */
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * Rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Rectangle.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Rectangle.
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Rectangle.
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Rectangle.
     */
    @Override
    public String toString() {
        String topLeft = this.topLeft.toString();
        String width = Double.toString(this.width);
        String length = Double.toString(this.length);
        String filled = Boolean.toString(this.filled);
        return "Rectangle[topLeft=" + topLeft + ",width=" + width + ",length="
                + length + ",color=" + this.color + ",filled=" + filled + "]";
    }

    /**
     * Rectangle.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle)) {
            return false;
        } else {
            Rectangle other = (Rectangle) o;
            return Math.abs(this.width - other.width) <= 0.001
                    && Math.abs(this.length - other.length) <= 0.001
                    && this.topLeft.equals(other.topLeft);
        }
    }

    /**
     * Rectangle.
     */
    @Override
    public int hashCode() {
        return Objects.hash(width, length, topLeft);
    }

    /**
     * Rectangle.
     */
    @Override
    public double getArea() {
        return this.length * this.width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (this.length + this.width);
    }

}
