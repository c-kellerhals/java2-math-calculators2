package edu.kirkwood.controller.issouf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetirementCalculatorTest {

    @Test
    @DisplayName("Test parsePercent with a whole number")
    void parsePercentWholeNumber() {
        // Arrange
        String input = "6";
        // Act
        double actual = RetirementCalculator.parsePercent(input);
        // Assert
        assertEquals(6.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parsePercent with a decimal")
    void parsePercentDecimal() {
        // Arrange
        String input = "7.5";
        // Act
        double actual = RetirementCalculator.parsePercent(input);
        // Assert
        assertEquals(7.5, actual, 0.01);
    }

    @Test
    @DisplayName("Test parsePercent with surrounding spaces")
    void parsePercentWithSpaces() {
        // Arrange
        String input = "  3  ";
        // Act
        double actual = RetirementCalculator.parsePercent(input);
        // Assert
        assertEquals(3.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parsePercent with zero")
    void parsePercentZero() {
        // Arrange
        String input = "0";
        // Act
        double actual = RetirementCalculator.parsePercent(input);
        // Assert
        assertEquals(0.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parsePercent with one hundred")
    void parsePercentOneHundred() {
        // Arrange
        String input = "100";
        // Act
        double actual = RetirementCalculator.parsePercent(input);
        // Assert
        assertEquals(100.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parsePercent with a negative value should throw exception")
    void parsePercentNegative() {
        // Arrange
        String input = "-6";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parsePercent(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_PERCENT_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test parsePercent above one hundred should throw exception")
    void parsePercentAboveOneHundred() {
        // Arrange
        String input = "101";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parsePercent(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_PERCENT_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test parsePercent with text should throw exception")
    void parsePercentNotANumber() {
        // Arrange
        String input = "abc";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parsePercent(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_NUMBER_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test parsePercent with an empty string should throw exception")
    void parsePercentEmpty() {
        // Arrange
        String input = "";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parsePercent(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_NUMBER_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test parseAmount with a whole number")
    void parseAmountWholeNumber() {
        // Arrange
        String input = "50000";
        // Act
        double actual = RetirementCalculator.parseAmount(input);
        // Assert
        assertEquals(50000.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parseAmount with a decimal")
    void parseAmountDecimal() {
        // Arrange
        String input = "20000.50";
        // Act
        double actual = RetirementCalculator.parseAmount(input);
        // Assert
        assertEquals(20000.50, actual, 0.01);
    }

    @Test
    @DisplayName("Test parseAmount with surrounding spaces")
    void parseAmountWithSpaces() {
        // Arrange
        String input = "  20000  ";
        // Act
        double actual = RetirementCalculator.parseAmount(input);
        // Assert
        assertEquals(20000.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parseAmount with zero")
    void parseAmountZero() {
        // Arrange
        String input = "0";
        // Act
        double actual = RetirementCalculator.parseAmount(input);
        // Assert
        assertEquals(0.0, actual, 0.01);
    }

    @Test
    @DisplayName("Test parseAmount with a negative value should throw exception")
    void parseAmountNegative() {
        // Arrange
        String input = "-1";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parseAmount(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_AMOUNT_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test parseAmount with text should throw exception")
    void parseAmountNotANumber() {
        // Arrange
        String input = "twenty thousand";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parseAmount(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_NUMBER_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test parseAmount with an empty string should throw exception")
    void parseAmountEmpty() {
        // Arrange
        String input = "";
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RetirementCalculator.parseAmount(input));
        // Arrange
        String expectedMessage = RetirementCalculator.INVALID_NUMBER_MESSAGE;
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test the calculator constants hold the expected messages")
    void constants() {
        // Act and Assert
        assertEquals("Invalid number. Enter a numeric value.", RetirementCalculator.INVALID_NUMBER_MESSAGE);
        assertEquals("Invalid percentage. Enter a value between 0 and 100.", RetirementCalculator.INVALID_PERCENT_MESSAGE);
        assertEquals("Invalid amount. Enter a value of zero or greater.", RetirementCalculator.INVALID_AMOUNT_MESSAGE);    }
}