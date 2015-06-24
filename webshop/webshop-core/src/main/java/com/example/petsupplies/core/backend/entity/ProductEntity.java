package com.example.petsupplies.core.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.petsupplies.core.common.constants.Constants;


/**
 * ProductEntity is used to define all attributes related product.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-09
 */

@Entity
@Table(name = "products")
@NamedQueries({
      @NamedQuery(name = Constants.QueryConstants.SHOW_ALL_PRODUCTS, query = "select prd from ProductEntity prd"),
      @NamedQuery(name = Constants.QueryConstants.SEARCH_PRODUCTS, query = "select prd from ProductEntity prd where (:categoryName is null or prd.categoryEntity.categoryName = :categoryName) and (:productName is null or prd.productName like :productName) and (:description is null or prd.description like :description) order by prd.productName") })
public class ProductEntity extends AbstractJPAEntity
{

   private Long productId;

   private String productName;

   private String description;

   private String brand;

   private Double price;

   private String imageUrl;

   private Double discount;

   private Date discountStartDate;

   private Date discountEndDate;

   private CategoryEntity categoryEntity;

   @Transient
   private Double discountedPrice;

   public Double getDiscountedPrice()
   {
      return discountedPrice;
   }

   public void setDiscountedPrice(Double discountedPrice)
   {
      this.discountedPrice = discountedPrice;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "product_id", length = 10)
   public Long getProductId()
   {
      return productId;
   }

   public void setProductId(Long productId)
   {
      this.productId = productId;
   }

   @Column(name = "description", length = 150)
   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   @Column(name = "product_name", length = 50, unique = true)
   public String getProductName()
   {
      return productName;
   }

   public void setProductName(String productName)
   {
      this.productName = productName;
   }

   @Column(name = "brand", length = 20)
   public String getBrand()
   {
      return brand;
   }

   public void setBrand(String brand)
   {
      this.brand = brand;
   }

   @Column(name = "price")
   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }

   @Column(name = "image_url", length = 200)
   public String getImageUrl()
   {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl)
   {
      this.imageUrl = imageUrl;
   }

   @Column(name = "discount")
   public Double getDiscount()
   {
      return discount;
   }

   public void setDiscount(Double discount)
   {
      this.discount = discount;
   }

   @Column(name = "discount_start_date")
   public Date getDiscountStartDate()
   {
      return discountStartDate;
   }

   public void setDiscountStartDate(Date discountStartDate)
   {
      this.discountStartDate = discountStartDate;
   }

   @Column(name = "discount_end_date")
   public Date getDiscountEndDate()
   {
      return discountEndDate;
   }

   public void setDiscountEndDate(Date discountEndDate)
   {
      this.discountEndDate = discountEndDate;
   }

   @ManyToOne(optional = false)
   @JoinColumn(name = "category_id", nullable = false, updatable = false)
   public CategoryEntity getCategoryEntity()
   {
      return categoryEntity;
   }

   public void setCategoryEntity(CategoryEntity categoryEntity)
   {
      this.categoryEntity = categoryEntity;
   }

   @Override
   public int hashCode()
   {
      return this.productName.hashCode();
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof ProductEntity)
      {
         ProductEntity product = (ProductEntity) obj;
         if (product.getProductName().equals(this.getProductName()))
         {
            return true;
         }
      }
      else
      {
         return false;
      }
      return false;
   }

   /**
    * This method is used to caluclate the discounted price after load and persist and update;
    */
   @PostLoad
   @PostPersist
   @PostUpdate
   public void calculateDiscountedPrice()
   {
      if (null != discount && discount.doubleValue() != 0)
      {
         double discountGiven = (price * discount) / 100;
         discountedPrice = price - discountGiven;
      }
   }
}
