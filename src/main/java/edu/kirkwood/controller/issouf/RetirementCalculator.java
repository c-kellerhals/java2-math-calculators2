package edu.kirkwood.controller.issouf;

import edu.kirkwood.model.issouf.RetirementAccount;
import edu.kirkwood.model.issouf.RetirementProjection;

import static edu.kirkwood.view.Helpers.toCurrency;
import static edu.kirkwood.view.Messages.retirementGoodbye;
import static edu.kirkwood.view.Messages.retirementGreet;
import static edu.kirkwood.view.Messages.retirementTerms;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UIUtility.printLine;
import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getDouble;
import static edu.kirkwood.view.UserInput.getInt;

/**
 * Command-line calculator that estimates a 401(k) balance at retirement.
 * This class contains the menu, the input collection, and the parsing helpers
 * for the retirement calculator. Entering a negative number at any prompt quits.
 */
public class RetirementCalculator {

    /** Message shown when the input is not a valid number. */
    public static final String INVALID_NUMBER_MESSAGE = "Invalid number. Enter a numeric value.";

    /** Message shown when a percentage falls outside 0 to 100. */
    public static final String INVALID_PERCENT_MESSAGE = "Invalid percentage. Enter a value between 0 and 100.";

    /** Message shown when a dollar amount is negative. */
    public static final String INVALID_AMOUNT_MESSAGE = "Invalid amount. Enter a value of zero or greater.";

    /**
     * Displays a menu and runs the retirement calculator until the user quits.
     */
    public static void start() {
        retirementGreet();
        while (true) {
            String[] menuItems = {"Learn about 401(k) terms", "Calculate a retirement projection", "Exit the application"};
            printMenu("Retirement Calculator", menuItems);
            int choice = getInt("Enter your choice (negative to quit)", true, -1, 3);

            if (choice < 0) {
                break;
            }
            if (choice == 1) {
                retirementTerms();
                pressEnterToContinue();
            } else if (choice == 2) {
                RetirementAccount account = collectAccount();
                if (account == null) {
                    break;
                }
                RetirementProjection projection = collectProjection();
                if (projection == null) {
                    break;
                }
                double retirementBalance = projection.projectRetirementBalance(account);
                displayProjectionSummary(projection, account, retirementBalance);
                pressEnterToContinue();
            } else {
                break;
            }
        }
        retirementGoodbye();
        pressEnterToContinue();
    }

    /**
     * I used AI to generate this method to collect the retirement account values.
     * Collects the retirement account values from the user.
     * Entering a negative amount quits.
     *
     * @return a RetirementAccount built from the user's input, or {@code null} when the user quits
     */
    public static RetirementAccount collectAccount() {
        double balance = getDouble("Enter your current 401(k) balance (negative to quit)", true, -1, Double.MAX_VALUE);
        if (balance < 0) {
            return null;
        }
        double salary = getDouble("Enter your current annual salary (negative to quit)", true, -1, Double.MAX_VALUE);
        if (salary < 0) {
            return null;
        }
        double contribution = getDouble("Enter your contribution percentage (negative to quit)", true, -1, 100);
        if (contribution < 0) {
            return null;
        }
        double matchRate = getDouble("Enter the employer match percentage (negative to quit)", true, -1, 100);
        if (matchRate < 0) {
            return null;
        }
        double matchLimit = getDouble("Enter the employer match limit percentage (negative to quit)", true, -1, 100);
        if (matchLimit < 0) {
            return null;
        }
        return new RetirementAccount(balance, salary, contribution, matchRate, matchLimit);
    }

    /**
     * Collects the retirement projection values from the user.
     * Entering a negative value quits.
     *
     * @return a RetirementProjection built from the user's input, or {@code null} when the user quits
     */
    public static RetirementProjection collectProjection() {
        int currentAge = getInt("Enter your current age (negative to quit)", true, -1);
        if (currentAge < 0) {
            return null;
        }
        int retirementAge;
        while (true) {
            retirementAge = getInt("Enter your retirement age (negative to quit)", true, -1);
            if (retirementAge < 0) {
                return null;
            }
            if (retirementAge > currentAge) {
                break;
            }
            displayError("Retirement age must be greater than the current age");
        }
        double increaseRate = getDouble("Enter the expected salary increase percentage (negative to quit)", true, -1, 100);
        if (increaseRate < 0) {
            return null;
        }
        double returnRate = getDouble("Enter the expected annual return percentage (negative to quit)", true, -1, 100);
        if (returnRate < 0) {
            return null;
        }
        return new RetirementProjection(currentAge, retirementAge, increaseRate, returnRate);
    }

    /**
     * I used AI to get the displayProjectionSummary
     * Displays the projection results, one value per line, with currency amounts
     * formatted through {@code Helpers.toCurrency}.
     *
     * @param projection the projection holding the ages and rates
     * @param account    the account holding the salary and balance
     * @param retirementBalance the estimated balance at retirement
     */
    public static void displayProjectionSummary(RetirementProjection projection, RetirementAccount account, double retirementBalance) {
        printLine();
        System.out.printf("%-33s %20s%n", "Current age:", projection.getCurrentAge());
        System.out.printf("%-33s %20s%n", "Retirement age:", projection.getRetirementAge());
        System.out.printf("%-33s %20s%n", "Years until retirement:", projection.calculateYearsUntilRetirement());
        System.out.printf("%-33s %20s%n", "Current salary:", toCurrency(account.getAnnualSalary()));
        System.out.printf("%-33s %20s%n", "Current 401(k) balance:", toCurrency(account.getCurrentBalance()));
        System.out.printf("%-33s %20s%n", "Employee contribution:", account.getContributionRate() + "%");
        System.out.printf("%-33s %20s%n", "Employer match:", account.getEmployerMatchRate() + "%");
        System.out.printf("%-33s %20s%n", "Expected annual return:", projection.getAnnualReturnRate() + "%");
        System.out.printf("%-33s %20s%n", "Estimated balance at retirement:", toCurrency(retirementBalance));
        printLine();
    }

    /**
     * Parses a whole-number percentage from text.
     *
     * @param input the raw input string from the user
     * @return the parsed percentage as a double
     * @throws IllegalArgumentException if the input is not a valid number or is out of range
     */
    public static double parsePercent(String input) throws IllegalArgumentException {
        double value;
        try {
            value = Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
        }
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(INVALID_PERCENT_MESSAGE);
        }
        return value;
    }

    /**
     * Parses a dollar amount from text.
     *
     * @param input the raw input string from the user
     * @return the parsed amount as a double
     * @throws IllegalArgumentException if the input is not a valid number or is negative
     */
    public static double parseAmount(String input) throws IllegalArgumentException {
        double value;
        try {
            value = Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
        }
        if (value < 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
        return value;
    }
}
