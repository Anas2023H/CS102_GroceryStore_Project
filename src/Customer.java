
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tarek
 */
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
