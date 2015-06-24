package com.example.petsupplies.core;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * ResourceProducer is used to produces Entity Manager and Logger so that those can be injected by other classes.
 * @author Jeetendra
 */

public class ResourceProducer
{

   /**
    * @return entityManager
    */
   @Produces
   @PersistenceContext(name = "webshopPU")
   private EntityManager entityManager;

   /**
    * @param injectionPoint
    * @return logger
    */
   @Produces
   public Logger produceLogger(InjectionPoint injectionPoint)
   {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
}
