/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.office.furniture.ejb;

import com.office.furniture.DTO.ProductDTO;
import java.util.List;

/**
 *
 * @author Silvan
 */
public interface ProductBeanInterface {

    public List<ProductDTO> getAll();

    public List<ProductDTO> getAllByCustomer(long customerIds);
}
