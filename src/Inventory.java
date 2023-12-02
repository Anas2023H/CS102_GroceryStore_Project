

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> productList;

    public Inventory() {
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public static Product findProductByName(Inventory inventory, String name) {
        for (Product product : inventory.getProductList()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

}