package edu.kirkwood.view;

import edu.kirkwood.controller.issouf.RetirementCalculator;
import edu.kirkwood.controller.marc.FractionCalculator;
import edu.kirkwood.controller.ryan.LoanCalculator;
import edu.kirkwood.controller.noah.InterestCalculatorController;
import edu.kirkwood.controller.masean.GPACalculator;
import edu.kirkwood.controller.evan.DoomsdayCalculatorController;
import edu.kirkwood.controller.xavian.HalfLifeCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {
    /**
     * Displays the main menu and processes selections until the user quits.
     */
    public static void show() {
        String[] menuItems = {
            "Marc's Fraction Calculator",
            "Xavian's Half Life Calculator",
            "Masean's GPA Calculator",
            "Noah's Interest Calculator",
            "Ryan's Amortization Loan Calculator", 
            "Bailey's Astro OEV Calculator", 
            "Issouf's Retirement Calculator", 
            "Evan's Doomsday Calculator",
            "Quit"
          };
        while(true) {
            printMenu("Main Menu", menuItems);
            int choice = getInt("Choose an option", true, 1, menuItems.length);
            switch(choice) {
                case 1:
                    FractionCalculator.start();
                    break;
                case 2:
                    HalfLifeCalculator.start();
                    break;
                case 3:
                    GPACalculator.start();
                    break;
                case 4:
                    InterestCalculatorController.start();
                    break;
                case 5:
                    LoanCalculator.start();
                    break;
                case 6:
                    edu.kirkwood.controller.bailey.AstroOEVCalculator.start();
                    break;
                case 7:
                    RetirementCalculator.start();
                    break;
                case 8:
                    DoomsdayCalculatorController.start();
                    break;
                default:
                    return;
            }
        }
    }
}
