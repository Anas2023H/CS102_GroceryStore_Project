import java.util.ArrayList;

public class Sale extends Cart {
    private double totalPrice;

    public Sale() {
        super(new ArrayList<>(), ""); // Provide an empty cart and a placeholder name
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (!(totalPrice == 0)) {
            this.totalPrice = totalPrice;
        } else {
            throw new IllegalArgumentException("Sale was canceled");
        }
    }

    public void checkout(Inventory inventory, ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customers to process.");
            return;
        }

        // Process the first customer in the list
        Cart currentCustomer = (Cart)customers.get(0);
        setName(currentCustomer.getName());
        setCart(currentCustomer.getCart());

        System.out.println("Invoice for " + getName() + ":");
        for (Product cartProduct : getCart()) {
            System.out.println(cartProduct.getName() + " - Price: $" + cartProduct.getPrice() * cartProduct.getQuantityWanted() +
                    " - Quantity bought: " + cartProduct.getQuantityWanted());

            totalPrice += cartProduct.getPrice() * cartProduct.getQuantityWanted();

        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Total Price: " + getTotalPrice() + "$");

        // Clear the cart
        getCart().clear();

        // Remove the processed customer
        customers.remove(0);

        // Recursive call for the next customer
        checkout(inventory, customers);
    }
}