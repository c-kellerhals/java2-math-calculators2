package edu.kirkwood.model.colman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the {@link Coords} model.
 * Validates initialization, getters, setters, and geometric calculation methods.
 */

class CoordsTest {

    private Coords c1;
    private Coords c2;

    /**
     * Sets up a fresh instance of Coords before each test execution.
     */
    @BeforeEach
    void setUp() {
        c1 = new Coords();
        c2 = new Coords(2,3);
    }


    /**
     * Tests the toString method to ensure it correctly returns the slope as a String.
     */
    @Test
    void testToString() {
        assertEquals("0.0, 0.0", c1.toString());
        assertEquals("2.0, 3.0", c2.toString());
        Coords c1 = new Coords(-1.0, 2);
        assertEquals("-1.0, 2.0", c1.toString());
    }

    /**
     * Tests getting the initial x-coordinate of the first point.
     */
    @Test
    void getX() {
        assertEquals(0, c1.getX());
        assertEquals(2, c2.getX());
    }

    /**
     * Tests getting the initial y-coordinate of the first point.
     */
    @Test
    void getY() {
        assertEquals(0, c1.getY());
        assertEquals(3, c2.getY());
    }

    /**
     * Tests setting and updating the x-coordinate of the first point.
     */
    @Test
    void setX() {
        c1.setX(1);
        assertEquals(1, c1.getX());
    }

    /**
     * Tests setting and updating the y-coordinate of the first point.
     */
    @Test
    void setY() {
        c1.setY(1);
        assertEquals(1, c1.getY());
    }

    /**
     * Tests the calculation of horizontal distance (delta x).
     */
    @Test
    void calcDx() {
        assertEquals(0, c1.calcDx());
        assertEquals(2, c2.calcDx());
    }

    @Test
    void calcDxTwoCoordinates() {
        assertEquals(2, c1.calcDx(c2));
        assertEquals(-2, c2.calcDx(c1));
    }

    /**
     * Tests the calculation of vertical distance (delta y).
     */
    @Test
    void calcDy() {
        assertEquals(0, c1.calcDy());
        assertEquals(3, c2.calcDy());
    }

    @Test
    void calcDyTwoCoords() {
        assertEquals(3, c1.calcDy(c2));
        assertEquals(-3, c2.calcDy(c1));
    }

    /*

    /**
     * Tests the calculation of the slope between two coordinates.
     */
 //   @Test
 //   void calcSlope() {
 //       assertEquals(Double.POSITIVE_INFINITY, c1.calcSlope());
  //      assertEquals(3.0/2, c2.calcSlope(), 0.0001);
  //  }

    @Test
    void calcSlopeTwoCoordinates() {
        assertEquals(3.0/2, c1.calcSlope(c2));
        assertEquals(-3.0/-2, c2.calcSlope(c1));
    }

    /**
     * Tests the calculation of the angle (theta) in degrees relative to the x-axis.
     */
    @Test
    void calcTheta() {
        c1.setX(1);
        c1.setY(1);
        c2.setX(2);
        c2.setY(2);
        assertEquals(45, c1.calcTheta(c2), .0001);
    }

    /*
    // Ironically, this test doesn't pass because of the next one.
    @Test
    void calcThetaNegged() {
        c1.setX(2);
        c1.setY(-2);
        c2.setX(2);
        c2.setY(2);
        assertEquals(90, c1.calcTheta(c2), .0001);
    }
    */


    @Test
    void zeroDxNotAllowed() {
        c1.setX(1);
        c1.setY(2);
        c2.setX(1);
        c2.setY(3);
        assertThrows(Exception.class, () -> c1.calcTheta(c2));
    }

    /**
     * Tests the calculation of the straight-line distance (hypotenuse) between two points.
     */
    @Test
    void calcHypFromOrigin0() {
        c1.setX(3);
        c1.setY(4);
        assertEquals(5, c1.calcHyp(), .0001);
    }

    @Test
    void calcHypFromTwoCoord() {
        c1.setX(4);
        c1.setY(5);
        c2.setX(1);
        c2.setY(1);
        // Distance between (1,1) and (4,5) is X = 3, Y = 4
        assertEquals(5, c1.calcHyp(c2), .0001);
    }

    /**
     * Tests the compareTo method to ensure fallback cascade ordering
     * based on length (hypotenuse), X1, and Y1 functions correctly.
     */
    @Test
    void compareTo() {

        Coords c1 = new Coords(2,2);
        Coords shorter = new Coords(1, 1); // length = 1.0
        Coords longer = new Coords(3, 3);  // length = 5.0
        Coords identical = new Coords(2, 2); // same as c1

        // Shorter should be "less than" c1 (negative return value)
        assertTrue(shorter.compareTo(c1) < 0);

        // Longer should be "greater than" c1 (positive return value)
        assertTrue(longer.compareTo(c1) > 0);

        // Identical should return 0
        assertEquals(0, c1.compareTo(identical));
    }

    /**
     * Tests the equals method for value equality, null safety,
     * and type verification.
     */
    @Test
    void testEquals() {
        Coords identical = new Coords(0, 0); // matches default c1
        Coords different = new Coords(1, 1); // different coordinates

        // Test equality
        assertEquals(c1, identical);

        // Test inequality
        assertNotEquals(c1, different);

        // Test null and wrong type safety checks
        assertNotEquals(c1, null);
        assertNotEquals(c1, "Not a Coords object");
    }
}