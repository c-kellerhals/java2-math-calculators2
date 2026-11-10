package edu.kirkwood.model.riona;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AngleTest {

    private Angle a;
    private String angleString;
    private static float ALLOWED_DIFFERENCE;

    @BeforeEach
    void setUp() {
        a = new Angle(30);
        angleString = "Angle = 30°";
        ALLOWED_DIFFERENCE = 0.001f;
    }

    @Test
    void getValueDegrees() {
        float expected = 30f;
        float actual = (float)a.getValueDegrees();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void setValueDegreesPositive() {
        double value = 15.5;
        a.setValueDegrees(value);
        float actual = (float)a.getValueDegrees();
        assertEquals(value,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void setValueDegreesNegative() {
        double value = -15.5;
        a.setValueDegrees(value);
        float actual = (float)a.getValueDegrees();
        assertEquals(value,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateSineGood() {
        float expected = 0.5f;
        float actual = (float)a.calculateSine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateSineGoodAngleZero() {
        double value = 0;
        a.setValueDegrees(value);
        float expected = 0f;
        float actual = (float)a.calculateSine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateSineGoodAngle90() {
        double value = 90;
        a.setValueDegrees(value);
        float expected = 1f;
        float actual = (float)a.calculateSine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateSineGoodAngleOver360() {
        double value = 370;
        a.setValueDegrees(value);
        float expected = 0.17364818f;
        float actual = (float)a.calculateSine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateSineGoodAngleNegative() {
        double value = -37;
        a.setValueDegrees(value);
        float expected = -0.60181502f;
        float actual = (float)a.calculateSine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateSine() {
    }

    @Test
    void calculateCosineGood() {
        float expected = 0.8660254f;
        float actual = (float)a.calculateCosine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateCosineGoodAngleZero() {
        double value = 0;
        a.setValueDegrees(value);
        float expected = 1f;
        float actual = (float)a.calculateCosine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateCosineGoodAngle90() {
        double value = 90;
        a.setValueDegrees(value);
        float expected = 0f;
        float actual = (float)a.calculateCosine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateCosineGoodAngleOver360() {
        double value = 370;
        a.setValueDegrees(value);
        float expected = 0.98480775f;
        float actual = (float)a.calculateCosine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateCosineGoodAngleNegative() {
        double value = -37;
        a.setValueDegrees(value);
        float expected = 0.79863551f;
        float actual = (float)a.calculateCosine();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateCosine() {
    }

    @Test
    void calculateTangentGood() {
        float expected = 0.57735027f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangentGoodAngleZero() {
        double value = 0;
        a.setValueDegrees(value);
        float expected = 0f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangentGoodAngle90() {
        double value = 90;
        a.setValueDegrees(value);
        float expected = 999f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangentGoodAngle180() {
        double value = 180;
        a.setValueDegrees(value);
        float expected = 0f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangentGoodAngle270() {
        double value = 270;
        a.setValueDegrees(value);
        float expected = 999f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangentGoodAngleOver360() {
        double value = 370;
        a.setValueDegrees(value);
        float expected = 0.17632698f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangentGoodAngleNegative() {
        double value = -37;
        a.setValueDegrees(value);
        float expected = -0.75355405f;
        float actual = (float)a.calculateTangent();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void calculateTangent() {
    }

    @Test
    void testToString() {
        String actual = a.toString();
        assertTrue(actual.equals(angleString));
    }

    @Test
    void convertToBaseAnglePositive() {
        double value = 390;
        a.setValueDegrees(value);
        Angle.convertToBaseAngle(a);
        float expected = 30f;
        float actual = (float)a.getValueDegrees();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void convertToBaseAngleNegative() {
        double value = -390;
        a.setValueDegrees(value);
        Angle.convertToBaseAngle(a);
        float expected = 330f;
        float actual = (float)a.getValueDegrees();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void convertToBaseAngleSimplifyToZero() {
        double value = 360;
        a.setValueDegrees(value);
        Angle.convertToBaseAngle(a);
        float expected = 0f;
        float actual = (float)a.getValueDegrees();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void toRadians() {
        double value = 180;
        a.setValueDegrees(value);
        float expected = (float)Math.PI;
        float actual = (float)a.toRadians();
        assertEquals(expected,actual,ALLOWED_DIFFERENCE);
    }

    @Test
    void compareToAbsoluteEqual() {
        Angle b = new Angle(30);
        int expected = 0;
        int actual = a.compareToAbsolute(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareToAbsoluteNegative() {
        Angle b = new Angle(-30);
        int expected = -1;
        int actual = a.compareToAbsolute(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareToAbsoluteSmaller() {
        Angle b = new Angle(370);
        int expected = 1;
        int actual = a.compareToAbsolute(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareToReal() {
        Angle b = new Angle(30);
        int expected = 0;
        int actual = a.compareToReal(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareToRealPositive() {
        Angle b = new Angle(720);
        int expected = -1;
        int actual = a.compareToReal(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareToRealNegative() {
        Angle b = new Angle(-30);
        int expected = 1;
        int actual = a.compareToReal(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareSine() {
        Angle b = new Angle(60);
        int expected = -1;
        int actual = a.compareSine(b);
        assertEquals(expected,actual);
    }

    @Test
    void compareCosine() {
        Angle b = new Angle(60);
        double actual = a.compareCosine(b);
        assertTrue(a.compareCosine(b) > 0);
    }

    @Test
    void compareTangent() {
        Angle b = new Angle(60);
        assertTrue(a.compareTangent(b) < 0);
    }
}