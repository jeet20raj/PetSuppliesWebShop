package com.petsupplies.webshop.user;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.petsupplies.webshop.user.qualifiers.CustomerLoggedIn;

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
