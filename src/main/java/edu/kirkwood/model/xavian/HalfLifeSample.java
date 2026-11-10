package edu.kirkwood.model.xavian;

import edu.kirkwood.view.Helpers;

/**
 * Represents a sample from a radiological survey.
 * Can be calculated multiple ways, depending on constructor or edit methods.
 * This class provides methods for half-life calculations, verifications, and comparisons.
 */
public class HalfLifeSample implements Comparable<HalfLifeSample> {
    private double initialValue;
    private double remainingValue;
    private double timeElapsed;
    private double halfLifeLength;
    private double halfLivesElapsed;
    private boolean isReliable;

    // TODO: figure out if I need to keep all these throw statements
    /**
     * Constructs a half life sample with the specified initial value, remaining value, time elapsed, half life length, half lives elapsed, and is reliable boolean.
     * This is the general constructor. Most values are meant to be calculated from others, so this constructor is not meant to be used on its own.
     * Public methods will take some of the values given and calculate the rest. These will be where the constructor is used in practice.
     *
     * @param initialValue is the initial quantity of radioactive material being measured. Keep units consistent with remainingValue.
     * @param remainingValue is the remaining quantity of radioactive material being measured. Keep units consistent with initialValue.
     * @param timeElapsed is the amount of time elapsed. Keep this unit consistent with halfLifeLength.
     * @param halfLifeLength is the amount of time in which half of the initial radioactive material will decay. Keep units consistent with timeElapsed.
     * @param halfLivesElapsed is the number of half lives that have passed in timeElapsed. This number has no units.
     * @param isReliable is a check of whether the half life measurement is a reliable source. This is calculated automatically, but can be overridden.
     * @throws IllegalArgumentException if initialValue is less than or equal to 0
     * @throws IllegalArgumentException if remainingValue is less than or equal to 0
     * @throws IllegalArgumentException if remainingValue is greater than initialValue
     * @throws IllegalArgumentException if timeElapsed is less than 0
     * @throws IllegalArgumentException if halfLifeLength is less than or equal to 0
     * @throws IllegalArgumentException if halfLivesElapsed is less than 0
     */
    public HalfLifeSample(double initialValue, double remainingValue, double timeElapsed, double halfLifeLength, double halfLivesElapsed, boolean isReliable) throws IllegalArgumentException {
        setInitialValue(initialValue);
        setRemainingValue(remainingValue);
        setTimeElapsed(timeElapsed);
        setHalfLifeLength(halfLifeLength);
        setHalfLivesElapsed(halfLivesElapsed);
        setIsReliable(isReliable);
    }

    /**
     * Constructs a simple half life sample with simple values.
     * Values:
     *      initialValue = 2
     *      remainingValue = 1
     *      timeElapsed = 1
     *      halfLifeLength = 1
     *      halfLivesElapsed = 1
     *      isReliable = true
     */
    public HalfLifeSample() {
        setInitialValue(2);
        setRemainingValue(1);
        setTimeElapsed(1);
        setHalfLifeLength(1);
        setHalfLivesElapsed(1);
        setIsReliable(true);
    }

    /*---------------------------- QUASI-CONSTRUCTOR PILE ---------------------------------*/

    /**
     * Calculates all values of sample from remainingValue, timeElapsed, and halfLifeLength.
     * Initializes a sample with the calculation results and returns said sample.
     * @param remainingValue the amount of radioactive material remaining in the sample at time of measurement
     * @param timeElapsed the age of the sample
     * @param halfLifeLength how long it takes for half of the radioactive material type measured to decay
     * @return the sample with the calculated values for all parameters.
     * @throws ArithmeticException if the half life is so short relative to the timeElapsed as to become zero.
     */
    public static HalfLifeSample sampleFindInitial(double remainingValue, double timeElapsed, double halfLifeLength) throws ArithmeticException{ // learned to throw multiple kinds of exception: https://stackoverflow.com/questions/2912565/throwing-multiple-exceptions-in-java
        double halfLivesElapsed = halfLivesElapsed(timeElapsed, halfLifeLength);
        // finding isReliable by checking halfLivesElapsed
        boolean isReliable = isReliable(halfLivesElapsed);
        // finding initialValue
        double denominator = Math.pow(1/2.0, halfLivesElapsed);
        if (denominator == 0) {
            throw new ArithmeticException("Too many half lives turned the denominator to zero!");
        }
        double initialValue = remainingValue / denominator;

        // constructing HalfLifeSample
        return new HalfLifeSample(initialValue, remainingValue, timeElapsed, halfLifeLength, halfLivesElapsed, isReliable);
    }

