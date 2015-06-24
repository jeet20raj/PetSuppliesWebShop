package com.example.petsupplies.core.exceptions;

/**
 * WebshopException is wrapping the system exception to application specific exception.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */

@SuppressWarnings("serial")
public class WebshopException extends RuntimeException
{

   private String errorCode;

   public WebshopException()
   {
      // Default Construtor
   }

   public WebshopException(String errorCode, String errorMessage)
   {
      super(errorMessage);
      this.errorCode = errorCode;
   }

   public String getErrorCode()
   {
      return errorCode;
   }

   public void setErrorCode(String errorCode)
   {
      this.errorCode = errorCode;
   }

   public String getErrorMessage()
   {
      return getErrorMessage();
   }

   public void setErrorMessage(String errorMessage)
   {
      setErrorMessage(errorMessage);
   }

}
