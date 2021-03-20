/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.controller;

import com.office.furniture.ejb.ProductBean;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.*;
import javax.ws.rs.GET;

/**
 *
 * @author Silvan
 */
@Path("/products")
@Produces("application/json")
public class ProductController implements ProductControllerInterface {
    
    @Inject
    private ProductBean productBean;
    
    @GET
    @Override
    public String getAllProducts() {
        String json = new Gson().toJson(productBean.getAll());
        System.out.println(json);
        return json;
    }
}
