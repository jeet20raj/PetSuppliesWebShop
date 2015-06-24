package com.petsupplies.webshop.admin.backingbeans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.service.UserSessionService;
import com.petsupplies.webshop.admin.constants.AdminConstants;
import com.petsupplies.webshop.admin.model.LoginForm;
import com.petsupplies.webshop.admin.qualifiers.UserLoggedIn;

/**
 * Login bean is backing bean to get data from page and use data for user login
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-14
 */
@Named
@SessionScoped
public class LoginBean implements Serializable
{

   @Inject
   private LoginForm loginForm;

   @Inject
   private UserSessionService userSessionService;

   private UserEntity loggedInUser;

   @Inject
   private transient Logger logger;

   /**
    * Capture the user name and password from Screen and pass user name and pass word for authentication
    * 
    * @param
    * @return the page view for navigation
    * @see Home page
    */
   public String login()
   {
      String viewPage = null;
      logger.log(Level.INFO, "LoginBean :: login starts");
      HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      if (null == httpSession || (null != httpSession && null == httpSession.getAttribute("userLogedIn")))
      {
         httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         loggedInUser = userSessionService.login(loginForm.getUserName(), loginForm.getPassword());
         httpSession.setAttribute(AdminConstants.USER_LOGGED_IN, loggedInUser);

      }
      else
      {
         loggedInUser = (UserEntity) httpSession.getAttribute(AdminConstants.USER_LOGGED_IN);
      }
      if (null != loggedInUser && loggedInUser.getIsAdmin())
      {
         viewPage = AdminConstants.PAGE_VIEW_USER_HOME;
      }
      else
      {
         String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "message").getString("webshop.errmsg.invalidlogin");
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
      }
      logger.log(Level.INFO, "LoginBean :: login ends");
      return viewPage;
   }

   /**
    * Logout the logged in user
    *
    * @param
    * @return the page view for navigation
    * @see Login page
    */
   public String logout()
   {
      logger.log(Level.INFO, "LoginBean :: logout starts");
      HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      if (null != httpSession && null != httpSession.getAttribute("userLogedIn"))
      {
         httpSession.setAttribute(AdminConstants.USER_LOGGED_IN, null);
      }
      logger.log(Level.INFO, "LoginBean :: logout ends");
      return AdminConstants.PAGE_VIEW_LOGIN;
   }

   /**
    * Used to produce the logged in user to be injected into other bean
    * 
    * @return User logged into the system
    */
   @Produces
   @UserLoggedIn
   UserEntity getLoggedInUser()
   {
      return loggedInUser;
   }
}
