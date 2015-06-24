package com.example.petsupplies.core.common.constants;

/**
 * Constants is used to define all constants to be used across the application.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */

public interface Constants
{
   public interface QueryConstants
   {
      String FIND_USER = "findUser";
      String FIND_USER_BY_NAME = "findUserByName";
      String FIND_CATEGORIES = "findCategories";
      String SHOW_ALL_PRODUCTS = "showAllProducts";
      String SEARCH_PRODUCTS = "searchProducts";
   }

   String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
   String PRODUCT_IMAGES_DIRECTOTY = "/images/product";
}
