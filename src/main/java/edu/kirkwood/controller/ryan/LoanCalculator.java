package edu.kirkwood.controller.ryan;

import edu.kirkwood.model.ryan.Loan;
import edu.kirkwood.view.Helpers;
import edu.kirkwood.view.UIUtility;

import static edu.kirkwood.view.Messages.loanGoodbye;
import static edu.kirkwood.view.Messages.loanGreet;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getDouble;
import static edu.kirkwood.view.UserInput.getInt;

public class LoanCalculator {
    /**
     * Starts the loan calculator application, displaying a greeting and menu options for
     * calculating a loan payment, a budgeted loan, or quitting the application. It then prompts the user for loan details
     * and displays the calculated monthly payment, total payment, and total interest or the budgeted loan amount and
     * total price based on the user's input.
     * The user can continue to perform calculations until they choose to quit.
     */
    public static void start(){
        loanGreet();
        while(true) {
            UIUtility.printMenu("Amortization Loan Calculator", new String[]{"Calculate Loan Payment", "Budgeted Monthly Payment", "Quit"});
            int value = getInt("Choose an option", true, 1, 3);
            if (value == 1) {
                displayMessage("When asked please enter the values for the loan principal, annual interest rate, and term in years.");
                displayMessage("Enter -1 at any prompt to exit the loan calculator.");
                Loan loan = new Loan();
                while (true) {
                    double principal = getDouble("Enter the loan principal", true);
                    if (principal == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setPrincipal(principal);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                while (true) {
                    double annualInterestRate = getDouble("Enter the annual interest rate of the loan - (as a percentage e.g., 5% would be 5)", true);
                    if (annualInterestRate == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setAnnualRate(annualInterestRate);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                while (true) {
                    int termInYears = getInt("Enter the loan term in years", true);
                    if (termInYears == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setTermYears(termInYears);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                    System.out.print("\nMonthly Loan Payment: " + Helpers.toCurrency(loan.calculateMonthlyPayment()));
                    System.out.print("\nTotal Payment (Including Interest): " + Helpers.toCurrency(loan.calculateTotalPayment()));
                    System.out.print("\nTotal Interest Of The Loan: " +  Helpers.toCurrency(loan.calculateTotalInterest()) + "\n");
                    pressEnterToContinue();
                    System.out.print("\nAmortization Schedule:");
                    int count = 0;
                    for(Loan.PaymentRecord record : loan.amortizationSchedule()){
                        if(record.getPaymentNumber() % 12 == 1) {
                            System.out.println("\nPayments in Year " + ((record.getPaymentNumber() - 1) / 12 + 1) +
                                                    " of " + loan.getTermYears() + "\n");
                        }
                        System.out.print("Payment #" + record.getPaymentNumber() +
                                            " | Payment Amount: " + Helpers.toCurrency(record.getPaymentAmount()) +
                                            " | Principal Paid: " + Helpers.toCurrency(record.getPrincipalPaid()) +
                                            " | Interest Paid: " + Helpers.toCurrency(record.getInterestPaid()) +
                                            " | Remaining Balance: " + Helpers.toCurrency(record.getRemainingBalance()) +
                                            "\n");
                        count++;
                        if(count % 12 == 0) {
                            pressEnterToContinue();
                        }
                    }

            } else if (value == 2) {
                displayMessage("When asked please enter the values for the down payment, budgeted monthly payment, interest rate, and term in years.");
                displayMessage("Enter -1 at any prompt to exit the loan calculator.");
                Loan loan = new Loan();
                while (true) {
                    double downPayment = getDouble("Enter your down payment (or 0 if no down payment)", true);
                    if (downPayment == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setDownPayment(downPayment);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                while (true) {
                    double budgetedPayment = getDouble("Enter your budgeted monthly payment amount - (the amount you can pay each month)", true);
                    if (budgetedPayment == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setBudgetedPayment(budgetedPayment);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                while (true) {
                    double annualInterestRate = getDouble("Enter the annual interest rate - (as a percentage e.g., 5% would be 5)", true);
                    if (annualInterestRate == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setAnnualRate(annualInterestRate);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                while (true) {
                    int termInYears = getInt("Enter the loan term in years", true);
                    if (termInYears == -1){
                        loanGoodbye();
                        return;
                    }
                    try {
                        loan.setTermYears(termInYears);
                        break;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
                double affordableLoan = loan.calculateAffordableLoan();
                double totalPrice = affordableLoan + loan.getDownPayment();
                System.out.print("\nBudgeted Loan Amount: " + Helpers.toCurrency(affordableLoan));
                System.out.print("\nTotal Price You Can Afford (Loan + Down Payment): " +
                                    Helpers.toCurrency(totalPrice) + "\n");
                pressEnterToContinue();
            } else {
                loanGoodbye();
                pressEnterToContinue();
                break;
            }
        }

    }
}