    /**
     * Calculates all values of sample from initialValue, timeElapsed, and halfLifeLength.
     * Initializes a sample with the calculation results and returns said sample.
     * @param initialValue the initial amount of radioactive material upon sample formation
     * @param timeElapsed the amount of time elapsed since sample formation
     * @param halfLifeLength the length of time over which half of the radioactive material would have decayed
     * @return a sample with given values for initialValue, timeElapsed, and halfLifeLength and calculated values for remainingValue, halfLivesElapsed, and isReliable
     */
    public static HalfLifeSample sampleFindRemaining(double initialValue, double timeElapsed, double halfLifeLength) {
        // finding halfLivesElapsed
        double halfLivesElapsed = halfLivesElapsed(timeElapsed, halfLifeLength);
        // finding isReliable
        boolean isReliable = isReliable(halfLivesElapsed);
        // finding remainingValue
        double remainingValue = remainingValue(initialValue, timeElapsed, halfLifeLength);

        // constructing HalfLifeSample
        return new HalfLifeSample(initialValue, remainingValue, timeElapsed, halfLifeLength, halfLivesElapsed, isReliable);
    }

    /**
     * Calculates all values of sample from initialValue, remainingValue, and halfLifeLength.
     * Initializes a sample with the calculation results and returns said sample.
     * @param initialValue the initial amount of radioactive material upon sample formation
     * @param remainingValue the remaining amount of radioactive material upon sample measurement
     * @param halfLifeLength the length of time it takes the sample to lose half of its radioactive material
     * @return a sample with given values for initialValue, remainingValue, and halfLifeLength and calculated values for timeElapsed, halfLivesElapsed, and isReliable
     */
    public static HalfLifeSample sampleFindTimeElapsed(double initialValue, double remainingValue, double halfLifeLength){
        // finding timeElapsed
        double timeElapsed = timeElapsed(initialValue, remainingValue, halfLifeLength);
        // finding halfLivesElapsed
        double halfLivesElapsed = halfLivesElapsed(timeElapsed, halfLifeLength);
        // finding isReliable
        boolean isReliable = isReliable(halfLivesElapsed);

        // constructing and returning sample
        return new HalfLifeSample(initialValue, remainingValue, timeElapsed, halfLifeLength, halfLivesElapsed, isReliable);
    }

    /**
     * Calculates all values of sample from initialValue, remainingValue, and timeElapsed.
     * Initializes a sample with the calculation results and returns said sample.
     * @param initialValue the initial amount of radioactive material upon sample formation
     * @param remainingValue the remaining amount of radioactive material upon sample measurement
     * @param timeElapsed the length of time between sample formation and measurement.
     * @return a sample with given values for initialValue, remainingValue, and timeElapsed and calculated values for halfLifeLength, halfLivesElapsed, and isReliable
     */
    public static HalfLifeSample sampleFindHalfLifeLength(double initialValue, double remainingValue, double timeElapsed) {
        // finding halfLifeLength
        double halfLifeLength = halfLifeLength(initialValue, remainingValue, timeElapsed);
        // finding halfLivesElapsed
        double halfLivesElapsed = halfLivesElapsed(timeElapsed, halfLifeLength);
        // finding isReliable
        boolean isReliable = isReliable(halfLivesElapsed);
        return new HalfLifeSample(initialValue, remainingValue, timeElapsed, halfLifeLength, halfLivesElapsed, isReliable);
    }

