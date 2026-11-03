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
     * Displays instructions for the respiratory calculator.
     */
    public static void respiratoryGreet() {
        displayMessage("Welcome to Makayla's Respiratory Calculator");
        System.out.println("Calculate respiratory rate using the number of breaths counted");
        System.out.println("and the time in seconds.");
        System.out.println("Example: 12 breaths in 60 seconds = 12 breaths per minute.\n");
        System.out.println("Enter '0' when finished to view your measurement history.\n");
    }

    /**
     * Displays the respiratory calculator's farewell message
     */
    public static void respiratoryGoodbye() {
        displayMessage("Thank you for using Makayla's Respiratory Calculator");

    }
}

