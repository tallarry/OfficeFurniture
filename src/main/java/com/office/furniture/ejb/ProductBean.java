/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.ejb;

import com.office.furniture.DTO.ProductDTO;
import com.office.furniture.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Silvan
 */
@Stateless
public class ProductBean {

    private static final Logger LOG = Logger.getLogger(Product.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    public List<ProductDTO> getAllProducts() {
        LOG.info("get all products");

        try {
            List<Product> products = (List<Product>) em.createQuery("SELECT p FROM Product p").getResultList();
            return createDtosFromProducts(products);
        } catch (Exception ex) {
            return null;
        }
    }

    private List<ProductDTO> createDtosFromProducts(List<Product> products) {
        List<ProductDTO> dtos = new ArrayList<>();

        products.forEach(product -> {
            dtos.add(ProductDTO.From(product));
        });
    	return dtos;
    }
}
