package edu.kirkwood.model.clapton;

import java.util.Objects;

public class Number implements Comparable<Number> {
    private String value;
    private Integer numberBase;

    public static final String NOT_A_NUMBER_ERROR = "Error: Not a Number. Please enter a valid number.";
    public static final String NOT_AN_INTEGER_ERROR = "Error: Not an Integer. Please enter a valid integer.";
    public static final String INVALID_NUMBER_BASE_ERROR = "Error: Invalid number base. Only 2, 8, and 10 are supported.";
    public static final String NULL_OR_EMPTY_ERROR = "Error: Value cannot be null or empty.";
    public static final String IMPLICIT_CONVERSION_ERROR = "Error: Cannot perform implicit conversion between number bases.";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";

    /**
     * Default constructor that initializes the
     * Number object with a base of null and a value of "0".
     */
    public Number() {
        this.numberBase = null;
        this.value = "0";

    }

    /**
     * Constructs a Number object with the specified value and number base.
     * The value is a String to allow for representations of number bases with leading zeros.
     *
     * @param numberBase the base of the number
     * @param value      the value of the number
     */
    public Number(int numberBase, String value) {
        setNumberBase(numberBase);
        setValue(value);

    }

    /**
     * Returns the value of the Number object.
     *
     * @return the value of the number
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of the Number object.
     *
     * @param value the new value of the number
     * @throws IllegalArgumentException if the value is null, empty, or invalid for the specified number base
     */
    public void setValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_ERROR);
        }
        if (value.length() >= 10) {
            throw new NumberFormatException("Value is too large.");
        }
        if (this.numberBase == 2 && !value.matches("^-?[0-1]+$")) {
            throw new IllegalArgumentException("Invalid binary value.");
        } else if (this.numberBase == 8 && !value.matches("^-?[0-7]+$")) {
            throw new IllegalArgumentException("Invalid octal value.");
        } else if (this.numberBase == 10 && !value.matches("^-?\\d+$")) {
            throw new IllegalArgumentException("Invalid decimal value.");
        }
        this.value = value;
    }

    /**
     * Returns the number base of the Number object.
     *
     * @return the base of the number
     */
    public int getNumberBase() {
        return this.numberBase;
    }

    /**
     * Sets the number base of the Number object.
     *
     * @param numberBase the new base of the number
     */
    public void setNumberBase(Integer numberBase) {
        if (numberBase != null && numberBase != 2 && numberBase != 8 && numberBase != 10) {
            throw new IllegalArgumentException(INVALID_NUMBER_BASE_ERROR);
        }
        // cannot allow a Number Base change to create illegal combinations
        if (this.numberBase != null) {
            throw new IllegalArgumentException(IMPLICIT_CONVERSION_ERROR);
        }
        this.numberBase = numberBase;
    }

    /**
     * Returns a string representation of the Number object.
     *
     * @return a string in the format "Value: [value], Base: [numberBase]"
     */
    public String toString() {
        return "Base " + ANSI_BRIGHT_CYAN + this.numberBase + ANSI_RESET + ", Value " +
                ANSI_ORANGE + this.value + ANSI_RESET;
    }

    /**
     * Converts a decimal number to binary.
     *
     * @param decimal the decimal number as a string
     * @return the binary representation as a string
     */
    public static String decimalToBinary(String decimal) {
        boolean isNegative = false;
        String binary = "";

        if (decimal.equalsIgnoreCase("0")) {
            return "0";
        }
        if (decimal.startsWith("-")) {
            isNegative = true;
        }

        int number = Math.abs(Integer.parseInt(decimal));

        while (number > 0) {
            binary = number % 2 + binary;
            number /= 2;
        }

        if (isNegative) {
            binary = "-" + binary;
        }

        return binary;
    }

    /**
     * Converts a binary number to decimal.
     *
     * @param binary the binary number as a string
     * @return the decimal representation as a string
     */
    public static String binaryToDecimal(String binary) {
        boolean isNegative = false;
        int decimal = 0;
        int power = 0;
        // Binary is 0s and 1s. if It doesn't contain 1, it's equal to 0.
        if (!binary.contains("1")) {
            return "0";
        }
        if (binary.startsWith("-")) {
            isNegative = true;
        }
        // remove the negative sign
        // Java Strings are immutable so we must make a new String
        String absBinary = String.valueOf(Math.abs(Integer.parseInt(binary)));

        int i = absBinary.length() - 1;
        while (i >= 0) {
            int digit = absBinary.charAt(i) - '0';
            decimal += digit * Math.pow(2, power);
            power++;
            i--;
        }
        String decimalString = Integer.toString(decimal);
        if (isNegative) {
            decimalString = "-" + decimalString;
        }
        return decimalString;
    }

    /**
     * Converts a decimal number to octal.
     *
     * @param decimal the decimal number as a string
     * @return the octal representation as a string
     */
    public static String decimalToOctal(String decimal) {
        boolean isNegative = false;
        String octal = "";

        if (decimal.equalsIgnoreCase("0")) {
            return "0";
        }
        if (decimal.startsWith("-")) {
            isNegative = true;
        }

        int number = Math.abs(Integer.parseInt(decimal));

        while (number > 0) {
            octal = (number % 8) + octal;
            number /= 8;
        }

        if (isNegative) {
            octal = "-" + octal;
        }

        return octal;
    }

    /**
     * Converts an octal number to decimal.
     *
     * @param octal the octal number as a string
     * @return the decimal representation as a string
     */
    public static String octalToDecimal(String octal) {
        boolean isNegative = false;
        int decimal = 0;
        // Octal is 0-7. There is probably a better way to check this,
        // but I could only find regex.Pattern and did not want to import it.
        if (!octal.contains("1")
                && !octal.contains("2")
                && !octal.contains("3")
                && !octal.contains("4")
                && !octal.contains("5")
                && !octal.contains("6")
                && !octal.contains("7")) {
            return "0";
        }
        if (octal.startsWith("-")) {
            isNegative = true;
        }
        // remove the negative sign
        // Java Strings are immutable so we must make a new String
        String absOctal = String.valueOf(Math.abs(Integer.parseInt(octal)));

        int i = 0;
        while (i < absOctal.length()) {
            decimal = decimal * 8 + (absOctal.charAt(i) - '0');
            i++;
        }
        String decimalString = Integer.toString(decimal);
        if (isNegative) {
            decimalString = "-" + decimalString;
        }
        return decimalString;
    }

    /**
     * Converts a binary number to octal.
     *
     * @param binary the binary number as a string
     * @return the octal representation as a string
     */
    public static String binaryToOctal(String binary) {
        boolean isNegative = false;
        if (!binary.contains("1")) {
            return "0";
        }
        if (binary.startsWith("-")) {
            isNegative = true;
        }

        String octal = "";
        String absBinary = String.valueOf(Math.abs(Integer.parseInt(binary)));

        // Pad binary with leading zeros until its length is a multiple of 3
        while (absBinary.length() % 3 != 0) {
            absBinary = "0" + absBinary;
        }

        int i = 0;

        while (i < absBinary.length()) {
            String group = absBinary.substring(i, i + 3);

            int value = 0;

            for (int j = 0; j < group.length(); j++) {
                value = value * 2 + (group.charAt(j) - '0');
            }

            octal += value;
            i += 3;
        }
        if (isNegative) {
            octal = "-" + octal;
        }

        return octal;
    }

    /**
     * Converts an octal number to binary.
     *
     * @param octal the octal number as a string
     * @return the binary representation as a string
     */
    public static String octalToBinary(String octal) {
        boolean isNegative = false;
        if (!octal.contains("1")
                && !octal.contains("2")
                && !octal.contains("3")
                && !octal.contains("4")
                && !octal.contains("5")
                && !octal.contains("6")
                && !octal.contains("7")) {
            return "0";
        }

        if (octal.startsWith("-")) {
            isNegative = true;
        }

        String binary = "";
        String absOctal = String.valueOf(Math.abs(Integer.parseInt(octal)));

        int i = 0;

        while (i < absOctal.length()) {
            int digit = absOctal.charAt(i) - '0';

            String bits = "";

            int j = 0;
            while (j < 3) {
                bits = (digit % 2) + bits;
                digit /= 2;
                j++;
            }

            binary += bits;
            i++;
        }

        // Remove unnecessary leading zeros
        while (binary.length() > 1 && binary.charAt(0) == '0') {
            binary = binary.substring(1);
        }

        // Add negative sign if necessary
        if (isNegative) {
            binary = "-" + binary;
        }

        return binary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return Objects.equals(getValue(), number.getValue()) && Objects.equals(getNumberBase(), number.getNumberBase());
    }

    @Override
    public int compareTo(Number other) {
        // Convert both numbers to the same base (decimal) for comparison
        if (other.getNumberBase() != 10) {
            if (other.getNumberBase() == 2) {
                other.setValue(binaryToDecimal(other.getValue()));
            } else if (other.getNumberBase() == 8) {
                other.setValue(octalToDecimal(other.getValue()));
            }
        }
        if (this.getNumberBase() != 10) {
            if (this.getNumberBase() == 2) {
                this.setValue(binaryToDecimal(this.getValue()));
            } else if (this.getNumberBase() == 8) {
                this.setValue(octalToDecimal(this.getValue()));
            }
        }
        return Integer.compare(Integer.parseInt(this.value), Integer.parseInt(other.value));
    }
}
