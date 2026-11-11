       // class that mainly has mainly methods for printing items, displaying the objects as menu at once  
       //and method to obtain object using numbers

public class menu {
      // method that prints that accepts object and prints details, number parameter is for numbering and will be used for selection later on
private static void printItem(int number, Item item) {
        System.out.println(number + ". " + item.getName()+ " @UGX " +  item.getPrice());
    }
     // method that prints all items from both arrays as one list using for loops
    public static void displayMenu() {
         // numbering starting from one
        int number = 1;

        System.out.println("===== MENU =====");
           // prints all food objects using printitem method then icreases number by one
        System.out.println("Food:");
        for (int i = 0; i < Food.count(); i++) {
            printItem(number, Food.get(i));
            number++;
        }
        // prints all drink objects but number continues from where it stopped in previous for loop
        System.out.println("Drinks:");
        for (int i = 0; i < Drink.count(); i++) {
            printItem(number, Drink.get(i));
            number++;
        }
    }
              
    public static Item getItem(int choice) {
        // 1. method that returns object using get method from item class which will be
              //  called when user selects an item from the displayed menu
              // 2. choice - 1 is used to align display menu numbering to index numbering of
              //  arrays because zero will be used for another purpose
        if (choice <= Food.count()) {
            return Food.get(choice - 1);
          
        } else { 
            return Drink.get(choice - Food.count() - 1);
            // choice - food count because all food items are printed 1st so it will be like starting 
          // from 1 for drinks then -1 is for aligning the numbering and array indexing
        }
    }
    
}
