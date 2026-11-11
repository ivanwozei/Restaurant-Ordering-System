// orderitem class is used to copy objects from Item class and store them in a blank array as order objects 
class OrderItem {
    private final String name;
    private final double unitPrice;
    private final int quantity;


    // copy constructor that copies objecs from item class and then adds on quantity attribute
    public OrderItem(Item item, int quantity) {
        this.name = item.getName();
        this.unitPrice = item.getPrice();
        this.quantity = quantity;
    }
  // getters
    public String getName(){
         return name;
         }
    public int getQuantity(){
         return quantity;
 }
 // method to claculate total for one item
    public double getTotalPriceforItem(){
         return unitPrice * quantity; 
          }
}

abstract class Order {

    // creating blank array list that will store the copied objects when the user selects from menu
    // 15 is the maximum number of orders that can be taken i.e the number of slots in the orderitems array.
    private OrderItem[] orderItems = new OrderItem[15];

    // counter for number of copied objects as orders
    // counter will also be used to act as a stopper when making loops to avoid looping through blank slots
    private int count = 0;



// method that accepts object and quantity then calls constructor to copy and store in array
public void addItem(Item item, int quantity) {    
    if (count == orderItems.length) {

        System.out.println("Order is full.");
        return;
    }else{
        // storing of copied object in order array
    orderItems[count] = new OrderItem(item, quantity);
    count = count + 1 ;
}
}
  
 // method to return boolean value to create method for scenario when no order is made and 
    public boolean isEmpty() {
         return count == 0;
        }
// method to add the total price of all items added to the order array using for loop 
// and uses count as the stopper to avoid iterating through blank slots in array
    public double getSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < count; i++) {
            subtotal = subtotal + orderItems[i].getTotalPriceforItem();
        }
        return subtotal;
    }
// abstract methods to create methods that will be overriden by subclass for takeaway and dine in subclass accordingly 
    public abstract String getOrderType();
    public abstract double getServiceCharge(double subtotal); 


    // method that will print the details of objects of orderitem array as the receipt
 public void printReceipt() {
        System.out.println("===== RECEIPT (" + getOrderType() + ") =====");
        for (int i = 0; i < count ; i++) {
            OrderItem oi = orderItems[i];
            System.out.println(oi.getQuantity() + " x " + oi.getName() + " = UGX " + oi.getTotalPriceforItem());
        }

        double subtotal = getSubtotal();
        double charge = getServiceCharge(subtotal);
        System.out.println("Subtotal: UGX " + subtotal);
        System.out.println("Charge:   UGX " + charge);
        System.out.println("TOTAL:    UGX " + (subtotal + charge));
    }
}

// dineinorder and takeaway order subclasses to cverride an provide implementatiosns of the abstract methods
// to calculate the charge based on scenario
class DineInOrder extends Order {


    @Override
    public String getOrderType() { 
        return "Dine-in";
     }

    @Override
    public double getServiceCharge(double subtotal) {
        return subtotal * 0.1;                  
    }
}

class TakeawayOrder extends Order {
    @Override
    public String getOrderType() {
         return "Takeaway";
         }

    @Override
    public double getServiceCharge(double subtotal) {
        return (subtotal * 0.1) + 2000;                            
    }

}



