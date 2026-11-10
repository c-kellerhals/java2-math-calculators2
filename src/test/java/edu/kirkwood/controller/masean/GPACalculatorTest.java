package edu.kirkwood.controller.masean;

import edu.kirkwood.model.masean.GradePointAverage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GPACalculatorTest {
    @Test
    void testCalculateGPA() {
        // Arrange
        double[] grades = {3.0, 3.5, 4.0};
        int[] credits = {3, 4, 3};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(3.5, finalGPA);
    }

    @Test
    void testCalculateGPAWithCreditsBeingTheSameValue() {
        // Arrange
        double[] grades = {3.0, 3.5, 4.0};
        int[] credits = {3, 3, 3};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(3.5, finalGPA);
    }

    @Test
    void testCalculateGPAWithGradesBeingTheSameValue() {
        // Arrange
        double[] grades = {4.0, 4.0, 4.0};
        int[] credits = {3, 4, 5};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(4.0, finalGPA);
    }

    @Test
    void testCalculateGPAWithGradesHavingOneValue() {
        // Arrange
        double[] grades = {4.0};
        int[] credits = {3};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(4.0, finalGPA);
    }

    @Test
    void testCalculateGPAWithDifferentArrayLengths() {
        // Arrange
        double[] grades = {4.0, 3.0, 2.0, 1.0};
        int[] credits = {3, 4};
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> GradePointAverage.calculateGPA(grades, credits));
    }


    @Test
    void testCalculateGPAWithAllGradesBeingZero() {
        //Arrange
        double[] grades = {0.0, 0.0, 0.0};
        int[] credits = {3, 4, 5};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(0.0, finalGPA);
    }

    @Test
    void testCalculateGPAWithOneGradeBeingZero() {
        //Arrange
        double[] grades = {3.0, 0.0, 4.0};
        int[] credits = {3, 4, 5};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(2.4166666666666665, finalGPA);
    }


    @Test
    void testCalculateGPAWithAllGradesHavingMaximumValue() {
        //Arrange
        double[] grades = {4.0, 4.0, 4.0};
        int[] credits = {3, 4, 5};
        // Act
        double finalGPA = GradePointAverage.calculateGPA(grades, credits);
        // Assert
        assertEquals(4.0, finalGPA);
    }
}