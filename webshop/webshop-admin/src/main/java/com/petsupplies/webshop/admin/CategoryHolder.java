package com.petsupplies.webshop.admin;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.petsupplies.webshop.admin.model.CategoryVO;

/**
 * CategoryHolder holds the list of categories to be displayed in Data table.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */

@Named("categoriesHolder")
@SessionScoped
public class CategoryHolder implements Serializable
{

   List<CategoryVO> categoryVOs;

   public CategoryHolder()
   {
   }

   public List<CategoryVO> getCategoryVOs()
   {
      return categoryVOs;
   }

   public void setCategoryVOs(List<CategoryVO> categoryVOs)
   {
      this.categoryVOs = categoryVOs;
   }
}
