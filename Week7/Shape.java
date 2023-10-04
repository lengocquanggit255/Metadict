public abstract class Shape {
    protected String color;
    protected boolean filled;

    /**
     * Shape.
     */
    public Shape() {

    }

    /**
     * Shape.
     */
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    /**
     * Shape.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Shape.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Shape.
     */
    public boolean isFilled() {
        return this.filled;
    }

    /**
     * Shape.
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Shape.
     */
    public String toString() {
        String filled = Boolean.toString(this.filled);
        return "Shape[color=" + this.color + ",filled=" + filled + "]";
    }

    /**
     * Shape.
     */
    public abstract double getArea();

    /**
     * Shape.
     */
    public abstract double getPerimeter();

}