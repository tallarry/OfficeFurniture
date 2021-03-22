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
import com.office.furniture.ejb.CustomerBean;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Silvan
 */
@Path("/products")
@Produces("application/json")
public class ProductController implements ProductControllerInterface {

    @Inject
    private ProductBean productBean;

    @Inject
    private CustomerBean customerBean;

    @GET
    @Override
    public Response getAllProducts() {
        String json = new Gson().toJson(productBean.getAll());
        System.out.println(json);
        if (!"null".equals(json)) {
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("null").build();
    }

    @GET
    @Path("/customers/{customerId}")
    @Override
    public Response getProductsForCustomer(@PathParam("customerId") long customerId, @HeaderParam("authorization") String authString) {
        if (!customerBean.isCustomerAuthorized(customerId, authString)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("null").build();
        }
        String json = new Gson().toJson(productBean.getAllByCustomer(customerId));
        System.out.println(json);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
