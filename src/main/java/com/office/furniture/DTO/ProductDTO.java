/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.DTO;

import com.office.furniture.model.Product;

/**
 *
 * @author Silvan
 */
public class ProductDTO {
    private String name;
    private String description;
    private Integer standardPrice;
    
    public static ProductDTO From(Product product) {
        if (product == null)
            return null;
        
        ProductDTO productDto = new ProductDTO();
        productDto.name = product.getName();
        productDto.description = product.getDescription();
        productDto.standardPrice = product.getStandardPrice();
        
        return productDto;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStandardPrice() {
        return standardPrice;
    }
}
