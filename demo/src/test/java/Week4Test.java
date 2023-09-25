import org.junit.Assert;
import org.junit.Test;

import com.example.Week4.Week4;

public class Week4Test {
    @Test
    public void testMax2Int1() {
        int a = 2;
        int b = 4;
        Assert.assertEquals(4, Week4.max2Int(a, b));
    }

    @Test
    public void testMax2Int2() {
        int a = 20000000;
        int b = 4;
        Assert.assertEquals(20000000, Week4.max2Int(a, b));
    }

    @Test
    public void testMax2Int3() {
        int a = 20000000;
        int b = 100000;
        Assert.assertEquals(20000000, Week4.max2Int(a, b));
    }

    @Test
    public void testMax2Int4() {
        int a = 200;
        int b = 100000;
        Assert.assertEquals(100000, Week4.max2Int(a, b));
    }

    @Test
    public void testMax2Int5() {
        int a = 400;
        int b = -1;
        Assert.assertEquals(400, Week4.max2Int(a, b));
    }

    @Test
    public void testMinArray1() {
        int array[] = { 1, 2, 3, 4, 5, 6, 7 };
        Assert.assertEquals(1, Week4.minArray(array));
    }

    @Test
    public void testMinArray2() {
        int array[] = { 3, -5, 0, 7, -2, 1, -4, 6, -1, 2 };
        Assert.assertEquals(-5, Week4.minArray(array));
    }

    @Test
    public void testMinArray3() {
        int array[] = { -3, -6, 5, -7, 4, -8, 9, -10, 8, -9 };
        Assert.assertEquals(-10, Week4.minArray(array));
    }

    @Test
    public void testMinArray4() {
        int array[] = { -6, -7, -4, -9, -5, -3, -10, -1, -2, 0, -8, 4, 3, 9, -10, 0, 2, -6, 1, 7, -8, 5, -7, 8, 6, 4, 3,
                0, 1, 9 };
        Assert.assertEquals(-10, Week4.minArray(array));
    }

    @Test
    public void testMinArray5() {
        int array[] = { -6, -7, -4, -9, -5, -3, -10, -1, -2, 0, -8, 4, 3, 9, -10, 0, 2, -6, 1, 7 };
        Assert.assertEquals(-10, Week4.minArray(array));
    }

    @Test
    public void testCalculateBMI1() {
        double weight = 30;
        double height = 1.65;
        Assert.assertEquals("Thiếu cân", Week4.calculateBMI(weight, height));
    }

    @Test
    public void testCalculateBMI2() {
        double weight = 70;
        double height = 1.75;
        Assert.assertEquals("Bình thường", Week4.calculateBMI(weight, height));
    }

    @Test
    public void testCalculateBMI3() {
        double weight = 80;
        double height = 1.85;
        Assert.assertEquals("Thừa cân", Week4.calculateBMI(weight, height));
    }

    @Test
    public void testCalculateBMI4() {
        double weight = 90;
        double height = 1.95;
        Assert.assertEquals("Thừa cân", Week4.calculateBMI(weight, height));

    }

    @Test
    public void testCalculateBMI5() {
        double weight = 100;
        double height = 1.65;
        Assert.assertEquals("Béo phì", Week4.calculateBMI(weight, height));
    }
}