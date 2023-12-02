
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class GroceryStoreApp {

    public static void main(String[] args) throws IOException {
        // Load products from the file
        Inventory inventory = loadProducts("ProductList.txt");

        // Get the number of customers to process
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of customers to process: ");
        int numCustomers = getValidQuantity(scanner);

        // Process customers recursively
        ArrayList<Customer> customers = new ArrayList<>();
        processCustomers(inventory, customers, numCustomers);

        // Close the scanner
        scanner.close();
    }

    private static void processCustomers(Inventory inventory, ArrayList<Customer> customers, int remainingCustomers) {
        if (remainingCustomers <= 0) {
            System.out.println("Processing complete.");
            return;
        }

        // Example customer
        Sale customer = new Sale();

        System.out.println("Available products:");
        for (Product product : inventory.getProductList()) {
            System.out.println(product);
        }

        try ( // Interaction loop
                Scanner scanner = new Scanner(System.in)) {
            String userInput;
            do {
                // User input
                System.out.print("Enter a product name to add to your cart (or type 'checkout' to complete your purchase): ");
                userInput = scanner.next();
                
                // Inside the do-while loop in processCustomers method
        if (!userInput.equalsIgnoreCase("checkout")) {
            // Find the product in the inventory
            Product selectedProduct = findProductByName(inventory, userInput);

            if (selectedProduct != null) {
                // Ask for quantity
                System.out.print("Enter the quantity: ");
                int amount = scanner.nextInt();
                
                if(amount<=selectedProduct.getQuantityInStock()){
                     selectedProduct.setQuantityWanted(amount);

                // Consume the newline character
                scanner.nextLine();

                // Add to the cart

                if(selectedProduct.getQuantityWanted()<=selectedProduct.getQuantityInStock()){
                    customer.addToCart(selectedProduct, selectedProduct.getQuantityWanted());
                    selectedProduct.setQuantityInStock(selectedProduct.getQuantityInStock() - selectedProduct.getQuantityWanted()); // Set the quantity in the cart
                    System.out.println("ya");
                }
                else System.out.println("Not enough of this product in stock please buy a valid amount");
                
            } 
            else {
                 System.out.println("Product not found. Please enter a valid product name.");
            }
            }
        }    
            } while (!userInput.equalsIgnoreCase("checkout"));
            // Set the name for the Sale
            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();
            customer.setName(customerName);
            // Cart checks out
            customers.add(customer);
            customer.checkout(inventory, customers);
            // Recursive call for the next customer
            processCustomers(inventory, customers, remainingCustomers - 1);
            // Close the scanner
        }
    }
    
    private static Inventory loadProducts(String filename) throws IOException {
        Inventory inventory = new Inventory();

        try (Scanner scanner = new Scanner(new FileInputStream(new File(filename)))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);

                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    product.setQuantityInStock(quantity);

                    inventory.addProduct(product);
                }
            }

        } catch (IOException e) {

        }

        return inventory;
    }

    private static Product findProductByName(Inventory inventory, String name) {
        for (Product product : inventory.getProductList()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    private static int getValidQuantity(Scanner scanner) {
        int quantity;
        while (true) {
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid positive quantity.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return quantity;
    }
}