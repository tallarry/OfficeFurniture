/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.model;

import com.office.furniture.model.Customer;
import com.office.furniture.model.Discount;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Silvan
 */
public class CustomerTest {
    
    Customer c;
    
    public CustomerTest() {
        c = new Customer();
    }
    
    @Test
    public void testUsername() {
        c.setUsername("koko");
        assertEquals("koko", c.getUsername());
    }
    
    @Test
    public void testDiscountAdding() {
        ArrayList<Discount> disc = (ArrayList<Discount>) c.getDiscounts();
        Discount d = new Discount();
        d.setAmount(13);
        d.setCustomer(c);
        d.setName("offer");
        disc.add(d);
        c.addDiscount(d);
        assertEquals(disc, c.getDiscounts());
    }
}
