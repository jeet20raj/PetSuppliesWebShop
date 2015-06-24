package com.example.petsupplies.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.petsupplies.core.backend.dao.ProductDAO;
import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;
import com.example.petsupplies.core.service.ProductSessionService;
/**
 * ProductSessionServiceImpl is used to add/edit/display the products.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */
@Stateless
public class ProductSessionServiceImpl implements ProductSessionService, Serializable
{

   @Inject
   private ProductDAO productDAO;

   public List<ProductEntity> getProducts(ProductSearchFilter searchFilter)
   {
      // TODO Auto-generated method stub
      return productDAO.getProducts(searchFilter);
   }

   public List<ProductEntity> getAllProducts()
   {
      return productDAO.getAllProducts();
   }

   public boolean createProduct(ProductEntity productEntity)
   {
      return productDAO.createProduct(productEntity);
   }

   public boolean editProduct(ProductEntity productEntity)
   {
      return productDAO.editProduct(productEntity);
   }

   public boolean deleteProduct(ProductEntity productEntity)
   {
      return productDAO.deleteProduct(productEntity);
   }

}
