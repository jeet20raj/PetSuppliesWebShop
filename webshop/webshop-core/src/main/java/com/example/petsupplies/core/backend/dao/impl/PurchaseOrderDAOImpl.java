package com.example.petsupplies.core.backend.dao.impl;

import java.util.logging.Level;

import javax.persistence.PersistenceException;

import com.example.petsupplies.core.backend.dao.BaseDAO;
import com.example.petsupplies.core.backend.dao.PurchaseOrderDAO;
import com.example.petsupplies.core.backend.entity.OrderEntity;

/**
 * @author Jeetendra PurchaseOrderDAOImpl is used process order.
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
