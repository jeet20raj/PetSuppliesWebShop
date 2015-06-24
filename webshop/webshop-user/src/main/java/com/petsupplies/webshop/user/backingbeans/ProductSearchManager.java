package com.petsupplies.webshop.user.backingbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;
import com.example.petsupplies.core.service.CategorySessionService;
import com.example.petsupplies.core.service.ProductSessionService;
import com.petsupplies.webshop.user.ProductHolder;
import com.petsupplies.webshop.user.constants.UserAppConstants;
import com.petsupplies.webshop.user.model.ProductVO;

/**
 * ProductSearchManager is used for products search based on different search parameters.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-17
 */

@Named
@RequestScoped
public class ProductSearchManager
{

   @Inject
   private ProductSessionService productSessionService;

   @Inject
   private CategorySessionService categorySessionService;

   @Inject
   private transient Logger logger;

   @Inject
   private ProductHolder productHolder;

   private List<ProductEntity> productsList;

   private ProductEntity selectedProductEntity;

   private List<CategoryEntity> categoriesList;

   private String categoryName;

   private String productName;

   private String productDescription;

   /**
    * Capture the the search parameters from screen and display the list of products.
    * 
    * @param
    * @return the page view for navigation
    * @see Page with list of products
    */
   public String getProducts()
   {
      logger.log(Level.INFO, "ProductSearchManager :: getProducts starts");
      getAllProducts();
      logger.log(Level.INFO, "ProductSearchManager :: getProducts ends");
      return UserAppConstants.PAGE_VIEW_USER_HOME;
   }

   /**
    * This is private method called internally on search and on load.
    */
   private void getAllProducts()
   {
      logger.log(Level.INFO, "ProductSearchManager :: getAllProducts starts");
      ProductSearchFilter productSearchFilter = new ProductSearchFilter();
      prepareSearchFilter(productSearchFilter);
      productsList = productSessionService.getProducts(productSearchFilter);
      List<ProductVO> products = new ArrayList<ProductVO>();
      makeProductVOList(productsList, products);
      productHolder.setProducts(products);
      logger.log(Level.INFO, "ProductSearchManager :: getAllProducts ends");
   }

   /**
    * Load the categories and products on page load. products.
    */
   @PostConstruct
   public void onLoad()
   {
      categoriesList = categorySessionService.getCategories();
      getAllProducts();
   }

   public ProductSessionService getProductSessionService()
   {
      return productSessionService;
   }

   public void setProductSessionService(ProductSessionService productSessionService)
   {
      this.productSessionService = productSessionService;
   }

   public Logger getLogger()
   {
      return logger;
   }

   public void setLogger(Logger logger)
   {
      this.logger = logger;
   }

   public List<ProductEntity> getProductsList()
   {
      return productsList;
   }

   public void setProductsList(List<ProductEntity> productsList)
   {
      this.productsList = productsList;
   }

   public List<CategoryEntity> getCategoriesList()
   {
      return categoriesList;
   }

   public void setCategoriesList(List<CategoryEntity> categoriesList)
   {
      this.categoriesList = categoriesList;
   }

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

   public String getProductDescription()
   {
      return productDescription;
   }

   public void setProductDescription(String productDescription)
   {
      this.productDescription = productDescription;
   }

   public ProductEntity getSelectedProductEntity()
   {
      return selectedProductEntity;
   }

   public void setSelectedProductEntity(ProductEntity selectedProductEntity)
   {
      this.selectedProductEntity = selectedProductEntity;
   }

   private void makeProductVOList(List<ProductEntity> productEntities, List<com.petsupplies.webshop.user.model.ProductVO> productVOs)
   {
      for (ProductEntity productEntity : productEntities)
      {
         ProductVO productVO = new ProductVO(productEntity);
         productVOs.add(productVO);
      }
   }

   private void prepareSearchFilter(ProductSearchFilter productSearchFilter)
   {
      productSearchFilter.setCategoryName("".equals(getCategoryName()) ? null : getCategoryName());
      if(null != getProductName() && !"".equals(getProductName())){
         productSearchFilter.setProductName("%"+getProductName()+"%");
      }else{
         productSearchFilter.setProductName(null);
      }
      if(null != getProductDescription() && !"".equals(getProductDescription())){
         productSearchFilter.setDescription("%"+getProductDescription()+"%");
      }else{
         productSearchFilter.setDescription(null);
      }
   }
}
