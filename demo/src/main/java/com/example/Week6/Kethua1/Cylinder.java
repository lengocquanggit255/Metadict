package com.example.Week6.Kethua1;

public class Cylinder extends Circle {
    private double height;

    /**
     * Method double.
     */
    public Cylinder() {
        super();
    }

    /**
     * Method double.
     */
    public Cylinder(double height) {
        super();
        this.height = height;
    }

    /**
     * Method double.
     */
    public Cylinder(double height, double radius) {
        super(radius);
        this.height = height;
    }

    /**
     * Method double.
     */
    public Cylinder(double height, double radius, String color) {
        super(radius, color);
        this.height = height;
    }

    /**
     * Method double.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Method double.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Method double.
     */
    public double getVolume() {
        return super.getArea() * this.height;
    }

    /**
     * Method double.
     */
    public double getArea() {
        return 2 * super.getArea() + 2 * Circle.PI * this.height;
    }

    /**
     * Method double.
     */
    public String toString() {
        return "Cylinder[" + super.toString() + ",height=" + this.height + "]";
    }
}
