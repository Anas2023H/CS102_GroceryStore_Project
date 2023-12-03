
import java.util.ArrayList;
import java.util.List;
// This class represents a shopping cart that is then filled with the customers products
public class Cart extends Customer implements CartInterface  {
    private ArrayList<Product> cart;
    

    public Cart(ArrayList<Product> cart, String name) {
        super(name);
        this.cart = cart;
    }
    

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
    
    

    public void addToCart(Product product, int quantity) {
        // Check if the product is in stock
        if (product.getQuantityInStock() >= quantity) {
            // Check if the product is already in the cart
            boolean productInCart = false;
            for (Product cartProduct : cart) {
                if (cartProduct.getName().equalsIgnoreCase(product.getName())) {
                    productInCart = true;
//                    cartProduct.setQuantityInStock(cartProduct.getQuantityInStock() + quantity);
                    break;
                }
            }

           



            // If the product is not in the cart, add it
            if (!productInCart) {
                // Use the existing product from the inventory
                cart.add(product);

                
            }

            System.out.println(quantity + " " + product.getName() + " added to your cart.");
        } else {
            System.out.println("Sorry, not enough stock for " + product.getName() + ".");
        }
        

    }


    public void displayCart() {
        System.out.println("Your Cart:");
        for (Product product : cart) {
            System.out.println(product);
        }
    }

    
}