package edu.kirkwood.model.colman;

/**
 * Represents a pair of 2D Cartesian coordinates defined by two points:
 * (X1, Y1) and (X2, Y2). Provides geometric and analytical methods
 * for calculating differences, slope, angle, and distance.
 */
public class Coords implements Comparable<Coords>{

    public double x;

    private double y;


    /**
     * Default constructor for Coords.
     * Initializes the first point to (0, 0) and the second point to (1, 1).
     */
    public Coords() {
        x = 0;
        y = 0;
    }

    /**
     * Parameterized constructor for Coords.
     * Constructs a coord point of specified x and y values.
     *
     * @param x the x-coordinate of the coord point
     * @param y the y-coordinate of the coord point
     */
    public Coords(double x, double y){
        setX(x);
        setY(y);
    }

    /**
     * Returns a string representation of the slope between the two points.
     *
     * @return the slope as a String
     */
    @Override
    public String toString(){
        return x + ", " + y;
    }

    /**
     * Gets the x-coordinate of the first point.
     *
     * @return x value
     */
    public double getX(){
        return x;
    }

    /**
     * Gets the y-coordinate of the first point.
     *
     * @return y value
     */
    public double getY(){
        return y;
    }

    /**
     * Sets the x-coordinate of the first point.
     *
     * @param x the new x-coordinate for the first point
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the first point.
     *
     * @param y the new y-coordinate for the first point
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Calculates the horizontal change (Delta x) between the two points.
     * @param other the second Coords point
     * @return the difference X2 - X1
     */
    public double calcDx(Coords other) {
        if (other.x != this.x) {
            return other.x - this.x;
        } else {
            throw new IllegalArgumentException("Slope Undefined");
        }
    }

    /**
     * Calculates the vertical change (Delta y) between the two points.
     * @param other the second Coords point
     * @return the difference Y2 - Y1
     */
    public double calcDy(Coords other){
        return other.y - this.y;
    }

    // weird AI code overload suggestion
    /**
     * Calculates horizontal distance from the origin (0,0).
     */
    public double calcDx() {
        if (this.x != 0) {
            return this.x; // distance from x=0
        }
        return this.x;
    }

    /**
     * Calculates vertical distance from the origin (0,0).
     */
    public double calcDy(){
        return this.y; // distance from y=0
    }
    //

    /**
     * Calculates the slope of the line connecting the two points.
     * Handles vertical lines by returning positive infinity.
     *
     * @return the slope (Dy / Dx), or Double.POSITIVE_INFINITY if Dx is 0
     */
    public double calcSlope(Coords other){
        double Dx = calcDx(other);
        double Dy = calcDy(other);
        if (Dx == 0 && Dy == 0) {
            throw new IllegalArgumentException("Slope Undefined");
        } else {
            return Dy / Dx;
        }
    }

    // weird AI code overload suggestion
    ///**
    /* Calculates the slope of this point from the origin (0,0).
    /* with a throw in calcTheta, I wasn't sure I also needed it here or above.
     */
    public double calcSlope(){
        if (this.x == 0) {
            throw new IllegalArgumentException("Slope Undefined");
        } else {
            return this.y / this.x;
        }
    }
    //

    /**
     *
     * @param other
     * @return angle of the slope.
     */
    // AI had to teach me about this; I've never seen anything like it.
    public double calcTheta(Coords other){

        double dx = this.calcDx(other);
        double dy = this.calcDy(other);

        if (dx == 0.0) {
            throw new IllegalArgumentException("Slope Undefined");
        }
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    /**
     * Calculates the straight-line distance (hypotenuse) between the point and 0 origin.
     *
     * @return the distance between point 1 and 0 origin
     */
    public double calcHyp() {
        double Dx = calcDx();
        double Dy = calcDy();

        if (Dx != 0 && Dy != 0) {
            return Math.sqrt(Math.pow(Dx, 2) + Math.pow(Dy, 2));
        } else {
            throw new IllegalArgumentException("Slope Undefined");
        }
    }


    /**
     * Calculates the straight-line distance (hypotenuse) between the two points.
     *
     * @return the distance between point 1 and point 2
     */
    public double calcHyp(Coords other) {
        if (!this.equals(other)) {
            double Dx = Math.abs(other.getX() - this.getX());
            double Dy = Math.abs(other.getY() - this.getY());
            return Math.sqrt(Math.pow(Dx, 2) + Math.pow(Dy, 2));
        } else {
            throw new IllegalArgumentException("Slope Undefined");
        }
    }

    /**
     * Compares this Coords object with another for order. Uses length (hypotenuse)
     * as the primary comparison metric, falling back to X1 and then Y1 coordinates
     * as tie-breakers.
     *
     * @param o the other Coords object to be compared
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Coords o) {
        int result = Double.compare(this.calcHyp(), o.calcHyp());

        if (result == 0) {
            result = Double.compare(this.x, o.x);
        }
        if (result == 0) {
            result = Double.compare(this.y, o.y);
        }
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Verifies type compatibility and checks all coordinate fields (X1, Y1, X2, Y2)
     * for exact equality.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;

        return Double.compare(coords.x, x) == 0 &&
                Double.compare(coords.y, y) == 0;
    }
}