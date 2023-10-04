import java.util.Objects;

public class Point {
    private double pointX;
    private double pointY;

    /**
     * Point.
     */
    public Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    /**
     * Point.
     */
    public double getPointX() {
        return this.pointX;
    }

    /**
     * Point.
     */
    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    /**
     * Point.
     */
    public double getPointY() {
        return this.pointY;
    }

    /**
     * Point.
     */
    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    /**
     * Point.
     */
    public double distance(Point newPoint) {
        return Math.sqrt((this.pointX - newPoint.getPointX()) * (this.pointX - newPoint.getPointX())
                + (this.pointY - newPoint.getPointY()) * (this.pointY - newPoint.getPointY()));
    }

    /**
     * Point.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        } else {
            Point other = (Point) o;
            return (this.pointX == other.pointX && this.pointY == other.pointY);
        }
    }

    /**
     * Point.
     */
    public int hashCode() {
        return Objects.hash(pointX, pointY);
    }

    /**
     * Point.
     */
    public String toString() {
        return "(" + pointX + "," + pointY + ")";
    }

}
