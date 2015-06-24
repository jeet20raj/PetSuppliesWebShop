package com.example.petsupplies.core.backend.dao.impl;

import java.util.logging.Level;

import javax.persistence.PersistenceException;

import com.example.petsupplies.core.backend.dao.BaseDAO;
import com.example.petsupplies.core.backend.dao.PurchaseOrderDAO;
import com.example.petsupplies.core.backend.entity.OrderEntity;

/**
 * PurchaseOrderDAOImpl is used process order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

public class PurchaseOrderDAOImpl extends BaseDAO implements PurchaseOrderDAO
{

   /**
    * @param OrderEntity
    * @return boolean This method is used to save order in Database.
    */
   public boolean processOrder(OrderEntity orderEntity)
   {
      logger.log(Level.INFO, "PurchaseOrderDAOImpl :: processOrder method called");
      try
      {
         entityManager.persist(orderEntity);
         return true;
      }
      catch (PersistenceException e)
      {
         logger.log(Level.INFO, "PurchaseOrderDAOImpl :: processOrder method ends with exception", e);
         return false;
      }

   }

}
