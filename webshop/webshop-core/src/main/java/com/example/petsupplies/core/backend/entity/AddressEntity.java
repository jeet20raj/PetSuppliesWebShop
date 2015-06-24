package com.example.petsupplies.core.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.petsupplies.core.common.enums.AddressType;

/**
 * AddressEntity is used to define all address related attributes.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-09
 */

@Entity
@Table(name = "user_address")
public class AddressEntity extends AbstractJPAEntity
{

   private Long addressId;

   private UserEntity userEntity;

   private String addressLine1;

   private String addressLine2;

   private String city;

   private String state;

   private String country;

   private String zipCode;

   private AddressType addressType;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "address_id", length = 10)
   public Long getAddressId()
   {
      return addressId;
   }

   public void setAddressId(Long addressId)
   {
      this.addressId = addressId;
   }

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
   public UserEntity getUserEntity()
   {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity)
   {
      this.userEntity = userEntity;
   }

   @Column(name = "address_line1", length = 100, nullable = false)
   public String getAddressLine1()
   {
      return addressLine1;
   }

   public void setAddressLine1(String addressLine1)
   {
      this.addressLine1 = addressLine1;
   }

   @Column(name = "address_line2", length = 100)
   public String getAddressLine2()
   {
      return addressLine2;
   }

   public void setAddressLine2(String addressLine2)
   {
      this.addressLine2 = addressLine2;
   }

   @Column(name = "city", length = 30, nullable = false)
   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   @Column(name = "state", length = 30, nullable = false)
   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   @Column(name = "country", length = 100, nullable = false)
   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   @Column(name = "zipcode", length = 50, nullable = false)
   public String getZipCode()
   {
      return zipCode;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   @Column(name = "address_type", length = 20)
   @Enumerated(EnumType.STRING)
   public AddressType getAddressType()
   {
      return addressType;
   }

   public void setAddressType(AddressType addressType)
   {
      this.addressType = addressType;
   }
}
