/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.DTO;

import com.office.furniture.model.Customer;

/**
 *
 * @author Silvan
 */
public class CustomerDTO {
    private long id;
    private String username;
    private String password;
    
    public static CustomerDTO From(Customer customer){
        if(customer == null)
            return null;
        
        CustomerDTO c = new CustomerDTO();
        c.id = customer.getId();
        c.username = customer.getUsername();
        c.password = customer.getPassword();
        return c;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
