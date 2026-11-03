package edu.kirkwood.view;

public class UIUtility {

    /**
     * Displays a message without a message type label.
     *
     * @param message message text to display
     */
    public static void displayMessage(String message) {
        displayMessage(message, "");
    }

    /**
     * Displays a message with an optional uppercase type label.
     *
     * @param message message text to display
     * @param type label that identifies the message type, or an empty string for no label
     */
    public static void displayMessage(String message, String type) {
        System.out.printf("*** %s%s ***\n", (!type.equals("") ? type.toUpperCase() + " - " : ""), message);
    }

    /**
     * Displays a message labeled as an error.
     *
     * @param message error text to display
     */
    public static void displayError(String message) {
        displayMessage(message, "error");
    }

    /**
     * Displays a message labeled as a warning.
     *
     * @param message warning text to display
     */
    public static void displayWarning(String message) {
        displayMessage(message, "warning");
    }

    /**
     * Displays a message labeled as a success.
     *
     * @param message success text to display
     */
    public static void displaySuccess(String message) {
        displayMessage(message, "success");
    }

    /**
     * Prompts the user to press Enter before continuing.
     */
    public static void pressEnterToContinue() {
        UserInput.getString("Press enter to continue", false);
    }

    /**
     * Prints a horizontal line of 40 hyphens.
     */
    public static void printLine() {
        printLine(40);
    }

    /**
     * Prints a horizontal line made of the requested number of hyphens.
     *
     * @param length number of hyphens to print
     */
    public static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints a numbered menu enclosed by horizontal lines.
     *
     * @param title menu title to display
     * @param menuItems items to number and display
     */
    public static void printMenu(String title, String[] menuItems) {
        System.out.println();
        printLine();
        displayMessage(title);
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ") " + menuItems[i]);
        }
        printLine();
    }

    /**
     * Prints the Strings from a provided array in a list format.
     *
     * @param listItems The array of Strings to print
     */
    public static void printList(String[] listItems) {
        System.out.println();
        for(int i = 0; i < listItems.length; i++){
            System.out.println("\t" + listItems[i]);
        }
    }

    /**
     * Creates a table separator using the supplied column widths.
     *
     * @param columnWidths widths of the table columns
     * @return a separator string with a bordered segment for each column
     */
    public static String separator(int[] columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int width : columnWidths) {
            sb.append("+ ");
            for (int i = 0; i < width; i++) {
                sb.append('-');
            }
            sb.append(' ');
        }
        sb.append("+");
        return sb.toString();
    }
}
