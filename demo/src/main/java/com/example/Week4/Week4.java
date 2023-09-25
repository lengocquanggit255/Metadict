package com.example.Week4;

public class Week4 {

    /**
     * Tim gia tri lon nhat cua hai so nguyen.
     */
    public static int max2Int(int a, int b) {
        // Tim gia tri lon nhat cua hai so nguyen
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Tim gia tri nho nhat cua 1 mang so nguyen.
     */
    public static int minArray(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Tinh BMI.
     */
    public static String calculateBMI(double weight, double height) {
        double BMI = weight / ((double) height * (double) height);
        double roundedNumber = Math.round(BMI * 10.0) / 10.0;
        System.out.println(roundedNumber);
        if (roundedNumber >= 25) {
            return "Béo phì";
        } else if (roundedNumber >= 23 && roundedNumber <= 24.9) {
            return "Thừa cân";
        } else if (roundedNumber >= 18.5 && roundedNumber <= 22.9) {
            return "Bình thường";
        } else {
            return "Thiếu cân";
        }
    }
}