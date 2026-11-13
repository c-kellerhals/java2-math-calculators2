package edu.kirkwood.model.clapton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.kirkwood.model.clapton.Number.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    private Number binaryNum;
    private Number octalNum;
    private Number decimalNum;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";

    @BeforeEach
    void setUp() {
        binaryNum = new Number(2, "1010"); // Binary
        octalNum = new Number(8, "12");   // Octal
        decimalNum = new Number(10, "10");  // Decimal
    }

    @Test
    void SuperIllegalBinary() {
        assertThrows(IllegalArgumentException.class, () -> new Number(2, "99"));
    }

    @Test
    void SuperIllegalOctal() {
        assertThrows(IllegalArgumentException.class, () -> new Number(8, "99"));
    }

    @Test
    void SuperIllegalDecimal() {
        assertThrows(IllegalArgumentException.class, () -> new Number(10, "1A"));
    }

    @Test
    void implicitConversionShouldFail() {
        assertThrows(IllegalArgumentException.class, () -> decimalNum.setNumberBase(2));
    }

    @Test
    void implicitConversionShouldPassToBinary() {
        Number num4 = new Number();
        num4.setNumberBase(2);

        assertEquals(2, num4.getNumberBase());
    }

    @Test
    void implicitConversionShouldPassToOctal() {
        Number num4 = new Number();
        num4.setNumberBase(8);

        assertEquals(8, num4.getNumberBase());
    }

    @Test
    void implicitConversionShouldPassToDecimal() {
        Number num4 = new Number();
        num4.setNumberBase(10);

        assertEquals(10, num4.getNumberBase());
    }

    @Test
    void implicitConversionShouldFailToNull() {
        Number num4 = new Number();
        num4.setNumberBase(10);
        assertThrows(IllegalArgumentException.class, () -> num4.setNumberBase(null));
    }

    @Test
    void testToString() {
        assertEquals("Base " + ANSI_BRIGHT_CYAN + "2" + ANSI_RESET + ", Value " +
                ANSI_ORANGE + "1010" + ANSI_RESET, binaryNum.toString());

        assertEquals("Base " + ANSI_BRIGHT_CYAN + "8" + ANSI_RESET + ", Value " +
                ANSI_ORANGE + "12" + ANSI_RESET, octalNum.toString());

        assertEquals("Base " + ANSI_BRIGHT_CYAN + "10" + ANSI_RESET + ", Value " +
                ANSI_ORANGE + "10" + ANSI_RESET, decimalNum.toString());
    }

    @Test
    void getValue() {
        assertEquals("1010", binaryNum.getValue());
        assertEquals("12", octalNum.getValue());
        assertEquals("10", decimalNum.getValue());
    }

    @Test
    void setValuePositive() {
        binaryNum.setValue("1100");
        assertEquals("1100", binaryNum.getValue());
    }

    @Test
    void setValuePositiveUnnecessarySign() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue("+1100"));
    }

    @Test
    void setValueBadCharacters() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue("ABCD"));
    }

    @Test
    void setValueNegative() {
        binaryNum.setValue("-1100");
        assertEquals("-1100", binaryNum.getValue());
    }

    @Test
    void setValueNegativeTooManySigns() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue("--1100"));
    }

    @Test
    void setValueNegativeIncorrectPlacement() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue("1100-"));
    }

    @Test
    void setValueNegativeWithNoValue() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue("-"));
    }

    @Test
    void setValueEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue(""));
    }

    @Test
    void setValueTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setValue("1111111111"));
    }

    @Test
    void getNumberBase() {
        assertEquals(2, binaryNum.getNumberBase());
        assertEquals(8, octalNum.getNumberBase());
        assertEquals(10, decimalNum.getNumberBase());
    }

    @Test
    void setNumberBase() {
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setNumberBase(10));
    }

    @Test
    void setNumberBaseInvalid() {
        // should only accept 2, 8, 10 (maybe 16, if time permits)
        assertThrows(IllegalArgumentException.class, () -> binaryNum.setNumberBase(5));
    }

    @Test
    void decimalToBinaryTest() {
        decimalNum.setValue("10");
        assertEquals("1010", decimalToBinary(decimalNum.getValue()));
    }

    @Test
    void decimalToBinaryTestAgain() {
        decimalNum.setValue("81");
        assertEquals("1010001", decimalToBinary(decimalNum.getValue()));
    }

    @Test
    void negativeDecimalToBinaryTest() {
        decimalNum.setValue("-10");
        assertEquals("-1010", decimalToBinary(decimalNum.getValue()));
    }

    @Test
    void binaryToDecimalTest() {
        assertEquals("10", binaryToDecimal(binaryNum.getValue()));
    }

    @Test
    void binaryToDecimalTestAgain() {
        binaryNum.setValue("1101");
        assertEquals("13", binaryToDecimal(binaryNum.getValue()));
    }

    @Test
    void negativeBinaryToDecimalTest() {
        binaryNum.setValue("-1010");
        assertEquals("-10", binaryToDecimal(binaryNum.getValue()));
    }

    @Test
    void decimalToOctalTest() {
        assertEquals("12", decimalToOctal(decimalNum.getValue()));
    }

    @Test
    void decimalToOctalTestAgain() {
        decimalNum.setValue("46");
        assertEquals("56", decimalToOctal(decimalNum.getValue()));
    }


    @Test
    void negativeDecimalToOctalTest() {
        decimalNum.setValue("-10");
        assertEquals("-12", decimalToOctal(decimalNum.getValue()));
    }

    @Test
    void octalToDecimalTest() {
        assertEquals("10", octalToDecimal(octalNum.getValue()));
    }

    @Test
    void negativeOctalToDecimalTest() {
        octalNum.setValue("-12");
        assertEquals("-10", octalToDecimal(octalNum.getValue()));
    }

    @Test
    void binaryToOctalTest() {
        assertEquals("12", binaryToOctal(binaryNum.getValue()));
    }

    @Test
    void negativeBinaryToOctalTest() {
        binaryNum.setValue("-1010");
        assertEquals("-12", binaryToOctal(binaryNum.getValue()));
    }

    @Test
    void octalToBinaryTest() {
        assertEquals("1010", octalToBinary(octalNum.getValue()));
    }

    @Test
    void negativeOctalToBinaryTest() {
        octalNum.setValue("-12");
        assertEquals("-1010", octalToBinary(octalNum.getValue()));
    }

    @Test
    void compareToTestPositive() {
        Number num1 = new Number(10, "10");
        Number num2 = new Number(10, "20");
        assertTrue(num1.compareTo(num2) < 0);
    }

    @Test
    void compareToTestNegative() {
        Number num1 = new Number(10, "20");
        Number num2 = new Number(10, "10");
        assertTrue(num1.compareTo(num2) > 0);
    }

    @Test
    void compareToTestEqual() {
        Number num1 = new Number(10, "10");
        Number num2 = new Number(10, "10");
        assertEquals(0, num1.compareTo(num2));
    }

    @Test
    void compareToTestBinaryDecimal() {
        Number num1 = new Number(2, "1010"); // 10 in decimal
        Number num2 = new Number(10, "10");  // 10 in decimal
        assertEquals(0, num1.compareTo(num2));
    }

    @Test
    void compareToTestBinaryOctal() {
        Number num1 = new Number(2, "1010"); // 10 in decimal
        Number num2 = new Number(8, "12");  // 10 in decimal
        assertEquals(0, num1.compareTo(num2));
    }

    @Test
    void compareToTestOctalDecimalTrue() {
        Number num1 = new Number(8, "12");  // 10 in decimal
        Number num2 = new Number(10, "10");  // 10 in decimal
        assertEquals(0, num1.compareTo(num2));;
    }

    @Test
    void compareToTestOctalDecimalFalse() {
        Number num1 = new Number(8, "14");  // 12 in decimal
        Number num2 = new Number(10, "10");  // 10 in decimal
        assertFalse(0 == num1.compareTo(num2));
    }
}