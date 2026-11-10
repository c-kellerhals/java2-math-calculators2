package edu.kirkwood.controller.masean;

import static edu.kirkwood.model.masean.GradePointAverage.*;
import static edu.kirkwood.view.Helpers.round;
import static edu.kirkwood.view.Messages.GPAGoodbye;
import static edu.kirkwood.view.Messages.GPAGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;


public class GPACalculator {
    public static void start() {
        GPAGreet();
        while(true) {
            // Get input for number of classes
            int numClasses = getInt("Enter the amount of classes [Must be above 0] (press 0 to quit)");
            if (numClasses == 0) {
                break;
            }

//            try {
//                numClasses = Integer.parseInt(value);
//            } catch (NumberFormatException e) {
//                displayError("Invalid input. Please enter a valid number of classes.");
//                continue;
//            }



            if (numClasses <= 0) {
                displayError("Number of classes cannot be lower than 1.");
                continue;
            }

            System.out.println(" ");

            String[] grades = new String[numClasses];
            int[] credits = new int[numClasses];
            double[] numericGrades = new double[numClasses];

            for (int i = 0; i < grades.length; i++) {
                while (true) {
                    String grade = getString("Enter your grade for class " + (i + 1) + " (Must be a valid letter grade)");

                    if (grade.matches("[-+]?\\\\d+(\\\\.\\\\d+)?")) {
                        displayError("Invalid input. Please enter a valid letter grade.");
                        continue;
                    }

                    Double gradePoint = numberGrades.get(grade.toUpperCase());
                    if (gradePoint == null) {
                        displayError("Invalid grade. Please enter a valid letter grade (A, B, C, D, F).");
                        continue;
                    }

                    grades[i] = grade;
                    numericGrades[i] = gradePoint;
                    break;
                }




                while (true) {
                    try {
                        credits[i] = getInt("Enter the number of credits for class " + (i + 1) + " (Must be above 0)");
                        if (credits[i] <= 0) {
                            displayError("Number of credits cannot be lower than 1.");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        displayError("Invalid input. Please enter a valid number of credits.");
                    }
                }
                System.out.println(" ");

//                credits[i] = getInt("Enter the number of credits for class " + (i+1) + " (Must be above 0)");
//                if (numericGrades[i] < 0.0 || numericGrades[i] > 4.3) {
//                    displayError("Invalid Grade. Please enter a grade between 0.0 and 4.3.");
//                    i--;
//                }
//                if (credits[i] <= 0) {
//                    displayError("Number of credits cannot be lower than 1.");
//                    i--;
//                }

            }


            // prompt for goal gpa
            double goal = 0.0;
            goal = promptForGoalGPA();


            // display the result
//            System.out.println(" your inputted grades are: " + java.util.Arrays.toString(grades));
//            System.out.println(" your inputted credits are: " + java.util.Arrays.toString(credits));

            double gpa = calculateGPA(numericGrades, credits);
            System.out.println("Your current GPA is: " + round(gpa, 2));

            double gpaDifference = goal - gpa;
            if (gpaDifference > 0) {
                System.out.println("You are: " + round(gpaDifference, 2) + " points away from your goal.");
            }else {
                System.out.println("You have reached your goal GPA!");
            }
        }


        GPAGoodbye();
        pressEnterToContinue();
    }






}
