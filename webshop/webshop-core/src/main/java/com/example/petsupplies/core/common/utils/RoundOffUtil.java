package com.example.petsupplies.core.common.utils;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * Status RoundOffUtil is used to round off the value upto nth place.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-23
 */
public class RoundOffUtil

{
   @Inject
   private transient static Logger logger;

   /*
    * Default private constructor so it can not be instantiated from outside as this class is Utility class. 
    */
   private RoundOffUtil()
   {

   }

   public static Double getRoundOfValue(Double value, int upTo)
   {
      DecimalFormat format = new DecimalFormat("#");
      format.setMinimumFractionDigits(upTo);
      try
      {
         return Double.parseDouble(format.format(value));
      }
      catch (Exception e)
      {
         logger.log(Level.INFO, "RoundOffUtil :: getRoundOfValue ends with exception : ", e);
         return Double.parseDouble("0.0");
      }
   }
}
