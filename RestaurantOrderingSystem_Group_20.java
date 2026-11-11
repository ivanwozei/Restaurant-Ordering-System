// GROUP 20: Restaurant Ordering System
//Members:
// 1. 26/U/0315     - OKALEBO ALVIN JOSEPH
// 2. 26/U/04033/PS - NAKITENDE BUSHILLAH
// 3. 26/U/03574/PS - MUNGOMA ISAAC TREVOR
// 4. 26/U/03486/PS - ANKUNDA JOSEPH BETONDE
// 5. 26/U/03671/PS - WOZEI IVAN DAVID

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;

public class RestaurantOrderingSystem_Group_20 {
    // Keeps the original output stream so we can restore it after capturing menu/receipt text.
    private static final PrintStream originalOut = System.out;
    // Stores printed output temporarily before showing it in a Swing dialog.
    private static ByteArrayOutputStream buffer;

    // Opens a Swing input dialog, reads the user response, and converts it to an integer.
    // If the input is blank, invalid, or the dialog is cancelled, the method returns -1
    // so the calling code can show an error message or exit the program.
    private static int askNumber(String message) {
        String text = JOptionPane.showInputDialog(null, message);
        if (text == null) {
            System.exit(0); // User pressed Cancel: close the order system.
        }
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Redirects the console output into a memory buffer.
    // This is useful when a menu or receipt is produced using print statements,
    // but we want to display that text inside a JOptionPane dialog instead of the terminal.
    private static void startCapture() {
        buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true));
    }

    // Stops capturing output and restores the normal console stream.
    // The method returns the text that was printed while capture was active.
    private static String stopCapture() {
        System.setOut(originalOut);
        return buffer.toString();
    }

    public static void main(String[] args) {
        int choice;
        // create order object and keep it null until the user selects dine-in or takeaway constructor.
        Order order = null;

        // Ask the user whether the order is for dine-in or takeaway.
        // The loop keeps repeating until the user enters a valid option.
        while (true) {
            choice = askNumber("Welcome!\nPlease select 1 or 2 for order type:\n1. Dine in\n2. Takeaway");
            if (choice == 1 || choice == 2) {
                switch (choice) {
                    case 1:
                        // assign order as DineInOrder object if the user selects dine-in.
                        order = new DineInOrder();
                        break;
                    case 2:
                        // assign order as TakeawayOrder object if the user selects takeaway.
                        order = new TakeawayOrder();
                        break;
                }
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1 or 2.");
            }
        }

        // Capture the printed menu text so it can be shown in a dialog box.
        // "0. Checkout" is added so the user can finish ordering.
        startCapture();
        menu.displayMenu();
        String menuText = stopCapture() + "0. Checkout\n";

        // Main order loop that keeps asking for an item until the user chooses checkout.
        while (true) {
            choice = askNumber(menuText + "Enter your choice:");
            // Zero will be used by the user to exit the item selecting loop and checkout.
            if (choice == 0) {
                break; // Exit item-selection loop when user checks out.
            }
            // prevents selection of invalid menu items.
            if (choice < 0 || choice > Food.count() + Drink.count()) {
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                continue;
            }

            // Ask for the quantity before adding anything to the order with a maximum of 100.
            int qty;
            while (true) {
                qty = askNumber("Enter quantity:");
                if (qty <= 0 || qty > 100) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid number.");
                    continue;
                } else {
                    break;
                }
            }

            // Add the chosen item and quantity to the  order array selecting using get item method and copying using additem method.
            order.addItem(menu.getItem(choice), qty);
            JOptionPane.showMessageDialog(null, "Added to order.");
        }

        // Show the receipt if the order has items, otherwise confirm the user left empty-handed using boolean value of isEmpty method.
        if (order.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items ordered. Goodbye!");
        } else {

            startCapture();
             order.printReceipt();
            JOptionPane.showMessageDialog(null, stopCapture());
        }
    }
}