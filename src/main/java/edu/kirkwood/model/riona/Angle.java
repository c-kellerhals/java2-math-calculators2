package edu.kirkwood.model.riona;

import java.text.DecimalFormat;

public class Angle {

    // Degree symbol: Alt + 0176

    /**
     * The Angle's value in degrees
     */
    private double valueDegrees;
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.####");

    /**
     * The default constructor for an Angle object
     */
    public Angle(){
        setValueDegrees(1);
    }

    /**
     * The full constructor for an Angle object
     * @param valueDegrees The value of the angle in degrees
     */
    public Angle(double valueDegrees){
        setValueDegrees(valueDegrees);
    }

    /**
     * Returns the value of the angle in degrees
     *
     * @return the value of the angle in degrees
     */
    public double getValueDegrees() {
        return valueDegrees;
    }

    /**
     * Sets the value of the angle in degrees.
     *
     * @param valueDegrees The new value to be assigned
     */
    public void setValueDegrees(double valueDegrees) {
        this.valueDegrees = valueDegrees;
    }

    /**
     * Simplifies an angle to have a value between 0 and 360 degrees
     *
     * @param a An angle to simplify the value of
     */
    public static void convertToBaseAngle(Angle a){
        double simplifiedAngle = 0;
        double originalAngle = a.getValueDegrees();
        Angle tempAngle = new Angle(originalAngle);

        if (!(originalAngle < 360 && originalAngle >= 0)) {
            if(originalAngle >= 360){
                simplifiedAngle = originalAngle - 360;
            }
            if(originalAngle < 0){
                simplifiedAngle = originalAngle + 360;
            }
            tempAngle.setValueDegrees(simplifiedAngle);
            convertToBaseAngle(tempAngle);
        }

        a.setValueDegrees(tempAngle.getValueDegrees());

    }

    public double returnBaseAngleValue(){
        double simplifiedAngle = 0;
        Angle a = new Angle(this.getValueDegrees());
        convertToBaseAngle(a);
        simplifiedAngle = a.getValueDegrees();
        return simplifiedAngle;
    }

    public double calculateSine(){
        double sin = Math.sin(toRadians());
        return sin;
    }

    public double calculateCosine(){
        double cos = Math.cos(toRadians());
        return cos;
    }

    public double calculateTangent(){
        double tan = 0;
        if(valueDegrees == 90 || valueDegrees == 270){
            tan = 999;
        } else {
            tan = Math.tan(toRadians());
        }
        return tan;
    }

    public double toRadians(){
        double radians = valueDegrees * (Math.PI)/180;
        return radians;
    }

    @Override
    public String toString(){
        String angleString = null;
        angleString = "Angle = " + DECIMAL_FORMAT.format(valueDegrees) + "°";
        return angleString;
    }

    /**
     * Compare two angle values with a limited domain of [0,2pi)
     *
     * @param o The other angle to compare with
     * @return a negative integer, zero, or a positive integer
     */
    public int compareToAbsolute(Angle o){
        int result = 0;

        Angle a = this;

        double angleA = a.returnBaseAngleValue();
        double angleB = o.returnBaseAngleValue();

        result = Double.compare(angleA,angleB);
        return result;
    }

    /**
     * Compare two angle values with an unlimited domain.
     *
     * @param o The other angle to compare with
     * @return a negative integer, zero, or a positive integer
     */
    public int compareToReal(Angle o){
        int result = 0;

        double angleA = this.getValueDegrees();
        double angleB = o.getValueDegrees();

        result = Double.compare(angleA,angleB);
        return result;
    }

    /**
     * Compare the Sine of two angles
     *
     * @param o The other angle to compare with
     * @return A negative integer, zero, or a positive integer
     */
    public int compareSine(Angle o){
        int result = 0;

        double sinA = this.calculateSine();
        double sinB = o.calculateSine();

        result = Double.compare(sinA,sinB);
        return result;
    }

    /**
     * Compare the Cosine of two angles
     *
     * @param o The other angle to compare with
     * @return A negative integer, zero, or a positive integer
     */
    public int compareCosine(Angle o){
        int result = 0;

        double cosinA = this.calculateCosine();
        double cosinB = o.calculateCosine();

        result = Double.compare(cosinA,cosinB);
        return result;
    }

    /**
     * Compare the Tangent of two angles
     *
     * @param o The other angle to compare with
     * @return A negative integer, zero, or a positive integer
     */
    public int compareTangent(Angle o){
        int result = 0;

        double tanA = this.calculateTangent();
        double tanB = o.calculateTangent();

        result = Double.compare(tanA, tanB);
        return result;
    }
}
