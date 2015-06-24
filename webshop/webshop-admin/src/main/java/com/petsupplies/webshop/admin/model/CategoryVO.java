package com.petsupplies.webshop.admin.model;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.model.SelectableDataModel;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.petsupplies.webshop.admin.CategoryHolder;

/**
 * CategoryVO is decorated class having ProductEntity implements SelectableDataModel to be displayed in the data table
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */

public class CategoryVO implements Serializable, SelectableDataModel<CategoryVO>
{

   private CategoryEntity categoryEntity;

   public CategoryVO()
   {
      // TODO Auto-generated constructor stub
   }

   public CategoryVO(CategoryEntity categoryEntity)
   {
      this.categoryEntity = categoryEntity;
   }

   @Inject
   private CategoryHolder categoryHolder;

   public CategoryHolder getCategoryHolder()
   {
      return categoryHolder;
   }

   public void setCategoryHolder(CategoryHolder categoryHolder)
   {
      this.categoryHolder = categoryHolder;
   }

   public CategoryEntity getCategoryEntity()
   {
      return categoryEntity;
   }

   /**
    * Returns the row key used in data table to chose what row was selected
    * 
    * @param
    * @return Row key
    */

   public Object getRowKey(CategoryVO categoryVO)
   {
      return categoryVO.getCategoryEntity().getCategoryName();
   }

   /**
    * Returns the selected row data using row key
    * 
    * @param
    * @return Object slected as row
    */

   public CategoryVO getRowData(String string)
   {
      for (CategoryVO categoryVO : categoryHolder.getCategoryVOs())
      {
         if (categoryVO.getCategoryEntity().getCategoryName().equals(this.getCategoryEntity().getCategoryName()))
         {
            return categoryVO;
         }
      }
      return null;
   }
}
