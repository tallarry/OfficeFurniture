/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.ejb;

import com.office.furniture.model.Customer;
import java.util.logging.Logger;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Silvan
 */
@Default
public class CustomerBean implements CustomerBeanInterface {

    private static final Logger LOG = Logger.getLogger(Customer.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Integer getDiscountPercent(long customerId) {
        LOG.info("get discount percent");
        
        Customer customer = getCustomerById(customerId);
        return calculateDiscount(customer);        
    }

    private Customer getCustomerById(long customerId) {
        LOG.info("get customer by id");
        
        try {
            Customer customer = em.find(Customer.class, customerId);
            return customer;
        } catch (Exception ex) {
            return null;
        }
    }

    private Integer calculateDiscount(Customer customer) {
        LOG.info("calculate discount");
        
        System.out.println("customer: " + customer.getUsername() + " discount size: " + customer.getDiscounts().size());
        Integer discount = 0;
        discount = customer
                .getDiscounts()
                .stream()
                .map(d -> d.getAmount())
                .reduce(discount, Integer::sum);
        return discount;
    }
}
