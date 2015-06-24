package com.example.petsupplies.core.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.petsupplies.core.common.enums.Status;

/**
 * OrderEntity is used to define all attributes related Purchase Order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

@Entity
@Table(name = "pruchase_orders")
public class OrderEntity extends AbstractJPAEntity
{

   private Long orderId;

   private Long userId;

   private Long addressId;

   private Status status;

   private List<CartItemEntity> cartItems;

   @Id
   @GeneratedValue
   @Column(name = "order_id")
   public Long getOrderId()
   {
      return orderId;
   }

   public void setOrderId(Long orderId)
   {
      this.orderId = orderId;
   }

   @Column(name = "user_id")
   public Long getUserId()
   {
      return userId;
   }

   public void setUserId(Long userId)
   {
      this.userId = userId;
   }

   @Column(name = "address_id")
   public Long getAddressId()
   {
      return addressId;
   }

   public void setAddressId(Long addressId)
   {
      this.addressId = addressId;
   }

   @Column(name = "order_status", length = 20)
   @Enumerated(EnumType.STRING)
   public Status getStatus()
   {
      return status;
   }

   public void setStatus(Status status)
   {
      this.status = status;
   }

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity", cascade = CascadeType.ALL)
   public List<CartItemEntity> getCartItems()
   {
      return cartItems;
   }

   public void setCartItems(List<CartItemEntity> cartItems)
   {
      this.cartItems = cartItems;
   }

}
