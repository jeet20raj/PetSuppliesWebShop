package com.example.petsupplies.core.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * @author Jeetendra UserEntity is used to define all attributes related User.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "webshop_users")
@NamedQueries({ @NamedQuery(name = "findUserByName", query = "select user from UserEntity user where user.userName = :userName"),
      @NamedQuery(name = "findUser", query = "select user from UserEntity user where user.userName = :userName and user.password = :password") })
public class UserEntity extends AbstractJPAEntity
{

   private Long userId;

   private String userName;

   private String firstName;

   private String lastName;

   private String password;

   private List<AddressEntity> addressList;

   private String confirmPassword;

   private Boolean isAdmin;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "user_id", length = 10)
   public Long getUserId()
   {
      return userId;
   }

   public void setUserId(Long userId)
   {
      this.userId = userId;
   }

   @Column(name = "user_name", length = 50, unique = true)
   public String getUserName()
   {
      return userName;
   }

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   @Column(name = "first_name", length = 150)
   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   @Column(name = "last_name", length = 150)
   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   @Column(name = "password", length = 15)
   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   @Column(name = "isAdminUser", length = 1)
   @Type(type = "org.hibernate.type.BooleanType")
   public Boolean getIsAdmin()
   {
      return isAdmin;
   }

   public void setIsAdmin(Boolean isAdmin)
   {
      this.isAdmin = isAdmin;
   }

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
   public List<AddressEntity> getAddressList()
   {
      return addressList;
   }

   public void setAddressList(List<AddressEntity> addressList)
   {
      this.addressList = addressList;
   }

   @Transient
   public String getConfirmPassword()
   {
      return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword)
   {
      this.confirmPassword = confirmPassword;
   }

   @Override
   public int hashCode()
   {
      return this.userName.hashCode();
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof UserEntity)
      {
         UserEntity userEntity = (UserEntity) obj;
         if (userEntity.getUserName().equals(this.getUserName()))
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      return false;
   }
}
