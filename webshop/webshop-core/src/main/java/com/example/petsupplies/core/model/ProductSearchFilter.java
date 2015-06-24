package com.example.petsupplies.core.model;

/**
 * ProductSearchFilter is having all the search parameters to filter products.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-18
 */
public class ProductSearchFilter
{
   private String categoryName;
   private String productName;
   private String brand;
   private String description;

   public String getCategoryName()
   {
      return categoryName;
   }

   public void setCategoryName(String categoryName)
   {
      this.categoryName = categoryName;
   }

   public String getProductName()
   {
      return productName;
   }

   public void setProductName(String productName)
   {
      this.productName = productName;
   }

   public String getBrand()
   {
      return brand;
   }

   public void setBrand(String brand)
   {
      this.brand = brand;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }
}
