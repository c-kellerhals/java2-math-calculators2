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
     * Displays instructions for using the Trigonometric Functions calculator.
     */
    public static void trigFuncGreet(){
        displayMessage("Welcome to Riona's Trigonometric Functions Calculator");
        System.out.println("Enter an angle value in degrees. Your value should contain only digits and decimal points.");
        System.out.println("Example: 60.5");
    }

    /**
     * Displays the Trigonometric Functions calculator's farewell message
     */
    public static void trigFuncGoodbye(){
        displayMessage("Thank you for using Riona's Trigonometric Functions Calculator");
    }

    /**
     * Prints a plain string with no asterisks.
     *
     * @param message The message to print to string
     */
    public static void showString(String message){
        System.out.println(message);
    }
}
