package com.example.petsupplies.core.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.junit.Ignore;

/**
 * CartItemEntity is used to define all attributes related to shopping cart items.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-19
 */

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends AbstractJPAEntity
{

   private Long itemId;

   private OrderEntity orderEntity;

   private Integer quantity;

   private Double totalPrice;

   private Long productId;

   private String productName;

   private Double productPrice;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "item_id")
   public Long getItemId()
   {
      return itemId;
   }

   public void setItemId(Long itemId)
   {
      this.itemId = itemId;
   }

   @ManyToOne(optional = false)
   @JoinColumn(name = "purchase_order_id", referencedColumnName = "order_id", nullable = false)
   public OrderEntity getOrderEntity()
   {
      return orderEntity;
   }

   public void setOrderEntity(OrderEntity orderEntity)
   {
      this.orderEntity = orderEntity;
   }

   @Column(name = "item_quantity")
   public Integer getQuantity()
   {
      return quantity;
   }

   public void setQuantity(Integer quantity)
   {
      this.quantity = quantity;
   }

   @Column(name = "total_price")
   public Double getTotalPrice()
   {
      return totalPrice;
   }

   public void setTotalPrice(Double totalPrice)
   {
      this.totalPrice = totalPrice;
   }

   @Column(name = "product_id")
   public Long getProductId()
   {
      return productId;
   }

   public void setProductId(Long productId)
   {
      this.productId = productId;
   }

   @Transient
   public String getProductName()
   {
      return productName;
   }

   public void setProductName(String productName)
   {
      this.productName = productName;
   }

   @Transient
   public Double getProductPrice()
   {
      return productPrice;
   }

   public void setProductPrice(Double productPrice)
   {
      this.productPrice = productPrice;
   }
   
   public int hashCode()
   {
      return this.productName.hashCode()+ this.productPrice.intValue();
   }
   
   public boolean equals(Object obj)
   {
      if (obj instanceof CartItemEntity)
      {
         CartItemEntity cartItemEntity = (CartItemEntity) obj;
         if (cartItemEntity.getProductName().equals(this.getProductName()))
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
}
