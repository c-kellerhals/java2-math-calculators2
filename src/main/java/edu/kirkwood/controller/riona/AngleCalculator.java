package edu.kirkwood.controller.riona;

import edu.kirkwood.model.riona.Angle;
import edu.kirkwood.view.*;

import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.*;
import edu.kirkwood.view.Messages.*;

import java.text.DecimalFormat;

public class AngleCalculator {

    private static final String FORMAT_BLANK_MESSAGE = "Invalid format: Your angle must contain a number";
    private static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format: Your angle must contain only numbers and decimal points.";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.####");

    public static void start(){
        Messages.trigFuncGreet();
        boolean quit = false;

        do {
            String angleString = getString("Enter your angle or 'q' to quit");

            try {
                if(angleString.equalsIgnoreCase("q") || angleString.equalsIgnoreCase("quit")){
                    quit = true;
                } else {
                    Angle anAngle = parseAngle(angleString);
                    quit = calculationsMenu(anAngle);
                }
            } catch (Exception e) {
                displayError(e.getMessage());
                continue;
            }

        } while(!quit);
        Messages.trigFuncGoodbye();
    }

    private static boolean calculationsMenu(Angle anAngle){
        boolean quit = false;
        boolean bigquit = false;
        String[] menuItems = {"Sine", "Cosine", "Tangent", "All", "Compare with another angle", "Enter a new angle value","Quit"};
        while (!quit) {
            printMenu("Trigonometry Functions", menuItems);
            int choice = getInt("Choose an operation:", false, 1, menuItems.length);
            switch (choice) {
                case 1:
                    sine(anAngle);
                    pressEnterToContinue();
                    break;
                case 2:
                    cosine(anAngle);
                    pressEnterToContinue();
                    break;
                case 3:
                    tangent(anAngle);
                    pressEnterToContinue();
                    break;
                case 4:
                    allFunctions(anAngle);
                    pressEnterToContinue();
                    break;
                case 5:
                    compare(anAngle);
                    pressEnterToContinue();
                    break;
                case 6:
                    quit = true;
                    break;
                case 7:
                    bigquit = true;
                    quit = true;
                    break;
                default:
                    System.out.println("Not a valid input.");
                    pressEnterToContinue();
                    break;
            }
        }
        return bigquit;
    }

    private static void sine(Angle anAngle){
        String msgString = "Sine of (" + DECIMAL_FORMAT.format(anAngle.getValueDegrees()) + ") = ";
        msgString += DECIMAL_FORMAT.format(anAngle.calculateSine());
        Messages.showString(msgString);
    }

    private static void cosine(Angle anAngle){
        String msgString = "Cosine of (" + DECIMAL_FORMAT.format(anAngle.getValueDegrees()) + ") = ";
        msgString += DECIMAL_FORMAT.format(anAngle.calculateCosine());
        Messages.showString(msgString);
    }

    private static void tangent(Angle anAngle){
        Angle tempAngle = new Angle(anAngle.getValueDegrees());
        String msgString = "Tangent of (" + anAngle.getValueDegrees() + ") = ";
        Angle.convertToBaseAngle(tempAngle);
        if(tempAngle.getValueDegrees() == 90 || tempAngle.getValueDegrees() == 270){
            msgString += "Undefined";
        } else {
            msgString += DECIMAL_FORMAT.format(anAngle.calculateTangent());
        }
        Messages.showString(msgString);
    }

    private static void allFunctions(Angle anAngle){
        sine(anAngle);
        cosine(anAngle);
        tangent(anAngle);
    }

    private static void compare(Angle anAngle){
        String msgString = "";
        String comparator = "";
        String[] listItems = {
                "On an infinite domain, ",
                "On domain [0°, 360°), ",
                "Sine: ",
                "Cosine: ",
                "Tangent: "
        };
        String angleA = DECIMAL_FORMAT.format(anAngle.getValueDegrees());
        String angleAAbsolute = DECIMAL_FORMAT.format(anAngle.returnBaseAngleValue());

        String angleString = getString("Enter an angle value in degrees. Your value should contain only digits and decimal points.");
        Angle otherAngle = parseAngle(angleString);
        String angleB = DECIMAL_FORMAT.format(otherAngle.getValueDegrees());
        String angleBAbsolute = DECIMAL_FORMAT.format(otherAngle.returnBaseAngleValue());

        if(anAngle.getValueDegrees() == otherAngle.getValueDegrees()){
            Messages.showString("These angles are equal.");
        } else {
            // compare real angle values
            comparator = getComparator(anAngle.compareToReal(otherAngle));
            msgString = "Angle A (" + angleA +
                    "°) " + comparator + " Angle B (" + angleB + "°)";
            listItems[0] += msgString;

            // compare absolute angle values
            comparator = getComparator(anAngle.compareToAbsolute(otherAngle));
            msgString = "Angle A (" + angleAAbsolute +
                    "°) " + comparator + " Angle B (" + angleBAbsolute + "°)";
            listItems[1] += msgString;

            // compare sine values
            comparator = getComparator(anAngle.compareSine(otherAngle));
            msgString = DECIMAL_FORMAT.format(anAngle.calculateSine())
                    + " " + comparator + " " +
                    DECIMAL_FORMAT.format(otherAngle.calculateSine());
            listItems[2] += msgString;

            // compare cosine values
            comparator = getComparator(anAngle.compareCosine(otherAngle));
            msgString = DECIMAL_FORMAT.format(anAngle.calculateCosine())
                    + " " + comparator + " " +
                    DECIMAL_FORMAT.format(otherAngle.calculateCosine());
            listItems[3] += msgString;

            // compare tangent values
            if(anAngle.calculateTangent() == 999 || otherAngle.calculateTangent() == 999){
                msgString = "Undefined";
            } else {
                comparator = getComparator(anAngle.compareTangent(otherAngle));
                msgString = DECIMAL_FORMAT.format(anAngle.calculateTangent())
                        + " " + comparator + " " +
                        DECIMAL_FORMAT.format(otherAngle.calculateTangent());
            }
            listItems[4] += msgString;

            printList(listItems);
        }
    }

    private static String getComparator(int relation){
        String comparator = "";
        if(relation > 0){
            comparator = ">";
        } else if (relation == 0){
            comparator = "=";
        } else {
            comparator = "<";
        }
        return comparator;
    }

    private static Angle parseAngle(String angleString) {
        Angle parsedAngle = null;

        try {
            if (angleString.isBlank() || angleString.isEmpty()) {
                throw new IllegalArgumentException(FORMAT_BLANK_MESSAGE);
            }
            double parsedAngleValue = Double.parseDouble(angleString);
            parsedAngle = new Angle(parsedAngleValue);
        } catch (NumberFormatException nfex){
            throw new NumberFormatException(FORMAT_EXCEPTION_MESSAGE);
        } catch (Exception e) {
            throw e;
        }

        return parsedAngle;
    }



}
