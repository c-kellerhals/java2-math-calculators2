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

    public static void loanGreet() {
        displayMessage("Welcome to Ryan's Amortization Loan Calculator");
    }

    public static void  loanGoodbye() {
        displayMessage("Thank you for using Ryan's Amortization Loan Calculator");
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
     * Displays the Half Life calculator's greeting message.
     */
    public static void halfLifeHello() {
        System.out.println();
        displayMessage("Welcome to Xavian's Half-Life Calculator!");
        System.out.println("Select an option below to start:");
    }

    /**
     * Displays the half life calculator's farewell message.
     */
    public static void halfLifeGoodbye() {
        System.out.println();
        displayMessage("Thank you for using Xavian's Half-Life Calculator.");
    }
}

