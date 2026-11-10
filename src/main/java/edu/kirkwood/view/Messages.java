package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;

public class Messages {

    /**
     * Displays the application's welcome message.
     */
    public static void hello() {
        displayMessage("Welcome to the Kirkwood Calculators Application");
    }

    /**
     * Displays the application's farewell message.
     */
    public static void goodbye() {
        displayMessage("Goodbye");
    }

    /**
     * Displays instructions for using the fraction calculator.
     */
    public static void fractionGreet() {
        displayMessage("Welcome to Marc's Fraction Calculator");
        System.out.println("Enter calculations in the format: [fraction] [operator] [fraction]");
        System.out.println("Example: 1 1/2 + 3/4\n");
    }

    /**
     * Displays the fraction calculator's farewell message.
     */
    public static void fractionGoodbye() {
        displayMessage("Thank you for using Marc's Fraction Calculator");
    }

    /**
     * Displays instructions for using the interest calculator.
     */
    public static void interestGreet() {
        displayMessage("Welcome to Noah's Interest Calculator");
        System.out.println("\nThis program calculates how much interest will be earned from a investment. Pick an investment type:\n");
    }

    /**
     * Displays the interest calculator's farewell message.
     */
    public static void interestGoodbye() {
        displayMessage("Thank you for using Noah's Interest Calculator.");
    }
  
    /**
     * Displays instructions for using the GPA calculator.
     */
    public static void GPAGreet() {
        displayMessage("Welcome to Masean's GPA Calculator");
        System.out.println("When prompted, carefully read the question and enter the requested information.\n");
    }

    /**
     * Displays the GPA calculator's farewell message.
     */
    public static void GPAGoodbye() {
        displayMessage("Thank you for using the GPA Calculator");
    }

    /**
     * Displays instructions for using the base conversion calculator.
     */
    public static void baseConversionGreet() {
        displayMessage("Welcome to Clapton's Base Conversion Calculator");
        System.out.println("First, you will be asked for a number base.\nThen, a value in that base.\nFinally, you will be asked for a base you wish to convert to.");
        System.out.println("Example: Base 10, value of 10, to base 2\n");;
    }
    /**
     * Displays the base conversion calculator's farewell message.
     */
    public static void baseConversionGoodbye() {
        displayMessage("Thank you for using Clapton's Base Conversion Calculator");
    }
}
