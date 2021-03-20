/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Silvan
 */
@Provider
public class ControllerSecurity implements ContainerRequestFilter {

    private static final String HEADER_KEY = "Authorization";
    private static final String HEADER_PREFIX = "Basic ";
    private static final String PRIVATE_URL_PREFIX = "customers";
    
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if(crc.getUriInfo().getPath().contains(PRIVATE_URL_PREFIX)) {
            List<String> authHeader = crc.getHeaders().get(HEADER_KEY);
            if(authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0).replaceFirst(HEADER_PREFIX, "");
                byte[] decodedBytes = Base64.getDecoder().decode(authToken);
                String decodedToken = new String(decodedBytes);
                StringTokenizer tokenizer = new StringTokenizer (decodedToken, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                
                if("ikea".equals(username) && "ikea".equals(password))
                    return;
            }
            Response unauthorizedStatus = Response
                                                .status(Response.Status.UNAUTHORIZED)
                                                .entity("Please login!")
                                                .build();
                
            crc.abortWith(unauthorizedStatus);
        }
    }
    
}
