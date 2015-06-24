package com.petsupplies.webshop.admin.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 * LoginForm is having the user details for login.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */
@Named
@RequestScoped
public class LoginForm
{
   private String userName;
   private String password;
   private String message;

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getUserName()
   {
      return userName;
   }

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

}
