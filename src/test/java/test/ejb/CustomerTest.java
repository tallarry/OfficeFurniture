/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ejb;

import com.office.furniture.ejb.CustomerBean;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author Silvan
 */
public class CustomerTest {
    
    private CustomerBean customerBean;
    
    public CustomerTest() {
        customerBean = new CustomerBean();
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    @DisplayName("Authorization Test")
    public void authorizationTest() {
        assertEquals(false, customerBean.isCustomerAuthorized(2,"Basic aWtlYTppa2Vh"),      
        "authorization should work");
    }
}