    /*--------------------------- End Quasi-Constructor pile --------------------------- */

    /* ------------------------- Begin Getters and Setters ----------------------------- */

    // getters

    /**
     * Gets the initial value of radiological material for this sample.
     * @return the initial value of radiological material for this sample.
     */
    public double getInitialValue() {
        return this.initialValue;
    }

    /**
     * Gets the remaining value of radiological material for this sample.
     * @return the remaining value of radiological material for this sample.
     */
    public double getRemainingValue() {
        return this.remainingValue;
    }

    /**
     * Gets the time elapsed between this sample's formation and measurement
     * @return the time elapsed between sample formation and measurement
     */
    public double getTimeElapsed() {
        return this.timeElapsed;
    }

    /**
     * Gets the half life length for the radiological isotope found in this sample
     * @return the half life length for this sample.
     */
    public double getHalfLifeLength() {
        return this.halfLifeLength;
    }

    /**
     * Gets the half lives elapsed between sample formation and measurement
     * @return the half lives elapsed by this sample
     */
    public double getHalfLivesElapsed() {
        return this.halfLivesElapsed;
    }

    /**
     * Gets whether the sample is a reliable source of radiological data.
     * @return true if reliable, false if not reliable
     */
    public boolean getIsReliable() {
        return this.isReliable;
    }

    // setters

    // setter default (non-auto-adjust)

    /**
     * Sets the initial value of the sample.
     * timeElapsed, halfLivesElapsed, and isReliable will NOT be updated with this version.
     * @param initialValue the initial quantity of radiological material upon sample formation
     */
    public void setInitialValue(double initialValue) {
        setInitialValue(initialValue, false);
    }

    /**
     * Sets the remaining value of the sample.
     * timeElapsed, halfLivesElapsed, and isReliable will NOT be updated with this version.
     * @param remainingValue the remaining quantity of radiological material upon sample measurement
     */
    public void setRemainingValue(double remainingValue) {
        setRemainingValue(remainingValue, false);
    }

    /**
     * Sets the time elapsed.
     * Will not automatically update other values.
     * @param timeElapsed the amount of time between a sample's formation and measurement
     */
    public void setTimeElapsed(double timeElapsed) {
        setTimeElapsed(timeElapsed, false);
    }

    /**
     * Sets half life length.
     * This will not automatically update other values.
     * @param halfLifeLength the time it takes for half of a radiological material to decay.
     */
    public void setHalfLifeLength(double halfLifeLength) {
        setHalfLifeLength(halfLifeLength, false);
    }

    /**
     * Sets the quantity of half lives elapsed.
     * This will not automatically update any other values.
     * @param halfLivesElapsed the number of half lives that a sample has passed.
     */
    public void setHalfLivesElapsed(double halfLivesElapsed) {
        setHalfLivesElapsed(halfLivesElapsed, false);
    }

    /**
     * Sets whether the sample measurement is reliable.
     * This will never automatically update any other value.
     * @param isReliable whether the sample is reliable
     */
    public void setIsReliable(boolean isReliable) {
        this.isReliable = isReliable;
    }

    // setter auto-adjusters

    /**
     * Sets the initial value of the sample.
     * timeElapsed, halfLivesElapsed, and isReliable will be automatically updated if override is true
     * @param initialValue the initial amount of radiological material present in a sample upon formation
     * @param override boolean whether to override other values
     * @throws IllegalArgumentException if initial value is less than or equal to zero
     */
    public void setInitialValue(double initialValue, boolean override) throws IllegalArgumentException{
        if (myRound(initialValue) <= 0) {throw new IllegalArgumentException("Initial value must be greater than zero!");}
        this.initialValue = myRound(initialValue);
        if (override) {
            setTimeElapsed(timeElapsed(getInitialValue(),getRemainingValue(),getHalfLifeLength()));
            setHalfLivesElapsed(halfLivesElapsed(getTimeElapsed(),getHalfLifeLength()));
            setIsReliable(isReliable(getHalfLivesElapsed()));
        }
    }

