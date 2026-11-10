package edu.kirkwood.view.ryan;

import edu.kirkwood.view.Helpers;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    @Test
    void isValidStringNotNull() {
        // arrange
        String str = "Hello";

        // assert
        assertTrue(Helpers.isValidString(str));
    }

    @Test
    void isValidStringIsNull() {
        // arrange
        String str = null;

        // assert
        assertFalse(Helpers.isValidString(str));
    }

    @Test
    void isValidStringNotEmpty() {
        // arrange
        String str = "Hello";

        // assert
        assertTrue(Helpers.isValidString(str));
    }

    @Test
    void isValidStringIsEmpty() {
        // arrange
        String str = "";

        // assert
        assertFalse(Helpers.isValidString(str));
    }

    @Test
    void roundToTwoDecimalPlaces() {
        // arrange
        double number = 3.14159;
        int decimalPlaces = 2;

        // act
        String result = Helpers.round(number, decimalPlaces);

        // assert
        assertEquals("3.14", result);
    }

    @Test
    void roundToTwoDecimalPlacesNegative() {
        // arrange
        double number = -3.14159;
        int decimalPlaces = 2;

        // act
        String result = Helpers.round(number, decimalPlaces);

        // assert
        assertEquals("-3.14", result);
    }

    @Test
    void roundUpToTwoDecimalPlaces() {
        // arrange
        double number = 3.1459;
        int decimalPlaces = 2;

        // act
        String result = Helpers.round(number, decimalPlaces);

        // assert
        assertEquals("3.15", result);
    }

    @Test
    void roundWithTrailingZero() {
        // arrange
        double number = 3.3000;
        int decimalPlaces = 3;

        // act
        String result = Helpers.round(number, decimalPlaces);

        // assert
        assertEquals("3.3", result);
    }

    @Test
    void toCurrencyCorrectReturn() {
        // arrange
        int amount = 1234;

        // act
        String result = Helpers.toCurrency(amount);

        // assert
        assertEquals("$1,234.00", result);
    }

    @Test
    void toCurrencyCorrectNegativeReturn() {
        // arrange
        int amount = -1234;

        // act
        String result = Helpers.toCurrency(amount);

        // assert
        assertEquals("-$1,234.00", result);
    }

    @Test
    void toCurrencyZero() {
        // arrange
        int amount = 0;

        // act
        String result = Helpers.toCurrency(amount);

        // assert
        assertEquals("$0.00", result);
    }

    @Test
    void formatDateLong() {
    }

    @Test
    void formatDateShort() {
    }

    @Test
    void isDateInThePastCorrectReturn() {
        // arrange
        LocalDate date = LocalDate.of(2020, 1, 1);

        // act
        boolean result = Helpers.isDateInThePast(date);

        // assert
        assertTrue(result);
    }

    @Test
    void isDateNotInThePastCorrectReturn() {
        // arrange
        LocalDate date = LocalDate.of(2027, 1, 1);

        // act
        boolean result = Helpers.isDateInThePast(date);

        // assert
        assertFalse(result);
    }

    @Test
    void isTodayDateInThePastCorrectReturn() {
        // arrange
        LocalDate date = LocalDate.now();

        // act
        boolean result = Helpers.isDateInThePast(date);

        // assert
        assertFalse(result);
    }


    @Test
    void isDateInRangeCorrectReturn() {
        // arrange
        LocalDate date = LocalDate.of(2026, 10, 2);
        LocalDate startDate = LocalDate.of(2026, 10, 1);
        LocalDate endDate = LocalDate.of(2026, 10, 3);

        // act
        boolean result = Helpers.isDateInRange(date, startDate, endDate);

        // assert
        assertTrue(result);
    }

    @Test
    void isDateInRangeNullThrowsException() {
        // arrange
        LocalDate date = null;
        LocalDate startDate = LocalDate.of(2026, 10, 1);
        LocalDate endDate = LocalDate.of(2026, 10, 3);

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> Helpers.isDateInRange(date, startDate, endDate));
    }
}