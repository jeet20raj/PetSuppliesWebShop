package com.petsupplies.webshop.admin.backingbeans;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.common.constants.Constants;
import com.example.petsupplies.core.exceptions.WebshopException;
import com.example.petsupplies.core.service.UserSessionService;
import com.petsupplies.webshop.admin.constants.AdminConstants;

/**
 * User bean is backing bean which captured user details from the Registration page and create new User into the system
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */
@Named
@RequestScoped
public class UserBean
{

   @Named
   @Produces
   @RequestScoped
   private UserEntity userEntity = new UserEntity();

   private UserEntity loggedInUser;

   @Inject
   private UserSessionService userSessionService;

   @Inject
   private transient Logger logger;

   /**
    * Capture the the user details to create new user
    * 
    * @param
    * @return the page view for navigation
    * @see Home page
    */
   public String createUser()
   {
      logger.log(Level.INFO, "UserBean :: createUser starts");
      try
      {
         if (!userEntity.getPassword().equals(userEntity.getConfirmPassword()))
         {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "message").getString("webshop.errmsg.password");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
            return null;
         }
         userEntity.setIsAdmin(true);
         loggedInUser = userSessionService.createUser(userEntity);
         HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         if (null == httpSession)
         {
            httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         }
         httpSession.setAttribute(AdminConstants.USER_LOGGED_IN, loggedInUser);
      }
      catch (WebshopException e)
      {
         if (Constants.USER_ALREADY_EXISTS.equals(e.getErrorCode()))
         {
            logger.log(Level.SEVERE, "UserBean :: Exception Occured User Aleady Exist ", e);
         }
      }
      logger.log(Level.INFO, "UserBean :: createUser ends");
      return AdminConstants.PAGE_VIEW_USER_HOME;
   }
}
