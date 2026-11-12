package edu.kirkwood.controller.makayla;

import edu.kirkwood.model.makayla.RespiratoryRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RespiratoryRateCalculatorTest {
    @Test
    @DisplayName("Test calculating respiratory rate with valid values")
    void ccalculateRateWithValidValues() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act
        double firstResult = calculator.calculateRate(12, 60);
        double secondResult = calculator.calculateRate(20, 60);
        // Assert
        assertEquals(12.0, firstResult);
        assertEquals(20.0, secondResult);
    }

    @Test
    @DisplayName("Test rejecting zero breaths")
    void calculateRateWithZeroBreaths() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateRate(0, 60)
        );
        // Assert
        assertEquals(
                RespiratoryRate.INVALID_BREATHS_MESSAGE,
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Test rejecting negative breaths")
    void calculateRateWithNegativeBreaths() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateRate(-5, 60)
        );

        assertEquals(
                RespiratoryRate.INVALID_BREATHS_MESSAGE,
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Test rejecting zero observation time")
    void calculateRateWithZeroObservationTime() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateRate(12, 0)
        );

        assertEquals(
                RespiratoryRate.INVALID_OBSERVATION_TIME_MESSAGE,
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Test rejecting negative observation time")
    void calculateRateWithNegativeObservationTime() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateRate(12, -30)
        );

        assertEquals(
                RespiratoryRate.INVALID_OBSERVATION_TIME_MESSAGE,
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Test calculating respiratory rate with large values")
    void calculateRateWithLargeValues() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act
        double result = calculator.calculateRate(100000, 1000);
        // Assert
        assertEquals(6000.0, result);
    }

    @Test
    @DisplayName("Test calculating respiratory rate with minimum valid values")
    void calculateRateWithMinimumValidValues() {
        // Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act
        double result = calculator.calculateRate(1, 1);
        // Assert
        assertEquals(60.0, result);
    }



    @Test
    @DisplayName("Test calculating rate with decimal result")
    void calculateRateWithDecimalResult() {
        //Arrange
        RespiratoryRateCalculator calculator = new RespiratoryRateCalculator();
        // Act
        double result = calculator.calculateRate(2, 78);
        // Assert
        assertEquals(1.5384615384615, result, 0.0000000000001);
    }
}