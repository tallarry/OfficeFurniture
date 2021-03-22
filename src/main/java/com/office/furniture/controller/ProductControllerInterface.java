/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.controller;

import javax.ws.rs.core.Response;

/**
 *
 * @author Silvan
 */
public interface ProductControllerInterface {

    public Response getAllProducts();

    public Response getProductsForCustomer(long customerId, String authString);
}