    /**
     * Sets the remaining value of the sample.
     * timeElapsed, halfLivesElapsed, and isReliable will be automatically updated if override is true.
     * @param remainingValue the remaining amount of radiological material present in a sample upon measurement
     * @param override boolean whether to override other values
     * @throws IllegalArgumentException if remaining value is less than or equal to zero.
     * @throws IllegalArgumentException if remaining value is greater than or equal to the initial value.
     */
    public void setRemainingValue(double remainingValue, boolean override) throws IllegalArgumentException{
        if (myRound(remainingValue) <= 0) {throw new IllegalArgumentException("Remaining value must be greater than zero!");}
        if (myRound(remainingValue) > getInitialValue()) {throw new IllegalArgumentException("Remaining value must be less than or equal to initial value!");}
        this.remainingValue = myRound(remainingValue);
        if (override) {
            setTimeElapsed(timeElapsed(getInitialValue(), getRemainingValue(), getHalfLifeLength()));
            setHalfLivesElapsed(halfLivesElapsed(getTimeElapsed(),getHalfLifeLength()));
            setIsReliable(isReliable(getHalfLivesElapsed()));
        }
    }

    /**
     * Sets the time elapsed.
     * If time elapsed is not set to zero, this will automatically update the half life length if override is true.
     * If time elapsed is set to zero, this will automatically update remainingValue to initialValue, halfLivesElapsed to zero, and isReliable to true if override is true.
     * @param timeElapsed the amount of time between a sample's formation and measurement
     * @param override boolean whether to override other values
     * @throws IllegalArgumentException if time elapsed is less than zero.
     */
    public void setTimeElapsed(double timeElapsed, boolean override) throws IllegalArgumentException {
        if (myRound(timeElapsed) < 0) {throw new IllegalArgumentException("Time elapsed cannot be less than zero!");}
        this.timeElapsed = myRound(timeElapsed);
        if (getTimeElapsed() != 0 && override) {
            setHalfLifeLength(halfLifeLength(getInitialValue(), getRemainingValue(), getTimeElapsed()));
        } else if (override){
            setRemainingValue(getInitialValue());
            setHalfLivesElapsed(0);
            setIsReliable(true);
        }
    }

    /**
     * Sets half life length.
     * This will automatically update halfLivesElapsed, remainingValue, and isReliable if override is true.
     * @param halfLifeLength the time it takes for half of a radiological material to decay.
     * @param override boolean whether the other values should be updated.
     * @throws IllegalArgumentException if half life is less than or equal to zero.
     */
    public void setHalfLifeLength(double halfLifeLength, boolean override) throws IllegalArgumentException {
        if (myRound(halfLifeLength) <= 0) {throw new IllegalArgumentException("Half life length must be greater than zero!");}
        this.halfLifeLength = myRound(halfLifeLength);
        if (override) {
            setHalfLivesElapsed(halfLivesElapsed(getTimeElapsed(),getHalfLifeLength()));
            setIsReliable(isReliable(getHalfLivesElapsed()));
            setRemainingValue(remainingValue(getInitialValue(), getTimeElapsed(), getHalfLifeLength()));
        }
    }

    /**
     * Sets the quantity of half lives elapsed.
     * This will automatically update remainingValue, timeElapsed, and isReliable if override is true.
     * @param halfLivesElapsed the number of half lives that a sample has passed.
     * @param override boolean whether other values should be overridden
     * @throws IllegalArgumentException if half lives elapsed is less than zero
     */
    public void setHalfLivesElapsed(double halfLivesElapsed, boolean override) throws IllegalArgumentException {
        if (myRound(halfLivesElapsed) < 0) {throw new IllegalArgumentException("Half lives elapsed cannot be less than zero!");}
        this.halfLivesElapsed = myRound(halfLivesElapsed);
        if (override) {
            setTimeElapsed(getHalfLivesElapsed() * getHalfLifeLength());
            setRemainingValue(remainingValue(getInitialValue(), getTimeElapsed(), getHalfLifeLength()));
            setIsReliable(isReliable(getHalfLivesElapsed()));
        }
    }

