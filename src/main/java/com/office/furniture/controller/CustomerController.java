/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.controller;

import com.google.gson.Gson;
import com.office.furniture.DTO.CustomerDTO;
import com.office.furniture.ejb.CustomerBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Silvan
 */
@Path("/customers")
@Produces("application/json")
public class CustomerController implements CustomerControllerInterface {
    
    @Inject
    private CustomerBean customerBean;
    
    @POST
    @Path("/login")
    @Consumes("application/x-www-form-urlencoded")
    @Override
    public Response authenticateUser(@FormParam("username") String username,
            @FormParam("password") String password){
        CustomerDTO customerDto = customerBean.login(username, password);
        if(customerDto != null) {
            String json = new Gson().toJson(customerDto);
            System.out.println(json);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("null").build();
    }
    
}
