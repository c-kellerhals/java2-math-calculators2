
package edu.kirkwood.controller.makayla;

import edu.kirkwood.model.makayla.RespiratoryHistory;
import edu.kirkwood.model.makayla.RespiratoryMeasurement;
import edu.kirkwood.model.makayla.RespiratoryRate;
import edu.kirkwood.view.Messages;

import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.*;

import java.time.Instant;

public class RespiratoryRateCalculator {

    private static final String INVALID_BREATHS_INPUT_MESSAGE =
            "Breaths cannot be negative. Enter a whole number, or Enter 0 to quit.";
    private static final String INVALID_BREATHS_FORMAT_MESSAGE =
            "Enter a whole number of breaths, such as 12.";
    private static final String INVALID_TIME_INPUT_MESSAGE =
            "Observation time must be greater than 0 seconds.";
    private static final String INVALID_TIME_FORMAT_MESSAGE =
            "Enter a whole number of seconds, such as 60.";

    private final RespiratoryHistory history = new RespiratoryHistory();

    /**
     * Calculates the respiratory rate from the number of breaths
     * and observation time.
     *
     * @param breaths         The number of breaths counted
     * @param observationTime The observation time in seconds
     * @return The respiratory rate in breaths per minute
     */
    public double calculateRate(int breaths, int observationTime) {
        return new RespiratoryRate(breaths, observationTime)
                .calculateRate();
    }

    /**
     * Starts the respiratory rate calculator and handles user input.
     */
    public void start() {
        Messages.respiratoryGreet();
        boolean running = true;
        while (running) {
            int breaths = -1;
            while (breaths < 0) {
                try {
                    breaths = Integer.parseInt(getString(
                            "Enter the number of breaths counted (0 to quit and view history):"
                    ));
                    if (breaths < 0) {
                        displayError(INVALID_BREATHS_INPUT_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    displayError(INVALID_BREATHS_FORMAT_MESSAGE);
                    breaths = -1;
                }
            }
            if (breaths == 0) {
                running = false;
                continue;
            }
            int observationTime = 0;
            while (observationTime < 1) {
                try {
                    observationTime = Integer.parseInt(getString(
                            "Enter the number of seconds counted (e.g., 60):"
                    ));
                    if (observationTime < 1) {
                        displayError(INVALID_TIME_INPUT_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    displayError(INVALID_TIME_FORMAT_MESSAGE);
                    observationTime = 0;
                }
            }
            try {
                RespiratoryRate respiratoryRate =
                        new RespiratoryRate(breaths, observationTime);
                RespiratoryMeasurement measurement =
                        new RespiratoryMeasurement(
                                respiratoryRate,
                                Instant.now()
                        );
                history.recordMeasurement(measurement);
                System.out.printf(
                        "%nRespiratory Rate: %.2f breaths per minute%n",
                        respiratoryRate.calculateRate()
                );
                pressEnterToContinue();
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
            }
        }
        displayHistory();
        Messages.respiratoryGoodbye();
    }

    private void displayHistory() {
        int measurementCount = history.getMeasurementCount();
        if (measurementCount > 0) {
            System.out.println("\n--- Respiratory Measurement History ---");
            System.out.println("Total Measurements: " + measurementCount);
            System.out.printf(
                    "Average Rate: %.2f breaths per minute%n",
                    history.calculateAverage()
            );
            System.out.printf(
                    "Highest Rate: %.2f breaths per minute%n",
                    history.getHighestRate()
            );
            System.out.printf(
                    "Lowest Rate: %.2f breaths per minute%n",
                    history.getLowestRate()
            );
            System.out.printf(
                    "Standard Deviation: %.2f breaths per minute%n",
                    history.calculateStandardDeviation()
            );
            if (measurementCount >= 2) {
                try {
                    double trend = history.calculateTrend();
                    System.out.printf("Rate of Change: %.2f%n", trend);
                    if (trend > 0) {
                        System.out.println("Trend Direction: Increasing");
                    } else if (trend < 0) {
                        System.out.println("Trend Direction: Decreasing");
                    } else {
                        System.out.println("Trend Direction: Steady");
                    }
                } catch (IllegalStateException e) {
                    displayError(e.getMessage());
                    System.out.println(
                            "Respiratory Rate Trend: Not available."
                    );
                }
            } else {
                System.out.println(
                        "Respiratory Rate Trend: Not available (at least two measurements required)."
                );
            }
            System.out.println("\n--- Recorded Rates ---");
            int measurementNumber = 1;
            for (RespiratoryMeasurement measurement : history.getMeasurements()) {
                System.out.printf(
                        "Measurement %d: %.2f breaths per minute%n",
                        measurementNumber,
                        measurement.getRespiratoryRate().calculateRate()
                );
                measurementNumber++;
            }
            pressEnterToContinue();
        }
    }
}