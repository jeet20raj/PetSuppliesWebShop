package com.petsupplies.webshop.user.backingbeans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.service.UserSessionService;
import com.petsupplies.webshop.user.BeanProducer;
import com.petsupplies.webshop.user.constants.UserAppConstants;
import com.petsupplies.webshop.user.model.LoginForm;

/**
 * Login bean captures the user credentials from screen and pass to service layer for authentication
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-17
 */

@Named
@SessionScoped
public class LoginBean implements Serializable
{

   @Inject
   private LoginForm loginForm;

   @Inject
   private UserSessionService userSessionService;

   @Inject
   private BeanProducer beanProducer;

   @Inject
   private transient Logger logger;

   public String login()
   {
      UserEntity loggedInCustomer = null;
      String viewPage = null;
      logger.log(Level.INFO, "LoginBean :: login starts");
      HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      if (null == httpSession || (null != httpSession && null == httpSession.getAttribute(UserAppConstants.USER_LOGGED_IN)))
      {
         httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         loggedInCustomer = userSessionService.login(loginForm.getUserName(), loginForm.getPassword());
         httpSession.setAttribute(UserAppConstants.USER_LOGGED_IN, loggedInCustomer);
      }
      else
      {
         loggedInCustomer = (UserEntity) httpSession.getAttribute(UserAppConstants.USER_LOGGED_IN);
      }
      if (null != loggedInCustomer && !loggedInCustomer.getIsAdmin())
      {
         beanProducer.setLoggedInCustomer(loggedInCustomer);
         viewPage = UserAppConstants.PAGE_VIEW_USER_HOME;
      }
      else
      {
         httpSession.removeAttribute(UserAppConstants.USER_LOGGED_IN);
         String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "message").getString("webshop.errmsg.invalidlogin");
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
      }
      logger.log(Level.INFO, "LoginBean :: login ends");
      return viewPage;
   }

   public String logout()
   {
      logger.log(Level.INFO, "LoginBean :: logout starts");
      HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      if (null != httpSession && null != httpSession.getAttribute(UserAppConstants.USER_LOGGED_IN))
      {
         httpSession.setAttribute(UserAppConstants.USER_LOGGED_IN, null);
      }
      logger.log(Level.INFO, "LoginBean :: logout ends");
      return UserAppConstants.PAGE_VIEW_LOGIN;
   }

   public BeanProducer getBeanProducer()
   {
      return beanProducer;
   }

   public void setBeanProducer(BeanProducer beanProducer)
   {
      this.beanProducer = beanProducer;
   }

}
