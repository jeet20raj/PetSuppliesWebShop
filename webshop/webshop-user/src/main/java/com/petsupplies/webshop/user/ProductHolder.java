package com.petsupplies.webshop.user;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.petsupplies.webshop.user.model.ProductVO;

/**
 * ProductHolder holds the list of products to be displayed in Data table.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-15
 */

@Named("productsHolder")
@SessionScoped
public class ProductHolder implements Serializable
{

   List<ProductVO> products;

   public ProductHolder()
   {
   }

   public List<ProductVO> getProducts()
   {
      return products;
   }

   public void setProducts(List<ProductVO> products)
   {
      this.products = products;
   }
}