    /* ------------------------- End Getters and Setters ------------------------------- */

    /* ------------------------- Begin Helper Functions -------------------------------- */

    /**
     * Calculates half lives elapsed from time elapsed and half life length.
     * @param timeElapsed the amount of time passed
     * @param halfLifeLength the amount of time per half life
     * @return the quantity of half lives elapsed
     * @throws ArithmeticException if half life length is zero.
     */
    public static double halfLivesElapsed(double timeElapsed, double halfLifeLength) throws ArithmeticException{
        if (halfLifeLength == 0) {throw new ArithmeticException("Half life length cannot be zero!");}
        return myRound(timeElapsed/halfLifeLength);
    }

    /**
     * Calculates if the sample is reliable based on half lives elapsed.
     * @param halfLivesElapsed the number of half lives elapsed
     * @return true if reliable, false if unreliable.
     */
    public static boolean isReliable(double halfLivesElapsed) {
        return halfLivesElapsed <= 10 && halfLivesElapsed >= 0;
    }

    /**
     * Calculate time elapsed from initialValue, remainingValue, and halfLifeLength.
     * @param initialValue the amount of radiological material in the sample at formation.
     * @param remainingValue the amount of radiological material in the sample at measurement.
     * @param halfLifeLength the amount of time it takes for half the radiological material to decay.
     * @return the amount of time passed between sample formation and measurement.
     */
    public static double timeElapsed(double initialValue, double remainingValue, double halfLifeLength) throws IllegalArgumentException {
        if (initialValue <= 0) {throw new IllegalArgumentException("Initial value must be greater than zero!");}
        if (remainingValue <= 0) {throw new IllegalArgumentException("Remaining Value must be greater than zero!");}
        if (remainingValue > initialValue) {throw new IllegalArgumentException("Initial value cannot be less than remaining value!");}
        return halfLifeLength * halfLivesElapsedCheck(initialValue, remainingValue);
    }

    /**
     * Calculates the half life length given initialValue, remainingValue, and timeElapsed.
     * @param initialValue the quantity of radiological material on sample formation
     * @param remainingValue the quantity of radiological material on sample measurement
     * @param timeElapsed the time passed between sample formation and measurement
     * @return the time it would take for half of the measured radiological material to decay
     * @throws IllegalArgumentException if initial value is less than or equal to zero
     * @throws IllegalArgumentException if remaining value is less than or equal to zero
     * @throws IllegalArgumentException if time elapsed is less than or equal to zero.
     * @throws ArithmeticException if initial value is equal to remaining value
     */
    public static double halfLifeLength(double initialValue, double remainingValue, double timeElapsed) throws IllegalArgumentException, ArithmeticException  {
        // learned to do multiple types of throws from one function on stack overflow
        if (initialValue <= 0) {throw new IllegalArgumentException("Initial value must be greater than zero!");}
        if (remainingValue <= 0) {throw new IllegalArgumentException("Remaining value must be greater than zero!");}
        if (timeElapsed <= 0) {throw new IllegalArgumentException("Cannot determine half life length with no change in time! [Zero half life]");}
        double denominator = Math.log(remainingValue / initialValue);
        if (denominator == 0) {throw new ArithmeticException("Cannot determine half life length with no change in quantity! [Infinite half life]");}
        return timeElapsed * Math.log(1/2.0) / denominator;
    }

    /**
     * Calculates the remaining value given initialValue, timeElapsed, and halfLifeLength.
     * @param initialValue the amount of radiological material present in a sample at formation
     * @param timeElapsed the amount of time passed between sample formation and measurement
     * @param halfLifeLength the amount of time it takes for half of the radiological material to decay
     * @return the remaining quantity of radiological material present in a sample upon measurement
     */
    public static double remainingValue(double initialValue, double timeElapsed, double halfLifeLength) {
        return initialValue * Math.pow(1/2.0, halfLivesElapsed(timeElapsed, halfLifeLength));
    }

