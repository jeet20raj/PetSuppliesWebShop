package com.petsupplies.webshop.user.model;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.model.SelectableDataModel;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.petsupplies.webshop.user.ProductHolder;

/**
 * ProductVO is decorated class having ProductEntity implements SelectableDataModel to be displayed in the data table
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */

public class ProductVO implements Serializable, SelectableDataModel<ProductVO>
{

   private ProductEntity productEntity;

   public ProductVO()
   {
      // TODO Auto-generated constructor stub
   }

   public ProductVO(ProductEntity productEntity)
   {
      this.productEntity = productEntity;
   }

   @Inject
   private ProductHolder productHolder;

   public ProductEntity getProductEntity()
   {
      return productEntity;
   }

   public ProductHolder getProductHolder()
   {
      return productHolder;
   }

   public void setProductHolder(ProductHolder productHolder)
   {
      this.productHolder = productHolder;
   }

   /**
    * Returns the row key used in data table to chose what row was selected
    * 
    * @param
    * @return Row key
    */
   public Object getRowKey(ProductVO productVO)
   {
      return productVO.getProductEntity().getProductName();
   }

   /**
    * Returns the selected row data using row key
    * 
    * @param
    * @return Object slected as row
    */
   public ProductVO getRowData(String string)
   {
      for (ProductVO productVO : productHolder.getProducts())
      {
         if (productVO.getProductEntity().getProductName().equals(this.getProductEntity().getProductName()))
         {
            return productVO;
         }
      }
      return null;
   }
}
