package edu.kirkwood.controller.clapton;


import edu.kirkwood.model.clapton.Number;

import static edu.kirkwood.model.clapton.Number.*;
import static edu.kirkwood.view.Helpers.*;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class BaseConversionCalculator {
    public static final String INVALID_NUMBER_BASE_ERROR = "Invalid number base. Please enter a base of 2, 8, or 10.";
    public static final String INVALID_NUMBER_ERROR = "Invalid number for the specified base. Please enter a valid number.";
    public static final String NUMBER_TOO_LARGE_ERROR = "The number is too large to convert. Please enter a smaller number.";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";

    public static void start() {
        baseConversionGreet();
        while (true) {
            Number userNumber = new Number();

            int fromBase = getInt("Enter your number base (2, 8, 10) or '-1' to quit: ", true);

            if (fromBase == -1) {
                break;
            }
            try {
                userNumber.setNumberBase(fromBase);
            } catch (IllegalArgumentException e) {
                displayError(INVALID_NUMBER_BASE_ERROR);
                continue;
            }
            while (true) {
                String numberValue;

                if (userNumber.getNumberBase() == 2) {
                    numberValue = getString("Enter your binary number [digits must be 0 through 1]: ", true);
                } else if (userNumber.getNumberBase() == 8) {
                    numberValue = getString("Enter your octal number [digits must be 0 through 7]: ", true);
                } else if (userNumber.getNumberBase() == 10) {
                    numberValue = getString("Enter your decimal number [digits must be 0 through 9]: ", true);
                } else {
                    displayError(INVALID_NUMBER_BASE_ERROR);
                    continue;
                }

                try {
                    Integer.parseInt(numberValue);
                    userNumber.setValue(numberValue);
                    break;
                } catch (NumberFormatException e) {
                    displayError(NUMBER_TOO_LARGE_ERROR);
                } catch (IllegalArgumentException e) {
                    displayError(INVALID_NUMBER_ERROR);
                }
            }

            String output = "";

            while (true) {
                int toBase = getInt("Enter the base to convert to (2, 8, 10): ", true);

                if (userNumber.getNumberBase() == toBase) {
                    output = "Base " + ANSI_BRIGHT_CYAN + toBase + ANSI_RESET + " = " +
                            ANSI_ORANGE + userNumber.getValue() + ANSI_RESET;
                    break;
                } else if (userNumber.getNumberBase() == 2 && toBase == 8) {
                    output = "Base" + ANSI_BRIGHT_CYAN + " 8 " + ANSI_RESET + "= " +
                            ANSI_ORANGE + binaryToOctal(userNumber.getValue()) + ANSI_RESET;
                    break;
                } else if (userNumber.getNumberBase() == 2 && toBase == 10) {
                    output = "Base" + ANSI_BRIGHT_CYAN + " 10 " + ANSI_RESET + "= " +
                            ANSI_ORANGE + binaryToDecimal(userNumber.getValue()) + ANSI_RESET;
                    break;
                } else if (userNumber.getNumberBase() == 8 && toBase == 2) {
                    output = "Base" + ANSI_BRIGHT_CYAN + " 2 " + ANSI_RESET + "= " +
                            ANSI_ORANGE + octalToBinary(userNumber.getValue()) + ANSI_RESET;
                    break;
                } else if (userNumber.getNumberBase() == 8 && toBase == 10) {
                    output = "Base" + ANSI_BRIGHT_CYAN + " 10 " + ANSI_RESET + "= " +
                            ANSI_ORANGE + octalToDecimal(userNumber.getValue()) + ANSI_RESET;
                    break;
                } else if (userNumber.getNumberBase() == 10 && toBase == 2) {
                    output = "Base" + ANSI_BRIGHT_CYAN + " 2 " + ANSI_RESET + "= " +
                            ANSI_ORANGE + decimalToBinary(userNumber.getValue()) + ANSI_RESET;
                    break;
                } else if (userNumber.getNumberBase() == 10 && toBase == 8) {
                    output = "Base" + ANSI_BRIGHT_CYAN + " 8 " + ANSI_RESET + "= " +
                            ANSI_ORANGE + decimalToOctal(userNumber.getValue()) + ANSI_RESET;
                    break;
                } else {
                    displayError(INVALID_NUMBER_ERROR);
                }
            }

            System.out.println(userNumber.toString() + " To " + output);
            pressEnterToContinue();
            try {
                clearScreenNonWindows();
                clearScreenWindows();
            } catch (Exception e) {
                displayError("Error occurred while clearing the screen.\nThis isn't your fault, but the program will continue. Error: " + e.getMessage());
            }

        }
        baseConversionGoodbye();
        pressEnterToContinue();
    }
}
