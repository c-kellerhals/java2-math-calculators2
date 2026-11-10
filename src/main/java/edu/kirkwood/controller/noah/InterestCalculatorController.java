package edu.kirkwood.controller.noah;

import edu.kirkwood.model.noah.InterestCalculator;
import edu.kirkwood.view.UIUtility;

import static edu.kirkwood.view.Messages.interestGoodbye;
import static edu.kirkwood.view.Messages.interestGreet;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getDouble;
import static edu.kirkwood.view.UserInput.getInt;

/**
 * Controls the flow of the Interest Calculator application.
 *
 * InterestCalculatorController
 */
public class InterestCalculatorController {

    public static void start() {

        double principal = 0;
        double annualRate = 0;
        int periodsPerYear = 0;
        int years = 0;
        double contributions = 0;

        interestGreet();
        String[] menuItems = {"Simple Interest", "Compounding Interest", "Compounding Interest With Contributions", "Quit"};
        while (true) {

            UIUtility.printMenu("Noah's Interest Calculator", menuItems);
            int userInput = getInt("", true, 1, 4);
            if (userInput == 4) {
                break;
            }

            switch (userInput) {
                case 1:
                    principal = getDouble("Enter a principal value (Or type '0' to quit)", true, 0.00, Double.MAX_VALUE);
                    if (principal == 0) {
                        break;
                    }
                    do {
                        annualRate = getDouble("Enter an annual interest rate in decimal form (Or type '0' to quit)", true, 0, Double.MAX_VALUE);
                        if (annualRate > 1) {
                            System.out.println("Please enter the interest rate in decimal form. ( Ex: 5% would be 0.05 )");
                        }
                    } while (annualRate > 1);

                    if (annualRate == 0) {
                        break;
                    }

                    years = getInt("Enter total years (Or type '0' to quit)", true, 0, Integer.MAX_VALUE);
                    if (years == 0) {
                        break;
                    }
                    InterestCalculator simpleInvestment = new InterestCalculator(principal, annualRate, years);
                    System.out.println(simpleInvestment.displayIncrementalInterestTable());
                    interestGoodbye();
                    pressEnterToContinue();
                    break;
                case 2:
                    principal = getDouble("Enter a principal value (Or type '0' to quit)", true, 0.00, Double.MAX_VALUE);
                    if (principal == 0) {
                        break;
                    }
                    do {
                        annualRate = getDouble("Enter an annual interest rate in decimal form (Or type '0' to quit)", true, 0, Double.MAX_VALUE);
                        if (annualRate > 1) {
                            System.out.println("Please enter the interest rate in decimal form. ( Ex: 5% would be 0.05 )");
                        }
                    } while (annualRate > 1);

                    periodsPerYear = getInt("Enter periods per year the investment compounds/gets contributions (Or type '0' to quit)", true, 0, Integer.MAX_VALUE);
                    if (periodsPerYear == 0) {
                        break;
                    }
                    years = getInt("Enter total years (Or type '0' to quit)", true, 0, Integer.MAX_VALUE);
                    if (years == 0) {
                        break;
                    }
                    InterestCalculator compoundingInvestment = new InterestCalculator(principal, annualRate, periodsPerYear, years);
                    System.out.println(compoundingInvestment.displayIncrementalInterestTable());
                    interestGoodbye();
                    pressEnterToContinue();
                    break;
                case 3:
                    principal = getDouble("Enter a principal value (Or type '0' to quit)", true, 0.00, Double.MAX_VALUE);
                    if (principal == 0) {
                        break;
                    }
                    do {
                        annualRate = getDouble("Enter an annual interest rate in decimal form (Or type '0' to quit)", true, 0, Double.MAX_VALUE);
                        if (annualRate > 1) {
                            System.out.println("Please enter the interest rate in decimal form. ( Ex: 5% would be 0.05 )");
                        }
                    } while (annualRate > 1);

                    periodsPerYear = getInt("Enter periods per year the investment compounds/gets contributions (Or type '0' to quit)", true, 0, Integer.MAX_VALUE);
                    if (periodsPerYear == 0) {
                        break;
                    }
                    years = getInt("Enter total years (Or type '0' to quit)", true, 0, Integer.MAX_VALUE);
                    if (years == 0) {
                        break;
                    }
                    contributions = getInt("Enter total amount of contributions (Or type '0' to quit)", true, 0, Integer.MAX_VALUE);
                    if (contributions == 0) {
                        break;
                    }
                    InterestCalculator contributionsInvestment = new InterestCalculator(principal, annualRate, periodsPerYear, years, contributions);
                    System.out.println(contributionsInvestment.displayIncrementalInterestTable());
                    interestGoodbye();
                    pressEnterToContinue();
                    break;
                default:
                    break;
            }
        }
    }
}
