package com.example.petsupplies.core.backend.dao;

import java.util.List;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;

/**
 * @author Jeetendra ProductDAO exposing the methods to add/edit/delete/display the products.
 */

public interface ProductDAO
{
   public List<ProductEntity> getProducts(ProductSearchFilter searchFilter);

   public List<ProductEntity> getAllProducts();

   public boolean createProduct(ProductEntity productEntity);

   public boolean editProduct(ProductEntity productEntity);

   public boolean deleteProduct(ProductEntity productEntity);

}
