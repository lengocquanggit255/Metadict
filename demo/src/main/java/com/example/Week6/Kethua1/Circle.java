package com.example.Week6.Kethua1;
public class Circle {
    private double radius;
    private String color;

    protected static final double PI = 3.14;

    /**
     * Method double.
     */
    public Circle() {
    }

    /**
     * @return double
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * @return double
     */
    public Circle(double radius, String color) {
        this.color = color;
        this.radius = radius;
    }

    /**
     * Method double.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Method double.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Method double.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Method double.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Method double.
     */
    public double getArea() {
        return this.radius + this.radius * PI;
    }

    /**
     * Method double.
     */
    public String toString() {
        return "Circle[radius=" + radius + ",color=" + color + "]";
    }
}
