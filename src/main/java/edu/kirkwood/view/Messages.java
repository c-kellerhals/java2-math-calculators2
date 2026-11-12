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
  
    /**
     * Displays the retirement calculator welcome message.
     */
    public static void retirementGreet() {
        displayMessage("Welcome to the Retirement Calculator");
        System.out.println("Plan for a secure future by estimating your retirement balance.");
    }

    /**
     * Displays retirement plan guidance and key terms.
     */
    public static void retirementTerms() {
        System.out.println("401(k): A retirement savings account sponsored by an employer.");
        System.out.println("Contribution rate: The percentage of your salary you contribute each year.");
        System.out.println("Employer match: The percentage of your salary the employer matches.");
        System.out.println("Return rate: The expected annual investment growth rate.");
    }

    /**
     * Displays the retirement calculator farewell message.
     */
    public static void retirementGoodbye() {
        displayMessage("Thank you for using the Retirement Calculator");
    }
  
    /*
     * Displays instructions for using Bailey's Astrophotography OED calculator.
     */
    public static void astroGreet() {
        displayMessage("Welcome to Bailey's Astrophotography OEV calculator!");
        System.out.println("Astrophotography is the practice of photographing objects deep in space, such as galaxies, nebulae, and star clusters.\n" +
                "This calculator accounts for your exposure settings, information about your camera and telescope, and\n" +
                "local light pollution, to produce an Overall-Exposure-Value.\n" +
                "'Overall-Exposure-Value' is a general, numeric indicator of how much light entered\n" +
                "the camera sensor throughout the entire imaging process.\n" +
                "Generally, a longer exposure time means a less-noisy, and more detailed image.\n" +
                "It also gives the standard exposure time, and a general 'quality rating' indicating how finished your final image is.\n" +
                "'Good' is a solid goal for most personal-images, and 'Excellent' is what you should aim for if you plan to print the image or sell it." +
                "\nWhat do each of these settings mean?\n" +
                "> Exposure Time: The amount of seconds that your camera shutter was open while taking each image.\n" +
                "       The final exposure time is calculated by multiplying the single-frame exposure time by the total amount of frames you captured.\n" +
                "> Light Frames: A light frame is a single image of the sky, usually around 15-60 seconds, or more. after a long night of imaging,\n" +
                "       hundreds of these are stacked together using special software, to create a final, blended image of extremely faint deep-sky-objects.\n" +
                "       Stacking the images cancelles out the random 'grain' or 'noise' in the images, but maintains the real pixel-data that's perfectly consistant across each image.\n" +
                "> F-stop: Also known as 'Aperture' - this is the value given to how wide the aperture is in your lens or telescope.\n" +
                "       The value is normally written as a fraction (ex: f/5.6), but this calculator just takes the denominator, in decimal form (ex: 5.6).\n" +
                "> Sensor Size: This is the size of your camera's sensor. A larger sensor has more surface area for light to hit it, and generally produces cleaner images.\n" +
                "       Options include 'micro' for Micro four-thirds, 'apsc' for APS-C, 'full' for Full-Frame, and 'medium' for Medium Format.\n" +
                "> Bortle Scale: The bortle scale is a scale that measures local light pollution in the sky. It's a number between 1 and 9, where\n" +
                "       9 is 'Middle of the city', and 1 is 'middle of the dessert at 2am'."
        );
        System.out.println("\nEnter each of the required values in the prompts below, in the specified format.");
        System.out.println("Enter '-1' at any time to Exit to Main Menu.\n");
    }

    /**
     * Displays instructions for using Bailey's Astrophotography OED calculator.
     */
    public static void astroGoodbye() {
        displayMessage("Thanks for using Bailey's Astro-Photo OEV Calculator!");
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
     * Displays instructions for using the Trigonometric Functions calculator.
     */
    public static void trigFuncGreet(){
        displayMessage("Welcome to Riona's Trigonometric Functions Calculator");
        System.out.println("Enter an angle value in degrees. Your value should contain only digits and decimal points.");
        System.out.println("Example: 60.5");
    }

    /**
     * Displays the Trigonometric Functions calculator's farewell message
     */
    public static void trigFuncGoodbye(){
        displayMessage("Thank you for using Riona's Trigonometric Functions Calculator");
    }

    /**
     * Prints a plain string with no asterisks.
     *
     * @param message The message to print to string
     */
    public static void showString(String message){
        System.out.println(message);
    }
    
    /**
     * Displays instructions and greeting for using the Doomsday calculator.
     */
    public static void doomsdayGreet() {
        displayMessage("Welcome to Evan's Doomsday Calculator");
        System.out.println("Using Conway’s Doomsday algorithm, this calculator determines the day of the week for " +
                "any given date and shows the steps used to calculate it.");
        System.out.println("Enter a date in the format Month/Day/Year. For example, enter 4/4/2021 or 1/1/1.");
    }

    /**
     * Displays the Doomsday calculator's farewell message.
     */
    public static void doomsdayGoodbye() {
        System.out.println();
        displayMessage("Thank you for using Evan's Doomsday Calculator");
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

