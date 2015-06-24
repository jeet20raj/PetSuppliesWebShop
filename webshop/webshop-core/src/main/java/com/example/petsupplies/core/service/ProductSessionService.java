package com.example.petsupplies.core.service;

import java.util.List;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;

/**
 * ProductSessionService is used expose methods to search/add/edit/delete products.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */

public interface ProductSessionService
{

   public List<ProductEntity> getProducts(ProductSearchFilter searchFilter);

   public List<ProductEntity> getAllProducts();

   public boolean createProduct(ProductEntity productEntity);

   public boolean editProduct(ProductEntity productEntity);

   public boolean deleteProduct(ProductEntity productEntity);
}
