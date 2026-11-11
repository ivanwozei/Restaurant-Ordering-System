   // class that creates the menu items as objects split into drinks and food using subclasses
public abstract class Item {

    private final String name;
    private final double price;
      // main constructor
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
// getters
    public String getName()  { 
        return name;
     }

    public double getPrice() { 
        return price;
     }
   
}
 // food subclasses
class Food extends Item {
      // constructor for food items 
    public Food(String name, double price) {
        // calling parent constructor
        super(name, price);
    }
    // array list of food objects
    private static final Food[] foods = {  
              
        new Food("Chicken ", 15000),
        new Food("Chips", 8000),
        new Food("Pizza", 20000),
        new Food("Burger", 12000)

    };
       // counts number of objects in food array
     public static int count(){
         return foods.length;
         }
        // method to get specific object in array
    public static Food get(int index) {
         return foods[index]; 
        }

    }
          // Drink subclass
class Drink extends Item {
 // construtor that calls parent constructor
    public Drink(String name, double price) {
        super(name, price);
    }
    // array list with drink objects
    private static final Drink[] drinks = {
        new Drink("Soda", 3000),
        new Drink("Water", 2000),
        new Drink("Juice", 5000)
    };

         public static int count(){
            return drinks.length;
        }

   public static Drink get(int index) { 
        return drinks[index]; 
    }
        
}