    /**
     * Rounds to the nearest ten thousandth. Helps get rid of some rounding point errors.
     * Created so I don't have to type Double.parseDouble(Helpers.round(number,4)); over and over again
     * @param number the number to round
     * @return the rounded number to the nearest four decimal points
     */
    public static double myRound(double number) {
        return Double.parseDouble(Helpers.round(number,4));
    }

    /**
     * Provides an alternate method to check half lives elapsed.
     * @param initialValue the initial quantity of radiological material in a sample upon formation
     * @param remainingValue the remaining quantity of radiological material in a sample upon measurement.
     * @return the number of half lives elapsed by the sample.
     * @throws IllegalArgumentException if initial value is less than or equal to zero
     * @throws IllegalArgumentException if remaining value is less than or equal to zero
     */
    public static double halfLivesElapsedCheck(double initialValue, double remainingValue) throws IllegalArgumentException {
        if (initialValue <= 0) {throw new IllegalArgumentException("Initial value must be greater than zero!");}
        if (remainingValue <= 0) {throw new IllegalArgumentException("Remaining Value must be greater than zero!");}
        return myRound(Math.log(remainingValue / initialValue) / Math.log(1/2.0));
    }

    /**
     * Use on a sample to grab data from said sample and return the minimum amount of time passed.
     * This test uses a ratio check between initialValue and remainingValue and adds 1/measureError of the ratio between them.
     * If measureError is zero, will not subtract anything.
     * @param measureError the inverse of the ratio to be subtracted
     * @return the minimum amount of time that may have passed between sample formation and measurement
     * @throws IllegalArgumentException if measureError is less than 0.
     */
    public double findMinTime(int measureError) {
        double result = 0;
        if (measureError == 0) {
            result = this.getTimeElapsed();
        }
        else if (measureError < 0) {
            throw new IllegalArgumentException("Measure Error cannot be negative!");
        }
        else {
            double ratio = this.getRemainingValue()/this.getInitialValue();
            double delta = ratio/measureError;
            double minTimeRatio = ratio + delta;
            result = Double.parseDouble(Helpers.round(timeElapsed(1000, minTimeRatio * 1000, getHalfLifeLength()),3));
        }

        return result;
    }

    /**
     * Use on a sample to grab data from said sample and return the minimum amount of time passed.
     * This test uses a ratio check between initialValue and remainingValue and adds 1/measureError of the ratio between them.
     * The default of measureError when one is not provided is 1000.
     * @return the minimum amount of time that may have passed between sample formation and measurement
     */
    public double findMinTime() {
        return findMinTime(1000);
    }

    /**
     * Use on a sample to grab data from said sample and return the maximum amount of time passed.
     * This test uses a ratio check between initialValue and remainingValue and subtracts 1/measureError of the ratio between them.
     * If measureError is zero, will not subtract anything.
     * @param measureError the inverse of the ratio to be added.
     * @return the maximum amount of time that may have passed between sample formation and measurement
     * @throws IllegalArgumentException if measureError is less than or equal to 1, but not zero.
     */
    public double findMaxTime(int measureError) {
        double result = 0;
        if (measureError == 0) {
            result = this.getTimeElapsed();
        } else if (measureError <= 1) {
            throw new IllegalArgumentException("Measure Error must be greater than one!");
        } else {
            double ratio = getRemainingValue()/getInitialValue();
            double delta = ratio/measureError; // = ratio * 1.0/measureError
            double maxTimeRatio = ratio - delta; // subtract to take more material away, so more time passed
            result = Double.parseDouble(Helpers.round(timeElapsed(1000, maxTimeRatio * 1000, getHalfLifeLength()),3));
        }
        return result;
    }

    /**
     * Use on a sample to grab data from said sample and return the maximum amount of time passed.
     * This test uses a ratio check between initialValue and remainingValue and subtracts 1/measureError of the ratio between them.
     * The default of measureError when one is not provided is 1000.
     * @return the maximum amount of time that may have passed between sample formation and measurement.
     */
    public double findMaxTime() {
        return findMaxTime(1000);
    }



