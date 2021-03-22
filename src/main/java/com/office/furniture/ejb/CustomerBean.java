/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.ejb;

import com.office.furniture.DTO.CustomerDTO;
import com.office.furniture.model.Customer;
import java.util.Base64;
import java.util.StringTokenizer;
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
    private static final String HEADER_PREFIX = "Basic ";

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer getDiscountPercent(long customerId) {
        LOG.info("get discount percent");

        Customer customer = getCustomerById(customerId);
        return calculateDiscount(customer);
    }

    @Override
    public boolean isCustomerAuthorized(long customerId, String authString) {
        LOG.info("isCustomerAuthorized");

        return isAuthorized(customerId, authString);
    }

    @Override
    public CustomerDTO login(String username, String password) {
        LOG.info("login");

        Customer customer = getCustomerByUsername(username);
        if (customer != null && password.equals(customer.getPassword())) {
            return CustomerDTO.From(customer);
        }
        return null;
    }

    private boolean isAuthorized(long customerId, String authString) {
        LOG.info("isAuthorized");

        if (authString != null) {
            String authToken = authString.replaceFirst(HEADER_PREFIX, "");
            byte[] decodedBytes = Base64.getDecoder().decode(authToken);
            String decodedToken = new String(decodedBytes);
            StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();

            Customer customer = getCustomerById(customerId);
            if (customer != null) {
                if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
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

    private Customer getCustomerByUsername(String username) {
        LOG.info("getCustomerByUsername");

        try {
            Customer customer = em
                    .createQuery("SELECT c FROM Customer c WHERE c.username = ?1", Customer.class)
                    .setParameter(1, username)
                    .getSingleResult();
            return customer;
        } catch (Exception e) {
            return null;
        }
    }
}
