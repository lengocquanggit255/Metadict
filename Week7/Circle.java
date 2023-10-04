import java.util.Objects;

public class Circle extends Shape {
    protected double radius;
    protected Point center;

    /**
     * Circle.
     */
    public Circle() {
        super();
    }

    /**
     * Circle.
     */
    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    /**
     * Circle.
     */
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    /**
     * Circle.
     */

    public Circle(Point center, double radius, String color, boolean filled) {
        super(color, filled);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Circle.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Circle.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Circle.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Circle.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Circle.
     */
    @Override
    public String toString() {
        String radius = Double.toString(this.radius);
        String filled = Boolean.toString(this.filled);
        String center = this.center.toString();
        return "Circle[center=" + center + ",radius=" + radius
                + ",color=" + this.color + ",filled=" + filled + "]";
    }

    /**
     * Circle.
     */
    @Override
    public double getArea() {
        return this.radius * this.radius * Math.PI;
    }

    /**
     * Circle.
     */
    @Override
    public double getPerimeter() {
        return 2 * this.radius * Math.PI;
    }

    /**
     * Circle.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) {
            return false;
        } else {
            Circle other = (Circle) o;
            return Math.abs(this.radius - other.radius) <= 0.001
                    && this.center.equals(other.center);
        }
    }

    /**
     * Circle.
     */
    @Override
    public int hashCode() {
        return Objects.hash(radius, center);
    }

}