    /* ------------------------- End Helper Functions ---------------------------------- */

    /* ---------------------------- Begin Override Elements ---------------------------- */

    /**
     * Compares timeElapsed with margin of error of sample a and sample b. [a.compareTo(b)]
     * Note that the longer timeElapsed should come first!
     * @param b the HalfLifeSample to be compared.
     * @return -1 if a is likely to have formed before b, 0 if a and b likely formed at the same time, and 1 if a likely formed after b.
     * @throws IllegalArgumentException if one of the samples is not reliable
     */
    @Override
    public int compareTo(HalfLifeSample b) throws IllegalArgumentException{
        // make sure both are reliable
        if (!this.isReliable || !b.isReliable) {throw new IllegalArgumentException("One or more of these samples is not reliable!");}
        // determine margin of error
        double maxTimeA = this.findMaxTime();
        double minTimeA = this.findMinTime();
        double minTimeB = b.findMinTime();
        double maxTimeB = b.findMaxTime();
        // separate into cases
        // if the longest time a could be ago is less than the shortest time b could be ago, a came after b
        if (maxTimeA < minTimeB) {
            return 1;
        }
        // if the shortest time a could be ago is greater than the longest time b could be ago, a came before b
        if (minTimeA > maxTimeB) {
            return -1;
        }
        return 0;
    }

    /**
     * Returns a string representation of the HalfLifeSample.
     * @return a string representation of the half life sample.
     */
    @Override
    public String toString() {
        String output = "";
        output += "Sample Details:\nInitial Value: ";
        output += this.initialValue;
        output += "\nRemaining Value: ";
        output += this.remainingValue;
        output += "\nTime Elapsed: ";
        output += this.timeElapsed;
        output += "\nHalf Life Length: ";
        output += this.halfLifeLength;
        output += "\nHalf Lives Elapsed: ";
        output += this.halfLivesElapsed;
        output += "\nSample is ";
        if (this.isReliable) {
            output += "reliable.";
        } else {
            output += "not reliable.";
        }
        return output;
    }

    /**
     * Compares two objects to see if all parameters are the same.
     * @param o   the reference object with which to compare.
     * @return true if both objects are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {return false;}
        HalfLifeSample halfLifeSample = (HalfLifeSample) o;
        return this.initialValue == halfLifeSample.initialValue && this.remainingValue == halfLifeSample.remainingValue && this.timeElapsed == halfLifeSample.timeElapsed && this.halfLifeLength == halfLifeSample.halfLifeLength && this.halfLivesElapsed == halfLifeSample.halfLivesElapsed && this.isReliable == halfLifeSample.isReliable;
    }

    /* -------------------------- End Override Elements ------------------------------- */

    /* ------------------------- Non-Override Print ----------------------------------- */

    /**
     * returns a string about comparing two half life samples.
     * uses compareTo method inside
     * @param b the half life sample to compare to
     * @return a string representing a human-readable comparison between the two half life samples.
     */
    public String stringCompareTo(HalfLifeSample b) {
        String output = "";
        int compare = Integer.MIN_VALUE;
        output += "Sample 1:\n";
        output += this.toString();
        output += "\n\nSample 2:\n";
        output += b.toString();
        output += "\n\nAnalysis:\nSample 1 Time Range: ";
        output += findMinTime();
        output += " - ";
        output += findMaxTime();
        output += "\nSample 2 Time Range: ";
        output += b.findMinTime();
        output += " - ";
        output += b.findMaxTime();
        output += "\n";
        output += "";
        if (getIsReliable() && b.getIsReliable()) {
            compare = compareTo(b);
        }
        switch (compare) {
            case 1: // b happened before a
                output += "Sample 1 formed after Sample 2.";
                break;
            case -1: // a happened before b
                output += "Sample 1 formed before Sample 2.";
                break;
            case 0: // b and a happened at the same time
                output += "Sample 1 and Sample 2 formed in the same time range.";
                break;
            default:
                return "One or more samples are not reliable. Cannot determine formation order.";
        }
        return output;
    }
}