/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.ejb;

import com.office.furniture.DTO.ProductDTO;
import com.office.furniture.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Silvan
 */
@Default
public class ProductBean implements ProductBeanInterface {

    private static final Logger LOG = Logger.getLogger(Product.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private CustomerBean customerBean;
    
    @Override
    public List<ProductDTO> getAll() {
        LOG.info("get all products");

        try {
            List<Product> products = (List<Product>) em.createQuery("SELECT p FROM Product p").getResultList();
            return createDtosFromProducts(products);
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<ProductDTO> getAllByCustomer(long customerId) {
        LOG.info("get all products with discount of customer");
        
        try {
            List<Product> products = (List<Product>) em.createQuery("SELECT p FROM Product p").getResultList();
            Integer customerDiscountPercent = customerBean.getDiscountPercent(customerId);
            return createDtosFromProducts(products, customerDiscountPercent);
        } catch (Exception ex) {
            return null;
        }
    }

    private List<ProductDTO> createDtosFromProducts(List<Product> products) {
        LOG.info("create DTOS from products");
        
        List<ProductDTO> dtos = new ArrayList<>();

        products.forEach(product -> {
            dtos.add(ProductDTO.From(product));
        });
    	return dtos;
    }
    
    private List<ProductDTO> createDtosFromProducts(List<Product> products, Integer discountPercent) {
        LOG.info("create DTOS from products with discount");
        
        List<ProductDTO> dtos = new ArrayList<>();

        products.forEach(product -> {
            dtos.add(ProductDTO.From(product, discountPercent));
        });
    	return dtos;
    }
}
