package com.example.Week4;

public class Solution {

    /**
     * The numerator of the fraction.
     */
    private int numerator;

    /**
     * The denominator of the fraction.
     */
    private int denominator;

    /**
     * The numerator of the fraction.
     */
    int getNumerator() {
        return numerator;
    }

    /**
     * The new numerator of the fraction.
     */
    void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * The denominator of the fraction.
     */
    int getDenominator() {
        return denominator;
    }

    /**
     * The new denominator of the fraction.
     */
    void setDenominator(int denominator) {
        if (denominator == 0) {
            this.denominator = 1;
        } else {
            this.denominator = denominator;
        }
    }

    /**
     * The numerator of the fraction.
     */
    public Solution(int numerator, int denominator) {
        if (denominator == 0) {
            this.denominator = 1;
            this.numerator = 0;
        } else {
            this.denominator = denominator;
            this.numerator = numerator;
        }
    }

    /**
     * Function to print the fraction.
     */
    private int gcd(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        return gcd(b, a % b);
    }

    /**
     * Reduce the current fraction.
     */
    public Solution reduce() {
        int gcd1 = gcd(numerator, denominator);
        this.denominator /= gcd1;
        this.numerator /= gcd1;
        return this;
    }

    /**
     * Other The solution object that will be added with the current object.
     */
    public Solution add(Solution other) {
        int newDenominator = this.denominator * other.denominator;
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        return new Solution(newNumerator, newDenominator);
    }

    /**
     * Other The solution object that will be subtracted with the current object.
     */
    public Solution subtract(Solution other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Solution(newNumerator, newDenominator);
    }

    /**
     * Other The solution object that will be multiplied with the current object.
     */
    public Solution multiply(Solution other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Solution(newNumerator, newDenominator);
    }

    /**
     * other The solution object that will be divided with the current object.
     */
    public Solution divide(Solution other) {
        if (other.numerator == 0) {
            return this;
        } else {
            int newNumerator = this.numerator * other.denominator;
            int newDenominator = this.denominator * other.numerator;
            return new Solution(newNumerator, newDenominator);
        }
    }

    /**
     * Obj is an object that will be compared with the current object.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Solution) {
            Solution other = (Solution) obj;
            return this.numerator * other.denominator == this.denominator * other.numerator;
        }
        return false;
    }

}