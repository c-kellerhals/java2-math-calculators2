package edu.kirkwood.controller.evan;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static edu.kirkwood.controller.evan.DoomsdayCalculatorController.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoomsdayCalculatorControllerTest {

    @Test
    void parseDateWithValidDate() {
        String input = "3/5/1975";
        LocalDate expected = LocalDate.of(1975, 3, 5);

        LocalDate actual = DoomsdayCalculatorController.parseDate(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseDateWithSingleDigitMonthAndDay() {
        String input = "1/1/2025";
        LocalDate expected = LocalDate.of(2025, 1, 1);

        LocalDate actual = DoomsdayCalculatorController.parseDate(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseDateWithDoubleDigitMonthAndDay() {
        String input = "12/25/2025";
        LocalDate expected = LocalDate.of(2025, 12, 25);

        LocalDate actual = DoomsdayCalculatorController.parseDate(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseDateWithLeapYear() {
        String input = "2/29/2024";
        LocalDate expected = LocalDate.of(2024, 2, 29);

        LocalDate actual = DoomsdayCalculatorController.parseDate(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseDateWithCenturyLeapYear() {
        String input = "2/29/2000";
        LocalDate expected = LocalDate.of(2000, 2, 29);

        LocalDate actual = DoomsdayCalculatorController.parseDate(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseDateWithYearOne() {
        String input = "1/1/1";
        LocalDate expected = LocalDate.of(1, 1, 1);

        LocalDate actual = DoomsdayCalculatorController.parseDate(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseDateWithInvalidMonth() {
        String input = "13/1/2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_MONTH, exception.getMessage());
    }

    @Test
    void parseDateWithZeroMonth() {
        String input = "0/1/2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_MONTH, exception.getMessage());
    }

    @Test
    void parseDateWithInvalidDay() {
        String input = "1/32/2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_DAY, exception.getMessage());
    }

    @Test
    void parseDateWithDayZero() {
        String input = "1/0/2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_DAY, exception.getMessage());
    }

    @Test
    void parseDateWithFebruary30() {
        String input = "2/30/2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_DAY, exception.getMessage());
    }

    @Test
    void parseDateWithFebruary29OnNonLeapYear() {
        String input = "2/29/2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_DAY, exception.getMessage());
    }

    @Test
    void parseDateWithYearZero() {
        String input = "1/1/0";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_YEAR, exception.getMessage());
    }

    @Test
    void parseDateWithNegativeYear() {
        String input = "1/1/-1";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_YEAR, exception.getMessage());
    }

    @Test
    void parseDateWithInvalidFormat() {
        String input = "March 5, 2025";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_FORMAT, exception.getMessage());
    }

    @Test
    void parseDateWithLettersInDate() {
        String input = "a/d/w";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_FORMAT, exception.getMessage());
    }

    @Test
    void parseDateWithInvalidSeparators() {
        String input = "///";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_FORMAT, exception.getMessage());
    }

    @Test
    void parseDateWithMissingDatePart() {
        String input = "3/5";

        DateTimeParseException exception = assertThrows(
                DateTimeParseException.class, () -> DoomsdayCalculatorController.parseDate(input));

        assertEquals(INVALID_FORMAT, exception.getMessage());
    }
}