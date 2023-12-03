
import java.util.ArrayList;
import java.util.Scanner;

// This class represents the customer, each Customer must have a name that is made up of letters only
public class Customer {
    private String name;

    public Customer(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

   public void setName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))) {
                throw new IllegalArgumentException("Invalid Name, must only have letters.");
            }
        }
        this.name = name;
    }
     
}
