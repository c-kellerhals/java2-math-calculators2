package edu.kirkwood.controller.evan;

import edu.kirkwood.model.evan.DoomsdayCalculator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static edu.kirkwood.view.Messages.doomsdayGoodbye;
import static edu.kirkwood.view.Messages.doomsdayGreet;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getString;

public class DoomsdayCalculatorController {
    public static final String INVALID_FORMAT = "The format you entered is not in the format of 'Month/Day/Year'";
    public static final String INVALID_DATE = "The date you entered is invalid.";
    public static final String INVALID_YEAR = "The year cannot be less than 1.";
    public static final String INVALID_MONTH = "The month must be between 1 and 12.";
    public static final String INVALID_DAY = "The day is not valid for the specified month and year.";

    /**
     * Starts the Doomsday Calculator and handles user input.
     */
    public static void start() {
        System.out.println();
        doomsdayGreet();

        while (true) {
            try {
                String dateStr = getDate();

                if (dateStr.isEmpty()) {
                    break;
                }

                LocalDate date = parseDate(dateStr);
                DoomsdayCalculator dc = new DoomsdayCalculator(date);
                System.out.println();
                System.out.println(dc.getCenturyAnchorDayCalculationSteps());
                System.out.println();
                printLine();
                System.out.println();
                System.out.println(dc.getCalculationSteps());
                System.out.println();
                printLine();
                System.out.println(dc.calculateDateWeekday());
                printLine();

            } catch (DateTimeParseException e) {
                displayError(e.getMessage());
            }
        }

        doomsdayGoodbye();
        pressEnterToContinue();
    }

    /**
     * Gets a date string from the user.
     *
     * @return the date string entered by the user.
     */
    public static String getDate() {
        System.out.println();
        return getString("Enter a date (or press Enter to quit) [Month/Day/Year]", true);
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr the string input to parse
     * @return the parsed date
     * @throws DateTimeParseException if the input format, year, month, or day is invalid
     */
    public static LocalDate parseDate(String dateStr) {
        String[] parts = dateStr.split("/", -1);

        if (parts.length != 3) {
            throw new DateTimeParseException(INVALID_FORMAT, dateStr, 0);
        }

        int month;
        int day;
        int year;

        try {
            month = Integer.parseInt(parts[0]);
            day = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new DateTimeParseException(INVALID_FORMAT, dateStr, 0);
        }

        if (year < 1) {
            throw new DateTimeParseException(INVALID_YEAR, dateStr, 0);
        }

        if (month < 1 || month > 12) {
            throw new DateTimeParseException(INVALID_MONTH, dateStr, 0);
        }

        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new DateTimeParseException(INVALID_DAY, dateStr, 0);
        }
    }
}