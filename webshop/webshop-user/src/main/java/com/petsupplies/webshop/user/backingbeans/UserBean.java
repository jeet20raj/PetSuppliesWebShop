package com.petsupplies.webshop.user.backingbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.petsupplies.core.backend.entity.AddressEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.common.constants.Constants;
import com.example.petsupplies.core.common.enums.AddressType;
import com.example.petsupplies.core.exceptions.WebshopException;
import com.example.petsupplies.core.service.CategorySessionService;
import com.example.petsupplies.core.service.UserSessionService;
import com.petsupplies.webshop.user.BeanProducer;
import com.petsupplies.webshop.user.constants.UserAppConstants;

/**
 * User bean is backing bean which captured user details from the Registration page and create new User into the system
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */

@Named
@RequestScoped
public class UserBean
{

   @Named
   @Produces
   @RequestScoped
   private UserEntity userEntity = new UserEntity();

   @Named
   @Produces
   @RequestScoped
   private AddressEntity addressEntity = new AddressEntity();

   @Inject
   private UserSessionService userSessionService;

   @Inject
   private CategorySessionService categorySessionService;

   @Inject
   private transient Logger logger;

   @Inject
   private BeanProducer beanProducer;

   /**
    * Capture the the user details and address details for the customer
    * 
    * @param
    * @return the page view for navigation
    * @see Home page
    */
   public String createUser()
   {
      UserEntity loggedInCustomer = null;
      logger.log(Level.INFO, "UserBean :: createUser starts");
      try
      {
         if (!userEntity.getPassword().equals(userEntity.getConfirmPassword()))
         {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "message").getString("webshop.errmsg.password");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
            return null;
         }
         userEntity.setIsAdmin(false);
         List<AddressEntity> addressList = new ArrayList<AddressEntity>();
         addressEntity.setAddressType(AddressType.HOME_ADDRESS);
         addressEntity.setUserEntity(userEntity);
         addressList.add(addressEntity);
         userEntity.setAddressList(addressList);
         loggedInCustomer = userSessionService.createUser(userEntity);
         HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         if (null == httpSession)
         {
            httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         }
         httpSession.setAttribute(UserAppConstants.USER_LOGGED_IN, loggedInCustomer);
      }
      catch (WebshopException e)
      {
         if (Constants.USER_ALREADY_EXISTS.equals(e.getErrorCode()))
         {
            logger.log(Level.SEVERE, "UserBean :: Exception Occured User Aleady Exist ", e);
         }
      }
      logger.log(Level.INFO, "UserBean :: createUser ends");
      beanProducer.setLoggedInCustomer(loggedInCustomer);
      categorySessionService.getCategories();
      return UserAppConstants.PAGE_VIEW_USER_HOME;
   }
}
