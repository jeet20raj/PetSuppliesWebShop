package com.petsupplies.webshop.admin.backingbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.exceptions.WebshopException;
import com.example.petsupplies.core.service.CategorySessionService;
import com.petsupplies.webshop.admin.CategoryHolder;
import com.petsupplies.webshop.admin.constants.AdminConstants;
import com.petsupplies.webshop.admin.model.CategoryVO;

/**
 * Category bean is backing bean which is used to display/add/soft delete categories
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */

@Named
@RequestScoped
public class CategoryBean
{

   @Named
   @RequestScoped
   @Produces
   private CategoryEntity categoryEntity = new CategoryEntity();

   @Inject
   private CategorySessionService categorySessionService;

   @Inject
   private CategoryHolder categoryHolder;

   private CategoryVO selectedCategoryVO;

   @Inject
   private transient Logger logger;

   /**
    * Capture category details from category screen and create new category.
    * 
    * @param
    * @return the page view for navigation.
    * @see home page
    */
   public String createCategory()
   {
      logger.log(Level.INFO, "CategoryBean :: createCategory method called");
      try
      {
         categorySessionService.createCategory(categoryEntity);
      }
      catch (WebshopException e)
      {
         logger.log(Level.SEVERE, "Error occured to save category :: ", e);
      }
      return AdminConstants.PAGE_VIEW_USER_HOME;
   }

   /**
    * Return list of categories
    * 
    * @param
    * @return the page view for navigation.
    * @see page with list of categories.
    */
   public String getAllCategories()
   {
      List<CategoryEntity> categoryEntities = categorySessionService.getCategories();
      List<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
      makeProductVOList(categoryEntities, categoryVOs);
      categoryHolder.setCategoryVOs(categoryVOs);
      return AdminConstants.PAGE_VIEW_SHOW_CATEGORIES;
   }

   /**
    * Delete category from DB
    * 
    * @param
    * @return the page view for navigation.
    * @see page with list of categories.
    */
   public String deleteCategory()
   {
      logger.log(Level.INFO, "CategoryBean :: deleteCategory method starts");
      if (null != getSelectedCategoryVO())
      {
         getSelectedCategoryVO().getCategoryEntity().setObsolete(1);
         boolean isDeleted = categorySessionService.deleteCategory(getSelectedCategoryVO().getCategoryEntity());
         if (isDeleted)
         {
            List<CategoryEntity> categoryEntities = categorySessionService.getCategories();
            List<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
            makeProductVOList(categoryEntities, categoryVOs);
            categoryHolder.setCategoryVOs(categoryVOs);
            return "showCategories";
         }
         String errorMessage = "Record not deleted";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
      }
      else
      {
         String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "message").getString("webshop.errmsg.selectfromgrid");
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
      }
      logger.log(Level.INFO, "CategoryBean :: deleteCategory method ends");
      return null;
   }

   /**
    * Prepares the decorated list of Products VO to display the Data table
    * 
    * @param
    * @return
    */
   private void makeProductVOList(List<CategoryEntity> categoryEntities, List<CategoryVO> categoryVOs)
   {
      for (CategoryEntity categoryEntity : categoryEntities)
      {
         CategoryVO productVO = new CategoryVO(categoryEntity);
         categoryVOs.add(productVO);
      }
   }

   public CategoryVO getSelectedCategoryVO()
   {
      return selectedCategoryVO;
   }

   public void setSelectedCategoryVO(CategoryVO selectedCategoryVO)
   {
      this.selectedCategoryVO = selectedCategoryVO;
   }

}
