/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.ejb;

/**
 *
 * @author Silvan
 */
public interface CustomerBeanInterface {

    public Integer getDiscountPercent(long customerId);
    
    public boolean isCustomerAuthorized(long customerId, String authString);
}
