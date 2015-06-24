package com.petsupplies.webshop.user;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.petsupplies.webshop.user.qualifiers.CustomerLoggedIn;
/**
 * BeanProducer produces logged in used to be injected in other class.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */
@SessionScoped
public class BeanProducer implements Serializable
{

   private UserEntity loggedInCustomer;

   public void setLoggedInCustomer(UserEntity loggedInCustomer)
   {
      this.loggedInCustomer = loggedInCustomer;
   }

   @Produces
   @CustomerLoggedIn
   public UserEntity getLoggedInCustomer()
   {
      return loggedInCustomer;
   }
}
