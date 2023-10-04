public class Square extends Rectangle {
    /**
     * Square.
     */
    public Square() {
        super();
    }

    /**
     * Square.
     */
    public Square(double side) {
        super(side, side);
    }

    /**
     * Square.
     */
    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }

    /**
     * Square.
     */
    public Square(Point topLeft, double side, String color, boolean filled) {
        super(topLeft, side, side, color, filled);
    }

    /**
     * Square.
     */
    public double getSide() {
        return this.length;
    }

    /**
     * Square.
     */
    public void setSide(double side) {
        super.setLength(side);
        super.setWidth(side);
    }

    /**
     * Square.
     */
    public void setWidth(double side) {
        this.width = side;
        this.length = side;
    }

    /**
     * Square.
     */
    public void setLength(double side) {
        this.width = side;
        this.length = side;
    }

    /**
     * Square.
     */
    @Override
    public String toString() {
        String topLeft = this.topLeft.toString();
        String side = Double.toString(this.length);
        String filled = Boolean.toString(this.filled);
        return "Square[topLeft=" + topLeft + ",side=" + side
                + ",color=" + this.color + ",filled=" + filled + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Square)) {
            return false;
        } else {
            Square other = (Square) o;
            return Math.abs(this.getSide() - other.getSide()) <= 0.001
                    && this.topLeft.equals(other.topLeft);
        }
    }

}
