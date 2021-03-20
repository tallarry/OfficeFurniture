/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.controller;

/**
 *
 * @author Silvan
 */
public interface ProductControllerInterface {
    
    public String getAllProducts();
    
    public String getProductsForCustomer(long customerId);
}
