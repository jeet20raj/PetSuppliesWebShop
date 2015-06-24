package com.example.petsupplies.core.backend.dao;

import java.util.List;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;

/**
 * ProductDAO exposing the methods to add/edit/delete/display the products.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-11
 */

public interface ProductDAO
{
   public List<ProductEntity> getProducts(ProductSearchFilter searchFilter);

   public List<ProductEntity> getAllProducts();

   public boolean createProduct(ProductEntity productEntity);

   public boolean editProduct(ProductEntity productEntity);

   public boolean deleteProduct(ProductEntity productEntity);

}
